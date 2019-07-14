package com.mislbd.ababil.treasury.command;

import com.mislbd.ababil.treasury.domain.Product;
import com.mislbd.asset.command.api.Command;

public class UpdateProductCommand extends Command<Product> {
  private Long id;

  public UpdateProductCommand(Product payload, Long id) {
    super(payload);
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public UpdateProductCommand setId(Long id) {
    this.id = id;
    return this;
  }
}
