package com.mislbd.ababil.treasury.command;

import com.mislbd.ababil.treasury.domain.Account;
import com.mislbd.asset.command.api.Command;

public class CreateAccountCommand extends Command<Account> {
  public CreateAccountCommand(Account payload) {
    super(payload);
  }
}
