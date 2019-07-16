package com.mislbd.ababil.treasury.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.treasury.command.CreateProductCommand;
import com.mislbd.ababil.treasury.command.DeleteProductCommand;
import com.mislbd.ababil.treasury.command.UpdateProductCommand;
import com.mislbd.ababil.treasury.domain.Product;
import com.mislbd.ababil.treasury.service.ProductService;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
  private final CommandProcessor commandProcessor;
  private final ProductService productService;

  public ProductController(CommandProcessor commandProcessor, ProductService productService) {
    this.commandProcessor = commandProcessor;
    this.productService = productService;
    //
  }

  @GetMapping()
  public ResponseEntity<?> getProducts(
      Pageable pageable,
      @RequestParam(value = "asPage", required = false) final boolean asPage,
      @RequestParam(value = "name", required = false) final String name,
      @RequestParam(value = "code", required = false) final String code) {
    if (asPage) {
      PagedResult<Product> pagedProducts = productService.findProducts(pageable, name, code);
      return ResponseEntity.ok(pagedProducts);
    } else {
      List<Product> products = productService.findProducts(name, code);
      return ResponseEntity.ok(products);
    }
  }

  @GetMapping(path = "/{productId}")
  public ResponseEntity<Product> getIDProduct(@PathVariable("productId") Long productId) {
    return productService
        .findProduct(productId)
        .map(ResponseEntity::ok)
        .orElseGet(status(NOT_FOUND)::build);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CommandResponse<Long>> createProduct(@Valid @RequestBody Product product) {
    return status(CREATED).body(commandProcessor.executeResult(new CreateProductCommand(product)));
  }

  @PutMapping(path = "/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> updateProduct(
      @PathVariable("productId") Long productId, @Valid @RequestBody Product product) {
    commandProcessor.executeUpdate(new UpdateProductCommand(product, productId));
    return status(ACCEPTED).build();
  }

  @DeleteMapping(path = "/{productId}")
  public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId) {
    commandProcessor.executeUpdate(new DeleteProductCommand(productId));
    return status(ACCEPTED).build();
  }
}
