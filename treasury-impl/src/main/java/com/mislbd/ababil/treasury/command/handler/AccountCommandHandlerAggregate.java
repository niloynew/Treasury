package com.mislbd.ababil.treasury.command.handler;

import com.mislbd.ababil.treasury.command.CreateAccountCommand;
import com.mislbd.ababil.treasury.mapper.AccountMapper;
import com.mislbd.ababil.treasury.repository.jpa.AccountRepository;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class AccountCommandHandlerAggregate {

  private final AccountRepository accountRepository;
  private final AccountMapper accountMapper;

  public AccountCommandHandlerAggregate(
      AccountRepository accountRepository, AccountMapper accountMapper) {
    this.accountRepository = accountRepository;
    this.accountMapper = accountMapper;
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Long> createAccount(CreateAccountCommand command) {
    return CommandResponse.of(
        accountRepository.save(accountMapper.domainToEntity().map(command.getPayload())).getId());
  }
}
