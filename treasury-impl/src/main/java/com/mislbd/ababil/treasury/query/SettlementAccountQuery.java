package com.mislbd.ababil.treasury.query;

import com.mislbd.asset.query.api.QueryRequest;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SettlementAccountQuery extends QueryRequest {
  private Pageable pageable;
  private String accountNumber;
  private LocalDate expiryDate;
  private Long ownerBranchId;
}
