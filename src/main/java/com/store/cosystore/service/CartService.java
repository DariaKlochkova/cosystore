package com.store.cosystore.service;

import com.store.cosystore.domain.Cart;
import com.store.cosystore.repos.CartRepo;
import com.store.cosystore.repos.ProductRepo;
import com.store.cosystore.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private UserRepo userRepo;

    public void addProduct(int userId, int productId){
        cartRepo.save(new Cart(userRepo.findById(userId), productRepo.findById(productId), 1));
    }

    public void deleteProduct(int userId, int productId){
        cartRepo.delete(cartRepo.findByUserIdAndProductId(userId, productId));
    }

    public Iterable<Cart> cart(int userId){
        return cartRepo.findByUserId(userId);
    }
}
