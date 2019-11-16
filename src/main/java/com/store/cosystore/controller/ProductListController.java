package com.store.cosystore.controller;

import com.store.cosystore.domain.Room;
import com.store.cosystore.domain.User;
import com.store.cosystore.service.CartService;
import com.store.cosystore.service.CategoryService;
import com.store.cosystore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductListController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;


    @GetMapping
    public String products(@AuthenticationPrincipal User user, @RequestParam int category, Model model){
        model.addAttribute("user", user);
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        model.addAttribute("rooms", Room.values());
        model.addAttribute("productVersions", productService.productVersionList(category));
        return "products";
    }

    @PostMapping
    @ResponseBody
    public String products(@RequestParam int userId, @RequestParam int productId){
        cartService.addProduct(userId, productId);
        return "Товар добавлен в корзину";
    }
}
