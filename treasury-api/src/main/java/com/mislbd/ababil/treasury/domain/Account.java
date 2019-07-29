package com.mislbd.ababil.treasury.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Account {

  private long id;
  private Long productId;
  private String currencyCode;
  private Long ownerBranchId;
  private Long bankId;
  private Long branchId;
  private String accountTitle;
  private String accountNumber;
  private BigDecimal amount;
  private String shadowAccountNumber;
  private LocalDate accountOpenDate;
  private LocalDate accountClosingDate;
  private LocalDate expiryDate;
  private int tenorAmount;
  private TenorType tenorType;
  private LocalDate renewalDate;
  private BigDecimal expectedProfitRate;
  private AccountStatus status;
  private String instrument;
  private TransactionEvent event;
  private BigDecimal profitAmount;
  private BigDecimal actualProfit;
  private Boolean renewWithProfit;
  private int newTenorAmount;
  private TenorType newTenorType;

  public long getId() {
    return id;
  }

  public Account setId(long id) {
    this.id = id;
    return this;
  }

  public Long getProductId() {
    return productId;
  }

  public Account setProductId(Long productId) {
    this.productId = productId;
    return this;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public Account setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

  public Long getOwnerBranchId() {
    return ownerBranchId;
  }

  public Account setOwnerBranchId(Long ownerBranchId) {
    this.ownerBranchId = ownerBranchId;
    return this;
  }

  public Long getBankId() {
    return bankId;
  }

  public Account setBankId(Long bankId) {
    this.bankId = bankId;
    return this;
  }

  public Long getBranchId() {
    return branchId;
  }

  public Account setBranchId(Long branchId) {
    this.branchId = branchId;
    return this;
  }

  public String getAccountTitle() {
    return accountTitle;
  }

  public Account setAccountTitle(String accountTitle) {
    this.accountTitle = accountTitle;
    return this;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public Account setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
    return this;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public Account setAmount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  public String getShadowAccountNumber() {
    return shadowAccountNumber;
  }

  public Account setShadowAccountNumber(String shadowAccountNumber) {
    this.shadowAccountNumber = shadowAccountNumber;
    return this;
  }

  public LocalDate getAccountOpenDate() {
    return accountOpenDate;
  }

  public Account setAccountOpenDate(LocalDate accountOpenDate) {
    this.accountOpenDate = accountOpenDate;
    return this;
  }

  public LocalDate getAccountClosingDate() {
    return accountClosingDate;
  }

  public Account setAccountClosingDate(LocalDate accountClosingDate) {
    this.accountClosingDate = accountClosingDate;
    return this;
  }

  public LocalDate getExpiryDate() {
    return expiryDate;
  }

  public Account setExpiryDate(LocalDate expiryDate) {
    this.expiryDate = expiryDate;
    return this;
  }

  public int getTenorAmount() {
    return tenorAmount;
  }

  public Account setTenorAmount(int tenorAmount) {
    this.tenorAmount = tenorAmount;
    return this;
  }

  public TenorType getTenorType() {
    return tenorType;
  }

  public Account setTenorType(TenorType tenorType) {
    this.tenorType = tenorType;
    return this;
  }

  public LocalDate getRenewalDate() {
    return renewalDate;
  }

  public Account setRenewalDate(LocalDate renewalDate) {
    this.renewalDate = renewalDate;
    return this;
  }

  public BigDecimal getExpectedProfitRate() {
    return expectedProfitRate;
  }

  public Account setExpectedProfitRate(BigDecimal expectedProfitRate) {
    this.expectedProfitRate = expectedProfitRate;
    return this;
  }

  public AccountStatus getStatus() {
    return status;
  }

  public Account setStatus(AccountStatus status) {
    this.status = status;
    return this;
  }

  public String getInstrument() {
    return instrument;
  }

  public Account setInstrument(String instrument) {
    this.instrument = instrument;
    return this;
  }

  public TransactionEvent getEvent() {
    return event;
  }

  public Account setEvent(TransactionEvent event) {
    this.event = event;
    return this;
  }

  public BigDecimal getProfitAmount() {
    return profitAmount;
  }

  public Account setProfitAmount(BigDecimal profitAmount) {
    this.profitAmount = profitAmount;
    return this;
  }

  public BigDecimal getActualProfit() {
    return actualProfit;
  }

  public Account setActualProfit(BigDecimal actualProfit) {
    this.actualProfit = actualProfit;
    return this;
  }

  public Boolean getRenewWithProfit() {
    return renewWithProfit;
  }

  public Account setRenewWithProfit(Boolean renewWithProfit) {
    this.renewWithProfit = renewWithProfit;
    return this;
  }

  public int getNewTenorAmount() {
    return newTenorAmount;
  }

  public Account setNewTenorAmount(int newTenorAmount) {
    this.newTenorAmount = newTenorAmount;
    return this;
  }

  public TenorType getNewTenorType() {
    return newTenorType;
  }

  public Account setNewTenorType(TenorType newTenorType) {
    this.newTenorType = newTenorType;
    return this;
  }
}
