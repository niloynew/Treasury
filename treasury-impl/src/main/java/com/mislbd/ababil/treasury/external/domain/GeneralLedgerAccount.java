package com.mislbd.ababil.treasury.external.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GeneralLedgerAccount {
  private long id;
  private String code;
  private String name;
}
