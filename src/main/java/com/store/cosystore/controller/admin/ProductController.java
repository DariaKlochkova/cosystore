package com.store.cosystore.controller.admin;

import com.store.cosystore.domain.Color;
import com.store.cosystore.domain.Product;
import com.store.cosystore.domain.User;
import com.store.cosystore.service.CategoryService;
import com.store.cosystore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;


    @GetMapping
    public String addProductView(@AuthenticationPrincipal User user,
                                 @RequestParam(name="categoryId", required=false, defaultValue="0") int categoryId,
                                 Model model){
        model.addAttribute("user", user);
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        model.addAttribute("selectedCategory", categoryService.getCategory(categoryId));
        model.addAttribute("properties", categoryService.propertyList(categoryId));
        model.addAttribute("colors", Color.values());
        return "admin/add";
    }

    @PostMapping
    public String addProduct(@RequestParam int categoryId,
                             @RequestParam String article,
                             @RequestParam String name,
                             @RequestParam int price,
                             @RequestParam int count,
                             @RequestParam String information,
                             @RequestParam String description,
                             @RequestParam("files") MultipartFile[] files,
                             @RequestParam String colorName,
                             @RequestParam int height,
                             @RequestParam int width,
                             @RequestParam int depth,
                             @RequestParam("room") List<String> roomNames) throws IOException
    {
        productService.addProduct(categoryId, article, name, price, count, information, description, files, colorName, height, width, depth, roomNames);
        return "redirect:/admin/add";
    }

//    @PostMapping
//    public String addProduct(@RequestBody Product product,
//                             @RequestParam int categoryId,
//                             @RequestParam("files")  MultipartFile[] files,
//                             @RequestParam List<String> roomNames) throws IOException {
//        productService.addProduct(product, categoryId, files, roomNames);
//        return "redirect:/admin/add";
//    }

}
