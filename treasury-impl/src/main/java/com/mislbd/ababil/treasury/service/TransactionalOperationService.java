package com.mislbd.ababil.treasury.service;

import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.transaction.domain.TransactionAmountType;
import com.mislbd.ababil.transaction.domain.TransactionDefinitionJournalType;
import com.mislbd.ababil.transaction.domain.TransactionDefinitionModule;
import com.mislbd.ababil.transaction.domain.TransactionRequestType;
import com.mislbd.ababil.transaction.service.TransactionDefinitionService;
import com.mislbd.ababil.transaction.service.TransactionService;
import com.mislbd.ababil.treasury.domain.*;
import com.mislbd.ababil.treasury.exception.*;
import com.mislbd.ababil.treasury.external.service.GlAccountService;
import com.mislbd.ababil.treasury.mapper.AccountMapper;
import com.mislbd.ababil.treasury.mapper.TransactionalOperationMapper;
import com.mislbd.ababil.treasury.repository.jpa.AccountProcessRepository;
import com.mislbd.ababil.treasury.repository.jpa.AccountRepository;
import com.mislbd.ababil.treasury.repository.jpa.ProductRelatedGLRepository;
import com.mislbd.ababil.treasury.repository.jpa.TransactionRecordRepository;
import com.mislbd.ababil.treasury.repository.schema.AccountEntity;
import com.mislbd.ababil.treasury.repository.schema.AccountProcessEntity;
import com.mislbd.ababil.treasury.repository.schema.TransactionRecordEntity;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.stereotype.Service;

@SuppressWarnings("Duplicates")
@Service
public class TransactionalOperationService {

  private static final Long PLACEMENT_ACTIVITY = Long.valueOf(801);
  private static final Long SETTLEMENT_OR_RENEW_ACTIVITY = Long.valueOf(802);
  private static final Long REACTIVE_ACTIVITY = Long.valueOf(803);
  private static final String SYSTEM_EXCHANGE_RATE_TYPE = "SYSTEM_EXCHANGE_RATE_TYPE";

  private final TransactionService transactionService;
  private final ConfigurationService configurationService;
  private final TransactionalOperationMapper mapper;
  private final ProductRelatedGLRepository productRelatedGLRepository;
  private final AccountRepository accountRepository;
  private final AccountMapper accountMapper;
  private final UtilityService utilityService;
  private final GlAccountService glAccountService;
  private final AccountProcessRepository processRepository;
  private final TransactionRecordRepository transactionRecordRepository;
  private final TransactionDefinitionService transactionDefinitionService;

  public TransactionalOperationService(
      TransactionService transactionService,
      ConfigurationService configurationService,
      TransactionalOperationMapper mapper,
      ProductRelatedGLRepository productRelatedGLRepository,
      AccountRepository accountRepository,
      AccountMapper accountMapper,
      UtilityService utilityService,
      GlAccountService glAccountService,
      AccountProcessRepository processRepository,
      TransactionRecordRepository transactionRecordRepository,
      TransactionDefinitionService transactionDefinitionService) {
    this.transactionService = transactionService;
    this.configurationService = configurationService;
    this.mapper = mapper;
    this.productRelatedGLRepository = productRelatedGLRepository;
    this.accountRepository = accountRepository;
    this.accountMapper = accountMapper;
    this.utilityService = utilityService;
    this.glAccountService = glAccountService;
    this.processRepository = processRepository;
    this.transactionRecordRepository = transactionRecordRepository;
    this.transactionDefinitionService = transactionDefinitionService;
  }

  /*
  ###### Placement Transaction #######
  *   get audit information
  *   debit treasury account
  *   credit settlement account
  * */

  public Long dolPlacementTransaction(AuditInformation auditInformation, Account account) {

    AccountEntity entity =
        accountRepository.saveAndFlush(
            accountMapper
                .domainToEntity(account.getAmount(), account.getAmount(), null, null, null)
                .map(account));

    TransactionalInformation txnInformation =
        getTransactionInformation(auditInformation, PLACEMENT_ACTIVITY, null);

    transactionService.doTreasuryTransaction(
        mapper.getPayableAccount(
            txnInformation,
            configurationService.getBaseCurrencyCode(),
            auditInformation,
            true,
            entity.getAccountNumber(),
            entity.getAmount(),
            "PRINCIPAL"),
        TransactionRequestType.TRANSFER,
        TransactionAmountType.PRINCIPAL);

    String settlementGlCode = getRelatedGlCode(entity.getProduct().getId(), GLType.SETTLEMENT_GL);

    transactionService.doGlTransaction(
        mapper.getPayableGL(
            txnInformation,
            configurationService.getBaseCurrencyCode(),
            auditInformation,
            false,
            entity.getAmount(),
            settlementGlCode,
            entity.getOpenDate(),
            "PRINCIPAL"),
        TransactionRequestType.TRANSFER);

    return txnInformation.getGlobalTxnNumber();
  }

