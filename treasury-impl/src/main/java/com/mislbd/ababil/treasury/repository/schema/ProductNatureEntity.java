package com.mislbd.ababil.treasury.repository.schema;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.PRODUCT_NATURE)
public class ProductNatureEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "PRODUCT_NATURE_ID_GEN")
  @SequenceGenerator(
      name = "PRODUCT_NATURE_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.PRODUCT_NATURE_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Column(name = "CODE")
  private String code;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column
  @OneToMany(mappedBy = "productNature")
  private List<ProductNatureEntity> productNature;

  public long getId() {
    return id;
  }

  public ProductNatureEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getCode() {
    return code;
  }

  public ProductNatureEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public ProductNatureEntity setDescription(String description) {
    this.description = description;
    return this;
  }

  public List<ProductNatureEntity> getProductNature() {
    return productNature;
  }

  public ProductNatureEntity setProductNature(List<ProductNatureEntity> productNature) {
    this.productNature = productNature;
    return this;
  }
}
