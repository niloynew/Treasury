package com.mislbd.ababil.treasury.query;

import com.mislbd.asset.query.api.QueryRequest;
import org.springframework.data.domain.Pageable;

public class ProductQuery extends QueryRequest {

  private final boolean asPage;
  private final Pageable pageable;
  private final String name;
  private final String code;

  public ProductQuery(boolean asPage, Pageable pageable, String name, String code) {
    this.asPage = asPage;
    this.pageable = pageable;
    this.name = name;
    this.code = code;
  }

  public boolean isAsPage() {
    return asPage;
  }

  public Pageable getPageable() {
    return pageable;
  }

  public String getName() {
    return name;
  }

  public String getCode() {
    return code;
  }
}
