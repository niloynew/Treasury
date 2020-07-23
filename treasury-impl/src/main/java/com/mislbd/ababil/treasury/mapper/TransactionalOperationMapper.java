package com.mislbd.ababil.treasury.mapper;

import com.mislbd.ababil.transaction.domain.GlTransactionRequest;
import com.mislbd.ababil.transaction.domain.TransactionAmountType;
import com.mislbd.ababil.transaction.domain.TransactionCorrectionRequest;
import com.mislbd.ababil.transaction.domain.TreasuryTransactionRequest;
import com.mislbd.ababil.treasury.domain.AuditInformation;
import com.mislbd.ababil.treasury.domain.TransactionalInformation;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@SuppressWarnings("Duplicates")
@Component
public class TransactionalOperationMapper {

  public TreasuryTransactionRequest getPayableAccount(
      TransactionalInformation txnInformation,
      String baseCurrency,
      AuditInformation auditInformation,
      boolean isDebit,
      String accountNumber,
      BigDecimal amount,
      TransactionAmountType amountType) {
    String narration =
        amountType.name() + (isDebit ? " debited" : " credited");
    TreasuryTransactionRequest request = new TreasuryTransactionRequest();
    request.setActivityId(txnInformation.getActivityId());
    request.setAmountCcy(amount);
    request.setAmountLcy(amount);
    request.setCurrencyCode(baseCurrency);
    request.setExchangeRate(txnInformation.getExchangeRate());
    request.setRateType(txnInformation.getExchangeRateType());
    request.setDebitTransaction(isDebit);
    request.setBatchNo(txnInformation.getBatchNumber());
    request.setGlobalTxnNo(txnInformation.getGlobalTxnNumber());
    request.setEntryUser(auditInformation.getEntryUser());
    request.setEntryTerminal(auditInformation.getEntryTerminal());
    request.setEntryTime(auditInformation.getEntryDate());
    request.setVerifyUser(auditInformation.getVerifyUser());
    request.setVerifyTerminal(auditInformation.getVerifyTerminal());
    request.setNarration(narration);
    request.setApprovalFlowInstanceId(auditInformation.getProcessId());
    request.setInitiatorModule("TREASURY");
    request.setInitiatorBranch(auditInformation.getUserBranch());
    request.setAccNumber(accountNumber);
    return request;
  }

  public GlTransactionRequest getPayableGL(
      TransactionalInformation txnInformation,
      String baseCurrency,
      AuditInformation auditInformation,
      boolean isDebit,
      BigDecimal amount,
      String glCode,
      LocalDate valueDate,
      TransactionAmountType amountType) {
    String narration =
        amountType.name() + (isDebit ? " debited to GL:" : " credited from GL:") + glCode;
    GlTransactionRequest glRequest = new GlTransactionRequest();
    glRequest.setActivityId(txnInformation.getActivityId());
    glRequest.setAmountLcy(amount);
    glRequest.setAmountCcy(amount);
    glRequest.setCurrencyCode(baseCurrency);
    glRequest.setExchangeRate(txnInformation.getExchangeRate());
    glRequest.setRateType(txnInformation.getExchangeRateType());
    glRequest.setDebitTransaction(isDebit);
    glRequest.setBatchNo(txnInformation.getBatchNumber());
    glRequest.setGlobalTxnNo(txnInformation.getGlobalTxnNumber());
    glRequest.setOwnerBranch(auditInformation.getUserBranch());
    glRequest.setEntryUser(auditInformation.getEntryUser());
    glRequest.setEntryTerminal(auditInformation.getEntryTerminal());
    glRequest.setEntryTime(auditInformation.getEntryDate());
    glRequest.setVerifyUser(auditInformation.getVerifyUser());
    glRequest.setVerifyTerminal(auditInformation.getVerifyTerminal());
    glRequest.setNarration(narration);
    glRequest.setApprovalFlowInstanceId(auditInformation.getProcessId());
    glRequest.setInitiatorModule("TREASURY");
    glRequest.setInitiatorBranch(auditInformation.getUserBranch());
    glRequest.setValueDate(valueDate);
    glRequest.setGlCode(glCode);
    return glRequest;
  }

  public TransactionCorrectionRequest doTransactionCorrection(
      TransactionalInformation transactionalInformation, AuditInformation auditInformation) {
    TransactionCorrectionRequest request = new TransactionCorrectionRequest();
    request.setGlobalTransactionNumber(transactionalInformation.getGlobalTxnNumber());
    request.setInitiatorBranch(Long.valueOf(auditInformation.getUserBranch()));
    request.setEntryUser(auditInformation.getEntryUser());
    request.setEntryTerminal(auditInformation.getEntryTerminal());
    request.setVerifyUser(auditInformation.getVerifyUser());
    request.setVerifyTerminal(auditInformation.getVerifyTerminal());
    return request;
  }
}
