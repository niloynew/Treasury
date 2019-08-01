package com.mislbd.ababil.treasury.mapper;

import com.mislbd.ababil.transaction.domain.GlTransactionRequest;
import com.mislbd.ababil.transaction.domain.TreasuryTransactionRequest;
import com.mislbd.ababil.treasury.domain.AuditInformation;
import com.mislbd.ababil.treasury.domain.TransactionalInformation;
import com.mislbd.ababil.treasury.repository.schema.AccountEntity;
import java.math.BigDecimal;
import java.time.LocalDate;
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
        "Principal "
            + entity.getAmount()
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

  public GlTransactionRequest getPrincipalPayableGL(
      TransactionalInformation txnInformation,
      String baseCurrency,
      AuditInformation auditInformation,
      boolean isDebit,
      AccountEntity entity,
      String glCode) {
    GlTransactionRequest glRequest = new GlTransactionRequest();
    glRequest.setActivityId(txnInformation.getActivityId());
    glRequest.setAmountLcy(entity.getAmount());
    glRequest.setAmountCcy(entity.getAmount());
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
    glRequest.setNarration(
        " GL "
            + (isDebit ? "DEBIT" : "CREDIT")
            + " FOR TREASURY."
            + entity.getShadowAccountNumber());
    glRequest.setApprovalFlowInstanceId(auditInformation.getProcessId());
    glRequest.setInitiatorModule("TREASURY");
    glRequest.setInitiatorBranch(auditInformation.getUserBranch());
    glRequest.setValueDate(entity.getOpenDate());
    glRequest.setGlCode(glCode);
    return glRequest;
  }

  public TreasuryTransactionRequest getProfitPayableAccount(
      TransactionalInformation txnInformation,
      String baseCurrency,
      AuditInformation auditInformation,
      boolean isDebit,
      String accountNumber,
      BigDecimal profitAmount) {
    TreasuryTransactionRequest request = new TreasuryTransactionRequest();
    request.setActivityId(txnInformation.getActivityId());
    request.setAmountCcy(profitAmount);
    request.setAmountLcy(profitAmount);
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
        "Profit "
            + profitAmount
            + " BDT "
            + (isDebit ? "debited to" : "credited from")
            + " account "
            + accountNumber);
    request.setApprovalFlowInstanceId(auditInformation.getProcessId());
    request.setInitiatorModule("TREASURY");
    request.setInitiatorBranch(auditInformation.getUserBranch());
    request.setAccNumber(accountNumber);
    return request;
  }

  public GlTransactionRequest getProfitPayableGL(
      TransactionalInformation txnInformation,
      String baseCurrency,
      AuditInformation auditInformation,
      boolean isDebit,
      String accountNumber,
      BigDecimal profitAmount,
      String glCode,
      LocalDate valueDate) {
    GlTransactionRequest glRequest = new GlTransactionRequest();
    glRequest.setActivityId(txnInformation.getActivityId());
    glRequest.setAmountLcy(profitAmount);
    glRequest.setAmountCcy(profitAmount);
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
    glRequest.setNarration(
        " PROFIT " + (isDebit ? "DEBIT" : "CREDIT") + " ON PLACEMENT A/C: " + accountNumber);
    glRequest.setApprovalFlowInstanceId(auditInformation.getProcessId());
    glRequest.setInitiatorModule("TREASURY");
    glRequest.setInitiatorBranch(auditInformation.getUserBranch());
    glRequest.setValueDate(valueDate);
    glRequest.setGlCode(glCode);
    return glRequest;
  }

  public GlTransactionRequest getBalancingPayableGl(
      TransactionalInformation txnInformation,
      String baseCurrency,
      AuditInformation auditInformation,
      boolean isDebit,
      String accountNumber,
      BigDecimal balance,
      String glCode,
      LocalDate valueDate) {
    GlTransactionRequest glRequest = new GlTransactionRequest();
    glRequest.setActivityId(txnInformation.getActivityId());
    glRequest.setAmountCcy(balance);
    glRequest.setAmountLcy(balance);
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
    glRequest.setNarration(
        " PROFIT " + (isDebit ? "DEBIT" : "CREDIT") + " ON PLACEMENT A/C: " + accountNumber);
    glRequest.setApprovalFlowInstanceId(auditInformation.getProcessId());
    glRequest.setInitiatorModule("TREASURY");
    glRequest.setInitiatorBranch(auditInformation.getUserBranch());
    glRequest.setValueDate(valueDate);
    glRequest.setGlCode(glCode);
    return glRequest;
  }

  public TreasuryTransactionRequest getPrincipalPayableAccount(
      TransactionalInformation txnInformation,
      String baseCurrency,
      AuditInformation auditInformation,
      boolean isDebit,
      String accountNumber,
      BigDecimal principalAmount) {
    TreasuryTransactionRequest request = new TreasuryTransactionRequest();
    request.setActivityId(txnInformation.getActivityId());
    request.setAmountCcy(principalAmount);
    request.setAmountLcy(principalAmount);
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
        "Principal "
            + principalAmount
            + " BDT "
            + (isDebit ? "debited to" : "credited from")
            + " account "
            + accountNumber);
    request.setApprovalFlowInstanceId(auditInformation.getProcessId());
    request.setInitiatorModule("TREASURY");
    request.setInitiatorBranch(auditInformation.getUserBranch());
    request.setAccNumber(accountNumber);
    return request;
  }
}
