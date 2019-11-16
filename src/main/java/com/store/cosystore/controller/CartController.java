package com.store.cosystore.controller;

import com.store.cosystore.domain.Cart;
import com.store.cosystore.domain.Product;
import com.store.cosystore.domain.ProductVersion;
import com.store.cosystore.domain.User;
import com.store.cosystore.service.CartService;
import com.store.cosystore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CartService cartService;

    @GetMapping
    public String products(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        model.addAttribute("cartPositions", cartService.cart(user.getId()));
        return "cart";
    }

    @PutMapping
    @ResponseBody
    public boolean editPositionCount(@AuthenticationPrincipal User user,
                                     @RequestParam int productVersionId,
                                     @RequestParam int count){
        cartService.editProductCount(user.getId(), productVersionId, count);
        return true;
    }

    @DeleteMapping
    @ResponseBody
    public String deletePosition(@AuthenticationPrincipal User user,
                                 @RequestParam int productVersionId){
        cartService.deleteProduct(user.getId(), productVersionId);
        return "Товар удалён из корзины";
    }
}
