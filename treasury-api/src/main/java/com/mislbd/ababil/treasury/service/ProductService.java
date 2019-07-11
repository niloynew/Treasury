package com.mislbd.ababil.treasury.service;

import com.mislbd.ababil.treasury.domain.Product;
import com.mislbd.ababil.treasury.domain.ProductStatus;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ProductService {
  PagedResult<Product> findProducts(
      Pageable pageable, String name, String code, ProductStatus status);

  List<Product> findProducts(String name, String code, ProductStatus status);
}
