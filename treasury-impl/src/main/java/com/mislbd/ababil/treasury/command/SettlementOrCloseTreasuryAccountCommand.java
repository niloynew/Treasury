package com.mislbd.ababil.treasury.command;

import com.mislbd.ababil.treasury.domain.Account;
import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.annotation.CommandAttribute;

@CommandAttribute(name = "Settlement Treasury Account", group = "TREASURY")
public class SettlementOrCloseTreasuryAccountCommand extends Command<Account> {

  public SettlementOrCloseTreasuryAccountCommand(Account payload) {
    super(payload);
  }
}
