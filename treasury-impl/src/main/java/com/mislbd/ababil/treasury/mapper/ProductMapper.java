package com.mislbd.ababil.treasury.mapper;

import com.mislbd.ababil.treasury.domain.Product;
import com.mislbd.ababil.treasury.exception.ProductNatureNotFoundException;
import com.mislbd.ababil.treasury.repository.jpa.ProductNatureRepository;
import com.mislbd.ababil.treasury.repository.jpa.ProductRelatedGLRepository;
import com.mislbd.ababil.treasury.repository.jpa.ProductRepository;
import com.mislbd.ababil.treasury.repository.schema.ProductEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductMapper {

  private final ProductRepository productRepository;
  private final ProductNatureRepository productNatureRepository;
  private final ProductNatureMapper productNatureMapper;
  private final ProductRelatedGLRepository productRelatedGLRepository;
  private final ProductGLMapper productGLMapper;

  public ProductMapper(
          ProductRepository productRepository,
          ProductNatureRepository productNatureRepository,
          ProductNatureMapper productNatureMapper, ProductRelatedGLRepository productRelatedGLRepository, ProductGLMapper productGLMapper) {
    this.productRepository = productRepository;
    this.productNatureRepository = productNatureRepository;
    this.productNatureMapper = productNatureMapper;
    this.productRelatedGLRepository = productRelatedGLRepository;
    this.productGLMapper = productGLMapper;
  }

  public ResultMapper<ProductEntity, Product> entityToDomain() {

    return entity ->
        new Product()
            .setId(entity.getId())
            .setCode(entity.getCode())
            .setName(entity.getName())
            .setProfitApplicable(entity.isProfitApplicable())
            .setProductNatureId(entity.getProductNature().getId())
            .setProfitCalculationMethod(entity.getProfitCalculationMethod())
            .setStatus(entity.getStatus())
            .setDaysInYear(entity.getDaysInYear())
                .setProductGeneralLedgerMappingList(
                        productRelatedGLRepository.findAllByProductId(entity.getId()).stream()
                        .map(productRelatedGLEntity -> productGLMapper.entityToDomain().map(productRelatedGLEntity))
                        .collect(Collectors.toList()));
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
                productNatureRepository
                    .findById(domain.getProductNatureId())
                    .orElseThrow(ProductNatureNotFoundException::new))
            .setProfitCalculationMethod(domain.getProfitCalculationMethod())
            .setDaysInYear(domain.getDaysInYear())
            .setStatus(domain.getStatus());
  }
}
