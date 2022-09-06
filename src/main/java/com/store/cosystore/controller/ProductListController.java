package com.store.cosystore.controller;

import com.store.cosystore.domain.*;
import com.store.cosystore.service.CategoryService;
import com.store.cosystore.service.ProductService;
import com.store.cosystore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        model.addAttribute("user", userService.getUser(user));
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        model.addAttribute("rooms", Room.values());
        model.addAttribute("colors", categoryService.getColorsOfCategory(category));
        model.addAttribute("header", categoryService.getCategory(category).getName());
        model.addAttribute("properties", categoryService.propertyList(category));
        model.addAttribute("productVersions", productService.productVersionList(category, colors, minPrice, maxPrice));
        return "products";
    }

    @GetMapping("/rooms/{room}")
    public String productListView(@AuthenticationPrincipal User user,
                                  @PathVariable String room,
                                  Model model){
        model.addAttribute("user", userService.getUser(user));
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        model.addAttribute("rooms", Room.values());
        model.addAttribute("header", Room.valueOf(room).getName());
        model.addAttribute("productVersions", productService.getProductsByRoom(room));
        return "room";
    }

    @PostMapping
    public String productListByProperties(@RequestBody List<Value> values, Model model){
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        model.addAttribute("rooms", Room.values());
        model.addAttribute("colors", new ArrayList<Color>());
        model.addAttribute("properties", new ArrayList<Property>());
        model.addAttribute("productVersions", productService.productsByProperties(values));
        return "products";
    }

    @GetMapping("{productId}")
    public String productsPage(@AuthenticationPrincipal User user,
                               @PathVariable int productId, Model model){
        model.addAttribute("user", userService.getUser(user));
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        model.addAttribute("rooms", Room.values());
        model.addAttribute("productVersion", productService.productVersionById(productId));
        return "productPage";
    }

}
