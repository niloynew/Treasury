package com.mislbd.ababil.treasury.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.treasury.domain.AccountStatus;
import com.mislbd.ababil.treasury.domain.TenureType;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
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

  @Column(name = "NOSTRO_ACCNO")
  private String nostroAccountNumber;

  @Column(name = "ACCNO")
  private String accountNumber;

  @Column(name = "NAME")
  private String accountTitle;

  @Column(name = "BRID")
  private Long ownerBranchId;

  @Column(name = "INSTRUMENTNO")
  private String instrumentNumber;

  @Column(name = "OPENDATE")
  private LocalDate openDate;

  @Column(name = "CLOSINGDATE")
  private LocalDate closingDate;

  @Column(name = "EXPIRYDATE")
  private LocalDate expiryDate;

  @Column(name = "RENEWALDATE")
  private LocalDate renewalDate;

  @Column(name = "LASTPROVITIONDATE")
  private LocalDate lastProvisionDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "ACCOUNT_STATUS")
  private AccountStatus status;

  @Column(name = "CURRENCY")
  private String currencyCode;

  @Column(name = "BANK_CODE")
  private Long bankId;

  @Column(name = "BRANCH_CODE")
  private Long branchId;

  @Column(name = "PROFITRATE")
  private BigDecimal profitRate;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "PRODID")
  private ProductEntity product;

  @Column(name = "OPENINGAMOUNT")
  private BigDecimal amount;

  @Column(name = "BALANCE")
  private BigDecimal balance;

  @Column(name = "TENOR_AMOUNT")
  private int tenureAmount;

  @Enumerated(EnumType.STRING)
  @Column(name = "TENOR_TYPE")
  private TenureType tenureType;

  @Column(name = "PRINCIPAL_DEBIT")
  private BigDecimal principalDebit;

  @Column(name = "PRINCIPAL_CREDIT")
  private BigDecimal principalCredit;

  @Column(name = "PROFIT_DEBIT")
  private BigDecimal profitDebit;

  @Column(name = "PROFIT_CREDIT")
  private BigDecimal profitCredit;

  @Column(name = "active")
  private boolean active;

  public long getId() {
    return id;
  }

  public AccountEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getNostroAccountNumber() {
    return nostroAccountNumber;
  }

  public AccountEntity setNostroAccountNumber(String nostroAccountNumber) {
    this.nostroAccountNumber = nostroAccountNumber;
    return this;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public AccountEntity setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
    return this;
  }

  public String getAccountTitle() {
    return accountTitle;
  }

  public AccountEntity setAccountTitle(String accountTitle) {
    this.accountTitle = accountTitle;
    return this;
  }

  public Long getOwnerBranchId() {
    return ownerBranchId;
  }

  public AccountEntity setOwnerBranchId(Long ownerBranchId) {
    this.ownerBranchId = ownerBranchId;
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

  public String getCurrencyCode() {
    return currencyCode;
  }

  public AccountEntity setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
    return this;
  }

  public Long getBankId() {
    return bankId;
  }

  public AccountEntity setBankId(Long bankId) {
    this.bankId = bankId;
    return this;
  }

  public Long getBranchId() {
    return branchId;
  }

  public AccountEntity setBranchId(Long branchId) {
    this.branchId = branchId;
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

  public BigDecimal getAmount() {
    return amount;
  }

  public AccountEntity setAmount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  public BigDecimal getBalance() {
    return balance;
  }

  public AccountEntity setBalance(BigDecimal balance) {
    this.balance = balance;
    return this;
  }

  public int getTenureAmount() {
    return tenureAmount;
  }

  public AccountEntity setTenureAmount(int tenureAmount) {
    this.tenureAmount = tenureAmount;
    return this;
  }

  public TenureType getTenureType() {
    return tenureType;
  }

  public AccountEntity setTenureType(TenureType tenureType) {
    this.tenureType = tenureType;
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

  public boolean isActive() {
    return active;
  }

  public AccountEntity setActive(boolean active) {
    this.active = active;
    return this;
  }
}
