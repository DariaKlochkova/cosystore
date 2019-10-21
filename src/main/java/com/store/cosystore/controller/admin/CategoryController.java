package com.store.cosystore.controller.admin;

import com.store.cosystore.domain.Category;
import com.store.cosystore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/categories")
    public String categories(Model model){
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        return "admin/categories";
    }

    @PostMapping("/categories/editGroup")
    public String editGroup(@RequestParam String oldGroupName, @RequestParam String newGroupName){
        categoryService.editGroup(oldGroupName, newGroupName);
        return "redirect:/admin/categories";
    }

    @PostMapping("/categories/deleteGroup")
    public String deleteGroup(@RequestParam String groupName){
        categoryService.deleteGroup(groupName);
        return "redirect:/admin/categories";
    }

    @PostMapping("/categories/addGroup")
    public String addGroup(@RequestParam String newGroupName){
        categoryService.addGroup(newGroupName);
        return "redirect:/admin/categories";
    }

    @PostMapping("/categories/editCategory")
    public String editCategory(@RequestParam String oldCategoryName, @RequestParam String editCategoryName){
        categoryService.editCategory(oldCategoryName, editCategoryName);
        return "redirect:/admin/categories";
    }

    @PostMapping("/categories/deleteCategory")
    public String deleteCategory(@RequestParam String categoryName){
        categoryService.deleteCategory(categoryName);
        return "redirect:/admin/categories";
    }

    @PostMapping("/categories/addCategory")
    public String addCategory(@RequestParam String groupName, @RequestParam String newCategoryName){
        categoryService.addCategory(groupName, newCategoryName);
        return "redirect:/admin/categories";
    }

    @PostMapping("/categories/saveProperties/{category}")
    public String saveProperties(@PathVariable Category category,
                                 @RequestParam("propertyName") List<String> propertyName,
                                 @RequestParam("propertyValues") List<String> propertyValues){
        categoryService.saveProperties(category, propertyName, propertyValues);
        return "redirect:/admin/categories";
    }
}
