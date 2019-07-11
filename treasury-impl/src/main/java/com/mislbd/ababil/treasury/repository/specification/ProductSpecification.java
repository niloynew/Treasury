package com.mislbd.ababil.treasury.repository.specification;

import com.mislbd.ababil.treasury.domain.ProductStatus;
import com.mislbd.ababil.treasury.repository.schema.ProductEntity;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

  public static Specification<ProductEntity> findProduct(
      String name, String code, ProductStatus status) {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();

      if (name != null) {
        predicate =
            cb.and(predicate, cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
      }

      if (code != null) {
        predicate = cb.and(predicate, cb.equal(root.get("code"), code));
      }

      if (status != null) {
        predicate = cb.and(predicate, cb.equal(root.get("status"), status));
      }

      return predicate;
    };
  }
}
