package com.mislbd.ababil.treasury.mapper;

import com.mislbd.ababil.transaction.domain.GlTransactionRequest;
import com.mislbd.ababil.transaction.domain.TreasuryTransactionRequest;
import com.mislbd.ababil.treasury.domain.AuditInformation;
import com.mislbd.ababil.treasury.domain.TransactionalInformation;
import com.mislbd.ababil.treasury.repository.schema.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionalOperationMapper {

  public TreasuryTransactionRequest getPayableAccount(
      TransactionalInformation txnInformation,
      String baseCurrency,
      AuditInformation auditInformation,
      boolean isDebit,
      AccountEntity entity) {
    TreasuryTransactionRequest request = new TreasuryTransactionRequest();
    request.setActivityId(txnInformation.getActivityId());
    request.setAmountCcy(entity.getAmount());
    request.setAmountLcy(entity.getAmount());
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
    request.setNarration(
        entity.getAmount()
            + " BDT "
            + (isDebit ? "debited to" : "credited from")
            + " account "
            + entity.getShadowAccountNumber());
    request.setApprovalFlowInstanceId(auditInformation.getProcessId());
    request.setInitiatorModule("TREASURY");
    request.setInitiatorBranch(auditInformation.getUserBranch());
    request.setAccNumber(entity.getShadowAccountNumber());
    return request;
  }

  public GlTransactionRequest getPayableGL(
      TransactionalInformation txnInformation,
      String baseCurrency,
      AuditInformation auditInformation,
      boolean isDebit,
      AccountEntity entity,
      String glCode) {
    String narration = "";
    GlTransactionRequest glRequest = new GlTransactionRequest();
    glRequest.setActivityId(txnInformation.getActivityId());
    glRequest.setAmountLcy(entity.getAmount());
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
    glRequest.setGlCode(entity.getShadowAccountNumber());
    glRequest.setValueDate(entity.getOpenDate());
    glRequest.setGlCode(glCode);
    return glRequest;
  }
}
