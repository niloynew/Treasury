package com.mislbd.ababil.treasury.mapper;

import com.mislbd.ababil.treasury.domain.Account;
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
            .setExpiryDate(entity.getExpiryDate())
            .setTenor(entity.getTenor())
            .setRenewalDate(entity.getRenewalDate())
            .setExpectedProfitRate(entity.getExpectedProfitRate())
            .setStatus(entity.getStatus())
            .setInstrument(entity.getInstrument());
  }

  public ResultMapper<Account, AccountEntity> domainToEntity() {
    return domain ->
        accountRepository
            .findById(domain.getId())
            .orElseGet(AccountEntity::new)
            .setId(domain.getId())
            .setProduct(
                domain.getProductId() != null
                    ? productRepository.getOne(domain.getProductId())
                    : null)
            .setCurrencyCode(domain.getCurrencyCode())
            .setBankId(domain.getBankId())
            .setBranchId(domain.getBranchId())
            .setAccountTitle(domain.getAccountTitle())
            .setAccountNumber(domain.getAccountNumber())
            .setAmount(domain.getAmount())
            .setShadowAccountNumber(domain.getShadowAccountNumber())
            .setAccountOpenDate(domain.getAccountOpenDate())
            .setExpiryDate(domain.getExpiryDate())
            .setTenor(domain.getTenor())
            .setRenewalDate(domain.getRenewalDate())
            .setExpectedProfitRate(domain.getExpectedProfitRate())
            .setStatus(domain.getStatus())
            .setInstrument(domain.getInstrument());
  }
}
