package com.mislbd.ababil.treasury.query;

import com.mislbd.asset.query.api.QueryRequest;

public class AccountQueryById extends QueryRequest {
  private Long id;

  public AccountQueryById(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public AccountQueryById setId(Long id) {
    this.id = id;
    return this;
  }
}
