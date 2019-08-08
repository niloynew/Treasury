package com.mislbd.ababil.treasury.service;

import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.transaction.domain.TransactionAmountType;
import com.mislbd.ababil.transaction.domain.TransactionRequestType;
import com.mislbd.ababil.transaction.service.TransactionService;
import com.mislbd.ababil.treasury.domain.*;
import com.mislbd.ababil.treasury.exception.*;
import com.mislbd.ababil.treasury.external.service.GlAccountService;
import com.mislbd.ababil.treasury.mapper.AccountMapper;
import com.mislbd.ababil.treasury.mapper.TransactionalOperationMapper;
import com.mislbd.ababil.treasury.repository.jpa.AccountProcessRepository;
import com.mislbd.ababil.treasury.repository.jpa.AccountRepository;
import com.mislbd.ababil.treasury.repository.jpa.ProductRelatedGLRepository;
import com.mislbd.ababil.treasury.repository.schema.AccountEntity;
import com.mislbd.ababil.treasury.repository.schema.AccountProcessEntity;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

@Service
public class TransactionalOperationService {

  private static final Long PLACEMENT_ACTIVITY = Long.valueOf(801);
  private static final Long SETTLEMENT_OR_CLOSE_ACTIVITY = Long.valueOf(802);
  private static final Long REACTIVE_ACTIVITY = Long.valueOf(803);
  private static final String SYSTEM_EXCHANGE_RATE_TYPE = "SYSTEM_EXCHANGE_RATE_TYPE";

  private final TransactionService transactionService;
  private final ConfigurationService configurationService;
  private final TransactionalOperationMapper mapper;
  private final String baseCurrency;
  private final ProductRelatedGLRepository productRelatedGLRepository;
  private final AccountRepository accountRepository;
  private final AccountMapper accountMapper;
  private final UtilityService utilityService;
  private final GlAccountService glAccountService;
  private final AccountProcessRepository processRepository;

  public TransactionalOperationService(
      TransactionService transactionService,
      ConfigurationService configurationService,
      TransactionalOperationMapper mapper,
      ProductRelatedGLRepository productRelatedGLRepository,
      AccountRepository accountRepository,
      AccountMapper accountMapper,
      UtilityService utilityService,
      GlAccountService glAccountService,
      AccountProcessRepository processRepository) {
    this.transactionService = transactionService;
    this.configurationService = configurationService;
    this.baseCurrency = configurationService.getBaseCurrencyCode();
    this.mapper = mapper;
    this.productRelatedGLRepository = productRelatedGLRepository;
    this.accountRepository = accountRepository;
    this.accountMapper = accountMapper;
    this.utilityService = utilityService;
    this.glAccountService = glAccountService;
    this.processRepository = processRepository;
  }

  /*
  ###### Placement Transaction #######
  *   get audit information
  *   debit treasury account
  *   credit settlement account
  * */

