package com.mislbd.ababil.treasury.mapper;

import com.mislbd.ababil.treasury.domain.TransactionRecord;
import com.mislbd.ababil.treasury.repository.jpa.TransactionRecordRepository;
import com.mislbd.ababil.treasury.repository.schema.TransactionRecordEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionRecordMapper {
  private final TransactionRecordRepository transactionRecordRepository;

  public TransactionRecordMapper(TransactionRecordRepository transactionRecordRepository) {
    this.transactionRecordRepository = transactionRecordRepository;
  }

  public ResultMapper<TransactionRecordEntity, TransactionRecord> entityToDomain() {

    return entity ->
        new TransactionRecord()
            .setId(entity.getId())
            .setTxnCode(entity.getTxnCode())
            .setTxnDate(entity.getTxnDate())
            .setAmount(entity.getAmount())
            .setValid(entity.isValid())
            .setInitiator(entity.getInitiator())
            .setInstrumentNo(entity.getInstrumentNo())
            .setTxndId(entity.getTxndId())
            .setTxndCode(entity.getTxndCode())
            .setRefAccId(entity.getRefAccId())
            .setNarration(entity.getNarration())
            .setGlobalTxnno(entity.getGlobalTxnno())
            .setInitiatorModule(entity.getInitiatorModule())
            .setCreatedByAppUser(entity.getCreatedByAppUser())
            .setVerifiedByAppUser(entity.getVerifiedByAppUser())
            .setCreatedTerminal(entity.getCreatedTerminal())
            .setVerfiedTermial(entity.getVerfiedTermial())
            .setCreatedSysUser(entity.getCreatedSysUser())
            .setVerifiedSysUser(entity.getVerifiedSysUser())
            .setOriginatingBrId(entity.getOriginatingBrId())
            .setOwnerBrId(entity.getOwnerBrId())
            .setTxnValueDate(entity.getTxnValueDate());
  }

  public ResultMapper<TransactionRecord, TransactionRecordEntity> domainToEntity() {
    return domain ->
        new TransactionRecordEntity()
            .setId(domain.getId())
            .setTxnCode(domain.getTxnCode())
            .setTxnDate(domain.getTxnDate())
            .setAmount(domain.getAmount())
            .setValid(domain.isValid())
            .setInitiator(domain.getInitiator())
            .setInstrumentNo(domain.getInstrumentNo())
            .setTxndId(domain.getTxndId())
            .setTxndCode(domain.getTxndCode())
            .setRefAccId(domain.getRefAccId())
            .setNarration(domain.getNarration())
            .setGlobalTxnno(domain.getGlobalTxnno())
            .setInitiatorModule(domain.getInitiatorModule())
            .setCreatedByAppUser(domain.getCreatedByAppUser())
            .setVerifiedByAppUser(domain.getVerifiedByAppUser())
            .setCreatedTerminal(domain.getCreatedTerminal())
            .setVerfiedTermial(domain.getVerfiedTermial())
            .setCreatedSysUser(domain.getCreatedSysUser())
            .setVerifiedSysUser(domain.getVerifiedSysUser())
            .setOriginatingBrId(domain.getOriginatingBrId())
            .setOwnerBrId(domain.getOwnerBrId())
            .setTxnValueDate(domain.getTxnValueDate());
  }
}
