package com.mislbd.ababil.treasury.service;

import java.math.BigDecimal;

public interface UtilityService {

  BigDecimal totalProvisionOfAccounts(
      String accountNumber, Boolean glPosted, Boolean accountPosted);

  void updateMonthendInfo(
      String accountNumber, String event, boolean accountPosted, Long globalTxnNumber);

  BigDecimal totalProductOfAccounts(
      String shadowAccountNumber, Boolean glPosted, Boolean accountPosted);

  void reactiveMonthendInfo(Long globalTxnNumber);
}
