package com.mislbd.ababil.treasury.service;

import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.transaction.domain.TransactionAmountType;
import com.mislbd.ababil.transaction.domain.TransactionRequestType;
import com.mislbd.ababil.transaction.service.TransactionService;
import com.mislbd.ababil.treasury.domain.Account;
import com.mislbd.ababil.treasury.domain.AuditInformation;
import com.mislbd.ababil.treasury.domain.GLType;
import com.mislbd.ababil.treasury.domain.TransactionalInformation;
import com.mislbd.ababil.treasury.exception.AccountNotFoundException;
import com.mislbd.ababil.treasury.exception.ProductRelatedGLNotFoundException;
import com.mislbd.ababil.treasury.exception.ProvisionMismatchException;
import com.mislbd.ababil.treasury.mapper.AccountMapper;
import com.mislbd.ababil.treasury.mapper.TransactionalOperationMapper;
import com.mislbd.ababil.treasury.repository.jpa.AccountRepository;
import com.mislbd.ababil.treasury.repository.jpa.ProductRelatedGLRepository;
import com.mislbd.ababil.treasury.repository.schema.AccountEntity;
import com.mislbd.ababil.treasury.repository.schema.ProductRelatedGLEntity;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class TransactionalOperationService {

  private static final Long PLACEMENT_ACTIVITY = Long.valueOf(801);
  private static final Long SETTLEMENT_ACTIVITY = Long.valueOf(802);
  private static final Long CLOSE_ACTIVITY = Long.valueOf(803);
  private static final Long REACTIVE_ACTIVITY = Long.valueOf(804);
  private static final String SYSTEM_EXCHANGE_RATE_TYPE = "SYSTEM_EXCHANGE_RATE_TYPE";

  private final TransactionService transactionService;
  private final ConfigurationService configurationService;
  private final TransactionalOperationMapper mapper;
  private final String baseCurrency;
  private final ProductRelatedGLRepository productRelatedGLRepository;
  private final AccountRepository accountRepository;
  private final AccountMapper accountMapper;
  private final UtilityService utilityService;

  public TransactionalOperationService(
      TransactionService transactionService,
      ConfigurationService configurationService,
      TransactionalOperationMapper mapper,
      ProductRelatedGLRepository productRelatedGLRepository,
      AccountRepository accountRepository,
      AccountMapper accountMapper,
      UtilityService utilityService) {
    this.transactionService = transactionService;
    this.configurationService = configurationService;
    this.baseCurrency = configurationService.getBaseCurrencyCode();
    this.mapper = mapper;
    this.productRelatedGLRepository = productRelatedGLRepository;
    this.accountRepository = accountRepository;
    this.accountMapper = accountMapper;
    this.utilityService = utilityService;
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
        mapper.getPrincipalPayableAccount(
            txnInformation, baseCurrency, auditInformation, true, entity),
        TransactionRequestType.TRANSFER,
        TransactionAmountType.PRINCIPAL);

    String settlementGlCode = getRelatedGl(entity.getProduct().getId(), GLType.SETTLEMENT_GL);

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

  public Long doSettlementTransaction(AuditInformation auditInformation, Account account) {

    /*
    ###### Settlement Transaction #######
    * Treasury account debit
    * Profit_Receivable credit
    * if actual_profit > provisional_profit
    *   then income gl credit (actual_profit - provisional_profit)
    * if provisional_profit > actual_profit
    *   then debit income gl  (provisional_profit - actual_profit)
    *
    ##### Renewal with profit #########
    * Treasury account credit
    * Settlement gl debit
    * */

    AccountEntity entity =
        accountRepository.findById(account.getId()).orElseThrow(AccountNotFoundException::new);

    TransactionalInformation txnInformation =
        getTransactionInformation(auditInformation, SETTLEMENT_ACTIVITY, null);

    String profitReceivableGl =
        getRelatedGl(entity.getProduct().getId(), GLType.PROFIT_RECEIVABLE_GL);
    String incomeGl = getRelatedGl(entity.getProduct().getId(), GLType.INCOME_GL);
    String settlementGl = getRelatedGl(entity.getProduct().getId(), GLType.SETTLEMENT_GL);

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
                false,
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

    if (account.getRenewWithProfit() != null
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

    doProvisionPosted(entity.getShadowAccountNumber(), account.getProfitAmount());

    //    PROCEDURE DO_PROVISION_POSTED(PACCNO VARCHAR2, PPROVISIONAMOUNT NUMBER, PPOSTING_EVENT
    // VARCHAR2) IS
    //    LPROVISIONAMOUNT NUMBER;
    //    BEGIN
    //
    //    SELECT SUM(TMPIPROVISIONAMOUNT) INTO LPROVISIONAMOUNT
    //    FROM TREASURY_MONTHLYPRODUCTINFO
    //    WHERE TMPIACCNO=PACCNO
    //    AND TMPIISGLPOSTED='TRUE'
    //    AND TMPIISACPOSTED='FALSE';
    //
    //    IF NVL(LPROVISIONAMOUNT,0)<>NVL(PPROVISIONAMOUNT,0) THEN
    //    COMMON.RAISEEXCEPTION('Provision amount '||LPROVISIONAMOUNT||' mismatch with given
    // amount'||PPROVISIONAMOUNT);
    //    END IF;
    //
    //    UPDATE TREASURY_MONTHLYPRODUCTINFO
    //    SET TMPIISACPOSTED='TRUE', TMPIISGLPOSTED='TRUE',
    //            TMPIPROFITPOSTEVENT=PPOSTING_EVENT
    //    WHERE TMPIACCNO=PACCNO
    //    AND TMPIISACPOSTED='FALSE';
    //    END;

    return txnInformation.getGlobalTxnNumber();
  }

  private void doProvisionPosted(String shadowAccountNumber, BigDecimal profitAmount) {
    BigDecimal provisionAmount = utilityService.totalProvisionOfAccounts(shadowAccountNumber);
    if (provisionAmount.compareTo(profitAmount) != 0) {
      throw new ProvisionMismatchException(
          "Provision not match for account " + shadowAccountNumber);
    }
    //        utilityService.updateMonthendInfo(shadowAccountNumber, true);
  }

  String getRelatedGl(long productId, GLType glType) {
    ProductRelatedGLEntity productRelatedGl =
        productRelatedGLRepository
            .findByProductIdAndGlType(productId, glType)
            .orElseThrow(ProductRelatedGLNotFoundException::new);
    return productRelatedGl.getGlCode();
  }
}
