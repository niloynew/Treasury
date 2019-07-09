package com.mislbd.ababil.treasury.repository.schema;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;
import java.util.List;

public class ProductEntity {

    @OneToMany(mappedBy = "product")
    private List<AccountEntity> accountEntityList;

    @SequenceGenerator(
            name = "ACCOUNT_ID_GEN",
            allocationSize = 1,
            sequenceName = SchemaConstant.ACCOUNT_SEQUENCE_NAME)
    @Column(name = "ID")
    private long id;

    @Column(name="PRODUCT_CODE")
    private String productCode;

    @Column(name="PRODUCT_NAME")
    private String productName;

    @Column(name="IS_PROFIT_APPLICABLE")
    private int isProfitApplicable;

    @Column(name="PRODUCT_NATURE_TYPE")
    private int productNatureType;

    @Column(name="PROFIT_CALCULATION_METHOD")
    private String profitCalculationMethod;

    @Column(name="DAY_SIN_YEAR")
    private String daySinYear;

    @Column(name="STATUS")
    private int status;

    @Column(name="SET_UP_USER_ID")
    private String setUpUserId;

    @Column(name="SET_UP_DATE_TIME")
    private LocalDate setUpDateTime;
}
