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
  private String nostroAccountNumber;
  private BigDecimal balance;
  private BigDecimal newBalance;
  private BigDecimal amount;
  private BigDecimal productAmount;
  private String accountNumber;
  private LocalDate accountOpenDate;
  private LocalDate accountClosingDate;
  private LocalDate expiryDate;
  private int tenureAmount;
  private TenureType tenureType;
  private LocalDate renewalDate;
  private LocalDate newRenewalDate;
  private BigDecimal expectedProfitRate;
  private AccountStatus status;
  private AccountStatus newStatus;
  private String instrument;
  private TransactionEvent event;
  private BigDecimal profitAmount;
  private BigDecimal actualProfit;
  private Boolean renewWithProfit;
  private int newTenureAmount;
  private TenureType newTenureType;
  private LocalDate valueDate;
  private BigDecimal newProfitRate;
  private LocalDate newExpiryDate;
  private Long globalTxnNumber;
  private LocalDate lastProvisionDate;

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

  public String getNostroAccountNumber() {
    return nostroAccountNumber;
  }

  public Account setNostroAccountNumber(String nostroAccountNumber) {
    this.nostroAccountNumber = nostroAccountNumber;
    return this;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public Account setBalance(BigDecimal balance) {
    this.balance = balance;
    return this;
  }

  public BigDecimal getNewBalance() {
    return newBalance;
  }

  public Account setNewBalance(BigDecimal newBalance) {
    this.newBalance = newBalance;
    return this;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public Account setAmount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  public BigDecimal getProductAmount() {
    return productAmount;
  }

  public Account setProductAmount(BigDecimal productAmount) {
    this.productAmount = productAmount;
    return this;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public Account setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
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

  public int getTenureAmount() {
    return tenureAmount;
  }

  public Account setTenureAmount(int tenureAmount) {
    this.tenureAmount = tenureAmount;
    return this;
  }

  public TenureType getTenureType() {
    return tenureType;
  }

  public Account setTenureType(TenureType tenureType) {
    this.tenureType = tenureType;
    return this;
  }

  public LocalDate getRenewalDate() {
    return renewalDate;
  }

  public Account setRenewalDate(LocalDate renewalDate) {
    this.renewalDate = renewalDate;
    return this;
  }

  public LocalDate getNewRenewalDate() {
    return newRenewalDate;
  }

  public Account setNewRenewalDate(LocalDate newRenewalDate) {
    this.newRenewalDate = newRenewalDate;
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

  public AccountStatus getNewStatus() {
    return newStatus;
  }

  public Account setNewStatus(AccountStatus newStatus) {
    this.newStatus = newStatus;
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

  public int getNewTenureAmount() {
    return newTenureAmount;
  }

  public Account setNewTenureAmount(int newTenureAmount) {
    this.newTenureAmount = newTenureAmount;
    return this;
  }

  public TenureType getNewTenureType() {
    return newTenureType;
  }

  public Account setNewTenureType(TenureType newTenureType) {
    this.newTenureType = newTenureType;
    return this;
  }

  public LocalDate getValueDate() {
    return valueDate;
  }

  public Account setValueDate(LocalDate valueDate) {
    this.valueDate = valueDate;
    return this;
  }

  public BigDecimal getNewProfitRate() {
    return newProfitRate;
  }

  public Account setNewProfitRate(BigDecimal newProfitRate) {
    this.newProfitRate = newProfitRate;
    return this;
  }

  public LocalDate getNewExpiryDate() {
    return newExpiryDate;
  }

  public Account setNewExpiryDate(LocalDate newExpiryDate) {
    this.newExpiryDate = newExpiryDate;
    return this;
  }

  public Long getGlobalTxnNumber() {
    return globalTxnNumber;
  }

  public Account setGlobalTxnNumber(Long globalTxnNumber) {
    this.globalTxnNumber = globalTxnNumber;
    return this;
  }

  public LocalDate getLastProvisionDate() {
    return lastProvisionDate;
  }

  public Account setLastProvisionDate(LocalDate lastProvisionDate) {
    this.lastProvisionDate = lastProvisionDate;
    return this;
  }
}
