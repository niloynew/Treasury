package com.mislbd.ababil.treasury.service;

import com.mislbd.ababil.treasury.repository.jpa.MonthendProductInfoRepository;
import com.mislbd.ababil.treasury.repository.schema.MonthendProductInfoEntity;
import com.mislbd.ababil.treasury.repository.specification.MonthendProductSpecification;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UtilityServiceImpl implements UtilityService {

  private final MonthendProductInfoRepository monthendProductInfoRepository;

  public UtilityServiceImpl(MonthendProductInfoRepository monthendProductInfoRepository) {
    this.monthendProductInfoRepository = monthendProductInfoRepository;
  }

  @Override
  public BigDecimal totalProvisionOfAccounts(String accountNumber) {
    List<MonthendProductInfoEntity> productInfoEntities =
        monthendProductInfoRepository.findAll(
            MonthendProductSpecification.getProvision(accountNumber));
    return !productInfoEntities.isEmpty()
        ? BigDecimal.valueOf(
            productInfoEntities
                .stream()
                .mapToDouble(entity -> entity.getProvisionAmount().doubleValue())
                .sum())
        : BigDecimal.ZERO;
  }
}
