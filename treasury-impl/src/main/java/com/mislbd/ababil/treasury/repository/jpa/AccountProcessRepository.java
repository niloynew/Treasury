package com.mislbd.ababil.treasury.repository.jpa;

import com.mislbd.ababil.treasury.domain.AccountStatus;
import com.mislbd.ababil.treasury.repository.schema.AccountProcessEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccountProcessRepository
    extends JpaRepository<AccountProcessEntity, Long>, JpaSpecificationExecutor {

  Optional<AccountProcessEntity> findByAccountNumberAndNewStatusAndValid(
      String accountNumber, AccountStatus status, Boolean isValid);
}
