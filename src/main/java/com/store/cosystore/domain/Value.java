package com.store.cosystore.domain;

import com.store.cosystore.domain.keys.ValueKey;
import javax.persistence.*;

@Entity
public class Value {

    @EmbeddedId
    ValueKey id;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "product_Id", insertable = false, updatable = false)
    private int productId;

    @ManyToOne
    @MapsId("property_id")
    @JoinColumn(name = "property_id")
    private Property property;

    @Column(name = "property_Id", insertable = false, updatable = false)
    private int propertyId;

    private String value;

    public ValueKey getId() {
        return id;
    }

    public void setId(ValueKey id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
