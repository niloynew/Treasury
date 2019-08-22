package com.mislbd.ababil.treasury.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.treasury.domain.DaysInYear;
import com.mislbd.ababil.treasury.domain.ProductStatus;
import com.mislbd.ababil.treasury.domain.ProfitCalculationMethod;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.PRODUCT_TABLE_NAME)
public class ProductEntity extends BaseEntity {

  //  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
  //  private List<AccountEntity> accountEntityList;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "PRODUCT_ID_GEN")
  @SequenceGenerator(
      name = "PRODUCT_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.PRODUCT_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Column(name = "PRODUCT_CODE", nullable = false, length = 3, unique = true)
  private String code;

  @Column(name = "PRODUCT_NAME", nullable = false, unique = true)
  private String name;

  @Column(name = "PROFIT_APPLICABLE")
  private boolean profitApplicable;

  @ManyToOne
  @JoinColumn(name = "PRODUCT_NATURE")
  private ProductNatureEntity productNature;

  @Column(name = "PROFIT_CALCULATION")
  @Enumerated(EnumType.STRING)
  private ProfitCalculationMethod profitCalculationMethod;

  @Column(name = "DAYS_IN_YEAR")
  @Enumerated(EnumType.STRING)
  private DaysInYear daysInYear;

  @Column(name = "STATUS")
  @Enumerated(EnumType.STRING)
  private ProductStatus status;

  @OneToMany(mappedBy = "product")
  private List<AccountEntity> accounts;

  public long getId() {
    return id;
  }

  public ProductEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getCode() {
    return code;
  }

  public ProductEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public ProductEntity setName(String name) {
    this.name = name;
    return this;
  }

  public boolean isProfitApplicable() {
    return profitApplicable;
  }

  public ProductEntity setProfitApplicable(boolean profitApplicable) {
    this.profitApplicable = profitApplicable;
    return this;
  }

  public ProductNatureEntity getProductNature() {
    return productNature;
  }

  public ProductEntity setProductNature(ProductNatureEntity productNature) {
    this.productNature = productNature;
    return this;
  }

  public ProfitCalculationMethod getProfitCalculationMethod() {
    return profitCalculationMethod;
  }

  public ProductEntity setProfitCalculationMethod(ProfitCalculationMethod profitCalculationMethod) {
    this.profitCalculationMethod = profitCalculationMethod;
    return this;
  }

  public DaysInYear getDaysInYear() {
    return daysInYear;
  }

  public ProductEntity setDaysInYear(DaysInYear daysInYear) {
    this.daysInYear = daysInYear;
    return this;
  }

  public ProductStatus getStatus() {
    return status;
  }

  public ProductEntity setStatus(ProductStatus status) {
    this.status = status;
    return this;
  }

  public List<AccountEntity> getAccounts() {
    return accounts;
  }

  public ProductEntity setAccounts(List<AccountEntity> accounts) {
    this.accounts = accounts;
    return this;
  }
}
