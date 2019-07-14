package com.mislbd.ababil.treasury.repository.specification;

import com.mislbd.ababil.treasury.domain.AccountStatus;
import com.mislbd.ababil.treasury.repository.schema.AccountEntity;
import java.time.LocalDate;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecification {
  public static Specification<AccountEntity> findAccount(
      Long productId,
      String currencyCode,
      LocalDate openDate,
      LocalDate expiryDate,
      AccountStatus status) {
    return (root, query, cb) -> {
      Predicate predicate = cb.conjunction();

      if (productId != null) {
        predicate = cb.and(predicate, cb.equal(root.get("productId"), productId));
      }
      if (currencyCode != null) {
        predicate = cb.and(predicate, cb.equal(root.get("currencyCode"), currencyCode));
      }
      if (openDate != null) {
        predicate = cb.and(predicate, cb.equal(root.get("openDate"), openDate));
      }
      if (expiryDate != null) {
        predicate = cb.and(predicate, cb.equal(root.get("expiryDate"), expiryDate));
      }
      if (status != null) {
        predicate = cb.and(predicate, cb.equal(root.get("status"), status));
      }

      return predicate;
    };
  }
}
