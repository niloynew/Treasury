package com.mislbd.ababil.treasury.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.treasury.domain.AccountStatus;
import com.mislbd.ababil.treasury.domain.TenorType;
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

  @ManyToOne
  @JoinColumn(name = "PRODUCT_ID")
  private ProductEntity product;

  @Column(name = "CURRENCY_CODE")
  private String currencyCode;

  @Column(name = "BANK_ID")
  private Long bankId;

  @Column(name = "BRANCH_ID")
  private Long branchId;

  @Column(name = "ACCOUNT_TITLE")
  private String accountTitle;

  @Column(name = "ACCOUNT_NO")
  private String accountNumber;

  @Column(name = "AMOUNT")
  private BigDecimal amount;

  @Column(name = "SHADOW_ACC_NO")
  private String shadowAccountNumber;

  @Column(name = "ACCOUNT_OPEN_DATE")
  private LocalDate accountOpenDate;

  @Column(name = "EXPIRY_DATE")
  private LocalDate expiryDate;

  @Column(name = "TENOR_AMOUNT")
  private int tenorAmount;

  @Enumerated(EnumType.STRING)
  @Column(name = "TENOR_TYPE")
  private TenorType tenorType;

  @Column(name = "RENEWAL_DATE")
  private LocalDate renewalDate;

  @Column(name = "EXPECTED_PROFIT_RATE")
  private BigDecimal expectedProfitRate;

  @Column(name = "STATUS")
  private AccountStatus status;

  @Column(name = "MTDR_INSTRUMENT")
  private String instrument;

  @Column(name = "active")
  private boolean active;

  public long getId() {
    return id;
  }

  public AccountEntity setId(long id) {
    this.id = id;
    return this;
  }

  public ProductEntity getProduct() {
    return product;
  }

  public AccountEntity setProduct(ProductEntity product) {
    this.product = product;
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

  public String getAccountTitle() {
    return accountTitle;
  }

  public AccountEntity setAccountTitle(String accountTitle) {
    this.accountTitle = accountTitle;
    return this;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public AccountEntity setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
    return this;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public AccountEntity setAmount(BigDecimal amount) {
    this.amount = amount;
    return this;
  }

  public String getShadowAccountNumber() {
    return shadowAccountNumber;
  }

  public AccountEntity setShadowAccountNumber(String shadowAccountNumber) {
    this.shadowAccountNumber = shadowAccountNumber;
    return this;
  }

  public LocalDate getAccountOpenDate() {
    return accountOpenDate;
  }

  public AccountEntity setAccountOpenDate(LocalDate accountOpenDate) {
    this.accountOpenDate = accountOpenDate;
    return this;
  }

  public LocalDate getExpiryDate() {
    return expiryDate;
  }

  public AccountEntity setExpiryDate(LocalDate expiryDate) {
    this.expiryDate = expiryDate;
    return this;
  }

  public int getTenorAmount() {
    return tenorAmount;
  }

  public AccountEntity setTenorAmount(int tenorAmount) {
    this.tenorAmount = tenorAmount;
    return this;
  }

  public TenorType getTenorType() {
    return tenorType;
  }

  public AccountEntity setTenorType(TenorType tenorType) {
    this.tenorType = tenorType;
    return this;
  }

  public LocalDate getRenewalDate() {
    return renewalDate;
  }

  public AccountEntity setRenewalDate(LocalDate renewalDate) {
    this.renewalDate = renewalDate;
    return this;
  }

  public BigDecimal getExpectedProfitRate() {
    return expectedProfitRate;
  }

  public AccountEntity setExpectedProfitRate(BigDecimal expectedProfitRate) {
    this.expectedProfitRate = expectedProfitRate;
    return this;
  }

  public AccountStatus getStatus() {
    return status;
  }

  public AccountEntity setStatus(AccountStatus status) {
    this.status = status;
    return this;
  }

  public String getInstrument() {
    return instrument;
  }

  public AccountEntity setInstrument(String instrument) {
    this.instrument = instrument;
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
