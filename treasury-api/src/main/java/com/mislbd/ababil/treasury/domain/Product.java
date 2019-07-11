package com.mislbd.ababil.treasury.domain;

public class Product {

  private long id;
  private String code;
  private String name;
  private boolean profitApplicable;
  private String productNature;
  private ProfitCalculationMethod profitCalculationMethod;
  private DaysInYear daysInYear;
  private ProductStatus status;

  public long getId() {
    return id;
  }

  public Product setId(long id) {
    this.id = id;
    return this;
  }

  public String getCode() {
    return code;
  }

  public Product setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public Product setName(String name) {
    this.name = name;
    return this;
  }

  public boolean isProfitApplicable() {
    return profitApplicable;
  }

  public Product setProfitApplicable(boolean profitApplicable) {
    this.profitApplicable = profitApplicable;
    return this;
  }

  public String getProductNature() {
    return productNature;
  }

  public Product setProductNature(String productNature) {
    this.productNature = productNature;
    return this;
  }

  public ProfitCalculationMethod getProfitCalculationMethod() {
    return profitCalculationMethod;
  }

  public Product setProfitCalculationMethod(ProfitCalculationMethod profitCalculationMethod) {
    this.profitCalculationMethod = profitCalculationMethod;
    return this;
  }

  public DaysInYear getDaysInYear() {
    return daysInYear;
  }

  public Product setDaysInYear(DaysInYear daysInYear) {
    this.daysInYear = daysInYear;
    return this;
  }

  public ProductStatus getStatus() {
    return status;
  }

  public Product setStatus(ProductStatus status) {
    this.status = status;
    return this;
  }
}
