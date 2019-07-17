package com.mislbd.ababil.treasury.query;

import com.mislbd.ababil.treasury.domain.AccountStatus;
import com.mislbd.asset.query.api.QueryRequest;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountQuery extends QueryRequest {

    private boolean asPage;
    private Pageable pageable;
    private Long productId;
    private String currencyCode;
    private LocalDate openDate;
    private LocalDate expiryDate;
    private AccountStatus status;
    private Long accountId;

    public AccountQuery(
            Long productId,
            String currencyCode,
            LocalDate openDate,
            LocalDate expiryDate,
            AccountStatus status) {
        this.productId = productId;
        this.currencyCode = currencyCode;
        this.openDate = openDate;
        this.expiryDate = expiryDate;
        this.status = status;
    }

}
