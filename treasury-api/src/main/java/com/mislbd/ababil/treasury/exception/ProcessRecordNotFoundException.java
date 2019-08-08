package com.mislbd.ababil.treasury.exception;

import com.mislbd.ababil.asset.exception.NotFoundException;

public class ProcessRecordNotFoundException extends NotFoundException {

  public ProcessRecordNotFoundException() {
    super(
        Error.PROCESS_RECORD_NOT_FOUND.getModule(),
        Error.PROCESS_RECORD_NOT_FOUND.getCode(),
        Error.PROCESS_RECORD_NOT_FOUND.getMessages());
  }

  public ProcessRecordNotFoundException(String message) {
    super(
        Error.PROCESS_RECORD_NOT_FOUND.getModule(),
        Error.PROCESS_RECORD_NOT_FOUND.getCode(),
        message);
  }
}
