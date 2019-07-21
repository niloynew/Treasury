package com.mislbd.ababil.treasury.repository.jpa;

import com.mislbd.ababil.treasury.repository.schema.TransactionRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRecordRepository extends JpaRepository<TransactionRecordEntity, Long> {}
