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
            .setTenureAmount(entity.getTenureAmount())
            .setTenureType(entity.getTenureType())
            .setRenewalDate(entity.getRenewalDate())
            .setExpectedProfitRate(entity.getProfitRate())
            .setStatus(entity.getStatus())
            .setInstrument(entity.getInstrumentNumber());
  }

  public ResultMapper<Account, AccountEntity> domainToEntity(
      BigDecimal balance,
      BigDecimal principalDebit,
      BigDecimal principalCredit,
      BigDecimal profitDebit,
      BigDecimal profitCredit) {
    return domain ->
        accountRepository
            .findById(domain.getId())
            .orElseGet(AccountEntity::new)
            .setId(domain.getId())
            .setNostroAccountNumber(domain.getNostroAccountNumber())
            .setAccountNumber(domain.getAccountNumber())
            .setAccountTitle(domain.getAccountTitle())
            .setOwnerBranchId(ngSession.getUserBranch())
            .setInstrumentNumber(domain.getInstrument())
            .setOpenDate(domain.getAccountOpenDate())
            .setClosingDate(domain.getAccountClosingDate())
            .setExpiryDate(domain.getExpiryDate())
            .setRenewalDate(domain.getRenewalDate())
            .setLastProvisionDate(domain.getLastProvisionDate())
            .setStatus(AccountStatus.REGULAR)
            .setCurrencyCode(domain.getCurrencyCode())
            .setBankId(domain.getBankId())
            .setBranchId(domain.getBranchId())
            .setProfitRate(domain.getExpectedProfitRate())
            .setProduct(
                productRepository
                    .findById(domain.getProductId())
                    .orElseThrow(ProductNotFoundException::new))
            .setAmount(domain.getAmount())
            .setBalance(balance)
            .setTenureAmount(domain.getTenureAmount())
            .setTenureType(domain.getTenureType())
            .setPrincipalDebit(principalDebit != null ? principalDebit : BigDecimal.ZERO)
            .setPrincipalCredit(principalCredit != null ? principalCredit : BigDecimal.ZERO)
            .setProfitDebit(profitDebit != null ? profitDebit : BigDecimal.ZERO)
            .setProfitCredit(profitCredit != null ? profitCredit : BigDecimal.ZERO)
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
            .setNewStatus(domain.getNewStatus())
            .setOldExpiryDate(domain.getExpiryDate())
            .setNewExpiryDate(domain.getNewExpiryDate())
            .setOldRenewalDate(domain.getRenewalDate())
            .setNewRenewalDate(domain.getNewRenewalDate())
            .setLastProvisionDate(domain.getLastProvisionDate())
            .setGlobalTxnNumber(domain.getGlobalTxnNumber())
            .setValid(true)
            .setRenewWithProfit(domain.isRenewWithProfit());
  }

  public ResultMapper<Account, AccountEntity> renewalDomainToEntity(
      BigDecimal balance,
      BigDecimal principalDebit,
      BigDecimal principalCredit,
      BigDecimal profitDebit,
      BigDecimal profitCredit) {
    return domain ->
        accountRepository
            .findById(domain.getId())
            .orElseThrow(AccountNotFoundException::new)
            .setBalance(balance != null ? balance : BigDecimal.ZERO)
            .setPrincipalDebit(principalDebit != null ? principalDebit : BigDecimal.ZERO)
            .setPrincipalCredit(principalCredit != null ? principalCredit : BigDecimal.ZERO)
            .setProfitDebit(profitDebit != null ? profitDebit : BigDecimal.ZERO)
            .setProfitCredit(profitCredit != null ? profitCredit : BigDecimal.ZERO)
            .setExpiryDate(domain.getNewExpiryDate())
            .setTenureAmount(domain.getNewTenureAmount())
            .setTenureType(domain.getNewTenureType())
            .setRenewalDate(domain.getExpiryDate().plusDays(1))
            .setProfitRate(domain.getNewProfitRate())
            .setStatus(AccountStatus.REGULAR);
  }

  public ResultMapper<Account, AccountEntity> closeDomainToEntity(
      BigDecimal balance,
      BigDecimal principalDebit,
      BigDecimal principalCredit,
      BigDecimal profitDebit,
      BigDecimal profitCredit) {
    return domain ->
        accountRepository
            .findById(domain.getId())
            .orElseThrow(AccountNotFoundException::new)
            .setBalance(balance != null ? balance : BigDecimal.ZERO)
            .setPrincipalDebit(principalDebit != null ? principalDebit : BigDecimal.ZERO)
            .setPrincipalCredit(principalCredit != null ? principalCredit : BigDecimal.ZERO)
            .setProfitDebit(profitDebit != null ? profitDebit : BigDecimal.ZERO)
            .setProfitCredit(profitCredit != null ? profitCredit : BigDecimal.ZERO)
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
            .setAmount(entity.getAmount())
            .setBalance(entity.getBalance())
            .setAccountNumber(entity.getAccountNumber())
            .setAccountOpenDate(entity.getOpenDate())
            .setExpiryDate(entity.getExpiryDate())
            .setTenureAmount(entity.getTenureAmount())
            .setTenureType(entity.getTenureType())
            .setRenewalDate(entity.getRenewalDate())
            .setExpectedProfitRate(entity.getProfitRate())
            .setInstrument(entity.getInstrumentNumber())
            .setProfitAmount(
                utilityService.totalProvisionOfAccounts(entity.getAccountNumber(), true, false))
            .setProductAmount(
                utilityService.totalProductOfAccounts(entity.getAccountNumber(), true, false));
  }

  public ResultMapper<AccountProcessEntity, AccountEntity> reactiveEntity(
      BigDecimal balance,
      BigDecimal principalDebit,
      BigDecimal principalCredit,
      BigDecimal profitDebit,
      BigDecimal profitCredit) {
    return process ->
        accountRepository
            .findByAccountNumber(process.getAccountNumber())
            .orElseThrow(AccountNotFoundException::new)
            .setBalance(balance)
            .setPrincipalDebit(principalDebit)
            .setPrincipalCredit(principalCredit)
            .setProfitDebit(profitDebit)
            .setProfitCredit(profitCredit)
            .setStatus(process.getOldStatus())
            .setLastProvisionDate(process.getLastProvisionDate())
            .setRenewalDate(process.getOldRenewalDate())
            .setExpiryDate(process.getOldExpiryDate())
            .setClosingDate(null);
  }
}