  private TransactionalInformation getTransactionInformation(
      AuditInformation auditInformation, Long activityId, Long globalTxnNumber) {

    return TransactionalInformation.builder()
        .batchNumber(
            transactionService.getBatchNumber(
                auditInformation.getEntryUser(),
                activityId,
                auditInformation.getUserBranch().longValue()))
        .globalTxnNumber(
            globalTxnNumber != null
                ? globalTxnNumber
                : transactionService.getGlobalTransactionNumber(
                    auditInformation.getEntryUser(), activityId))
        .exchangeRate(
            transactionService.getSystemExchangeRate(configurationService.getBaseCurrencyCode()))
        .exchangeRateType(
            Long.valueOf(
                configurationService.getConfiguration(SYSTEM_EXCHANGE_RATE_TYPE).get().getValue()))
        .activityId(activityId)
        .build();
  }

  public Long doSettlementOrRenewTransaction(AuditInformation auditInformation, Account account) {

    /*
    ###### Common Transaction #######
    * Treasury account debit with actual_profit
    * Profit_Receivable credit with provisional_profit
    * if actual_profit > provisional_profit
    *   then income gl credit (amount = actual_profit - provisional_profit)
    * if provisional_profit > actual_profit
    *   then debit income gl  (amount = provisional_profit - actual_profit)

    ##### Renewal with profit #########
    * Treasury account credit
    * Settlement gl debit
    * Update treasury account set new renewal date, profit rate, expiry date and status

    ##### Settlement Transaction ###########
    * calculate closing balance and principal
    * Treasury account credit
    * Settlement gl debit
    * Update treasury account set status closed
    * */

    AccountEntity entity =
        accountRepository.findById(account.getId()).orElseThrow(AccountNotFoundException::new);

    TransactionalInformation txnInformation =
        getTransactionInformation(auditInformation, SETTLEMENT_OR_RENEW_ACTIVITY, null);

    String profitReceivableGl =
        getRelatedGlCode(entity.getProduct().getId(), GLType.PROFIT_RECEIVABLE_GL);
    String incomeGl = getRelatedGlCode(entity.getProduct().getId(), GLType.INCOME_GL);
    String settlementGl = getRelatedGlCode(entity.getProduct().getId(), GLType.SETTLEMENT_GL);

    if (account.getActualProfit().signum() == 1) {
      transactionService.doTreasuryTransaction(
          mapper.getPayableAccount(
              txnInformation,
              configurationService.getBaseCurrencyCode(),
              auditInformation,
              true,
              entity.getAccountNumber(),
              account.getActualProfit(),
              "ACTUAL PROFIT"),
          TransactionRequestType.TRANSFER,
          TransactionAmountType.PROFIT);
    }

    if (account.getProvisionAmount().signum() == 1) {
      transactionService.doGlTransaction(
          mapper.getPayableGL(
              txnInformation,
              configurationService.getBaseCurrencyCode(),
              auditInformation,
              false,
              account.getProfitAmount(),
              profitReceivableGl,
              account.getValueDate(),
              "PROVISIONAL PROFIT"),
          TransactionRequestType.TRANSFER);
    }

    if (account.getProvisionAmount() != null && account.getActualProfit() != null) {

      if (account.getProvisionAmount().compareTo(account.getActualProfit()) == 1) {
        BigDecimal overBalance = account.getProvisionAmount().subtract(account.getActualProfit());
        transactionService.doGlTransaction(
            mapper.getPayableGL(
                txnInformation,
                configurationService.getBaseCurrencyCode(),
                auditInformation,
                true,
                overBalance,
                incomeGl,
                account.getValueDate(),
                "OVER PROFIT REVERSED"),
            TransactionRequestType.TRANSFER);
      }

      if (account.getActualProfit().compareTo(account.getProvisionAmount()) == 1) {
        BigDecimal lowerBalance = account.getActualProfit().subtract(account.getProvisionAmount());
        transactionService.doGlTransaction(
            mapper.getPayableGL(
                txnInformation,
                configurationService.getBaseCurrencyCode(),
                auditInformation,
                false,
                lowerBalance,
                incomeGl,
                account.getValueDate(),
                "DOWN PROFIT REVERSED"),
            TransactionRequestType.TRANSFER);
      }
    }

    if (account.getEvent() == TransactionEvent.Renew) {
      if (!account.isRenewWithProfit()
          && account.getActualProfit().compareTo(BigDecimal.ZERO) == 1) {
        transactionService.doTreasuryTransaction(
            mapper.getPayableAccount(
                txnInformation,
                configurationService.getBaseCurrencyCode(),
                auditInformation,
                false,
                entity.getAccountNumber(),
                account.getActualProfit(),
                "ACTUAL PROFIT"),
            TransactionRequestType.TRANSFER,
            TransactionAmountType.PROFIT);
        transactionService.doGlTransaction(
            mapper.getPayableGL(
                txnInformation,
                configurationService.getBaseCurrencyCode(),
                auditInformation,
                true,
                account.getActualProfit(),
                settlementGl,
                account.getValueDate(),
                "ACTUAL PROFIT"),
            TransactionRequestType.TRANSFER);
        accountRepository.save(
            accountMapper
                .renewalDomainToEntity(
                    entity.getBalance(),
                    entity.getPrincipalDebit(),
                    entity.getPrincipalCredit(),
                    account.getActualProfit(),
                    account.getActualProfit())
                .map(account));
      } else {
        accountRepository.save(
            accountMapper
                .renewalDomainToEntity(
                    entity.getBalance().add(account.getActualProfit()),
                    entity.getPrincipalDebit().add(account.getActualProfit()),
                    entity.getPrincipalCredit(),
                    entity.getProfitDebit().add(account.getActualProfit()),
                    entity.getProfitCredit().add(account.getActualProfit()))
                .map(account));
      }
    }

    if (account.getEvent() == TransactionEvent.Settlement) {
      BigDecimal closingProfit =
          entity.getProfitDebit().subtract(entity.getProfitCredit()).add(account.getActualProfit());
      BigDecimal closingPrincipal =
          entity.getPrincipalDebit().subtract(entity.getPrincipalCredit());
      if (closingProfit.signum() == 1) {
        transactionService.doTreasuryTransaction(
            mapper.getPayableAccount(
                txnInformation,
                configurationService.getBaseCurrencyCode(),
                auditInformation,
                false,
                entity.getAccountNumber(),
                closingProfit,
                "SETTLEMENT PROFIT"),
            TransactionRequestType.TRANSFER,
            TransactionAmountType.PROFIT);
      }

      if (closingPrincipal.signum() == 1) {
        transactionService.doTreasuryTransaction(
            mapper.getPayableAccount(
                txnInformation,
                configurationService.getBaseCurrencyCode(),
                auditInformation,
                false,
                entity.getAccountNumber(),
                closingPrincipal,
                "SETTLEMENT PRINCIPAL"),
            TransactionRequestType.TRANSFER,
            TransactionAmountType.PRINCIPAL);
      }

      BigDecimal closingTotal = closingPrincipal.add(closingProfit);

      if (closingTotal.signum() == 1) {
        transactionService.doGlTransaction(
            mapper.getPayableGL(
                txnInformation,
                configurationService.getBaseCurrencyCode(),
                auditInformation,
                true,
                closingTotal,
                settlementGl,
                account.getValueDate(),
                "PRINCIPAL"),
            TransactionRequestType.TRANSFER);
      }

      BigDecimal balance =
          entity.getBalance().add(account.getActualProfit()).subtract(closingTotal);
      accountRepository.save(
          accountMapper
              .closeDomainToEntity(
                  balance,
                  entity.getPrincipalDebit(),
                  closingPrincipal,
                  entity.getProfitDebit().add(account.getActualProfit()),
                  closingProfit)
              .map(account));
    }

    utilityService.updateMonthendInfo(
        entity.getAccountNumber(),
        account.getEvent().name(),
        true,
        txnInformation.getGlobalTxnNumber());

    return txnInformation.getGlobalTxnNumber();
  }

