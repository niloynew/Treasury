package com.mislbd.ababil.treasury.domain;

import java.time.LocalDate;

public class TransactionRecord {

  private long id;
  private String txnCode;
  private LocalDate txnDate;
  private int amount;
  private boolean Valid;
  private String initiator;
  private String instrumentNo;
  private int txndId;
  private int txndCode;
  private int refAccId;
  private String narration;
  private int globalTxnno;
  private String initiatorModule;
  private String createdByAppUser;
  private String verifiedByAppUser;
  private String createdTerminal;
  private String verfiedTermial;
  private String createdSysUser;
  private String verifiedSysUser;
  private int originatingBrId;
  private int ownerBrId;
  private LocalDate txnValueDate;

  public long getId() {
    return id;
  }

  public TransactionRecord setId(long id) {
    this.id = id;
    return this;
  }

  public String getTxnCode() {
    return txnCode;
  }

  public TransactionRecord setTxnCode(String txnCode) {
    this.txnCode = txnCode;
    return this;
  }

  public LocalDate getTxnDate() {
    return txnDate;
  }

  public TransactionRecord setTxnDate(LocalDate txnDate) {
    this.txnDate = txnDate;
    return this;
  }

  public int getAmount() {
    return amount;
  }

  public TransactionRecord setAmount(int amount) {
    this.amount = amount;
    return this;
  }

  public boolean isValid() {
    return Valid;
  }

  public TransactionRecord setValid(boolean valid) {
    Valid = valid;
    return this;
  }

  public String getInitiator() {
    return initiator;
  }

  public TransactionRecord setInitiator(String initiator) {
    this.initiator = initiator;
    return this;
  }

  public String getInstrumentNo() {
    return instrumentNo;
  }

  public TransactionRecord setInstrumentNo(String instrumentNo) {
    this.instrumentNo = instrumentNo;
    return this;
  }

  public int getTxndId() {
    return txndId;
  }

  public TransactionRecord setTxndId(int txndId) {
    this.txndId = txndId;
    return this;
  }

  public int getTxndCode() {
    return txndCode;
  }

  public TransactionRecord setTxndCode(int txndCode) {
    this.txndCode = txndCode;
    return this;
  }

  public int getRefAccId() {
    return refAccId;
  }

  public TransactionRecord setRefAccId(int refAccId) {
    this.refAccId = refAccId;
    return this;
  }

  public String getNarration() {
    return narration;
  }

  public TransactionRecord setNarration(String narration) {
    this.narration = narration;
    return this;
  }

  public int getGlobalTxnno() {
    return globalTxnno;
  }

  public TransactionRecord setGlobalTxnno(int globalTxnno) {
    this.globalTxnno = globalTxnno;
    return this;
  }

  public String getInitiatorModule() {
    return initiatorModule;
  }

  public TransactionRecord setInitiatorModule(String initiatorModule) {
    this.initiatorModule = initiatorModule;
    return this;
  }

  public String getCreatedByAppUser() {
    return createdByAppUser;
  }

  public TransactionRecord setCreatedByAppUser(String createdByAppUser) {
    this.createdByAppUser = createdByAppUser;
    return this;
  }

  public String getVerifiedByAppUser() {
    return verifiedByAppUser;
  }

  public TransactionRecord setVerifiedByAppUser(String verifiedByAppUser) {
    this.verifiedByAppUser = verifiedByAppUser;
    return this;
  }

  public String getCreatedTerminal() {
    return createdTerminal;
  }

  public TransactionRecord setCreatedTerminal(String createdTerminal) {
    this.createdTerminal = createdTerminal;
    return this;
  }

  public String getVerfiedTermial() {
    return verfiedTermial;
  }

  public TransactionRecord setVerfiedTermial(String verfiedTermial) {
    this.verfiedTermial = verfiedTermial;
    return this;
  }

  public String getCreatedSysUser() {
    return createdSysUser;
  }

  public TransactionRecord setCreatedSysUser(String createdSysUser) {
    this.createdSysUser = createdSysUser;
    return this;
  }

  public String getVerifiedSysUser() {
    return verifiedSysUser;
  }

  public TransactionRecord setVerifiedSysUser(String verifiedSysUser) {
    this.verifiedSysUser = verifiedSysUser;
    return this;
  }

  public int getOriginatingBrId() {
    return originatingBrId;
  }

  public TransactionRecord setOriginatingBrId(int originatingBrId) {
    this.originatingBrId = originatingBrId;
    return this;
  }

  public int getOwnerBrId() {
    return ownerBrId;
  }

  public TransactionRecord setOwnerBrId(int ownerBrId) {
    this.ownerBrId = ownerBrId;
    return this;
  }

  public LocalDate getTxnValueDate() {
    return txnValueDate;
  }

  public TransactionRecord setTxnValueDate(LocalDate txnValueDate) {
    this.txnValueDate = txnValueDate;
    return this;
  }
}
