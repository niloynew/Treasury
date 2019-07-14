package com.mislbd.ababil.treasury.command.handler;

import static com.mislbd.ababil.treasury.domain.ProductStatus.INACTIVE;

import com.mislbd.ababil.treasury.command.CreateProductCommand;
import com.mislbd.ababil.treasury.command.DeleteProductCommand;
import com.mislbd.ababil.treasury.command.UpdateProductCommand;
import com.mislbd.ababil.treasury.domain.Product;
import com.mislbd.ababil.treasury.exception.ProductNotFoundException;
import com.mislbd.ababil.treasury.mapper.ProductMapper;
import com.mislbd.ababil.treasury.repository.jpa.ProductNatureRepository;
import com.mislbd.ababil.treasury.repository.jpa.ProductRepository;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class ProductCommandHandlerAggregate {
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final ProductNatureRepository productNatureRepository;

  public ProductCommandHandlerAggregate(
      ProductRepository productRepository,
      ProductMapper productMapper,
      ProductNatureRepository productNatureRepository) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
    this.productNatureRepository = productNatureRepository;
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
    Product product = command.getPayload();
    productRepository.save(
        productRepository
            .findById(product.getId())
            .orElseThrow(ProductNotFoundException::new)
            .setId(product.getId())
            .setCode(product.getCode())
            .setName(product.getName())
            .setProfitApplicable(product.isProfitApplicable())
            .setProductNature(
                productNatureRepository.findByCode(product.getProductNature().getCode()).get())
            .setProfitCalculationMethod(product.getProfitCalculationMethod())
            .setDaysInYear(product.getDaysInYear())
            .setStatus(product.getStatus()));
    return CommandResponse.asVoid();
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> deleteProduct(DeleteProductCommand command) {
    productRepository.save(
        productRepository
            .findById(command.getPayload())
            .orElseThrow(ProductNotFoundException::new)
            .setStatus(INACTIVE));
    return CommandResponse.asVoid();
  }
}
