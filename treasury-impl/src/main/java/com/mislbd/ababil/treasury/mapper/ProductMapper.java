package com.mislbd.ababil.treasury.mapper;

import com.mislbd.ababil.treasury.domain.Product;
import com.mislbd.ababil.treasury.repository.jpa.ProductRepository;
import com.mislbd.ababil.treasury.repository.schema.ProductEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  private final ProductRepository productRepository;

  public ProductMapper(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public ResultMapper<ProductEntity, Product> entityToDomain() {

    return entity ->
        new Product()
            .setId(entity.getId())
            .setCode(entity.getCode())
            .setName(entity.getName())
            .setProfitApplicable(entity.isProfitApplicable())
            .setProductNature(entity.getProductNature())
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
            .setProductNature(domain.getProductNature())
            .setProfitCalculationMethod(domain.getProfitCalculationMethod())
            .setDaysInYear(domain.getDaysInYear())
            .setStatus(domain.getStatus());
  }
}
