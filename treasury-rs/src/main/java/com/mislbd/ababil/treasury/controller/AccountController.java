package com.mislbd.ababil.treasury.controller;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.treasury.command.CreateTreasuryAccountCommand;
import com.mislbd.ababil.treasury.command.DeleteTreasuryAccountCommand;
import com.mislbd.ababil.treasury.command.UpdateTreasuryAccountCommand;
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

import com.mislbd.security.core.NgSession;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

  private final CommandProcessor commandProcessor;
  private final QueryManager queryManager;
  private final AccountService accountService;
  private final NgSession ngSession;

  public AccountController(
          CommandProcessor commandProcessor, QueryManager queryManager, AccountService accountService, NgSession ngSession) {
    this.commandProcessor = commandProcessor;
    this.queryManager = queryManager;
    this.accountService = accountService;
    this.ngSession = ngSession;
  }

  @GetMapping()
  public ResponseEntity<?> getAccounts(
      Pageable pageable,
      @RequestParam(value = "asPage", required = false) final boolean asPage,
      @RequestParam(value = "accountNumber", required = false) final String accountNumber,
      @RequestParam(value = "accountTitle", required = false) final String accountTitle,
      @RequestParam(value = "productId", required = false) final Long productId,
      @RequestParam(value = "currencyCode", required = false) final String currencyCode,
      @RequestParam(value = "openDate", required = false)
          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          final LocalDate openDate,
      @RequestParam(value = "expiryDate", required = false) final LocalDate expiryDate,
      @RequestParam(value = "status", required = false) final AccountStatus status,
      @RequestParam(value = "ownerBranchId", required = false) final Long ownerBranchId) {

    QueryResult<?> queryResult =
        queryManager.executeQuery(
            new AccountQuery(
                asPage,
                pageable,
                accountNumber,
                accountTitle,
                productId,
                currencyCode,
                openDate,
                expiryDate,
                status,
                ownerBranchId==null?ngSession.getUserBranch():ownerBranchId ));
    if (queryResult.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(queryResult.getResult());
  }

  @GetMapping(path = "/{accountId}")
  public ResponseEntity<?> getAccount(@PathVariable Long accountId) {
    Account account = accountService.findById(accountId);
    return ResponseEntity.ok(account);
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CommandResponse<Long>> createAccounts(@Valid @RequestBody Account account) {
    return status(CREATED)
        .body(commandProcessor.executeResult(new CreateTreasuryAccountCommand(account)));
  }

  //  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  //  public ResponseEntity<CommandResponse<Long>> settleOrCloseAccounts(
  //      @Valid @RequestBody Account account) {
  //    return status(CREATED)
  //        .body(commandProcessor.executeResult(new SettlementTreasuryAccountCommand(account)));
  //  }

  @PutMapping(path = "/{accountId}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> updateAccount(
      @PathVariable("accountId") Long accountId, @Valid @RequestBody Account account) {
    commandProcessor.executeUpdate(new UpdateTreasuryAccountCommand(account, accountId));
    return status(ACCEPTED).build();
  }

  @DeleteMapping(path = "/{accountId}")
  public ResponseEntity<Void> deleteProduct(@PathVariable("accountId") Long accountId) {
    commandProcessor.executeUpdate(new DeleteTreasuryAccountCommand(accountId));
    return status(ACCEPTED).build();
  }
}
