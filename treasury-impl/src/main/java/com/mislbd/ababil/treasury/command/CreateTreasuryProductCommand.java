package com.mislbd.ababil.treasury.command;

import com.mislbd.ababil.treasury.domain.Product;
import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.annotation.CommandAttribute;

@CommandAttribute(name = "Create Treasury Product", group = "TREASURY")
public class CreateTreasuryProductCommand extends Command<Product> {
  public CreateTreasuryProductCommand(Product payload) {
    super(payload);
  }
}
