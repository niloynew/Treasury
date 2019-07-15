package com.mislbd.ababil.treasury.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.treasury.command.CreateAccountCommand;
import com.mislbd.ababil.treasury.command.DeleteAccountCommand;
import com.mislbd.ababil.treasury.command.UpdateAccountCommand;
import com.mislbd.ababil.treasury.domain.Account;
import com.mislbd.ababil.treasury.domain.AccountStatus;
import com.mislbd.ababil.treasury.service.AccountService;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.commons.data.domain.PagedResult;
import java.time.LocalDate;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

  private final CommandProcessor commandProcessor;
  private final AccountService accountService;

  public AccountController(CommandProcessor commandProcessor, AccountService accountService) {
    this.commandProcessor = commandProcessor;
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
      PagedResult<Account> pagedAccounts =
          accountService.findAccounts(
              pageable, productId, currencyCode, openDate, expiryDate, status);
      return ResponseEntity.ok(pagedAccounts);
    } else {
      List<Account> accounts =
          accountService.findAccounts(productId, currencyCode, openDate, expiryDate, status);
      return ResponseEntity.ok(accounts);
    }
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
  public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long accountId) {
    commandProcessor.executeUpdate(new DeleteAccountCommand(accountId));
    return status(ACCEPTED).build();
  }
}
