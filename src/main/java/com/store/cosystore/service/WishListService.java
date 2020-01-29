package com.store.cosystore.service;

import com.store.cosystore.domain.ProductVersion;
import com.store.cosystore.domain.User;
import com.store.cosystore.repos.ProductVersionRepo;
import com.store.cosystore.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListService {

    @Autowired
    private ProductVersionRepo productVersionRepo;
    @Autowired
    private UserRepo userRepo;

    public void addProduct(User user, int productVersionId){
        ProductVersion productVersion = productVersionRepo.findById(productVersionId);
        if (!user.getWishList().contains(productVersion)){
            user.getWishList().add(productVersion);
            userRepo.save(user);
        }
    }

    public void deleteProduct(User user, int productVersionId){
        user.getWishList().remove(productVersionRepo.findById(productVersionId));
        userRepo.save(user);
    }


}
