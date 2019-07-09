package com.mislbd.ababil.treasury.repository.schema;

import com.mislbd.ababil.asset.repository.schema.BaseEntity;
import com.mislbd.ababil.treasury.domain.ProductStatus;
import com.mislbd.ababil.treasury.domain.ProfitCalculationMethod;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Table(name = SchemaConstant.PRODUCT_TABLE_NAME)
public class ProductEntity extends BaseEntity {

    @OneToMany(mappedBy = "product")
    private List<AccountEntity> accountEntityList;

    @SequenceGenerator(
            name = "PRODUCT_ID_GEN",
            allocationSize = 1,
            sequenceName = SchemaConstant.PRODUCT_SEQUENCE_NAME)
    @Column(name = "ID")
    private long id;

    @Column(name="PRODUCT_CODE")
    private String code;

    @Column(name="PRODUCT_NAME")
    private String name;

    @Column(name="PROFIT_APPLICABLE")
    private boolean profitApplicable;

    @Column(name="PRODUCT_NATURE")
    private int productNature;

    @Column(name="PROFIT_CALCULATION")
    @Enumerated(EnumType.STRING)
    private ProfitCalculationMethod profitCalculationMethod;

    @Column(name="rule360")
    private boolean rule360;

    @Column(name="STATUS")
    private ProductStatus status;

    public List<AccountEntity> getAccountEntityList() {
        return accountEntityList;
    }

    public ProductEntity setAccountEntityList(List<AccountEntity> accountEntityList) {
        this.accountEntityList = accountEntityList;
        return this;
    }

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

    public int getProductNature() {
        return productNature;
    }

    public ProductEntity setProductNature(int productNature) {
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

    public boolean isRule360() {
        return rule360;
    }

    public ProductEntity setRule360(boolean rule360) {
        this.rule360 = rule360;
        return this;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public ProductEntity setStatus(ProductStatus status) {
        this.status = status;
        return this;
    }
}
