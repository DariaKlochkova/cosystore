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
    Product product;

    @ManyToOne
    @MapsId("property_id")
    @JoinColumn(name = "property_id")
    Property property;

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
}
