package com.mislbd.ababil.treasury.service;

import com.mislbd.ababil.asset.service.ConfigurationService;
import com.mislbd.ababil.treasury.repository.jpa.MonthendProductInfoRepository;
import com.mislbd.ababil.treasury.repository.schema.MonthendProductInfoEntity;
import com.mislbd.ababil.treasury.repository.specification.MonthendProductSpecification;
import java.math.BigDecimal;
import java.util.List;

import com.mislbd.security.core.NgSession;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class UtilityServiceImpl implements UtilityService {

  private final MonthendProductInfoRepository monthendProductInfoRepository;
  private final ConfigurationService configurationService;
  private final NgSession ngSession;

  public UtilityServiceImpl(MonthendProductInfoRepository monthendProductInfoRepository, ConfigurationService configurationService, NgSession ngSession) {
    this.monthendProductInfoRepository = monthendProductInfoRepository;
    this.configurationService = configurationService;
    this.ngSession = ngSession;
  }

  @Override
  public BigDecimal totalProvisionOfAccounts(
      String accountNumber, boolean glPosted, boolean accountPosted) {
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
      String shadowAccountNumber, boolean glPosted, boolean accountPosted) {
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

  @Override
  public String generateAccountNumber(Long productId, Long branchId) {
    int branchCodeLength = configurationService.getBranchCodeLength();
    int productCodeLength = configurationService.getProductCodeLength();
    int accountSerialDigit = configurationService.
    int accountCodeLength = configurationService.getAccountNumberLength();

    String branchCode = StringUtils.leftPad(String.valueOf(branchId), branchCodeLength, "0");
    int totalAccountPrefix = branchCodeLength+productCodeLength+1;

    return null;
  }

  private List<MonthendProductInfoEntity> getMonthendProductInfoData(
      String accountNumber, Boolean glPosted, Boolean accPosted) {
    return monthendProductInfoRepository.findAll(
        MonthendProductSpecification.getProvision(accountNumber, glPosted, accPosted));
  }
}
