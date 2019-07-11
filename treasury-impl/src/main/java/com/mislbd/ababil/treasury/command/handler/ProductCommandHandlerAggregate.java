package com.mislbd.ababil.treasury.command.handler;

import com.mislbd.ababil.treasury.command.CreateProductCommand;
import com.mislbd.ababil.treasury.mapper.ProductMapper;
import com.mislbd.ababil.treasury.repository.jpa.ProductRepository;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class ProductCommandHandlerAggregate {
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  public ProductCommandHandlerAggregate(
      ProductRepository productRepository, ProductMapper productMapper) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Long> createProduct(CreateProductCommand command) {
    return CommandResponse.of(
        productRepository.save(productMapper.domainToEntity().map(command.getPayload())).getId());
  }
}
