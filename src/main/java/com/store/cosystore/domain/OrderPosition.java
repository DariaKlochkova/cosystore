package com.store.cosystore.domain;

import com.store.cosystore.domain.keys.OrderPositionKey;
import javax.persistence.*;

@Entity
public class OrderPosition {

    @EmbeddedId
    private OrderPositionKey id;

    @ManyToOne
    @MapsId("product_version_id")
    @JoinColumn(name = "product_version_id")
    private ProductVersion productVersion;

    @ManyToOne
    @MapsId("order_id")
    @JoinColumn(name = "order_id")
    private Order order;

    private int count;

    public OrderPosition() {
    }

    public OrderPositionKey getId() {
        return id;
    }

    public void setId(OrderPositionKey id) {
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
