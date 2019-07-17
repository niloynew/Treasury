package com.mislbd.ababil.treasury.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.treasury.command.CreateAccountCommand;
import com.mislbd.ababil.treasury.command.DeleteAccountCommand;
import com.mislbd.ababil.treasury.command.UpdateAccountCommand;
import com.mislbd.ababil.treasury.domain.Account;
import com.mislbd.ababil.treasury.domain.AccountStatus;
import com.mislbd.ababil.treasury.query.AccountQuery;
import com.mislbd.ababil.treasury.service.AccountService;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.query.api.QueryManager;
import com.mislbd.asset.query.api.QueryResult;
import java.time.LocalDate;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

  private final CommandProcessor commandProcessor;
  private final QueryManager queryManager;
  private final AccountService accountService;

  public AccountController(
      CommandProcessor commandProcessor, QueryManager queryManager, AccountService accountService) {
    this.commandProcessor = commandProcessor;

    this.queryManager = queryManager;

    this.accountService = accountService;
  }

  @GetMapping()
  public ResponseEntity<?> getAccounts(
      Pageable pageable,
      @RequestParam(value = "asPage", required = false) final boolean asPage,
      @RequestParam(value = "productId", required = false) final Long productId,
      @RequestParam(value = "currencyCode", required = false) final String currencyCode,
      @RequestParam(value = "openDate", required = false) final LocalDate openDate,
      @RequestParam(value = "expiryDate", required = false) final LocalDate expiryDate,
      @RequestParam(value = "status", required = false) final AccountStatus status) {

    if (asPage) {
      QueryResult<?> queryResult =
          queryManager.executeQuery(
              new AccountQuery(pageable, productId, currencyCode, openDate, expiryDate, status));
      if (queryResult.isEmpty()) {
        return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(queryResult.getResult());

    } else {
      QueryResult<?> queryResult =
          queryManager.executeQuery(
              new AccountQuery(productId, currencyCode, openDate, expiryDate, status));
      if (queryResult.isEmpty()) {
        return ResponseEntity.noContent().build();
      }
      return ResponseEntity.ok(queryResult.getResult());
    }
  }

  @GetMapping(path = "/{accountId}")
  public ResponseEntity<?> getAccount(@PathVariable Long accountId) {
    Account account = accountService.findById(accountId);
    return ResponseEntity.ok(account);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CommandResponse<Long>> createAccounts(@Valid @RequestBody Account account) {
    return status(CREATED).body(commandProcessor.executeResult(new CreateAccountCommand(account)));
  }

  @PutMapping(path = "/{accountId}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> updateAccount(
      @PathVariable("accountId") Long accountId, @Valid @RequestBody Account account) {
    commandProcessor.executeUpdate(new UpdateAccountCommand(account, accountId));
    return status(ACCEPTED).build();
  }

  @DeleteMapping(path = "/{accountId}")
  public ResponseEntity<Void> deleteProduct(@PathVariable("accountId") Long accountId) {
    commandProcessor.executeUpdate(new DeleteAccountCommand(accountId));
    return status(ACCEPTED).build();
  }
}
