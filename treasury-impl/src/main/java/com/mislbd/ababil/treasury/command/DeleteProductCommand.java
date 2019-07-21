package com.mislbd.ababil.treasury.command;

import com.mislbd.asset.command.api.Command;

public class DeleteProductCommand extends Command<Long> {
  public DeleteProductCommand(Long payload) {
    super(payload);
  }
}
