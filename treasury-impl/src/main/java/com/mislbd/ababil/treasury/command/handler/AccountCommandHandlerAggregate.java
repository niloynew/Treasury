package com.mislbd.ababil.treasury.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.treasury.command.CreateAccountCommand;
import com.mislbd.ababil.treasury.command.DeleteAccountCommand;
import com.mislbd.ababil.treasury.command.UpdateAccountCommand;
import com.mislbd.ababil.treasury.domain.Account;
import com.mislbd.ababil.treasury.domain.AccountStatus;
import com.mislbd.ababil.treasury.exception.AccountNotFoundException;
import com.mislbd.ababil.treasury.exception.ProductNotFoundException;
import com.mislbd.ababil.treasury.mapper.AccountMapper;
import com.mislbd.ababil.treasury.repository.jpa.AccountRepository;
import com.mislbd.ababil.treasury.repository.jpa.ProductRepository;
import com.mislbd.asset.command.api.CommandEvent;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import com.mislbd.asset.command.api.annotation.CommandListener;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class AccountCommandHandlerAggregate {

  private final AccountRepository accountRepository;
  private final AccountMapper accountMapper;
  private final ProductRepository productRepository;
  private final Auditor auditor;

  public AccountCommandHandlerAggregate(
      AccountRepository accountRepository,
      AccountMapper accountMapper,
      ProductRepository productRepository,
      Auditor auditor) {
    this.accountRepository = accountRepository;
    this.accountMapper = accountMapper;
    this.productRepository = productRepository;
    this.auditor = auditor;
  }

  @CommandListener(commandClasses = {CreateAccountCommand.class, UpdateAccountCommand.class})
  public void auditChargeCommandListener(CommandEvent e) {
    auditor.audit(e.getCommand().getPayload(), e.getCommand());
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Long> createAccount(CreateAccountCommand command) {
    return CommandResponse.of(
        accountRepository.save(accountMapper.domainToEntity().map(command.getPayload())).getId());
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> updateAccount(UpdateAccountCommand command)
      throws AccountNotFoundException {
    Account account = command.getPayload();
    accountRepository
        .save(
            accountRepository
                .findById(account.getId())
                .orElseThrow(AccountNotFoundException::new)
                .setId(account.getId())
                .setProduct(
                    productRepository
                        .findById(account.getProductId())
                        .orElseThrow(ProductNotFoundException::new)))
        .setCurrencyCode(account.getCurrencyCode())
        .setBankId(account.getBankId())
        .setBranchId(account.getBranchId())
        .setAccountTitle(account.getAccountTitle())
        .setAccountNumber(account.getAccountNumber())
        .setAmount(account.getAmount())
        .setShadowAccountNumber(account.getShadowAccountNumber())
        .setAccountOpenDate(account.getAccountOpenDate())
        .setExpiryDate(account.getExpiryDate())
        .setTenorAmount(account.getTenorAmount())
        .setTenorType(account.getTenorType())
        .setRenewalDate(account.getRenewalDate())
        .setExpectedProfitRate(account.getExpectedProfitRate())
        .setStatus(AccountStatus.REGULAR)
        .setInstrument(account.getInstrument())
        .setActive(true);

    return CommandResponse.asVoid();
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> deleteAccount(DeleteAccountCommand command) {
    accountRepository.save(
        accountRepository
            .findById(command.getPayload())
            .orElseThrow(AccountNotFoundException::new)
            .setActive(false));
    return CommandResponse.asVoid();
  }
}
