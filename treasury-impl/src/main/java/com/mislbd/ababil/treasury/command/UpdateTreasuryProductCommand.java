package com.mislbd.ababil.treasury.command;

import com.mislbd.ababil.treasury.domain.Product;
import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.annotation.CommandAttribute;

@CommandAttribute(name = "Update Treasury Product", group = "TREASURY")
public class UpdateTreasuryProductCommand extends Command<Product> {
  private Long id;

  public UpdateTreasuryProductCommand(Product payload, Long id) {
    super(payload);
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public UpdateTreasuryProductCommand setId(Long id) {
    this.id = id;
    return this;
  }
}
