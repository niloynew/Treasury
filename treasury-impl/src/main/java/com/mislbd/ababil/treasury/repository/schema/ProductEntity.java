package com.mislbd.ababil.treasury.repository.schema;

import javax.persistence.OneToMany;
import java.util.List;

public class ProductEntity {

    @OneToMany(mappedBy = "product")
    private List<AccountEntity> accountEntityList;
}
