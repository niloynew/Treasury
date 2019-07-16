package com.mislbd.ababil.treasury.query.handler;

import com.mislbd.ababil.treasury.domain.Product;
import com.mislbd.ababil.treasury.query.ProductQuery;
import com.mislbd.ababil.treasury.query.ProductQueryById;
import com.mislbd.ababil.treasury.service.ProductService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;
import java.util.List;

@QueryAggregate
public class ProductQueryHandlerAggregate {

  private ProductService productService;

  public ProductQueryHandlerAggregate(ProductService productService) {
    this.productService = productService;
  }

  @QueryHandler
  public QueryResult<?> productSearchById(ProductQueryById productQueryById) {
    return QueryResult.of(productService.findById(productQueryById.getId()));
  }

  @QueryHandler
  public QueryResult<?> productSearch(ProductQuery productQuery) {
    if (productQuery.isAsPage()) {
      PagedResult<Product> productPage =
          productService.findProducts(
              productQuery.getPageable(), productQuery.getCode(), productQuery.getName());
      return QueryResult.of(productPage);
    } else {
      List<Product> products =
          productService.findProducts(productQuery.getName(), productQuery.getCode());
      return QueryResult.of(products);
    }
  }
}
