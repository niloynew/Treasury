package com.mislbd.ababil.treasury.repository.schema;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name = SchemaConstant.TRANSACTION_RECORD_TABLE_NAME)
public class TransactionRecordEntity {

  @SequenceGenerator(
      name = "TRANSACTION_RECORD_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.TRANSACTION_RECORD_SEQUENCE_NAME)
  @Column(name = "TRID")
  private long id;

  @Column(name = "TRCODE")
  private String txnCode;

  @Column(name = "TRDATE")
  private LocalDate txnDate;

  @Column(name = "TRAMOUNT")
  private int amount;

  @Column(name = "TRISVALID")
  private boolean Valid;

  @Column(name = "TRINITIATOR")
  private String initiator;

  @Column(name = "TRINSTRUMENTNO")
  private String instrumentNo;

  @Column(name = "TRTRDID")
  private int txndId;

  @Column(name = "TRTRDCODE")
  private int txndCode;

  @Column(name = "TRREFACCID")
  private int refAccId;

  @Column(name = "TRNARRATION")
  private String narration;

  @Column(name = "TRGLOBALTXNNO")
  private int globalTxnno;

  @Column(name = "TRINITIATORMODULE")
  private String initiatorModule;

  @Column(name = "TRCREATEDBYAPPUSER")
  private String createdByAppUser;

  @Column(name = "TRVERIFIEDBYAPPUSER")
  private String verifiedByAppUser;

  @Column(name = "TRCREATEDTERMINAL")
  private String createdTerminal;

  @Column(name = "TRVERIFIEDTERMINAL")
  private String verfiedTermial;

  @Column(name = "TRCRATEDSYSUSER")
  private String createdSysUser;

  @Column(name = "TRVERIFIEDSYSUSER")
  private String verifiedSysUser;

  @Column(name = "TRCRETETIME")
  private LocalTime createTime;

  @Column(name = "TRVERIFYTIME")
  private LocalTime verifyTime;

  @Column(name = "TRORIGINATINGBRID")
  private int originatingBrId;

  @Column(name = "TROWNERBRID")
  private int ownerBrId;

  @Column(name = "TRVALUEDATE")
  private LocalDate txnValueDate;

  public long getId() {
    return id;
  }

  public TransactionRecordEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getTxnCode() {
    return txnCode;
  }

  public TransactionRecordEntity setTxnCode(String txnCode) {
    this.txnCode = txnCode;
    return this;
  }

  public LocalDate getTxnDate() {
    return txnDate;
  }

  public TransactionRecordEntity setTxnDate(LocalDate txnDate) {
    this.txnDate = txnDate;
    return this;
  }

  public int getAmount() {
    return amount;
  }

  public TransactionRecordEntity setAmount(int amount) {
    this.amount = amount;
    return this;
  }

  public boolean isValid() {
    return Valid;
  }

  public TransactionRecordEntity setValid(boolean valid) {
    Valid = valid;
    return this;
  }

  public String getInitiator() {
    return initiator;
  }

  public TransactionRecordEntity setInitiator(String initiator) {
    this.initiator = initiator;
    return this;
  }

  public String getInstrumentNo() {
    return instrumentNo;
  }

  public TransactionRecordEntity setInstrumentNo(String instrumentNo) {
    this.instrumentNo = instrumentNo;
    return this;
  }

  public int getTxndId() {
    return txndId;
  }

  public TransactionRecordEntity setTxndId(int txndId) {
    this.txndId = txndId;
    return this;
  }

  public int getTxndCode() {
    return txndCode;
  }

  public TransactionRecordEntity setTxndCode(int txndCode) {
    this.txndCode = txndCode;
    return this;
  }

  public int getRefAccId() {
    return refAccId;
  }

  public TransactionRecordEntity setRefAccId(int refAccId) {
    this.refAccId = refAccId;
    return this;
  }

  public String getNarration() {
    return narration;
  }

  public TransactionRecordEntity setNarration(String narration) {
    this.narration = narration;
    return this;
  }

  public int getGlobalTxnno() {
    return globalTxnno;
  }

  public TransactionRecordEntity setGlobalTxnno(int globalTxnno) {
    this.globalTxnno = globalTxnno;
    return this;
  }

  public String getInitiatorModule() {
    return initiatorModule;
  }

  public TransactionRecordEntity setInitiatorModule(String initiatorModule) {
    this.initiatorModule = initiatorModule;
    return this;
  }

  public String getCreatedByAppUser() {
    return createdByAppUser;
  }

  public TransactionRecordEntity setCreatedByAppUser(String createdByAppUser) {
    this.createdByAppUser = createdByAppUser;
    return this;
  }

  public String getVerifiedByAppUser() {
    return verifiedByAppUser;
  }

  public TransactionRecordEntity setVerifiedByAppUser(String verifiedByAppUser) {
    this.verifiedByAppUser = verifiedByAppUser;
    return this;
  }

  public String getCreatedTerminal() {
    return createdTerminal;
  }

  public TransactionRecordEntity setCreatedTerminal(String createdTerminal) {
    this.createdTerminal = createdTerminal;
    return this;
  }

  public String getVerfiedTermial() {
    return verfiedTermial;
  }

  public TransactionRecordEntity setVerfiedTermial(String verfiedTermial) {
    this.verfiedTermial = verfiedTermial;
    return this;
  }

  public String getCreatedSysUser() {
    return createdSysUser;
  }

  public TransactionRecordEntity setCreatedSysUser(String createdSysUser) {
    this.createdSysUser = createdSysUser;
    return this;
  }

  public String getVerifiedSysUser() {
    return verifiedSysUser;
  }

  public TransactionRecordEntity setVerifiedSysUser(String verifiedSysUser) {
    this.verifiedSysUser = verifiedSysUser;
    return this;
  }

  public LocalTime getCreateTime() {
    return createTime;
  }

  public TransactionRecordEntity setCreateTime(LocalTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public LocalTime getVerifyTime() {
    return verifyTime;
  }

  public TransactionRecordEntity setVerifyTime(LocalTime verifyTime) {
    this.verifyTime = verifyTime;
    return this;
  }

  public int getOriginatingBrId() {
    return originatingBrId;
  }

  public TransactionRecordEntity setOriginatingBrId(int originatingBrId) {
    this.originatingBrId = originatingBrId;
    return this;
  }

  public int getOwnerBrId() {
    return ownerBrId;
  }

  public TransactionRecordEntity setOwnerBrId(int ownerBrId) {
    this.ownerBrId = ownerBrId;
    return this;
  }

  public LocalDate getTxnValueDate() {
    return txnValueDate;
  }

  public TransactionRecordEntity setTxnValueDate(LocalDate txnValueDate) {
    this.txnValueDate = txnValueDate;
    return this;
  }
}
