package com.store.cosystore.domain.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CartKey implements Serializable {
    @Column(name = "user_id")
    int userId;

    @Column(name = "product_version_id")
    int productVersionId;

    public CartKey(int userId, int productVersionId) {
        this.userId = userId;
        this.productVersionId = productVersionId;
    }

    public CartKey() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductVersionIdId() {
        return productVersionId;
    }

    public void setProductVersionId(int productVersionId) {
        this.productVersionId = productVersionId;
    }
}
