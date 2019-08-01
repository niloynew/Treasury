package com.mislbd.ababil.treasury.query.handler;

import com.mislbd.ababil.treasury.domain.Account;
import com.mislbd.ababil.treasury.query.AccountNumberGenerationQuery;
import com.mislbd.ababil.treasury.query.AccountQuery;
import com.mislbd.ababil.treasury.query.SettlementAccountQuery;
import com.mislbd.ababil.treasury.service.AccountService;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.query.annotation.QueryAggregate;
import com.mislbd.asset.query.annotation.QueryHandler;
import com.mislbd.asset.query.api.QueryResult;
import java.util.List;

@QueryAggregate
public class AccountQueryHandlerAggregate {
  private final AccountService accountService;

  public AccountQueryHandlerAggregate(AccountService accountService) {
    this.accountService = accountService;
  }

  @QueryHandler
  public QueryResult<?> accountSearch(AccountQuery accountQuery) {
    if (accountQuery.isAsPage()) {
      PagedResult<Account> accountPage =
          accountService.findAccounts(
              accountQuery.getPageable(),
              accountQuery.getAccountNumber(),
              accountQuery.getAccountTitle(),
              accountQuery.getProductId(),
              accountQuery.getCurrencyCode(),
              accountQuery.getOpenDate(),
              accountQuery.getExpiryDate(),
              accountQuery.getStatus(),
              accountQuery.getOwnerBranchId());
      return QueryResult.of(accountPage);
    } else {
      List<Account> accounts =
          accountService.findAccounts(
              accountQuery.getAccountNumber(),
              accountQuery.getAccountTitle(),
              accountQuery.getProductId(),
              accountQuery.getCurrencyCode(),
              accountQuery.getOpenDate(),
              accountQuery.getExpiryDate(),
              accountQuery.getStatus(),
              accountQuery.getOwnerBranchId());
      return QueryResult.of(accounts);
    }
  }

  @QueryHandler
  public QueryResult<?> settlementAccountSearch(SettlementAccountQuery accountQuery) {
    PagedResult<Account> accountPage =
        accountService.findSettlementAccounts(
            accountQuery.getPageable(),
            accountQuery.getAccountNumber(),
            accountQuery.getExpiryDate(),
            accountQuery.getOwnerBranchId());
    return QueryResult.of(accountPage);
  }

  @QueryHandler
  public QueryResult<?> getGeneratedAccountNumber(AccountNumberGenerationQuery accountQuery) {
    String accountNumber =
        accountService.generateAccountNumber(
            accountQuery.getProductId(), accountQuery.getBranchId());
    return QueryResult.of(accountNumber);
  }
}
