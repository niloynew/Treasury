package com.mislbd.ababil.treasury.repository.jpa;

import com.mislbd.ababil.treasury.repository.schema.MonthendProductInfoEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MonthendProductInfoRepository
    extends JpaRepository<MonthendProductInfoEntity, Long>, JpaSpecificationExecutor {

  List<MonthendProductInfoEntity> findAllByGlobalTxnNumber(Long globalTxnNumber);
}
