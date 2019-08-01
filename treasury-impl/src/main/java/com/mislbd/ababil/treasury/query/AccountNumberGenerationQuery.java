package com.mislbd.ababil.treasury.query;

import com.mislbd.asset.query.api.QueryRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountNumberGenerationQuery extends QueryRequest {
  private Long productId;
  private Long branchId;
}
