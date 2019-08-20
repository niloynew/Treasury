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
      String accountNumber,
      String accountTitle,
      Long productId,
      String currencyCode,
      LocalDate openDate,
      LocalDate expiryDate,
      AccountStatus status,
      Long ownerBranchId);

  List<Account> findAccounts(
      String accountNumber,
      String accountTitle,
      Long productId,
      String currencyCode,
      LocalDate openDate,
      LocalDate expiryDate,
      AccountStatus status,
      Long ownerBranchId);

  Account findById(Long accountId);

  Account findSettlementAccounts(String accountNumber, LocalDate expiryDate, Long brId);

  String generateAccountNumber(Long productId, Long branchId);

  void registerTransactionProcess(Account account);
}
