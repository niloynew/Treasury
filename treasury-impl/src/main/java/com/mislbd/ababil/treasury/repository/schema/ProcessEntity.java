package com.mislbd.ababil.treasury.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.treasury.domain.AccountStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name = SchemaConstant.PROCESS_TABLE_NAME)
public class ProcessEntity extends BaseEntity {
  @SequenceGenerator(
      name = "PROCESSING_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.PROCESS_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Column(name = "ACC_NO")
  private String accountNumber;

  @Column(name = "NAME")
  private String name;

  @Column(name = "BALANCE")
  private BigDecimal balance;

  @Column(name = "TOTAL_PRODUCT")
  private BigDecimal totalProduct;

  @Column(name = "PROFIT_RATE")
  private BigDecimal profitRate;

  @Column(name = "PROVISION_AMOUNT")
  private BigDecimal provisionAmount;

  @Column(name = "ACTUAL_PROVISION")
  private BigDecimal actualProvision;

  @Column(name = "ACCOUNT_OLD_STATUS")
  private AccountStatus accountOldStatus;

  @Column(name = "ACCOUNT_NEW_STATUS")
  private AccountStatus accountNewStatus;

  @Column(name = "OLD_EXPIRY_DATE")
  private LocalDate oldExpiryDate;

  @Column(name = "NEW_EXPIRY_DATE")
  private LocalDate newExpiryDate;

  @Column(name = "LAST_PROVISION_DATE")
  private LocalDate lastProvisionDate;

  @Column(name = "GLOBAL_TXN_NO")
  private int globalTxnNo;

  @Column(name = "IS_VALID")
  private boolean valid;

  @Column(name = "IS_RENEW_WITH_PFT")
  private boolean renewWithProfit;

  @Column(name = "OLD_RENEWAL_DATE")
  private LocalDate oldRenewalDate;

  @Column(name = "NEW_RENEWAL_DATE")
  private LocalDate newRenewalDate;

  public long getId() {
    return id;
  }

  public ProcessEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public ProcessEntity setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
    return this;
  }

  public String getName() {
    return name;
  }

  public ProcessEntity setName(String name) {
    this.name = name;
    return this;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public ProcessEntity setBalance(BigDecimal balance) {
    this.balance = balance;
    return this;
  }

  public BigDecimal getTotalProduct() {
    return totalProduct;
  }

  public ProcessEntity setTotalProduct(BigDecimal totalProduct) {
    this.totalProduct = totalProduct;
    return this;
  }

  public BigDecimal getProfitRate() {
    return profitRate;
  }

  public ProcessEntity setProfitRate(BigDecimal profitRate) {
    this.profitRate = profitRate;
    return this;
  }

  public BigDecimal getProvisionAmount() {
    return provisionAmount;
  }

  public ProcessEntity setProvisionAmount(BigDecimal provisionAmount) {
    this.provisionAmount = provisionAmount;
    return this;
  }

  public BigDecimal getActualProvision() {
    return actualProvision;
  }

  public ProcessEntity setActualProvision(BigDecimal actualProvision) {
    this.actualProvision = actualProvision;
    return this;
  }

  public AccountStatus getAccountOldStatus() {
    return accountOldStatus;
  }

  public ProcessEntity setAccountOldStatus(AccountStatus accountOldStatus) {
    this.accountOldStatus = accountOldStatus;
    return this;
  }

  public AccountStatus getAccountNewStatus() {
    return accountNewStatus;
  }

  public ProcessEntity setAccountNewStatus(AccountStatus accountNewStatus) {
    this.accountNewStatus = accountNewStatus;
    return this;
  }

  public LocalDate getOldExpiryDate() {
    return oldExpiryDate;
  }

  public ProcessEntity setOldExpiryDate(LocalDate oldExpiryDate) {
    this.oldExpiryDate = oldExpiryDate;
    return this;
  }

  public LocalDate getNewExpiryDate() {
    return newExpiryDate;
  }

  public ProcessEntity setNewExpiryDate(LocalDate newExpiryDate) {
    this.newExpiryDate = newExpiryDate;
    return this;
  }

  public LocalDate getLastProvisionDate() {
    return lastProvisionDate;
  }

  public ProcessEntity setLastProvisionDate(LocalDate lastProvisionDate) {
    this.lastProvisionDate = lastProvisionDate;
    return this;
  }

  public int getGlobalTxnNo() {
    return globalTxnNo;
  }

  public ProcessEntity setGlobalTxnNo(int globalTxnNo) {
    this.globalTxnNo = globalTxnNo;
    return this;
  }

  public boolean isValid() {
    return valid;
  }

  public ProcessEntity setValid(boolean valid) {
    this.valid = valid;
    return this;
  }

  public boolean isRenewWithProfit() {
    return renewWithProfit;
  }

  public ProcessEntity setRenewWithProfit(boolean renewWithProfit) {
    this.renewWithProfit = renewWithProfit;
    return this;
  }

  public LocalDate getOldRenewalDate() {
    return oldRenewalDate;
  }

  public ProcessEntity setOldRenewalDate(LocalDate oldRenewalDate) {
    this.oldRenewalDate = oldRenewalDate;
    return this;
  }

  public LocalDate getNewRenewalDate() {
    return newRenewalDate;
  }

  public ProcessEntity setNewRenewalDate(LocalDate newRenewalDate) {
    this.newRenewalDate = newRenewalDate;
    return this;
  }
}
