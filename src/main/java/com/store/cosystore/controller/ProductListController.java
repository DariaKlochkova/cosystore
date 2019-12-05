package com.store.cosystore.controller;

import com.store.cosystore.domain.Room;
import com.store.cosystore.domain.User;
import com.store.cosystore.service.CategoryService;
import com.store.cosystore.service.ProductService;
import com.store.cosystore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/products")
public class ProductListController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;


    @GetMapping
    public String productListView(@AuthenticationPrincipal User user,
                                  @RequestParam int category,
                                  @RequestParam(required=false) String[] colors,
                                  @RequestParam(required=false) Integer minPrice,
                                  @RequestParam(required=false) Integer maxPrice,
                                  Model model){
        model.addAttribute("user", userService.getUserById(user.getId()));
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        model.addAttribute("rooms", Room.values());
        model.addAttribute("colors", categoryService.getColorsOfCategory(category));
        model.addAttribute("header", categoryService.getCategory(category).getName());
        model.addAttribute("productVersions", productService.productVersionList(category, colors, minPrice, maxPrice));
        return "products";
    }

    @GetMapping("{productId}")
    public String productsPage(@AuthenticationPrincipal User user,
                               @PathVariable int productId, Model model){
        model.addAttribute("user", userService.getUserById(user.getId()));
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        model.addAttribute("rooms", Room.values());
        model.addAttribute("productVersion", productService.productVersionById(productId));
        return "productPage";
    }

}
