package com.mislbd.ababil.treasury.mapper;

import com.mislbd.ababil.transaction.domain.GlTransactionRequest;
import com.mislbd.ababil.treasury.domain.AuditInformation;
import com.mislbd.ababil.treasury.domain.TransactionalInformation;
import com.mislbd.ababil.treasury.repository.schema.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionalOperationMapper {

  public GlTransactionRequest getPayableGL(
      AccountEntity entity,
      String baseCurrency,
      boolean isDebit,
      AuditInformation auditInformation,
      TransactionalInformation txnInformation) {
    return null;
  }
}
