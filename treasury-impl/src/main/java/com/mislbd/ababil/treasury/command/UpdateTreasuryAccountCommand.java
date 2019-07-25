package com.mislbd.ababil.treasury.command;

import com.mislbd.ababil.treasury.domain.Account;
import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.annotation.CommandAttribute;

@CommandAttribute(name = "Update Treasury Account", group = "TREASURY")
public class UpdateTreasuryAccountCommand extends Command<Account> {
  private Long id;

  public UpdateTreasuryAccountCommand(Account payload, Long id) {
    super(payload);
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public UpdateTreasuryAccountCommand setId(Long id) {
    this.id = id;
    return this;
  }
}
