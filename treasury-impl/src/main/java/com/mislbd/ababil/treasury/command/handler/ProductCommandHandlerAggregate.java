package com.mislbd.ababil.treasury.command.handler;

import static com.mislbd.ababil.treasury.domain.ProductStatus.INACTIVE;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.treasury.command.CreateTreasuryProductCommand;
import com.mislbd.ababil.treasury.command.DeleteTreasuryProductCommand;
import com.mislbd.ababil.treasury.command.UpdateTreasuryProductCommand;
import com.mislbd.ababil.treasury.domain.Product;
import com.mislbd.ababil.treasury.exception.DuplicateProductCodeException;
import com.mislbd.ababil.treasury.exception.DuplicateProductNameException;
import com.mislbd.ababil.treasury.exception.ProductNatureNotFoundException;
import com.mislbd.ababil.treasury.exception.ProductNotFoundException;
import com.mislbd.ababil.treasury.mapper.ProductGLMapper;
import com.mislbd.ababil.treasury.mapper.ProductMapper;
import com.mislbd.ababil.treasury.repository.jpa.ProductNatureRepository;
import com.mislbd.ababil.treasury.repository.jpa.ProductRelatedGLRepository;
import com.mislbd.ababil.treasury.repository.jpa.ProductRepository;
import com.mislbd.ababil.treasury.service.ProductService;
import com.mislbd.asset.command.api.CommandEvent;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import com.mislbd.asset.command.api.annotation.CommandListener;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class ProductCommandHandlerAggregate {
  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final ProductNatureRepository productNatureRepository;
  private final ProductRelatedGLRepository productRelatedGLRepository;
  private final ProductGLMapper productGLMapper;
  private final Auditor auditor;
  private final ProductService productService;

  public ProductCommandHandlerAggregate(
      ProductRepository productRepository,
      ProductMapper productMapper,
      ProductNatureRepository productNatureRepository,
      ProductRelatedGLRepository productRelatedGLRepository,
      ProductGLMapper productGLMapper,
      Auditor auditor,
      ProductService productService) {
    this.productRepository = productRepository;
    this.productMapper = productMapper;
    this.productNatureRepository = productNatureRepository;
    this.productRelatedGLRepository = productRelatedGLRepository;
    this.productGLMapper = productGLMapper;
    this.auditor = auditor;
    this.productService = productService;
  }

  @CommandListener(
      commandClasses = {CreateTreasuryProductCommand.class, UpdateTreasuryProductCommand.class})
  public void auditProductCreateAndUpdate(CommandEvent e) {
    auditor.audit(e.getCommand().getPayload(), e.getCommand());
  }

  @CommandListener(commandClasses = {DeleteTreasuryProductCommand.class})
  public void auditProductDelete(CommandEvent e) {
    auditor.audit(productService.findProduct((Long) e.getCommand().getPayload()), e.getCommand());
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Long> createProduct(CreateTreasuryProductCommand command) {
    if (productRepository.existsByCode(command.getPayload().getCode())) {
      throw new DuplicateProductCodeException();
    }
    if (productRepository.existsByName(command.getPayload().getName())) {
      throw new DuplicateProductNameException();
    }
    long productId =
        productRepository.save(productMapper.domainToEntity().map(command.getPayload())).getId();
    command
        .getPayload()
        .getProductGeneralLedgerMappingList()
        .forEach(
            glMap -> {
              glMap.setProductId(productId);
              productRelatedGLRepository.save(productGLMapper.domainToEntity().map(glMap));
            });
    return CommandResponse.of(productId);
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> updateProduct(UpdateTreasuryProductCommand command) {
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
                productNatureRepository
                    .findById(product.getProductNatureId())
                    .orElseThrow(ProductNatureNotFoundException::new))
            .setProfitCalculationMethod(product.getProfitCalculationMethod())
            .setDaysInYear(product.getDaysInYear())
            .setStatus(product.getStatus()));

    if (!productRelatedGLRepository.findAllByProductId(product.getId()).isEmpty())
      productRelatedGLRepository.deleteAllByProductId(product.getId());
    product
        .getProductGeneralLedgerMappingList()
        .forEach(
            glMap -> {
              glMap.setProductId(product.getId());
              productRelatedGLRepository.save(productGLMapper.domainToEntity().map(glMap));
            });
    return CommandResponse.asVoid();
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> deleteProduct(DeleteTreasuryProductCommand command) {
    productRepository.save(
        productRepository
            .findById(command.getPayload())
            .orElseThrow(ProductNotFoundException::new)
            .setStatus(INACTIVE));
    return CommandResponse.asVoid();
  }
}
