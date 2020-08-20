package com.mislbd.ababil.treasury.repository.schema;

import com.mislbd.ababil.treasury.domain.ProfitType;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.MONTHEND_PRODUCT_TABLE_NAME)
public class MonthendProductInfoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "MONTHEND_PRODUCT_ID_GEN")
  @SequenceGenerator(
      name = "MONTHEND_PRODUCT_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.MONTHEND_PRODUCT_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Column(name = "ACCOUNT_NO")
  private String accNo;

  @Column(name = "START_DATE")
  private LocalDate startDate;

  @Column(name = "END_DATE")
  private LocalDate endDate;

  @Column(name = "INSERT_DATE")
  private LocalDate insertDate;

  @Column(name = "ACCOUNT_PRODUCT")
  private BigDecimal accProduct;

  @Column(name = "PROFIT_RATE_ACC_TYPE")
  private int profitRateAccType;

  @Column(name = "IS_PROVISION_POSTED", columnDefinition = "int default 0")
  private boolean provisionalPosted;

  @Column(name = "IS_ENHANCED_POSTED", columnDefinition = "int default 0")
  private boolean enhancedPosted;

  @Column(name = "GL_POSTED_DATE")
  private LocalDate glPostedDate;

  @Column(name = "IS_GL_POSTED", columnDefinition = "int default 0")
  private boolean glPosted;

  @Column(name = "PROVISION_RATE")
  private BigDecimal provisionRate;

  @Column(name = "IS_ACCOUNT_POSTED", columnDefinition = "int default 0")
  private boolean accPosted;

  @Enumerated(EnumType.STRING)
  @Column(name = "PROFIT_TYPE")
  private ProfitType profitType;

  @Column(name = "PROFIT_APP_DATE")
  private LocalDate profitAppDate;

  @Column(name = "PROFIT_EVENT")
  private String profitEvent;

  @Column(name = "PROFIT_POST_EVENT")
  private String profitPostingEvent;

  @Column(name = "PROVISION_AMOUNT")
  private BigDecimal provisionAmount;

  @Column(name = "GLOBALTXNNUMBER")
  private Long globalTxnNumber;

  @Column(name = "BRANCH_ID")
  private long branchid;

  @Column(name = "NARRATION")
  private String narration;

  public long getId() {
    return id;
  }

  public MonthendProductInfoEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getAccNo() {
    return accNo;
  }

  public MonthendProductInfoEntity setAccNo(String accNo) {
    this.accNo = accNo;
    return this;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public MonthendProductInfoEntity setStartDate(LocalDate startDate) {
    this.startDate = startDate;
    return this;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public MonthendProductInfoEntity setEndDate(LocalDate endDate) {
    this.endDate = endDate;
    return this;
  }

  public LocalDate getInsertDate() {
    return insertDate;
  }

  public MonthendProductInfoEntity setInsertDate(LocalDate insertDate) {
    this.insertDate = insertDate;
    return this;
  }

  public BigDecimal getAccProduct() {
    return accProduct;
  }

  public MonthendProductInfoEntity setAccProduct(BigDecimal accProduct) {
    this.accProduct = accProduct;
    return this;
  }

  public int getProfitRateAccType() {
    return profitRateAccType;
  }

  public MonthendProductInfoEntity setProfitRateAccType(int profitRateAccType) {
    this.profitRateAccType = profitRateAccType;
    return this;
  }

  public boolean isProvisionalPosted() {
    return provisionalPosted;
  }

  public MonthendProductInfoEntity setProvisionalPosted(boolean provisionalPosted) {
    this.provisionalPosted = provisionalPosted;
    return this;
  }

  public boolean isEnhancedPosted() {
    return enhancedPosted;
  }

  public MonthendProductInfoEntity setEnhancedPosted(boolean enhancedPosted) {
    this.enhancedPosted = enhancedPosted;
    return this;
  }

  public LocalDate getGlPostedDate() {
    return glPostedDate;
  }

  public MonthendProductInfoEntity setGlPostedDate(LocalDate glPostedDate) {
    this.glPostedDate = glPostedDate;
    return this;
  }

  public boolean isGlPosted() {
    return glPosted;
  }

  public MonthendProductInfoEntity setGlPosted(boolean glPosted) {
    this.glPosted = glPosted;
    return this;
  }

  public BigDecimal getProvisionRate() {
    return provisionRate;
  }

  public MonthendProductInfoEntity setProvisionRate(BigDecimal provisionRate) {
    this.provisionRate = provisionRate;
    return this;
  }

  public boolean isAccPosted() {
    return accPosted;
  }

  public MonthendProductInfoEntity setAccPosted(boolean accPosted) {
    this.accPosted = accPosted;
    return this;
  }

  public ProfitType getProfitType() {
    return profitType;
  }

  public MonthendProductInfoEntity setProfitType(ProfitType profitType) {
    this.profitType = profitType;
    return this;
  }

  public LocalDate getProfitAppDate() {
    return profitAppDate;
  }

  public MonthendProductInfoEntity setProfitAppDate(LocalDate profitAppDate) {
    this.profitAppDate = profitAppDate;
    return this;
  }

  public String getProfitEvent() {
    return profitEvent;
  }

  public MonthendProductInfoEntity setProfitEvent(String profitEvent) {
    this.profitEvent = profitEvent;
    return this;
  }

  public String getProfitPostingEvent() {
    return profitPostingEvent;
  }

  public MonthendProductInfoEntity setProfitPostingEvent(String profitPostingEvent) {
    this.profitPostingEvent = profitPostingEvent;
    return this;
  }

  public BigDecimal getProvisionAmount() {
    return provisionAmount;
  }

  public MonthendProductInfoEntity setProvisionAmount(BigDecimal provisionAmount) {
    this.provisionAmount = provisionAmount;
    return this;
  }

  public Long getGlobalTxnNumber() {
    return globalTxnNumber;
  }

  public MonthendProductInfoEntity setGlobalTxnNumber(Long globalTxnNumber) {
    this.globalTxnNumber = globalTxnNumber;
    return this;
  }

  public long getBranchid() {
    return branchid;
  }

  public MonthendProductInfoEntity setBranchid(long branchid) {
    this.branchid = branchid;
    return this;
  }

  public String getNarration() {
    return narration;
  }

  public MonthendProductInfoEntity setNarration(String narration) {
    this.narration = narration;
    return this;
  }
}
