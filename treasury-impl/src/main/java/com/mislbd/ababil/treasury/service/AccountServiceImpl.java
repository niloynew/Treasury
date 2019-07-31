package com.mislbd.ababil.treasury.service;

import com.mislbd.ababil.treasury.domain.Account;
import com.mislbd.ababil.treasury.domain.AccountStatus;
import com.mislbd.ababil.treasury.exception.AccountNotFoundException;
import com.mislbd.ababil.treasury.mapper.AccountMapper;
import com.mislbd.ababil.treasury.repository.jpa.AccountRepository;
import com.mislbd.ababil.treasury.repository.specification.AccountSpecification;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;
  private final AccountMapper accountMapper;

  public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
    this.accountRepository = accountRepository;
    this.accountMapper = accountMapper;
  }

  @Override
  public PagedResult<Account> findAccounts(
      Pageable pageable,
      String accountNumber,
      String accountTitle,
      Long productId,
      String currencyCode,
      LocalDate openDate,
      LocalDate expiryDate,
      AccountStatus status,
      Long ownerBranchId) {
    return PagedResultBuilder.build(
        accountRepository.findAll(
            AccountSpecification.findAccount(
                accountNumber,
                accountTitle,
                productId,
                currencyCode,
                openDate,
                expiryDate,
                status,
                ownerBranchId),
            pageable),
        accountMapper.entityToDomain());
  }

  @Override
  public List<Account> findAccounts(
      String accountNumber,
      String accountTitle,
      Long productId,
      String currencyCode,
      LocalDate openDate,
      LocalDate expiryDate,
      AccountStatus status,
      Long ownerBranchId) {
    return ListResultBuilder.build(
        accountRepository.findAll(
            AccountSpecification.findAccount(
                accountNumber,
                accountTitle,
                productId,
                currencyCode,
                openDate,
                expiryDate,
                status,
                ownerBranchId)),
        accountMapper.entityToDomain());
  }

  @Override
  public Account findById(Long accountId) {
    return accountMapper
        .entityToDomain()
        .map(accountRepository.findById(accountId).orElseThrow(AccountNotFoundException::new));
  }

  @Override
  public PagedResult<Account> findSettlementAccounts(
      Pageable pageable, String accountNumber, LocalDate expiryDate, Long ownerBranchId) {

    return PagedResultBuilder.build(
        accountRepository.findAll(
            AccountSpecification.findSettlementAccounts(accountNumber, expiryDate, ownerBranchId),
            pageable),
        accountMapper.entityToSettlementDomain());
  }
}
