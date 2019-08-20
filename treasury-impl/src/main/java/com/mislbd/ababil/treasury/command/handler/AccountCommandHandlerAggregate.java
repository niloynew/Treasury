package com.mislbd.ababil.treasury.command.handler;

import com.mislbd.ababil.asset.service.Auditor;
import com.mislbd.ababil.treasury.command.*;
import com.mislbd.ababil.treasury.domain.Account;
import com.mislbd.ababil.treasury.domain.AuditInformation;
import com.mislbd.ababil.treasury.exception.AccountNotFoundException;
import com.mislbd.ababil.treasury.exception.ProductNotFoundException;
import com.mislbd.ababil.treasury.repository.jpa.AccountRepository;
import com.mislbd.ababil.treasury.repository.jpa.ProductRepository;
import com.mislbd.ababil.treasury.service.AccountService;
import com.mislbd.ababil.treasury.service.TransactionalOperationService;
import com.mislbd.asset.command.api.Command;
import com.mislbd.asset.command.api.CommandEvent;
import com.mislbd.asset.command.api.CommandResponse;
import com.mislbd.asset.command.api.annotation.Aggregate;
import com.mislbd.asset.command.api.annotation.CommandHandler;
import com.mislbd.asset.command.api.annotation.CommandListener;
import com.mislbd.security.core.NgSession;
import java.time.LocalDate;
import org.springframework.transaction.annotation.Transactional;

@Aggregate
public class AccountCommandHandlerAggregate {

  private final AccountRepository accountRepository;
  private final ProductRepository productRepository;
  private final Auditor auditor;
  private final AccountService accountService;
  private final NgSession ngSession;
  private final TransactionalOperationService operationService;

  public AccountCommandHandlerAggregate(
      AccountRepository accountRepository,
      ProductRepository productRepository,
      Auditor auditor,
      AccountService accountService,
      NgSession ngSession,
      TransactionalOperationService operationService) {
    this.accountRepository = accountRepository;
    this.productRepository = productRepository;
    this.auditor = auditor;
    this.accountService = accountService;
    this.ngSession = ngSession;
    this.operationService = operationService;
  }

  @CommandListener(
      commandClasses = {CreateTreasuryAccountCommand.class, UpdateTreasuryAccountCommand.class})
  public void auditAccountCreateAndUpdate(CommandEvent e) {
    auditor.audit(e.getCommand().getPayload(), e.getCommand());
  }

  @CommandListener(commandClasses = {DeleteTreasuryAccountCommand.class})
  public void auditAccountDelete(CommandEvent e) {

    auditor.audit(accountService.findById((Long) e.getCommand().getPayload()), e.getCommand());
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Long> createAccount(CreateTreasuryAccountCommand command) {
    AuditInformation auditInformation = getAuditInformation(command);
    Long globalTxnNumber =
        operationService.dolPlacementTransaction(auditInformation, command.getPayload());
    return CommandResponse.of(globalTxnNumber);
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Long> renewOrSettlementAccount(
      SettlementOrCloseTreasuryAccountCommand command) {
    AuditInformation auditInformation = getAuditInformation(command);
    Long globalTxnNumber =
        operationService.doSettlementOrRenewTransaction(auditInformation, command.getPayload());
    accountService.registerTransactionProcess(
        command.getPayload().setGlobalTxnNumber(globalTxnNumber));
    return CommandResponse.of(globalTxnNumber);
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> updateAccount(UpdateTreasuryAccountCommand command)
      throws AccountNotFoundException {
    Account account = command.getPayload();
    accountRepository
        .save(
            accountRepository
                .findById(account.getId())
                .orElseThrow(AccountNotFoundException::new)
                .setId(account.getId())
                .setProduct(
                    productRepository
                        .findById(account.getProductId())
                        .orElseThrow(ProductNotFoundException::new)))
        .setCurrencyCode(account.getCurrencyCode())
        .setBankId(account.getBankId())
        .setBranchId(account.getBranchId())
        .setAccountTitle(account.getAccountTitle())
        .setNostroAccountNumber(account.getNostroAccountNumber())
        .setAmount(account.getAmount())
        .setAccountNumber(account.getAccountNumber())
        .setOpenDate(account.getAccountOpenDate())
        .setClosingDate(account.getAccountClosingDate())
        .setExpiryDate(account.getExpiryDate())
        .setTenorAmount(account.getTenorAmount())
        .setTenorType(account.getTenorType())
        .setRenewalDate(account.getRenewalDate())
        .setProfitRate(account.getExpectedProfitRate())
        .setStatus(account.getStatus())
        .setInstrumentNumber(account.getInstrument())
        .setActive(true);

    return CommandResponse.asVoid();
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Void> deleteAccount(DeleteTreasuryAccountCommand command) {
    accountRepository.save(
        accountRepository
            .findById(command.getPayload())
            .orElseThrow(AccountNotFoundException::new)
            .setActive(false));
    return CommandResponse.asVoid();
  }

  @Transactional
  @CommandHandler
  public CommandResponse<Long> reactivateAccount(ReactivateTreasuryAccountCommand command) {
    AuditInformation auditInformation = getAuditInformation(command);
    Long globalTxnNumber =
        operationService.doReactiveTransaction(auditInformation, command.getPayload());
    return CommandResponse.of(globalTxnNumber);
  }

  private AuditInformation getAuditInformation(Command<?> command) {
    AuditInformation auditInformation = new AuditInformation();
    auditInformation
        .setEntryUser(command.getInitiator())
        .setEntryTerminal(command.getInitiatorTerminal())
        .setVerifyUser(ngSession.getUsername())
        .setVerifyTerminal(ngSession.getTerminal())
        .setUserBranch(ngSession.getUserBranch().intValue())
        .setProcessId(command.getProcessId())
        .setEntryDate(LocalDate.now());
    return auditInformation;
  }
}
