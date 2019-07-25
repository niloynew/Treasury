package com.mislbd.ababil.treasury.mapper;

import com.mislbd.ababil.treasury.domain.ProductGeneralLedgerMapping;
import com.mislbd.ababil.treasury.repository.jpa.ProductRelatedGLRepository;
import com.mislbd.ababil.treasury.repository.schema.ProductRelatedGLEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductGLMapper {

  private final ProductRelatedGLRepository productRelatedGLRepository;

  public ProductGLMapper(ProductRelatedGLRepository productRelatedGLRepository) {
    this.productRelatedGLRepository = productRelatedGLRepository;
  }

  public ResultMapper<ProductRelatedGLEntity, ProductGeneralLedgerMapping> entityToDomain() {
    return entity ->
        new ProductGeneralLedgerMapping()
            .setId(entity.getId())
            .setGlType(entity.getGlType())
            .setGeneralLedgerCode(entity.getGlCode())
            .setProductId(entity.getProductId());
  }

  public ResultMapper<ProductGeneralLedgerMapping, ProductRelatedGLEntity> domainToEntity() {
    return domain ->
        productRelatedGLRepository
            .findById(domain.getId())
            .orElseGet(ProductRelatedGLEntity::new)
            .setGlType(domain.getGlType())
            .setGlCode(domain.getGeneralLedgerCode())
            .setProductId(domain.getProductId());
  }
}
