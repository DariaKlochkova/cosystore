package com.store.cosystore.domain;

import javax.persistence.*;

@Entity
public class OrderPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private Order order;

    @ManyToOne(optional = false, cascade=CascadeType.ALL)
    @JoinColumn(name = "product_version_id")
    private ProductVersion productVersion;

    private int count;

    public OrderPosition() {
    }

    public OrderPosition(ProductVersion productVersion, int count) {
        this.productVersion = productVersion;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductVersion getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(ProductVersion productVersion) {
        this.productVersion = productVersion;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
