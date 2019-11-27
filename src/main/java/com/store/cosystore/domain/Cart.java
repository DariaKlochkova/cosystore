package com.store.cosystore.domain;

import com.store.cosystore.domain.keys.CartKey;
import javax.persistence.*;

@Entity
public class Cart {

    @EmbeddedId
    CartKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("product_version_id")
    @JoinColumn(name = "product_version_id")
    private ProductVersion productVersion;

    private int count;

    public Cart(User user, ProductVersion productVersion, int count) {
        id = new CartKey(user.getId(), productVersion.getId());
        this.productVersion = productVersion;
        this.user = user;
        this.count = count;
    }

    public Cart(ProductVersion productVersion, int count) {
        this.productVersion = productVersion;
        this.count = count;
    }

    public Cart() {
    }

    public CartKey getId() {
        return id;
    }

    public void setId(CartKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProductVersion getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(ProductVersion product) {
        this.productVersion = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
