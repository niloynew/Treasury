// package com.mislbd.ababil.treasury.repository.schema;
//
// import com.mislbd.ababil.treasury.domain.TransactionType;
// import javax.persistence.*;
//
// @Entity
// @Table(name = SchemaConstant.TRANSACTION_CODE_TABLE_NAME)
// public class TransactionCodeEntity {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.AUTO, generator = "TRANSACTION_CODE_ID_GEN")
//  @SequenceGenerator(
//      name = "TRANSACTION_CODE_ID_GEN",
//      allocationSize = 1,
//      sequenceName = SchemaConstant.TRANSACTION_CODE_SEQUENCE_NAME)
//  @Column(name = "ID")
//  private long id;
//
//  @Column(name = "CBS_CODE")
//  private String cbsCode;
//
//  @Column(name = "CODE")
//  private String code;
//
//  @Column(name = "DESCRIPTION")
//  private String description;
//
//  @Enumerated(EnumType.STRING)
//  @Column(name = "TRANSACTION_TYPE")
//  private TransactionType transactionType;
//
//  public long getId() {
//    return id;
//  }
//
//  public TransactionCodeEntity setId(long id) {
//    this.id = id;
//    return this;
//  }
//
//  public String getCbsCode() {
//    return cbsCode;
//  }
//
//  public TransactionCodeEntity setCbsCode(String cbsCode) {
//    this.cbsCode = cbsCode;
//    return this;
//  }
//
//  public String getCode() {
//    return code;
//  }
//
//  public TransactionCodeEntity setCode(String code) {
//    this.code = code;
//    return this;
//  }
//
//  public String getDescription() {
//    return description;
//  }
//
//  public TransactionCodeEntity setDescription(String description) {
//    this.description = description;
//    return this;
//  }
//
//  public TransactionType getTransactionType() {
//    return transactionType;
//  }
//
//  public TransactionCodeEntity setTransactionType(TransactionType transactionType) {
//    this.transactionType = transactionType;
//    return this;
//  }
// }
