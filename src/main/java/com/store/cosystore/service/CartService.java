package com.store.cosystore.service;

import com.store.cosystore.domain.Cart;
import com.store.cosystore.repos.CartRepo;
import com.store.cosystore.repos.ProductVersionRepo;
import com.store.cosystore.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private ProductVersionRepo productVersionRepo;
    @Autowired
    private UserRepo userRepo;

    public void addProduct(int userId, int productVersionId){
        cartRepo.save(new Cart(userRepo.findById(userId), productVersionRepo.findById(productVersionId), 1));
    }

    public void deleteProduct(int userId, int productVersionId){
        cartRepo.delete(cartRepo.findByUserIdAndProductVersionId(userId, productVersionId));
    }

    public Iterable<Cart> cart(int userId){
        return cartRepo.findByUserId(userId);
    }

    public void editProductCount(int userId, int productVersionId, int count){
        Cart c = cartRepo.findByUserIdAndProductVersionId(userId, productVersionId);
        c.setCount(count);
        cartRepo.save(c);
    }
}
