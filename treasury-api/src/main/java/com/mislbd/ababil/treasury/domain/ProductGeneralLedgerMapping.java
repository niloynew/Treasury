package com.mislbd.ababil.treasury.domain;

import javax.validation.constraints.NotNull;

public class ProductGeneralLedgerMapping {

  private long id;
  @NotNull private GLType glType;
  @NotNull private String generalLedgerCode;
  @NotNull private String generalLedgerId;
  @NotNull private long productId;

  public long getId() {
    return id;
  }

  public ProductGeneralLedgerMapping setId(long id) {
    this.id = id;
    return this;
  }

  public GLType getGlType() {
    return glType;
  }

  public ProductGeneralLedgerMapping setGlType(GLType glType) {
    this.glType = glType;
    return this;
  }

  public String getGeneralLedgerCode() {
    return generalLedgerCode;
  }

  public ProductGeneralLedgerMapping setGeneralLedgerCode(String generalLedgerCode) {
    this.generalLedgerCode = generalLedgerCode;
    return this;
  }

  public long getProductId() {
    return productId;
  }

  public ProductGeneralLedgerMapping setProductId(long productId) {
    this.productId = productId;
    return this;
  }

  public String getGeneralLedgerId() {
    return generalLedgerId;
  }

  public ProductGeneralLedgerMapping setGeneralLedgerId(String generalLedgerId) {
    this.generalLedgerId = generalLedgerId;
    return this;
  }
}
