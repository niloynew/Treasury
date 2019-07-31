package com.mislbd.ababil.treasury.service;

import java.math.BigDecimal;

public interface UtilityService {

  BigDecimal totalProvisionOfAccounts(String accountNumber, boolean glPosted, boolean accountPosted);

  void updateMonthendInfo(String shadowAccountNumber, String event, boolean accountPosted);

  BigDecimal totalProductOfAccounts(String shadowAccountNumber, boolean glPosted, boolean accountPosted);
}
