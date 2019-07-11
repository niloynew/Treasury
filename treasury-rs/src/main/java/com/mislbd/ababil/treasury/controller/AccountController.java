package com.mislbd.ababil.treasury.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.status;

import com.mislbd.ababil.treasury.command.CreateAccountCommand;
import com.mislbd.ababil.treasury.domain.Account;
import com.mislbd.asset.command.api.CommandProcessor;
import com.mislbd.asset.command.api.CommandResponse;
import javax.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

  private final CommandProcessor commandProcessor;

  public AccountController(CommandProcessor commandProcessor) {
    this.commandProcessor = commandProcessor;
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<CommandResponse<Long>> createAccounts(@Valid @RequestBody Account account) {
    return status(CREATED).body(commandProcessor.executeResult(new CreateAccountCommand(account)));
  }
}
