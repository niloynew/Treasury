package com.mislbd.ababil.treasury.domain;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Builder
public class TransactionalInformation {
  private String batchNumber;
  private Long globalTxnNumber;
  private BigDecimal exchangeRate;
  private Long exchangeRateType;
  private Long activityId;
}
