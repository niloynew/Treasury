package com.mislbd.ababil.treasury.mapper;

import com.mislbd.ababil.treasury.domain.Product;
import com.mislbd.ababil.treasury.repository.jpa.ProductNatureRepository;
import com.mislbd.ababil.treasury.repository.jpa.ProductRepository;
import com.mislbd.ababil.treasury.repository.schema.ProductEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  private final ProductRepository productRepository;
  private final ProductNatureRepository productNatureRepository;
  private final ProductNatureMapper productNatureMapper;

  public ProductMapper(
      ProductRepository productRepository,
      ProductNatureRepository productNatureRepository,
      ProductNatureMapper productNatureMapper) {
    this.productRepository = productRepository;
    this.productNatureRepository = productNatureRepository;
    this.productNatureMapper = productNatureMapper;
  }

  public ResultMapper<ProductEntity, Product> entityToDomain() {

    return entity ->
        new Product()
            .setId(entity.getId())
            .setCode(entity.getCode())
            .setName(entity.getName())
            .setProfitApplicable(entity.isProfitApplicable())
            .setProductNature(productNatureMapper.entityToDomain().map(entity.getProductNature()))
            .setProfitCalculationMethod(entity.getProfitCalculationMethod())
            .setDaysInYear(entity.getDaysInYear())
            .setStatus(entity.getStatus());
  }

  public ResultMapper<Product, ProductEntity> domainToEntity() {
    return domain ->
        productRepository
            .findById(domain.getId())
            .orElseGet(ProductEntity::new)
            .setId(domain.getId())
            .setCode(domain.getCode())
            .setName(domain.getName())
            .setProfitApplicable(domain.isProfitApplicable())
            .setProductNature(
                productNatureRepository.findByCode(domain.getProductNature().getCode()).get())
            .setProfitCalculationMethod(domain.getProfitCalculationMethod())
            .setDaysInYear(domain.getDaysInYear())
            .setStatus(domain.getStatus());
  }
}
