package com.mislbd.ababil.treasury.exception;

import com.mislbd.asset.commons.central.ExtendedRuntimeException;

public class ProvisionMismatchException extends ExtendedRuntimeException {
  public ProvisionMismatchException() {
    super(
        Error.PROVISION_MISMATCH.getModule(),
        Error.PROVISION_MISMATCH.getCode(),
        Error.PROVISION_MISMATCH.getMessages());
  }

  public ProvisionMismatchException(String message) {
    super(Error.PROVISION_MISMATCH.getModule(), Error.PROVISION_MISMATCH.getCode(), message);
  }
}
