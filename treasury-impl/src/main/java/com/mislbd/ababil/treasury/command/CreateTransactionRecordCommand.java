package com.mislbd.ababil.treasury.command;

import com.mislbd.ababil.treasury.domain.TransactionRecord;
import com.mislbd.asset.command.api.Command;

public class CreateTransactionRecordCommand extends Command<TransactionRecord> {
  public CreateTransactionRecordCommand(TransactionRecord payload) {
    super(payload);
  }
}
