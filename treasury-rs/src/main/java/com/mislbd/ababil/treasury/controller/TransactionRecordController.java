package com.mislbd.ababil.treasury.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.treasury.command.CreateTransactionRecordCommand;
import com.mislbd.ababil.treasury.domain.TransactionRecord;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.command.api.CommandResponse;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/transactionRecords", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionRecordController {

  private final CommandProcessor commandProcessor;

  public TransactionRecordController(CommandProcessor commandProcessor) {
    this.commandProcessor = commandProcessor;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CommandResponse<Long>> createTransactionRecords(
      @Valid @RequestBody TransactionRecord transactionRecord) {
    return status(CREATED)
        .body(
            commandProcessor.executeResult(new CreateTransactionRecordCommand(transactionRecord)));
  }

  //  @PutMapping(path = "/{transactionRecordId}", consumes = MediaType.APPLICATION_JSON_VALUE)
  //  public ResponseEntity<Void> updateTransactionRecord(
  //      @RequestBody TransactionRecord transactionRecord,
  //      @PathVariable("transactionRecordId") Long transactionRecordId) {
  //
  //    commandProcessor.executeUpdate(
  //        new UpdateTransactionRecordCommand(transactionRecord, transactionRecordId));
  //
  //    return status(ACCEPTED).build();
  //  }
}
