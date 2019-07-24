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
    return null;
  }

  public GlTransactionRequest getPayableGL(
      TransactionalInformation txnInformation,
      String baseCurrency,
      AuditInformation auditInformation,
      boolean isDebit,
      AccountEntity entity) {
    return null;
  }
}
