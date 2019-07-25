package com.mislbd.ababil.treasury.command;

import com.mislbd.ababil.treasury.domain.Account;
import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.annotation.CommandAttribute;

@CommandAttribute(name = "Create Treasury Account", group = "TREASURY")
public class CreateTreasuryAccountCommand extends Command<Account> {
  public CreateTreasuryAccountCommand(Account payload) {
    super(payload);
  }
}
