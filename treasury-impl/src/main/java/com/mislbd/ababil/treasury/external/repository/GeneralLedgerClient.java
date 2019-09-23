package com.mislbd.ababil.treasury.external.repository;

import com.mislbd.ababil.treasury.external.domain.GeneralLedgerAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "ababil-general-ledger", url = "${mislbd.api.gatewayUrl}/ababil-general-ledger")
public interface GeneralLedgerClient {

  @RequestMapping(
      method = RequestMethod.GET,
      value = "/general-ledger-accounts/{generalLedgerAccountId}")
  ResponseEntity<GeneralLedgerAccount> getGeneralLedgerAccount(
      @PathVariable("generalLedgerAccountId") Long id);
}
