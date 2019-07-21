package com.mislbd.ababil.treasury.mapper;

import com.mislbd.ababil.treasury.domain.Account;
import com.mislbd.ababil.treasury.domain.AccountStatus;
import com.mislbd.ababil.treasury.exception.ProductNotFoundException;
import com.mislbd.ababil.treasury.repository.jpa.AccountRepository;
import com.mislbd.ababil.treasury.repository.jpa.ProductRepository;
import com.mislbd.ababil.treasury.repository.schema.AccountEntity;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

  private final AccountRepository accountRepository;
  private final ProductRepository productRepository;

  public AccountMapper(AccountRepository accountRepository, ProductRepository productRepository) {
    this.accountRepository = accountRepository;
    this.productRepository = productRepository;
  }

  public ResultMapper<AccountEntity, Account> entityToDomain() {

    return entity ->
        new Account()
            .setId(entity.getId())
            .setProductId(entity.getProduct().getId())
            .setCurrencyCode(entity.getCurrencyCode())
            .setBankId(entity.getBankId())
            .setBranchId(entity.getBranchId())
            .setAccountTitle(entity.getAccountTitle())
            .setAccountNumber(entity.getAccountNumber())
            .setAmount(entity.getAmount())
            .setShadowAccountNumber(entity.getShadowAccountNumber())
            .setAccountOpenDate(entity.getAccountOpenDate())
            .setAccountClosingDate(entity.getAccountClosingDate())
            .setExpiryDate(entity.getExpiryDate())
            .setTenorAmount(entity.getTenorAmount())
            .setTenorType(entity.getTenorType())
            .setRenewalDate(entity.getRenewalDate())
            .setExpectedProfitRate(entity.getExpectedProfitRate())
            .setStatus(entity.getStatus())
            .setInstrument(entity.getInstrument())
            .setProfitAmount(entity.getProfitAmount())
            .setActualProfit(entity.getActualProfit())
            .setRenewWithProfit(entity.isRenewWithProfit());
  }

  public ResultMapper<Account, AccountEntity> domainToEntity() {
    return domain ->
        accountRepository
            .findById(domain.getId())
            .orElseGet(AccountEntity::new)
            .setId(domain.getId())
            .setProduct(
                productRepository
                    .findById(domain.getProductId())
                    .orElseThrow(ProductNotFoundException::new))
            .setCurrencyCode(domain.getCurrencyCode())
            .setBankId(domain.getBankId())
            .setBranchId(domain.getBranchId())
            .setAccountTitle(domain.getAccountTitle())
            .setAccountNumber(domain.getAccountNumber())
            .setAmount(domain.getAmount())
            .setShadowAccountNumber(domain.getShadowAccountNumber())
            .setAccountOpenDate(domain.getAccountOpenDate())
            .setExpiryDate(domain.getExpiryDate())
            .setTenorAmount(domain.getTenorAmount())
            .setTenorType(domain.getTenorType())
            .setRenewalDate(domain.getRenewalDate())
            .setExpectedProfitRate(domain.getExpectedProfitRate())
            .setStatus(AccountStatus.REGULAR)
            .setInstrument(domain.getInstrument())
            .setActive(true);
  }
}
