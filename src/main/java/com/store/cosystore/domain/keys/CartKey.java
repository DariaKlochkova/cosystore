package com.store.cosystore.domain.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CartKey implements Serializable {
    @Column(name = "user_id")
    int userId;

    @Column(name = "product_id")
    int productId;

    public CartKey(int userId, int productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public CartKey() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
