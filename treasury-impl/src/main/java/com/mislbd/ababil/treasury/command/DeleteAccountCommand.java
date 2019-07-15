package com.mislbd.ababil.treasury.command;

import com.mislbd.asset.command.api.Command;

public class DeleteAccountCommand extends Command<Long> {
  public DeleteAccountCommand(Long payload) {
    super(payload);
  }
}
