package com.mislbd.ababil.treasury.repository.schema;

import com.mislbd.ababil.treasury.domain.GLType;
import javax.persistence.*;

@Entity
@Table(name = SchemaConstant.PRODUCT_RELATED_GL_TABLE_NAME)
public class ProductRelatedGLEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "PRODUCT_RELATED_GL_ID_GEN")
  @SequenceGenerator(
      name = "PRODUCT_RELATED_GL_ID_GEN",
      allocationSize = 1,
      sequenceName = SchemaConstant.PRODUCT_RELATED_GL_SEQUENCE_NAME)
  @Column(name = "ID")
  private long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "GL_TYPE")
  private GLType glType;

  @Column(name = "PRODUCT_ID")
  private long productId;

  @Column(name = "GL_ID")
  private long glId;

  public long getId() {
    return id;
  }

  public ProductRelatedGLEntity setId(long id) {
    this.id = id;
    return this;
  }

  public GLType getGlType() {
    return glType;
  }

  public ProductRelatedGLEntity setGlType(GLType glType) {
    this.glType = glType;
    return this;
  }

  public long getProductId() {
    return productId;
  }

  public ProductRelatedGLEntity setProductId(long productId) {
    this.productId = productId;
    return this;
  }

  public long getGlId() {
    return glId;
  }

  public ProductRelatedGLEntity setGlId(long glId) {
    this.glId = glId;
    return this;
  }
}
