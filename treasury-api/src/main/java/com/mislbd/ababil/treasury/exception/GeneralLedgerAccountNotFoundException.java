package com.mislbd.ababil.treasury.exception;

import com.mislbd.ababil.asset.exception.NotFoundException;

public class GeneralLedgerAccountNotFoundException extends NotFoundException {

  public GeneralLedgerAccountNotFoundException() {
    super(
        Error.GENERAL_LEDGER_ACCOUNT_NOT_FOUND.getModule(),
        Error.GENERAL_LEDGER_ACCOUNT_NOT_FOUND.getCode(),
        Error.GENERAL_LEDGER_ACCOUNT_NOT_FOUND.getMessages());
  }

  public GeneralLedgerAccountNotFoundException(String message) {
    super(
        Error.GENERAL_LEDGER_ACCOUNT_NOT_FOUND.getModule(),
        Error.GENERAL_LEDGER_ACCOUNT_NOT_FOUND.getCode(),
        message);
  }
}
