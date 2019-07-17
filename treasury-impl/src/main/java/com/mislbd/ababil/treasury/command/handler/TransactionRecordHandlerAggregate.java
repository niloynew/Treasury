package com.mislbd.ababil.treasury.command.handler;

import com.mislbd.ababil.treasury.command.CreateTransactionRecordCommand;
import com.mislbd.ababil.treasury.mapper.TransactionRecordMapper;
import com.mislbd.ababil.treasury.repository.jpa.TransactionRecordRepository;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import javax.transaction.Transactional;

@Aggregate
public class TransactionRecordHandlerAggregate {
  private final TransactionRecordRepository transactionRecordRepository;
  private TransactionRecordMapper transactionRecordMapper;

  public TransactionRecordHandlerAggregate(
      TransactionRecordRepository transactionRecordRepository,
      TransactionRecordMapper transactionRecordMapper) {
    this.transactionRecordRepository = transactionRecordRepository;
    this.transactionRecordMapper = transactionRecordMapper;
  }

  @Transactional
  @CommandHandler
  public CommandResponse<?> createTransactionRecord(CreateTransactionRecordCommand command) {
    return CommandResponse.of(
        transactionRecordRepository
            .save(transactionRecordMapper.domainToEntity().map(command.getPayload()))
            .getId());
  }

  //  @Transactional
  //  @CommandHandler
  //  public CommandResponse<?> updateTransactionRecord(
  //      UpdateTransactionRecordCommand command, Long id) {
  //
  //    TransactionRecord transactionRecord = command.getPayload();
  //    transactionRecordRepository.findById(id).orElseThrow(AccountNotFoundException::new);
  //    transactionRecord.setId(id);
  //
  //    transactionRecordRepository
  //        .save(transactionRecordMapper.domainToEntity().map(command.getPayload()))
  //        .getId();
  //    return CommandResponse.asVoid();
  //  }
}
