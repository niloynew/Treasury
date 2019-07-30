package com.mislbd.ababil.treasury.repository.specification;

import com.mislbd.ababil.treasury.repository.schema.MonthendProductInfoEntity;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class MonthendProductSpecification {

  public static Specification<MonthendProductInfoEntity> getProvision(String accountNumber) {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();

      if (accountNumber != null) {
        predicate = cb.and(predicate, cb.equal(root.get("accNo"), accountNumber));
      }
      predicate = cb.and(predicate, cb.equal(root.get("glPosted"), true));
      predicate = cb.and(predicate, cb.equal(root.get("accPosted"), false));

      return predicate;
    };
  }
}
