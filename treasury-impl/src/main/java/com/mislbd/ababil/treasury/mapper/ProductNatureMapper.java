package com.mislbd.ababil.treasury.mapper;

import com.mislbd.ababil.treasury.domain.ProductNature;
import com.mislbd.ababil.treasury.repository.jpa.ProductNatureRepository;
import com.mislbd.ababil.treasury.repository.schema.ProductNatureEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductNatureMapper {
  private final ProductNatureRepository productNatureRepository;

  public ProductNatureMapper(ProductNatureRepository productNatureRepository) {
    this.productNatureRepository = productNatureRepository;
  }

  public ResultMapper<ProductNatureEntity, ProductNature> entityToDomain() {

    return entity ->
        new ProductNature()
            .setId(entity.getId())
            .setCode(entity.getCode())
            .setDescription(entity.getDescription());
  }

  public ResultMapper<ProductNature, ProductNatureEntity> domainToEntity() {
    return domain ->
        (ProductNatureEntity)
            productNatureRepository
                .findById(domain.getId())
                .orElseGet(ProductNatureEntity::new)
                .setId(domain.getId())
                .setCode(domain.getCode())
                .setDescription(domain.getDescription());
  }
}
