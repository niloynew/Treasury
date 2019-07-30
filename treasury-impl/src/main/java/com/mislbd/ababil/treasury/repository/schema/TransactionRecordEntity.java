package com.mislbd.ababil.treasury.repository.schema;

import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.TRANSACTION_RECORD_TABLE_NAME)
public class TransactionRecordEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "TRANSACTION_RECORD_ID_GEN")
  @SequenceGenerator(
      name = "TRANSACTION_RECORD_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.TRANSACTION_RECORD_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Column(name = "CODE")
  private String txnCode;

  @Column(name = "TXN_DATE")
  private LocalDate txnDate;

  @Column(name = "AMOUNT")
  private int amount;

  @Column(name = "INIT_BRANCH")
  private String initiatorBranch;

  @Column(name = "INSTRUMENT_NO")
  private String instrumentNo;

  @Column(name = "TXN_DEF_ID")
  private int txnDefId;

  @Column(name = "REF_ACC_ID")
  private int refAccId;

  @Column(name = "NARRATION")
  private String narration;

  @Column(name = "TRGLOBALTXNNO")
  private int globalTxnNo;

  @Column(name = "CREATED_USER")
  private String createdUser;

  @Column(name = "CREATED_TERMINAL")
  private String createdTerminal;

  @Column(name = "CRETE_TIME")
  private LocalDate createTime;

  @Column(name = "VERIFIED_USER")
  private String verifiedUser;

  @Column(name = "VERIFIED_TERMINAL")
  private String verifiedTermial;

  @Column(name = "VERIFY_TIME")
  private LocalDate verifyTime;

  @Column(name = "ORIGINATING_BR_ID")
  private int originatingBrId;

  @Column(name = "OWNER_BR_ID")
  private int ownerBrId;

  @Column(name = "VALUE_DATE")
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

  public String getInitiatorBranch() {
    return initiatorBranch;
  }

  public TransactionRecordEntity setInitiatorBranch(String initiatorBranch) {
    this.initiatorBranch = initiatorBranch;
    return this;
  }

  public String getInstrumentNo() {
    return instrumentNo;
  }

  public TransactionRecordEntity setInstrumentNo(String instrumentNo) {
    this.instrumentNo = instrumentNo;
    return this;
  }

  public int getTxnDefId() {
    return txnDefId;
  }

  public TransactionRecordEntity setTxnDefId(int txnDefId) {
    this.txnDefId = txnDefId;
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

  public int getGlobalTxnNo() {
    return globalTxnNo;
  }

  public TransactionRecordEntity setGlobalTxnNo(int globalTxnNo) {
    this.globalTxnNo = globalTxnNo;
    return this;
  }

  public String getCreatedUser() {
    return createdUser;
  }

  public TransactionRecordEntity setCreatedUser(String createdUser) {
    this.createdUser = createdUser;
    return this;
  }

  public String getCreatedTerminal() {
    return createdTerminal;
  }

  public TransactionRecordEntity setCreatedTerminal(String createdTerminal) {
    this.createdTerminal = createdTerminal;
    return this;
  }

  public LocalDate getCreateTime() {
    return createTime;
  }

  public TransactionRecordEntity setCreateTime(LocalDate createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getVerifiedUser() {
    return verifiedUser;
  }

  public TransactionRecordEntity setVerifiedUser(String verifiedUser) {
    this.verifiedUser = verifiedUser;
    return this;
  }

  public String getVerifiedTermial() {
    return verifiedTermial;
  }

  public TransactionRecordEntity setVerifiedTermial(String verifiedTermial) {
    this.verifiedTermial = verifiedTermial;
    return this;
  }

  public LocalDate getVerifyTime() {
    return verifyTime;
  }

  public TransactionRecordEntity setVerifyTime(LocalDate verifyTime) {
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
