package com.mislbd.ababil.treasury.repository.jpa;

import com.google.common.base.Optional;
import com.mislbd.ababil.treasury.repository.schema.ProductNatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductNatureRepository extends JpaRepository<ProductNatureEntity, Long> {
  Optional<ProductNatureEntity> findByDescription(String description);

  Optional<ProductNatureEntity> findByCode(String code);
}
