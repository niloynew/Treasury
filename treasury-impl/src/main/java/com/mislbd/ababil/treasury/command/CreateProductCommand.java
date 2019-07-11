package com.mislbd.ababil.treasury.command;

import com.mislbd.ababil.treasury.domain.Product;
import com.mislbd.asset.command.api.Command;

public class CreateProductCommand extends Command<Product> {
  public CreateProductCommand(Product payload) {
    super(payload);
  }
}
