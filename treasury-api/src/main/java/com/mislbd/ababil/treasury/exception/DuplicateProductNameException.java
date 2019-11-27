package com.mislbd.ababil.treasury.exception;

import com.mislbd.asset.commons.central.ExtendedRuntimeException;

public class DuplicateProductNameException extends ExtendedRuntimeException {

  public DuplicateProductNameException() {
    super(
        Error.DUPLICATE_PRODUCT_NAME.getModule(),
        Error.DUPLICATE_PRODUCT_NAME.getCode(),
        Error.DUPLICATE_PRODUCT_NAME.getMessages());
  }

  public DuplicateProductNameException(String message) {
    super(
        Error.DUPLICATE_PRODUCT_NAME.getModule(), Error.DUPLICATE_PRODUCT_NAME.getCode(), message);
  }
}
