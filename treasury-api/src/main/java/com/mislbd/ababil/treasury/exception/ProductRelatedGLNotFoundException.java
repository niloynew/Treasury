package com.mislbd.ababil.treasury.exception;

import com.mislbd.ababil.asset.exception.NotFoundException;

public class ProductRelatedGLNotFoundException extends NotFoundException {

  public ProductRelatedGLNotFoundException() {
    super(
        Error.PRODUCT_RELATED_GL_NOT_FOUND.getModule(),
        Error.PRODUCT_RELATED_GL_NOT_FOUND.getCode(),
        Error.PRODUCT_RELATED_GL_NOT_FOUND.getMessages());
  }

  public ProductRelatedGLNotFoundException(String message) {
    super(
        Error.PRODUCT_RELATED_GL_NOT_FOUND.getModule(),
        Error.PRODUCT_RELATED_GL_NOT_FOUND.getCode(),
        message);
  }
}
