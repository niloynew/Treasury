package com.mislbd.ababil.treasury.command;

import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.annotation.CommandAttribute;

@CommandAttribute(name = "Delete Treasury Product", group = "TREASURY")
public class DeleteTreasuryProductCommand extends Command<Long> {
  public DeleteTreasuryProductCommand(Long payload) {
    super(payload);
  }
}
