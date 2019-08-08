package com.mislbd.ababil.treasury.repository.jpa;

import com.mislbd.ababil.treasury.repository.schema.AccountEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccountRepository
    extends JpaRepository<AccountEntity, Long>, JpaSpecificationExecutor {
  Optional<AccountEntity> findByAccountNumberAndOwnerBranchId(String accountNumber, Long branchId);
}
