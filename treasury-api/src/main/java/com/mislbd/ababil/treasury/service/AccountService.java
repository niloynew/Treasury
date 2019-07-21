package com.mislbd.ababil.treasury.service;

import com.mislbd.ababil.treasury.domain.Account;
import com.mislbd.ababil.treasury.domain.AccountStatus;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface AccountService {
  PagedResult<Account> findAccounts(
      Pageable pageable,
      Long productId,
      String currencyCode,
      LocalDate openDate,
      LocalDate expiryDate,
      AccountStatus status);

  List<Account> findAccounts(
      Long productId,
      String currencyCode,
      LocalDate openDate,
      LocalDate expiryDate,
      AccountStatus status);

  Account findById(Long accountId);

  //    Optional<AccountEntity> findById(Long accountId);
}
