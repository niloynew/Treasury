package com.mislbd.ababil.treasury.command;

import com.mislbd.ababil.treasury.domain.Account;
import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.annotation.CommandAttribute;

@CommandAttribute(name = "Reactivate Treasury Account", group = "TREASURY")
public class ReactivateTreasuryAccountCommand extends Command<Account> {

  public ReactivateTreasuryAccountCommand(Account payload) {
    super(payload);
  }
}
