package com.mislbd.ababil.treasury.query;

import com.mislbd.asset.query.api.QueryRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductQuery extends QueryRequest {

  private boolean asPage;
  private Pageable pageable;
  private String name;
  private String code;
}
