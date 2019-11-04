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
    User user;

    @ManyToOne
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    Product product;

    private int count;

    public Cart(User user, Product product, int count) {
        id = new CartKey(user.getId(), product.getId());
        this.product = product;
        this.user = user;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
