package com.mislbd.ababil.treasury.command.handler;

import com.mislbd.ababil.treasury.command.CreateProductCommand;
import com.mislbd.ababil.treasury.command.DeleteProductCommand;
import com.mislbd.ababil.treasury.command.UpdateProductCommand;
import com.mislbd.ababil.treasury.exception.ProductNotFoundException;
import com.mislbd.ababil.treasury.mapper.ProductMapper;
import com.mislbd.ababil.treasury.repository.jpa.ProductRepository;
import com.mislbd.ababil.treasury.repository.schema.ProductEntity;
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

  @Transactional
  @CommandHandler
  public CommandResponse<Void> updateProduct(UpdateProductCommand command) {
    productMapper.domainToEntity().map(command.getPayload());
    return CommandResponse.asVoid();
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> deleteProduct(DeleteProductCommand command) {
    ProductEntity productEntity =
        productRepository.findById(command.getPayload()).orElseThrow(ProductNotFoundException::new);
    return CommandResponse.asVoid();
  }
}
