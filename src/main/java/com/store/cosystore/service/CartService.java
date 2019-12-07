package com.store.cosystore.service;

import com.store.cosystore.domain.Cart;
import com.store.cosystore.domain.User;
import com.store.cosystore.repos.CartRepo;
import com.store.cosystore.repos.ProductVersionRepo;
import com.store.cosystore.session.SessionCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private ProductVersionRepo productVersionRepo;

    public void addProduct(User user, int productVersionId){
        cartRepo.save(new Cart(user, productVersionRepo.findById(productVersionId), 1));
    }

    public void deleteProduct(int userId, int productVersionId){
        cartRepo.delete(cartRepo.findByUserIdAndProductVersionId(userId, productVersionId));
    }

    public void cleanUserCart(User user){
        cartRepo.deleteAll(cartRepo.findByUserId(user.getId()));
    }

    public Set<Cart> cart(int userId){
        return cartRepo.findByUserId(userId);
    }

    public Set<Cart> sessionCart(SessionCart sessionCart) {
        return sessionCart.getCart().entrySet().stream()
                .map(e -> new Cart(productVersionRepo.findById(e.getKey()).get(), e.getValue()))
                .collect(Collectors.toSet());
    }

    public void editProductCount(int userId, int productVersionId, int count){
        Cart c = cartRepo.findByUserIdAndProductVersionId(userId, productVersionId);
        c.setCount(count);
        cartRepo.save(c);
    }
}
