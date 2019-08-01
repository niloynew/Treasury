package com.mislbd.ababil.treasury.service;

import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.treasury.domain.Account;
import com.mislbd.ababil.treasury.domain.AccountStatus;
import com.mislbd.ababil.treasury.exception.AccountNotFoundException;
import com.mislbd.ababil.treasury.exception.ProductNotFoundException;
import com.mislbd.ababil.treasury.mapper.AccountMapper;
import com.mislbd.ababil.treasury.repository.jpa.AccountRepository;
import com.mislbd.ababil.treasury.repository.schema.AccountEntity;
import com.mislbd.ababil.treasury.repository.specification.AccountSpecification;
import com.mislbd.asset.commons.data.domain.ListResultBuilder;
import com.mislbd.asset.commons.data.domain.PagedResult;
import com.mislbd.asset.commons.data.domain.PagedResultBuilder;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;
  private final AccountMapper accountMapper;
  private final ProductService productService;
  private final ConfigurationService configurationService;

  public AccountServiceImpl(
      AccountRepository accountRepository,
      AccountMapper accountMapper,
      ProductService productService,
      ConfigurationService configurationService) {
    this.accountRepository = accountRepository;
    this.accountMapper = accountMapper;
    this.productService = productService;
    this.configurationService = configurationService;
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

  @Override
  public String generateAccountNumber(Long productId, Long branchId) {
    int branchCodeLength = configurationService.getBranchCodeLength();
    int productCodeLength = configurationService.getProductCodeLength();
    int accountCodeLength = configurationService.getAccountNumberLength();

    String branchCode = StringUtils.leftPad(String.valueOf(branchId), branchCodeLength, "0");
    String productCode =
        productService
            .findProduct(productId)
            .orElseThrow(ProductNotFoundException::new)
            .getCode()
            .trim();
    int totalAccountPrefix = branchCodeLength + productCodeLength + 1;

    String maxSerialNumber =
        getMaxSerialNumber(productId, branchId, totalAccountPrefix, accountCodeLength);

    String newSerialNumber =
        StringUtils.leftPad(
            String.valueOf(Integer.valueOf(maxSerialNumber) + 1), maxSerialNumber.length(), "0");

    return branchCode
        .concat(productCode)
        .concat(newSerialNumber)
        .concat(getCheckDigit(branchCode, productCode, newSerialNumber));
  }

  @Override
  public String getMaxSerialNumber(
      Long productId, Long branchId, Integer startPoint, Integer endPoint) {
    List<AccountEntity> accounts =
        accountRepository.findAll(
            AccountSpecification.findProductAndBranchSpecificAccount(productId, branchId));
    return accounts
        .stream()
        .max(Comparator.comparing(AccountEntity::getShadowAccountNumber))
        .orElseThrow(AccountNotFoundException::new)
        .getShadowAccountNumber()
        .substring(startPoint, endPoint);
  }

  private String getCheckDigit(String branchCode, String productCode, String serial) {
    int checkDigit = 0;
    String temp = branchCode.concat(productCode).concat(serial);
    for (int i = 0; i < temp.length(); i++) {
      checkDigit += Integer.valueOf(temp.charAt(i));
    }
    checkDigit %= 9;
    if (checkDigit == 0) {
      checkDigit = 9;
    }
    return String.valueOf(checkDigit);
  }
}
