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
  public BigDecimal totalProvisionOfAccounts(
      String accountNumber, Boolean glPosted, Boolean accountPosted) {
    List<MonthendProductInfoEntity> productInfoEntities =
        getMonthendProductInfoData(accountNumber, glPosted, accountPosted);
    return !productInfoEntities.isEmpty()
        ? BigDecimal.valueOf(
            productInfoEntities
                .stream()
                .mapToDouble(entity -> entity.getProvisionAmount().doubleValue())
                .sum())
        : BigDecimal.ZERO;
  }

  @Override
  public void updateMonthendInfo(String accountNumber, String event, boolean accountPosted) {
    List<MonthendProductInfoEntity> productInfoEntities =
        getMonthendProductInfoData(accountNumber, null, false);
    productInfoEntities
        .stream()
        .forEach(
            object ->
                monthendProductInfoRepository.save(
                    object.setProfitPostingEvent(event).setGlPosted(true).setAccPosted(true)));
  }

  @Override
  public BigDecimal totalProductOfAccounts(
      String shadowAccountNumber, Boolean glPosted, Boolean accountPosted) {
    List<MonthendProductInfoEntity> productInfoEntities =
        getMonthendProductInfoData(shadowAccountNumber, glPosted, accountPosted);
    return !productInfoEntities.isEmpty()
        ? BigDecimal.valueOf(
            productInfoEntities
                .stream()
                .mapToDouble(entity -> entity.getAccProduct().doubleValue())
                .sum())
        : BigDecimal.ZERO;
  }

  private List<MonthendProductInfoEntity> getMonthendProductInfoData(
      String accountNumber, Boolean glPosted, Boolean accPosted) {
    return monthendProductInfoRepository.findAll(
        MonthendProductSpecification.getProvision(accountNumber, glPosted, accPosted));
  }
}
