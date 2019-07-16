package com.mislbd.ababil.treasury.query;

import com.mislbd.ababil.treasury.domain.AccountStatus;
import com.mislbd.asset.query.api.QueryRequest;
import java.time.LocalDate;
import org.springframework.data.domain.Pageable;

public class AccountQuery extends QueryRequest {
  private boolean asPage;
  private Pageable pageable;
  private Long productId;
  private String currencyCode;
  private LocalDate openDate;
  private LocalDate expiryDate;
  private AccountStatus status;
  private Long accountId;

  public AccountQuery(
      Pageable pageable,
      Long productId,
      String currencyCode,
      LocalDate openDate,
      LocalDate expiryDate,
      AccountStatus status) {
    this.pageable = pageable;
    this.productId = productId;
    this.currencyCode = currencyCode;
    this.openDate = openDate;
    this.expiryDate = expiryDate;
    this.status = status;
    this.asPage = asPage;
  }

  public AccountQuery(
      Long productId,
      String currencyCode,
      LocalDate openDate,
      LocalDate expiryDate,
      AccountStatus status) {
    this.productId = productId;
    this.currencyCode = currencyCode;
    this.openDate = openDate;
    this.expiryDate = expiryDate;
    this.status = status;
  }

  public AccountQuery(Long accountId) {
    this.accountId = accountId;
  }

  public boolean isAsPage() {
    return asPage;
  }

  public void setAsPage(boolean asPage) {
    this.asPage = asPage;
  }

  public Pageable getPageable() {
    return pageable;
  }

  public void setPageable(Pageable pageable) {
    this.pageable = pageable;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public LocalDate getOpenDate() {
    return openDate;
  }

  public void setOpenDate(LocalDate openDate) {
    this.openDate = openDate;
  }

  public LocalDate getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(LocalDate expiryDate) {
    this.expiryDate = expiryDate;
  }

  public AccountStatus getStatus() {
    return status;
  }

  public void setStatus(AccountStatus status) {
    this.status = status;
  }
}
