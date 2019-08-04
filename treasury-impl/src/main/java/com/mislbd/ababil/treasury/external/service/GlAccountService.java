package com.mislbd.ababil.treasury.external.service;

import com.mislbd.ababil.treasury.exception.GeneralLedgerAccountNotFoundException;
import com.mislbd.ababil.treasury.external.domain.GeneralLedgerAccount;
import com.mislbd.ababil.treasury.external.repository.GeneralLedgerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GlAccountService {

  private final GeneralLedgerClient generalLedgerClient;

  public GlAccountService(GeneralLedgerClient generalLedgerClient) {
    this.generalLedgerClient = generalLedgerClient;
  }

  public GeneralLedgerAccount getGlAccount(Long id) {
    ResponseEntity<GeneralLedgerAccount> glAccount =
        generalLedgerClient.getGeneralLedgerAccount(id);
    if (glAccount.getStatusCode() == HttpStatus.NOT_FOUND)
      throw new GeneralLedgerAccountNotFoundException("GL account not exists with id-" + id);
    return glAccount.getBody();
  }
}
