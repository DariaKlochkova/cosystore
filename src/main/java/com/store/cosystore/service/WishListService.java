package com.store.cosystore.service;

import com.store.cosystore.domain.ProductVersion;
import com.store.cosystore.domain.User;
import com.store.cosystore.repos.ProductVersionRepo;
import com.store.cosystore.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class WishListService {

    @Autowired
    private ProductVersionRepo productVersionRepo;
    @Autowired
    private UserRepo userRepo;

    public Set<ProductVersion> wishList(User user){
        Set<ProductVersion> wishList = new LinkedHashSet<>();
        for(ProductVersion pv : user.getWishList())
            wishList.add(productVersionRepo.findById(pv.getId()));
        return wishList;
    }

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
