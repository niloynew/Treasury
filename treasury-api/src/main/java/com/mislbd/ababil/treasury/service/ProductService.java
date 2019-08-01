package com.mislbd.ababil.treasury.service;

import com.mislbd.ababil.treasury.domain.Product;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;

public interface ProductService {
  PagedResult<Product> findProducts(Pageable pageable, String name, String code);

  List<Product> findProducts(String name, String code);

  Optional<Product> findProduct(Long id);

  boolean isExist(Long id);
}
