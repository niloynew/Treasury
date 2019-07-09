package com.mislbd.ababil.treasury.repository.jpa;

import com.mislbd.ababil.treasury.repository.schema.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
