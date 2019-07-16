package com.mislbd.ababil.treasury.query;

import com.mislbd.asset.query.api.QueryRequest;
import org.springframework.data.domain.Pageable;

public class ProductQuery extends QueryRequest {

  private boolean asPage;
  private Pageable pageable;
  private String name;
  private String code;

  public ProductQuery(boolean asPage, Pageable pageable, String name, String code) {
    this.asPage = asPage;
    this.pageable = pageable;
    this.name = name;
    this.code = code;
  }

  public ProductQuery(String name, String code) {
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
