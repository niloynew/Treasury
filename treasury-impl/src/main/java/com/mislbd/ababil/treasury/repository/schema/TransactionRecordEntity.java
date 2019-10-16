package com.mislbd.ababil.treasury.repository.schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = SchemaConstant.TRANSACTION_RECORD_TABLE_NAME)
public class TransactionRecordEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "TRANSACTION_RECORD_ID_GEN")
  @SequenceGenerator(
      name = "TRANSACTION_RECORD_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.TRANSACTION_RECORD_SEQUENCE_NAME)
  @Column(name = "TRID")
  private long id;

  @Column(name = "TRISVALID")
  private boolean valid;

  @Column(name = "CODE")
  private String txnCode;

  @Column(name = "TRDATE")
  private LocalDate txnDate;

  @Column(name = "TRAMOUNT")
  private BigDecimal amount;

  @Column(name = "TRORIGINATINGBRID")
  private String trOiginatingBrId;

  @Column(name = "TRINSTRUMENTNO")
  private String instrumentNo;

  @Column(name = "TRTRDID")
  private Long txnDefId;

  @Column(name = "TRREFACCID")
  private int refAccId;

  @Column(name = "TRNARRATION")
  private String narration;

  @Column(name = "TRGLOBALTXNNO")
  private Long globalTxnNo;

  @Column(name = "TRINITIATOR")
  private String initiator;

  @Column(name = "TRINITIATORMODULE")
  private String initiatorModule;

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
}
