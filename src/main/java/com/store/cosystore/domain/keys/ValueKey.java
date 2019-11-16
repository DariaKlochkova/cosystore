package com.store.cosystore.domain.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ValueKey implements Serializable {
    @Column(name = "product_id")
    private int productId;

    @Column(name = "property_id")
    private int propertyId;

    public ValueKey() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }
}
