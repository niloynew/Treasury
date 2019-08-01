package com.mislbd.ababil.treasury.service;

import com.mislbd.ababil.treasury.domain.Product;
import com.mislbd.ababil.treasury.mapper.ProductMapper;
import com.mislbd.ababil.treasury.repository.jpa.ProductRepository;
import com.mislbd.ababil.treasury.repository.specification.ProductSpecification;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
  }

  @Override
  public PagedResult<Product> findProducts(Pageable pageable, String name, String code) {
    return PagedResultBuilder.build(
        productRepository.findAll(ProductSpecification.findProduct(name, code), pageable),
        productMapper.entityToDomain());
  }

  @Override
  public List<Product> findProducts(String name, String code) {
    return ListResultBuilder.build(
        productRepository.findAll(ProductSpecification.findProduct(name, code)),
        productMapper.entityToDomain());
  }

  @Override
  public Optional<Product> findProduct(Long id) {
    return productRepository.findById(id).map(productMapper.entityToDomain()::map);
  }

  @Override
  public boolean isExist(Long id) {
    return productRepository.existsById(id);
  }
}
