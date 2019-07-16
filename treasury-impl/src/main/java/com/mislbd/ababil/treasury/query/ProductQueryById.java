package com.mislbd.ababil.treasury.query;

import com.mislbd.asset.query.api.QueryRequest;

public class ProductQueryById extends QueryRequest {
  private Long id;

  public ProductQueryById(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public ProductQueryById setId(Long id) {
    this.id = id;
    return this;
  }
}
