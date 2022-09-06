package com.store.cosystore.controller;

import com.store.cosystore.domain.Category;
import com.store.cosystore.domain.User;
import com.store.cosystore.service.CategoryService;
import com.store.cosystore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String categories(@AuthenticationPrincipal User user,
                             Model model){
        model.addAttribute("user", userService.getUser(user));
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        return "admin/categories";
    }

    @PutMapping("/group")
    public String editGroup(@RequestParam String oldGroupName, @RequestParam String editGroupName){
        categoryService.editGroup(oldGroupName, editGroupName);
        return "redirect:/admin/categories";
    }

    @DeleteMapping("/group")
    @ResponseBody
    public String deleteGroup(@RequestParam String groupName){
        return categoryService.deleteGroup(groupName);
    }

    @PostMapping("/group")
    public String addGroup(@RequestParam String newGroupName){
        categoryService.addGroup(newGroupName);
        return "redirect:/admin/categories";
    }

    @PutMapping
    public String editCategory(@RequestParam String oldCategoryName, @RequestParam String editCategoryName){
        categoryService.editCategory(oldCategoryName, editCategoryName);
        return "redirect:/admin/categories";
    }

    @DeleteMapping
    @ResponseBody
    public String deleteCategory(@RequestParam String categoryName){
        return categoryService.deleteCategory(categoryName);
    }

    @PostMapping
    public String addCategory(@RequestParam String groupName, @RequestParam String newCategoryName){
        categoryService.addCategory(groupName, newCategoryName);
        return "redirect:/admin/categories";
    }

    @PutMapping("/properties")
    @ResponseBody
    public String saveProperties(@RequestBody Category category){
        categoryService.saveProperties(category);
        return "Характеристики сохранены";
    }
}
