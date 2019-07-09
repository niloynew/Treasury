package com.mislbd.ababil.treasury.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.treasury.domain.AccountStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

@Table(name = SchemaConstant.ACCOUNT_TABLE_NAME)
public class AccountEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "ACCOUNT_ID_GEN")
  @SequenceGenerator(
      name = "ACCOUNT_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.ACCOUNT_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Column(name = "ACC_NO")
  private String accountNumber;

  @Column(name = "SHADOW_ACC_NO")
  private String shadowAccountNumber;

  @Column(name = "NAME")
  private String name;

  @Column(name = "BR_ID")
  private int branchId;

  @Column(name = "INSTRUMENT_NO")
  private String instrumentNumber;

  @Column(name = "OPEN_DATE")
  private LocalDate openDate;

  @Column(name = "CLOSING_DATE")
  private LocalDate closingDate;

  @Column(name = "EXPIRY_DATE")
  private LocalDate expiryDate;

  @Column(name = "RENEWAL_DATE")
  private LocalDate renewalDate;

  @Column(name = "LAST_PROVISION_DATE")
  private LocalDate lastProvisionDate;

  @Column(name = "ACCOUNT_STATUS")
  @Enumerated(EnumType.STRING)
  private AccountStatus status;

  @Column(name = "CURRENCY")
  private String currency;

  @Column(name = "BANK_CODE")
  private String bankCode;

  @Column(name = "BRANCH_CODE")
  private String branchCode;

  @Column(name = "PROFIT_RATE")
  private BigDecimal profitRate;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "PRODUCT_ID")
  private ProductEntity product;

  @Column(name = "OPENING_AMOUNT")
  private BigDecimal openingAmount;

  @Column(name = "BALANCE")
  private BigDecimal balance;

  @Column(name = "OLD_ACC_NO")
  private String oldAccountNumber;

  @Column(name = "ACCOUNT_TYPE")
  private String accountType;

  @Column(name = "PRINCIPAL_DEBIT")
  private BigDecimal principalDebit;

  @Column(name = "PRINCIPAL_CREDIT")
  private BigDecimal principalCredit;

  @Column(name = "PROFIT_DEBIT")
  private BigDecimal profitDebit;

  @Column(name = "PROFIT_CREDIT")
  private BigDecimal profitCredit;

  public long getId() {
    return id;
  }

  public AccountEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public AccountEntity setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
    return this;
  }

  public String getShadowAccountNumber() {
    return shadowAccountNumber;
  }

  public AccountEntity setShadowAccountNumber(String shadowAccountNumber) {
    this.shadowAccountNumber = shadowAccountNumber;
    return this;
  }

  public String getName() {
    return name;
  }

  public AccountEntity setName(String name) {
    this.name = name;
    return this;
  }

  public int getBranchId() {
    return branchId;
  }

  public AccountEntity setBranchId(int branchId) {
    this.branchId = branchId;
    return this;
  }

  public String getInstrumentNumber() {
    return instrumentNumber;
  }

  public AccountEntity setInstrumentNumber(String instrumentNumber) {
    this.instrumentNumber = instrumentNumber;
    return this;
  }

  public LocalDate getOpenDate() {
    return openDate;
  }

  public AccountEntity setOpenDate(LocalDate openDate) {
    this.openDate = openDate;
    return this;
  }

  public LocalDate getClosingDate() {
    return closingDate;
  }

  public AccountEntity setClosingDate(LocalDate closingDate) {
    this.closingDate = closingDate;
    return this;
  }

  public LocalDate getExpiryDate() {
    return expiryDate;
  }

  public AccountEntity setExpiryDate(LocalDate expiryDate) {
    this.expiryDate = expiryDate;
    return this;
  }

  public LocalDate getRenewalDate() {
    return renewalDate;
  }

  public AccountEntity setRenewalDate(LocalDate renewalDate) {
    this.renewalDate = renewalDate;
    return this;
  }

  public LocalDate getLastProvisionDate() {
    return lastProvisionDate;
  }

  public AccountEntity setLastProvisionDate(LocalDate lastProvisionDate) {
    this.lastProvisionDate = lastProvisionDate;
    return this;
  }

  public AccountStatus getStatus() {
    return status;
  }

  public AccountEntity setStatus(AccountStatus status) {
    this.status = status;
    return this;
  }

  public String getCurrency() {
    return currency;
  }

  public AccountEntity setCurrency(String currency) {
    this.currency = currency;
    return this;
  }

  public String getBankCode() {
    return bankCode;
  }

  public AccountEntity setBankCode(String bankCode) {
    this.bankCode = bankCode;
    return this;
  }

  public String getBranchCode() {
    return branchCode;
  }

  public AccountEntity setBranchCode(String branchCode) {
    this.branchCode = branchCode;
    return this;
  }

  public BigDecimal getProfitRate() {
    return profitRate;
  }

  public AccountEntity setProfitRate(BigDecimal profitRate) {
    this.profitRate = profitRate;
    return this;
  }

  public ProductEntity getProduct() {
    return product;
  }

  public AccountEntity setProduct(ProductEntity product) {
    this.product = product;
    return this;
  }

  public BigDecimal getOpeningAmount() {
    return openingAmount;
  }

  public AccountEntity setOpeningAmount(BigDecimal openingAmount) {
    this.openingAmount = openingAmount;
    return this;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public AccountEntity setBalance(BigDecimal balance) {
    this.balance = balance;
    return this;
  }

  public String getOldAccountNumber() {
    return oldAccountNumber;
  }

  public AccountEntity setOldAccountNumber(String oldAccountNumber) {
    this.oldAccountNumber = oldAccountNumber;
    return this;
  }

  public String getAccountType() {
    return accountType;
  }

  public AccountEntity setAccountType(String accountType) {
    this.accountType = accountType;
    return this;
  }

  public BigDecimal getPrincipalDebit() {
    return principalDebit;
  }

  public AccountEntity setPrincipalDebit(BigDecimal principalDebit) {
    this.principalDebit = principalDebit;
    return this;
  }

  public BigDecimal getPrincipalCredit() {
    return principalCredit;
  }

  public AccountEntity setPrincipalCredit(BigDecimal principalCredit) {
    this.principalCredit = principalCredit;
    return this;
  }

  public BigDecimal getProfitDebit() {
    return profitDebit;
  }

  public AccountEntity setProfitDebit(BigDecimal profitDebit) {
    this.profitDebit = profitDebit;
    return this;
  }

  public BigDecimal getProfitCredit() {
    return profitCredit;
  }

  public AccountEntity setProfitCredit(BigDecimal profitCredit) {
    this.profitCredit = profitCredit;
    return this;
  }
}
