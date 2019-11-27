package com.store.cosystore.session;

import java.util.HashMap;
import java.util.Map;

public class SessionCart {

    private Map<Integer, Integer> cart;

    public SessionCart() {
        this.cart = new HashMap<>();
    }

    public Map<Integer, Integer> getCart() {
        return cart;
    }

    public void addProduct(Integer productId){
        if(!cart.keySet().contains(productId))
            cart.put(productId, 1);
    }

    public void editCount(Integer productId, Integer count){
        cart.put(productId, count);
    }

    public void deleteProduct(Integer productId){
        cart.remove(productId);
    }
}
