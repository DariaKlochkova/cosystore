package com.store.cosystore.controller;

import com.store.cosystore.domain.*;
import com.store.cosystore.domain.Color;
import com.store.cosystore.repos.CategoryGroupRepo;
import com.store.cosystore.repos.CategoryRepo;
import com.store.cosystore.repos.ProductRepo;
import com.store.cosystore.repos.PropertyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private CategoryGroupRepo categoryGroupRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private PropertyRepo propertyRepo;

    @Value("${upload.path}")
    private String uploadPath;


    // Товары

    @GetMapping("/add")
    public String add(@RequestParam(name="categoryName", required=false, defaultValue="Другое") String category, Model model){
        model.addAttribute("categoryGroups", categoryGroupRepo.findAll());
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
        Set<String> images = new HashSet<>();
        for(int i = 0; i < files.length; i++){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String fileName = UUID.randomUUID().toString() +"." + files[i].getOriginalFilename();
            files[i].transferTo(new File(uploadPath + "/" + fileName));
            images.add(fileName);
        }

        Set<Room> rooms = new HashSet<>();
        for (String room : roomNames) {
            rooms.add(Room.valueOf(room));
        }

        Product product = new Product(categoryRepo.findByName(category), article, name, price, count, information, description, Color.getByName(colorName), height, width, depth, images, rooms);
        productRepo.save(product);
        return "admin/add";
    }


    // Категории

    @GetMapping("/categories")
    public String product(Model model){
        model.addAttribute("categoryGroups", categoryGroupRepo.findAll());
        return "admin/categories";
    }

    @PostMapping("/categories/editGroup")
    public String editGroup(@RequestParam String oldGroupName, @RequestParam String editGroupName){
        CategoryGroup cg = categoryGroupRepo.findByName(oldGroupName);
        cg.setName(editGroupName);
        categoryGroupRepo.save(cg);
        return "redirect:/admin/categories";
    }

    @PostMapping("/categories/deleteGroup")
    public String deleteGroup(@RequestParam String groupName){
        CategoryGroup cg = categoryGroupRepo.findByName(groupName);
        if(cg.getCategories().size() == 0)
            categoryGroupRepo.delete(cg);
        return "redirect:/admin/categories";
    }

    @PostMapping("/categories/addGroup")
    public String addGroup(@RequestParam String newGroupName){
        categoryGroupRepo.save(new CategoryGroup(newGroupName));
        return "redirect:/admin/categories";
    }

    @PostMapping("/categories/editCategory")
    public String editCategory(@RequestParam String oldCategoryName, @RequestParam String editCategoryName){
        Category c = categoryRepo.findByName(oldCategoryName);
        c.setName(editCategoryName);
        categoryRepo.save(c);
        return "redirect:/admin/categories";
    }

    @PostMapping("/categories/deleteCategory")
    public String deleteCategory(@RequestParam String groupName, @RequestParam String categoryName){
        CategoryGroup cg = categoryGroupRepo.findByName(groupName);
        Category c = categoryRepo.findByName(categoryName);
        if(c.getProducts().size() == 0) {
            cg.getCategories().remove(c);
            categoryRepo.delete(c);
        }
        return "redirect:/admin/categories";
    }

    @PostMapping("/categories/addCategory")
    public String addCategory(@RequestParam String groupName, @RequestParam String newCategoryName){
        CategoryGroup cg = categoryGroupRepo.findByName(groupName);
        cg.getCategories().add(new Category(newCategoryName, cg));
        categoryGroupRepo.save(cg);
        return "redirect:/admin/categories";
    }

    @PostMapping("/categories/saveProperties{category}")
    public String saveProperties(@PathVariable Category category,
                                 @RequestParam String categoryName,
                                 @RequestParam("propertyName") List<String> propertyName,
                                 @RequestParam("propertyValues") List<String> propertyValues){
        //Category c = categoryRepo.findByName(categoryName);
        category.getProperties().clear();
        propertyRepo.deleteAll(propertyRepo.findByCategory_Id(category.getId()));
        for(int i = 0; i < propertyName.size(); i++){
            Property p = new Property(propertyName.get(i), propertyValues.get(i), category);
            propertyRepo.save(p);

            category.getProperties().add(p);
        }
        categoryRepo.save(category);
        return "redirect:/admin/categories";
    }
}