  String getRelatedGlCode(long productId, GLType glType) {
    return glAccountService
        .getGlAccount(
            productRelatedGLRepository
                .findByProductIdAndGlType(productId, glType)
                .orElseThrow(ProductRelatedGLNotFoundException::new)
                .getGlId())
        .getCode();
  }

  public Long doReactiveTransaction(AuditInformation auditInformation, Account account) {

    AccountEntity entity =
        accountRepository.findById(account.getId()).orElseThrow(AccountNotFoundException::new);

    AccountProcessEntity processEntity =
        processRepository
            .findByAccountNumberAndNewStatusAndValid(
                entity.getAccountNumber(), entity.getStatus(), true)
            .orElseThrow(ProcessRecordNotFoundException::new);

    TransactionalInformation txnInformation =
        getTransactionInformation(
            auditInformation, REACTIVE_ACTIVITY, processEntity.getGlobalTxnNumber());

    if (!entity.getClosingDate().isEqual(account.getValueDate())) {
      throw new ReactiveTransactionException(
          "Can not be reverse, account closing date "
              + DateTimeFormatter.ofPattern("MM/dd/yyyy").format(entity.getClosingDate())
              + " not equal to current date.");
    }

    if (entity.getStatus() != AccountStatus.CLOSED) {
      throw new ReactiveTransactionException(
          "Can not be reverse, account status found " + entity.getStatus());
    }

    transactionService.correctTransaction(
        mapper.doTransactionCorrection(txnInformation, auditInformation));

    updateTreasuryAccount(entity, processEntity);

    return txnInformation.getGlobalTxnNumber();
  }

