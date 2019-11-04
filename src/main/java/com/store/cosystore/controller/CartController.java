package com.store.cosystore.controller;

import com.store.cosystore.domain.Cart;
import com.store.cosystore.domain.Product;
import com.store.cosystore.domain.User;
import com.store.cosystore.service.CartService;
import com.store.cosystore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        List<Product> products = new ArrayList<>();
        for (Cart c : cartService.cart(user.getId())){
            products.add(c.getProduct());
        }
        model.addAttribute("products", products);
        return "cart";
    }

    @DeleteMapping
    public String deleteCartPosition(@RequestParam int userId,
                                     @RequestParam int productId){
        cartService.deleteProduct(userId, productId);
        return "Товар удалён из корзины";
    }
}
