package com.mislbd.ababil.treasury.mapper;

import com.mislbd.ababil.treasury.domain.Account;
import com.mislbd.ababil.treasury.domain.AccountStatus;
import com.mislbd.ababil.treasury.exception.AccountNotFoundException;
import com.mislbd.ababil.treasury.exception.ProductNotFoundException;
import com.mislbd.ababil.treasury.repository.jpa.AccountRepository;
import com.mislbd.ababil.treasury.repository.jpa.ProductRepository;
import com.mislbd.ababil.treasury.repository.schema.AccountEntity;
import com.mislbd.ababil.treasury.repository.schema.AccountProcessEntity;
import com.mislbd.ababil.treasury.service.UtilityService;
import com.mislbd.asset.commons.data.domain.ResultMapper;
import com.mislbd.security.core.NgSession;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

  private final AccountRepository accountRepository;
  private final ProductRepository productRepository;
  private final NgSession ngSession;
  private final UtilityService utilityService;

  public AccountMapper(
      AccountRepository accountRepository,
      ProductRepository productRepository,
      NgSession ngSession,
      UtilityService utilityService) {
    this.accountRepository = accountRepository;
    this.productRepository = productRepository;
    this.ngSession = ngSession;
    this.utilityService = utilityService;
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
            .setNostroAccountNumber(entity.getNostroAccountNumber())
            .setAmount(entity.getAmount())
            .setBalance(entity.getBalance())
            .setOwnerBranchId(entity.getOwnerBranchId())
            .setAccountNumber(entity.getAccountNumber())
            .setAccountOpenDate(entity.getOpenDate())
            .setAccountClosingDate(entity.getClosingDate())
            .setExpiryDate(entity.getExpiryDate())
            .setTenorAmount(entity.getTenorAmount())
            .setTenorType(entity.getTenorType())
            .setRenewalDate(entity.getRenewalDate())
            .setExpectedProfitRate(entity.getProfitRate())
            .setStatus(entity.getStatus())
            .setInstrument(entity.getInstrumentNumber());
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
            .setNostroAccountNumber(domain.getNostroAccountNumber())
            .setAmount(domain.getAmount())
            .setAccountNumber(domain.getAccountNumber())
            .setOpenDate(domain.getAccountOpenDate())
            .setExpiryDate(domain.getExpiryDate())
            .setTenorAmount(domain.getTenorAmount())
            .setTenorType(domain.getTenorType())
            .setRenewalDate(domain.getRenewalDate())
            .setProfitRate(domain.getExpectedProfitRate())
            .setStatus(AccountStatus.REGULAR)
            .setInstrumentNumber(domain.getInstrument())
            .setOwnerBranchId(ngSession.getUserBranch())
            .setActive(true);
  }

  public ResultMapper<Account, AccountProcessEntity> domainToProcessEntity() {
    return domain ->
        new AccountProcessEntity()
            .setAccountNumber(domain.getAccountNumber())
            .setName(domain.getAccountTitle())
            .setBalance(domain.getBalance())
            .setTotalProduct(domain.getProductAmount())
            .setProfitRate(domain.getExpectedProfitRate())
            .setProvisionAmount(domain.getProfitAmount())
            .setActualProvision(domain.getActualProfit())
            .setOldStatus(domain.getStatus())
            .setNewStatus(domain.getStatus())
            .setOldExpiryDate(domain.getExpiryDate())
            .setNewExpiryDate(domain.getNewExpiryDate())
            .setOldRenewalDate(domain.getRenewalDate())
            .setNewRenewalDate(domain.getRenewalDate())
            .setLastProvisionDate(domain.getLastProvisionDate())
            .setGlobalTxnNumber(domain.getGlobalTxnNumber())
            .setValid(true)
            .setRenewWithProfit(domain.getRenewWithProfit());
  }

  public ResultMapper<Account, AccountEntity> renwalDomainToEntity() {
    return domain ->
        accountRepository
            .findById(domain.getId())
            .orElseThrow(AccountNotFoundException::new)
            .setAccountNumber(domain.getAccountNumber())
            .setExpiryDate(domain.getNewExpiryDate())
            .setTenorAmount(domain.getNewTenorAmount())
            .setTenorType(domain.getNewTenorType())
            .setRenewalDate(domain.getRenewalDate().plusDays(1))
            .setProfitRate(domain.getNewProfitRate())
            .setStatus(AccountStatus.REGULAR);
  }

  public ResultMapper<Account, AccountEntity> closeDomainToEntity() {
    return domain ->
        accountRepository
            .findById(domain.getId())
            .orElseThrow(AccountNotFoundException::new)
            .setAmount(BigDecimal.ZERO)
            .setClosingDate(domain.getValueDate())
            .setStatus(AccountStatus.CLOSED);
  }

  public ResultMapper<AccountEntity, Account> entityToSettlementDomain() {

    return entity ->
        new Account()
            .setId(entity.getId())
            .setProductId(entity.getProduct().getId())
            .setCurrencyCode(entity.getCurrencyCode())
            .setBankId(entity.getBankId())
            .setBranchId(entity.getBranchId())
            .setAccountTitle(entity.getAccountTitle())
            .setNostroAccountNumber(entity.getNostroAccountNumber())
            .setBalance(entity.getBalance())
            .setAccountNumber(entity.getAccountNumber())
            .setAccountOpenDate(entity.getOpenDate())
            .setExpiryDate(entity.getExpiryDate())
            .setTenorAmount(entity.getTenorAmount())
            .setTenorType(entity.getTenorType())
            .setRenewalDate(entity.getRenewalDate())
            .setExpectedProfitRate(entity.getProfitRate())
            .setInstrument(entity.getInstrumentNumber())
            .setProfitAmount(
                utilityService.totalProvisionOfAccounts(entity.getAccountNumber(), null, false))
            .setProductAmount(
                utilityService.totalProductOfAccounts(entity.getAccountNumber(), null, false));
  }
}
