package com.mislbd.ababil.treasury.query.handler;

import com.mislbd.ababil.treasury.domain.Account;
import com.mislbd.ababil.treasury.query.AccountQuery;
import com.mislbd.ababil.treasury.service.AccountService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.api.QueryResult;
import java.util.List;

@QueryAggregate
public class AccountQueryHandlerAggregate {
  private final AccountService accountService;

  public AccountQueryHandlerAggregate(AccountService accountService) {
    this.accountService = accountService;
  }

  public QueryResult<?> accountSearch(AccountQuery accountQuery) {
    if (accountQuery.isAsPage()) {
      PagedResult<Account> accountPage =
          accountService.findAccounts(
              accountQuery.getPageable(),
              accountQuery.getProductId(),
              accountQuery.getCurrencyCode(),
              accountQuery.getOpenDate(),
              accountQuery.getExpiryDate(),
              accountQuery.getStatus());
      return QueryResult.of(accountPage);
    } else {
      List<Account> accounts =
          accountService.findAccounts(
              accountQuery.getProductId(),
              accountQuery.getCurrencyCode(),
              accountQuery.getOpenDate(),
              accountQuery.getExpiryDate(),
              accountQuery.getStatus());
      return QueryResult.of(accounts);
    }
  }
}
