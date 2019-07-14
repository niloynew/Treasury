package com.mislbd.ababil.treasury.domain;

public class ProductNature {
  private long id;
  private String code;
  private String description;

  public long getId() {
    return id;
  }

  public ProductNature setId(long id) {
    this.id = id;
    return this;
  }

  public String getCode() {
    return code;
  }

  public ProductNature setCode(String code) {
    this.code = code;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public ProductNature setDescription(String description) {
    this.description = description;
    return this;
  }
}
