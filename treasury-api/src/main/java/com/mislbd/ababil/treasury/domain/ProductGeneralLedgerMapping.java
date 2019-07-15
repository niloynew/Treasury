package com.mislbd.ababil.treasury.domain;

public class ProductGeneralLedgerMapping {

    private long id;
    private GLType glType;
    private long generalLedgerId;
    private long productId;

    public long getId() {
        return id;
    }

    public ProductGeneralLedgerMapping setId(long id) {
        this.id = id;
        return this;
    }

    public GLType getGlType() {
        return glType;
    }

    public ProductGeneralLedgerMapping setGlType(GLType glType) {
        this.glType = glType;
        return this;
    }

    public long getGeneralLedgerId() {
        return generalLedgerId;
    }

    public ProductGeneralLedgerMapping setGeneralLedgerId(long generalLedgerId) {
        this.generalLedgerId = generalLedgerId;
        return this;
    }

    public long getProductId() {
        return productId;
    }

    public ProductGeneralLedgerMapping setProductId(long productId) {
        this.productId = productId;
        return this;
    }
}
