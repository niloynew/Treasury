package com.mislbd.ababil.treasury.repository.specification;

import com.mislbd.ababil.treasury.repository.schema.MonthendProductInfoEntity;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class MonthendProductSpecification {

  public static Specification<MonthendProductInfoEntity> getProvision(
      String accountNumber, Boolean glPosted, Boolean accPosted) {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();

      if (accountNumber != null) {
        predicate = cb.and(predicate, cb.equal(root.get("accNo"), accountNumber));
      }
      if (glPosted != null) {
        predicate = cb.and(predicate, cb.equal(root.get("glPosted"), glPosted));
      }
      if (accPosted != null) {
        predicate = cb.and(predicate, cb.equal(root.get("accPosted"), accPosted));
      }

      return predicate;
    };
  }
}
