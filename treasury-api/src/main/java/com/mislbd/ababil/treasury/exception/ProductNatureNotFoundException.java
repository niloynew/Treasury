package com.mislbd.ababil.treasury.exception;

import com.mislbd.ababil.asset.exception.NotFoundException;

public class ProductNatureNotFoundException extends NotFoundException {

  public ProductNatureNotFoundException() {
    super(
        Error.PRODUCT_NATURE_NOT_FOUND.getModule(),
        Error.PRODUCT_NATURE_NOT_FOUND.getCode(),
        Error.PRODUCT_NATURE_NOT_FOUND.getMessages());
  }

  public ProductNatureNotFoundException(String message) {
    super(
        Error.PRODUCT_NATURE_NOT_FOUND.getModule(),
        Error.PRODUCT_NATURE_NOT_FOUND.getCode(),
        message);
  }
}