  public Long dolPlacementTransaction(AuditInformation auditInformation, Account account) {

    AccountEntity entity =
        accountRepository.saveAndFlush(accountMapper.domainToEntity().map(account));

    TransactionalInformation txnInformation =
        getTransactionInformation(auditInformation, PLACEMENT_ACTIVITY, null);

    transactionService.doTreasuryTransaction(
        mapper.getPayableAccount(txnInformation, baseCurrency, auditInformation, true, entity),
        TransactionRequestType.TRANSFER,
        TransactionAmountType.PRINCIPAL);

    String settlementGlCode = getRelatedGlCode(entity.getProduct().getId(), GLType.SETTLEMENT_GL);

    transactionService.doGlTransaction(
        mapper.getPrincipalPayableGL(
            txnInformation, baseCurrency, auditInformation, false, entity, settlementGlCode),
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
        .exchangeRate(transactionService.getSystemExchangeRate(baseCurrency))
        .exchangeRateType(
            Long.valueOf(
                configurationService.getConfiguration(SYSTEM_EXCHANGE_RATE_TYPE).get().getValue()))
        .activityId(activityId)
        .build();
  }

  public Long doSettlementOrCloseTransaction(AuditInformation auditInformation, Account account) {

    /*
    ###### Settlement Transaction #######
    * Treasury account debit
    * Profit_Receivable credit
    * if actual_profit > provisional_profit
    *   then income gl credit (amount = actual_profit - provisional_profit)
    * if provisional_profit > actual_profit
    *   then debit income gl  (amount = provisional_profit - actual_profit)
    *
    ##### Renewal with profit #########
    * Treasury account credit
    * Settlement gl debit
    *
    *
    ##### Close Transaction ###########
    * calculate closing balance and principal
    * Treasury account credit
    * Settlement gl debit
    *
    #### Update treasury account ######
    * set new renewal date, profit rate, expiry date and status module
    * */

    AccountEntity entity =
        accountRepository.findById(account.getId()).orElseThrow(AccountNotFoundException::new);

    TransactionalInformation txnInformation =
        getTransactionInformation(auditInformation, SETTLEMENT_OR_CLOSE_ACTIVITY, null);

    String profitReceivableGl =
        getRelatedGlCode(entity.getProduct().getId(), GLType.PROFIT_RECEIVABLE_GL);
    String incomeGl = getRelatedGlCode(entity.getProduct().getId(), GLType.INCOME_GL);
    String settlementGl = getRelatedGlCode(entity.getProduct().getId(), GLType.SETTLEMENT_GL);

    transactionService.doTreasuryTransaction(
        mapper.getProfitPayableAccount(
            txnInformation,
            baseCurrency,
            auditInformation,
            true,
            entity.getShadowAccountNumber(),
            account.getActualProfit()),
        TransactionRequestType.TRANSFER,
        TransactionAmountType.PROFIT);

    transactionService.doGlTransaction(
        mapper.getProfitPayableGL(
            txnInformation,
            baseCurrency,
            auditInformation,
            false,
            entity.getShadowAccountNumber(),
            account.getProfitAmount(),
            profitReceivableGl,
            account.getValueDate()),
        TransactionRequestType.TRANSFER);

    if (account.getProfitAmount() != null && account.getActualProfit() != null) {

      if (account.getProfitAmount().compareTo(account.getActualProfit()) == 1) {
        BigDecimal overBalance = account.getProfitAmount().subtract(account.getActualProfit());
        transactionService.doGlTransaction(
            mapper.getBalancingPayableGl(
                txnInformation,
                baseCurrency,
                auditInformation,
                true,
                entity.getShadowAccountNumber(),
                overBalance,
                incomeGl,
                account.getValueDate()),
            TransactionRequestType.TRANSFER);
      }

      if (account.getActualProfit().compareTo(account.getProfitAmount()) == 1) {
        BigDecimal lowerBalance = account.getActualProfit().subtract(account.getProfitAmount());
        transactionService.doGlTransaction(
            mapper.getBalancingPayableGl(
                txnInformation,
                baseCurrency,
                auditInformation,
                false,
                entity.getShadowAccountNumber(),
                lowerBalance,
                incomeGl,
                account.getValueDate()),
            TransactionRequestType.TRANSFER);
      }
    }

    if (account.getEvent() == TransactionEvent.Settlement) {
      if (!account.getRenewWithProfit()
          && account.getProfitAmount().compareTo(BigDecimal.ZERO) == 1) {
        transactionService.doTreasuryTransaction(
            mapper.getProfitPayableAccount(
                txnInformation,
                baseCurrency,
                auditInformation,
                false,
                entity.getShadowAccountNumber(),
                account.getProfitAmount()),
            TransactionRequestType.TRANSFER,
            TransactionAmountType.PROFIT);
        transactionService.doGlTransaction(
            mapper.getProfitPayableGL(
                txnInformation,
                baseCurrency,
                auditInformation,
                true,
                entity.getShadowAccountNumber(),
                account.getProfitAmount(),
                settlementGl,
                account.getValueDate()),
            TransactionRequestType.TRANSFER);
      }
      accountRepository.save(accountMapper.renwalDomainToEntity().map(account));
    }

    if (account.getEvent() == TransactionEvent.Close) {
      entity =
          accountRepository.findById(account.getId()).orElseThrow(AccountNotFoundException::new);
      BigDecimal closingProfit = entity.getProfitDebit().subtract(entity.getProfitCredit());
      BigDecimal closingPrincipal = entity.getBalance().subtract(closingProfit);
      transactionService.doTreasuryTransaction(
          mapper.getProfitPayableAccount(
              txnInformation,
              baseCurrency,
              auditInformation,
              false,
              entity.getShadowAccountNumber(),
              closingProfit),
          TransactionRequestType.TRANSFER,
          TransactionAmountType.PROFIT);
      transactionService.doTreasuryTransaction(
          mapper.getPrincipalPayableAccount(
              txnInformation,
              baseCurrency,
              auditInformation,
              false,
              entity.getShadowAccountNumber(),
              closingPrincipal),
          TransactionRequestType.TRANSFER,
          TransactionAmountType.PRINCIPAL);
      transactionService.doGlTransaction(
          mapper.getBalancingPayableGl(
              txnInformation,
              baseCurrency,
              auditInformation,
              true,
              entity.getShadowAccountNumber(),
              entity.getBalance(),
              settlementGl,
              account.getValueDate()),
          TransactionRequestType.TRANSFER);
      accountRepository.save(accountMapper.closeDomainToEntity().map(account));
    }

    doProvisionPosted(entity.getShadowAccountNumber(), account.getProfitAmount());

    return txnInformation.getGlobalTxnNumber();
  }

  private void doProvisionPosted(String shadowAccountNumber, BigDecimal profitAmount) {
    BigDecimal provisionAmount =
        utilityService.totalProvisionOfAccounts(shadowAccountNumber, true, false);
    if (provisionAmount.compareTo(profitAmount) != 0) {
      throw new ProvisionMismatchException(
          "Provision not match for account " + shadowAccountNumber);
    }
    utilityService.updateMonthendInfo(shadowAccountNumber, "RENEWAL", false);
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
                entity.getShadowAccountNumber(), entity.getStatus(), true)
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

    if (entity.getStatus() != AccountStatus.CLOSED || entity.getStatus() != AccountStatus.REGULAR) {
      throw new ReactiveTransactionException(
          "Can not be reverse, account status found " + entity.getStatus());
    }

    transactionService.correctTransaction(
        mapper.doTransactionCorrection(txnInformation, auditInformation));
    return txnInformation.getGlobalTxnNumber();
  }
}
