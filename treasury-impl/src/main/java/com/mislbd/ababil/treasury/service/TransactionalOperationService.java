package com.mislbd.ababil.treasury.service;

import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.transaction.domain.TransactionRequestType;
import com.mislbd.ababil.transaction.service.TransactionService;
import com.mislbd.ababil.treasury.domain.AuditInformation;
import com.mislbd.ababil.treasury.domain.TransactionalInformation;
import com.mislbd.ababil.treasury.mapper.TransactionalOperationMapper;
import com.mislbd.ababil.treasury.repository.schema.AccountEntity;
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

  public TransactionalOperationService(
      TransactionService transactionService,
      ConfigurationService configurationService,
      TransactionalOperationMapper mapper) {
    this.transactionService = transactionService;
    this.configurationService = configurationService;
    this.baseCurrency = configurationService.getBaseCurrencyCode();
    this.mapper = mapper;
  }

  /*
  *
  ###### Placement Transaction #######
  *   get audit information
  *   debit treasury account
  *   credit settlement account
  * */

  public Long dolPlacementTransaction(AuditInformation auditInformation, AccountEntity entity) {
    TransactionalInformation txnInformation =
        getTransactionInformation(auditInformation, PLACEMENT_ACTIVITY, null);
    transactionService.doGlTransaction(
        mapper.getPayableGL(entity, baseCurrency, false, auditInformation, txnInformation),
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
        .build();
  }
}
