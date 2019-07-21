package com.mislbd.ababil.treasury.exception;

import com.mislbd.ababil.asset.exception.NotFoundException;

public class ProductNotFoundException extends NotFoundException {
  public ProductNotFoundException() {
    super(
        Error.PRODUCT_NOT_FOUND.getModule(),
        Error.PRODUCT_NOT_FOUND.getCode(),
        Error.PRODUCT_NOT_FOUND.getMessages());
  }

  public ProductNotFoundException(String message) {
    super(Error.PRODUCT_NOT_FOUND.getModule(), Error.PRODUCT_NOT_FOUND.getCode(), message);
  }
}
