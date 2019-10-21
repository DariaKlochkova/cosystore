package com.store.cosystore.controller.admin;

import com.store.cosystore.domain.*;
import com.store.cosystore.domain.Color;
import com.store.cosystore.repos.CategoryGroupRepo;
import com.store.cosystore.repos.CategoryRepo;
import com.store.cosystore.repos.ProductRepo;
import com.store.cosystore.service.CategoryService;
import com.store.cosystore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryRepo categoryRepo;


    @GetMapping("/add")
    public String addProductView(@RequestParam(name="categoryName", required=false, defaultValue="Другое") String category, Model model){
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        model.addAttribute("categoryName", category);
        model.addAttribute("colors", Color.values());
        return "admin/add";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam String category,
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
        productService.addProduct(category, article, name, price, count, information, description, files, colorName, height, width, depth, roomNames);
        return "admin/add";
    }

}
