package com.mislbd.ababil.treasury.repository.schema;


import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.treasury.domain.AccountStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = SchemaConstant.ACCOUNT_PROCESS_TABLE_NAME)
public class AccountProcessEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "ACCOUNT_PROCESS_ID_GEN")
    @SequenceGenerator(
            name = "ACCOUNT_PROCESS_ID_GEN",
            allocationSize = 1,
            sequenceName = SchemaConstant.ACCOUNT_PROCESS_SEQUENCE_NAME)
    @Column(name = "ID")
    private long id;

    @Column(name = "ACCNO")
    private String accountNumber;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BALANCE")
    private BigDecimal balance;

    @Column(name = "TOTAL_PRODUCT")
    private BigDecimal totalProduct;

    @Column(name = "PROFITRATE")
    private BigDecimal profitRate;

    @Column(name = "PROVISION_AMOUNT")
    private BigDecimal provisionAmount;

    @Column(name = "ACTUAL_PROVISION")
    private BigDecimal actualProvision;

    @Column(name = "ACCOUNT_OLD_STATUS")
    private AccountStatus oldStatus;

    @Column(name = "ACCOUNT_NEW_STATUS")
    private AccountStatus newStatus;

    @Column(name = "OLD_EXPIRYDATE")
    private LocalDate oldExpiryDate;

    @Column(name = "NEW_EXPIRYDATE")
    private LocalDate newExpiryDate;

    @Column(name = "OLD_RENEWAL_DATE")
    private LocalDate oldRenewalDate;

    @Column(name = "NEW_RENEWAL_DATE")
    private LocalDate newRenewalDate;

    @Column(name = "LASTPROVITIONDATE")
    private LocalDate lastProvisionDate;

    @Column(name = "GLOBALTXNNO")
    private Long globalTxnNumber;

    @Column(name = "IS_VALID")
    private Boolean valid;

    @Column(name = "IS_RENW_WITH_PFT")
    private Boolean renewWithProfit;

    @Column(name = "SETUP_USERID")
    private String createdUser;

    @Column(name = "SETUP_DATETIME")
    private LocalDate createTime;

    @Column(name = "VERIFY_USERID")
    private String verifyUser;

    @Column(name = "VERIFY_DATETIME")
    private LocalDate verifyTime;

    public long getId() {
        return id;
    }

    public AccountProcessEntity setId(long id) {
        this.id = id;
        return this;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public AccountProcessEntity setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public String getName() {
        return name;
    }

    public AccountProcessEntity setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public AccountProcessEntity setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public BigDecimal getTotalProduct() {
        return totalProduct;
    }

    public AccountProcessEntity setTotalProduct(BigDecimal totalProduct) {
        this.totalProduct = totalProduct;
        return this;
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }

    public AccountProcessEntity setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
        return this;
    }

    public BigDecimal getProvisionAmount() {
        return provisionAmount;
    }

    public AccountProcessEntity setProvisionAmount(BigDecimal provisionAmount) {
        this.provisionAmount = provisionAmount;
        return this;
    }

    public BigDecimal getActualProvision() {
        return actualProvision;
    }

    public AccountProcessEntity setActualProvision(BigDecimal actualProvision) {
        this.actualProvision = actualProvision;
        return this;
    }

    public AccountStatus getOldStatus() {
        return oldStatus;
    }

    public AccountProcessEntity setOldStatus(AccountStatus oldStatus) {
        this.oldStatus = oldStatus;
        return this;
    }

    public AccountStatus getNewStatus() {
        return newStatus;
    }

    public AccountProcessEntity setNewStatus(AccountStatus newStatus) {
        this.newStatus = newStatus;
        return this;
    }

    public LocalDate getOldExpiryDate() {
        return oldExpiryDate;
    }

    public AccountProcessEntity setOldExpiryDate(LocalDate oldExpiryDate) {
        this.oldExpiryDate = oldExpiryDate;
        return this;
    }

    public LocalDate getNewExpiryDate() {
        return newExpiryDate;
    }

    public AccountProcessEntity setNewExpiryDate(LocalDate newExpiryDate) {
        this.newExpiryDate = newExpiryDate;
        return this;
    }

    public LocalDate getOldRenewalDate() {
        return oldRenewalDate;
    }

    public AccountProcessEntity setOldRenewalDate(LocalDate oldRenewalDate) {
        this.oldRenewalDate = oldRenewalDate;
        return this;
    }

    public LocalDate getNewRenewalDate() {
        return newRenewalDate;
    }

    public AccountProcessEntity setNewRenewalDate(LocalDate newRenewalDate) {
        this.newRenewalDate = newRenewalDate;
        return this;
    }

    public LocalDate getLastProvisionDate() {
        return lastProvisionDate;
    }

    public AccountProcessEntity setLastProvisionDate(LocalDate lastProvisionDate) {
        this.lastProvisionDate = lastProvisionDate;
        return this;
    }

    public Long getGlobalTxnNumber() {
        return globalTxnNumber;
    }

    public AccountProcessEntity setGlobalTxnNumber(Long globalTxnNumber) {
        this.globalTxnNumber = globalTxnNumber;
        return this;
    }

    public Boolean getValid() {
        return valid;
    }

    public AccountProcessEntity setValid(Boolean valid) {
        this.valid = valid;
        return this;
    }

    public Boolean getRenewWithProfit() {
        return renewWithProfit;
    }

    public AccountProcessEntity setRenewWithProfit(Boolean renewWithProfit) {
        this.renewWithProfit = renewWithProfit;
        return this;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public AccountProcessEntity setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
        return this;
    }

    public LocalDate getCreateTime() {
        return createTime;
    }

    public AccountProcessEntity setCreateTime(LocalDate createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getVerifyUser() {
        return verifyUser;
    }

    public AccountProcessEntity setVerifyUser(String verifyUser) {
        this.verifyUser = verifyUser;
        return this;
    }

    public LocalDate getVerifyTime() {
        return verifyTime;
    }

    public AccountProcessEntity setVerifyTime(LocalDate verifyTime) {
        this.verifyTime = verifyTime;
        return this;
    }
}
