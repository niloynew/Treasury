package com.mislbd.ababil.treasury.command;

import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.annotation.CommandAttribute;

@CommandAttribute(name = "Delete Treasury Account", group = "TREASURY")
public class DeleteTreasuryAccountCommand extends Command<Long> {
  public DeleteTreasuryAccountCommand(Long payload) {
    super(payload);
  }
}