  private void updateTreasuryAccount(
      AccountEntity accountEntity, AccountProcessEntity processEntity) {

    BigDecimal balance = accountEntity.getBalance();
    BigDecimal principalDebit = accountEntity.getPrincipalDebit();
    BigDecimal principalCredit = accountEntity.getPrincipalCredit();
    BigDecimal profitDebit = accountEntity.getProfitDebit();
    BigDecimal profitCredit = accountEntity.getProfitCredit();

    Long principalDebitDefId =
        transactionDefinitionService.getId(
            TransactionDefinitionJournalType.DEBIT,
            TransactionRequestType.TRANSFER,
            TransactionDefinitionModule.TREASURY,
            TransactionAmountType.PRINCIPAL);
    Long principalCreditDefId =
        transactionDefinitionService.getId(
            TransactionDefinitionJournalType.CREDIT,
            TransactionRequestType.TRANSFER,
            TransactionDefinitionModule.TREASURY,
            TransactionAmountType.PRINCIPAL);
    Long profitDebitDefId =
        transactionDefinitionService.getId(
            TransactionDefinitionJournalType.DEBIT,
            TransactionRequestType.TRANSFER,
            TransactionDefinitionModule.TREASURY,
            TransactionAmountType.PROFIT);
    Long profitCreditDefId =
        transactionDefinitionService.getId(
            TransactionDefinitionJournalType.CREDIT,
            TransactionRequestType.TRANSFER,
            TransactionDefinitionModule.TREASURY,
            TransactionAmountType.PROFIT);

    List<TransactionRecordEntity> recordList =
        transactionRecordRepository.findAllByGlobalTxnNo(processEntity.getGlobalTxnNumber());
    for (TransactionRecordEntity record : recordList) {
      if (record.getTxnDefId().compareTo(principalDebitDefId) == 0) {
        balance = balance.subtract(record.getAmount());
        principalDebit = principalDebit.subtract(record.getAmount());
      }
      if (record.getTxnDefId().compareTo(principalCreditDefId) == 0) {
        balance = balance.add(record.getAmount());
        principalCredit = principalCredit.subtract(record.getAmount());
      }
      if (record.getTxnDefId().compareTo(profitDebitDefId) == 0) {
        balance = balance.subtract(record.getAmount());
        profitDebit = profitDebit.subtract(record.getAmount());
      }
      if (record.getTxnDefId().compareTo(profitCreditDefId) == 0) {
        balance = balance.add(record.getAmount());
        profitCredit = profitCredit.subtract(record.getAmount());
      }
    }

    accountRepository.save(
        accountMapper
            .reactiveEntity(balance, principalDebit, principalCredit, profitDebit, profitCredit)
            .map(processEntity));
  }
}
