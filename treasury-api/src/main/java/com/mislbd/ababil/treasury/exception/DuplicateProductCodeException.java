package com.mislbd.ababil.treasury.exception;

import com.mislbd.asset.commons.central.ExtendedRuntimeException;

public class DuplicateProductCodeException extends ExtendedRuntimeException {

  public DuplicateProductCodeException() {
    super(
        Error.DUPLICATE_PRODUCT_CODE.getModule(),
        Error.DUPLICATE_PRODUCT_CODE.getCode(),
        Error.DUPLICATE_PRODUCT_CODE.getMessages());
  }

  public DuplicateProductCodeException(String message) {
    super(
        Error.DUPLICATE_PRODUCT_CODE.getModule(), Error.DUPLICATE_PRODUCT_CODE.getCode(), message);
  }
}
