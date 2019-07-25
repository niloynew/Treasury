package com.mislbd.ababil.treasury.repository.jpa;

import com.mislbd.ababil.treasury.domain.GLType;
import com.mislbd.ababil.treasury.repository.schema.ProductRelatedGLEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRelatedGLRepository
    extends JpaRepository<ProductRelatedGLEntity, Long>, JpaSpecificationExecutor {

  List<ProductRelatedGLEntity> findAllByProductId(long productId);

  void deleteAllByProductId(long productId);

  Optional<ProductRelatedGLEntity> findByProductIdAndGlType(Long productId, GLType glType);
}
