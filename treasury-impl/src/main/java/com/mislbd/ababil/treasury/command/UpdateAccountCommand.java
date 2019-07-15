package com.mislbd.ababil.treasury.command;

import com.mislbd.ababil.treasury.domain.Account;
import com.mislbd.asset.command.api.Command;

public class UpdateAccountCommand extends Command<Account> {
  private Long id;

  public UpdateAccountCommand(Account payload, Long id) {
    super(payload);
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public UpdateAccountCommand setId(Long id) {
    this.id = id;
    return this;
  }
}
