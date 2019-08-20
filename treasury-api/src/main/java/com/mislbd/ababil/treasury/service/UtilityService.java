package com.mislbd.ababil.treasury.service;

import java.math.BigDecimal;

public interface UtilityService {

  BigDecimal totalProvisionOfAccounts(
      String accountNumber, Boolean glPosted, Boolean accountPosted);

  void updateMonthendInfo(String shadowAccountNumber, String event, boolean accountPosted);

  BigDecimal totalProductOfAccounts(
      String shadowAccountNumber, Boolean glPosted, Boolean accountPosted);
}
