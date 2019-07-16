package com.mislbd.ababil.treasury.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.treasury.command.CreateProductCommand;
import com.mislbd.ababil.treasury.command.DeleteProductCommand;
import com.mislbd.ababil.treasury.command.UpdateProductCommand;
import com.mislbd.ababil.treasury.domain.Product;
import com.mislbd.ababil.treasury.query.ProductQuery;
import com.mislbd.ababil.treasury.service.ProductService;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.query.api.QueryManager;
import com.mislbd.asset.query.api.QueryResult;
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
  private final QueryManager queryManager;

  public ProductController(
      CommandProcessor commandProcessor, ProductService productService, QueryManager queryManager) {
    this.commandProcessor = commandProcessor;
    this.productService = productService;
    this.queryManager = queryManager;
  }

  @GetMapping()
  public ResponseEntity<?> getProducts(
      Pageable pageable,
      @RequestParam(value = "asPage", required = false) final boolean asPage,
      @RequestParam(value = "name", required = false) final String name,
      @RequestParam(value = "code", required = false) final String code) {
    //    if (asPage) {
    //      PagedResult<Product> pagedProducts = productService.findProducts(pageable, name, code);
    //      return ResponseEntity.ok(pagedProducts);
    //    } else {
    //      List<Product> products = productService.findProducts(name, code);
    //      return ResponseEntity.ok(products);
    //    }

    if (asPage) {
      QueryResult<?> queryResult =
          queryManager.executeQuery(new ProductQuery(asPage, pageable, name, code));
      if (queryResult.isEmpty()) {
        return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(queryResult.getResult());
    } else {
      QueryResult<?> queryResult =
          queryManager.executeQuery(new ProductQuery(false, null, name, code));
      if (queryResult.isEmpty()) {
        return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(queryResult.getResult());
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
