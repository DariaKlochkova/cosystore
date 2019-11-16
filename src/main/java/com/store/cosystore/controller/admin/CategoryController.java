package com.store.cosystore.controller.admin;

import com.store.cosystore.domain.Category;
import com.store.cosystore.domain.User;
import com.store.cosystore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categories")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String categories(@AuthenticationPrincipal User user,
                             Model model){
        model.addAttribute("user", user);
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        return "admin/categories";
    }

    @PutMapping("/group")
    public String editGroup(@RequestParam String oldGroupName, @RequestParam String editGroupName){
        categoryService.editGroup(oldGroupName, editGroupName);
        return "redirect:/admin/categories";
    }

    @DeleteMapping("/group")
    public String deleteGroup(@RequestParam String groupName){
        categoryService.deleteGroup(groupName);
        return "redirect:/admin/categories";
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
    public String deleteCategory(@RequestParam String categoryName){
        categoryService.deleteCategory(categoryName);
        return "redirect:/admin/categories";
    }

    @PostMapping
    public String addCategory(@RequestParam String groupName, @RequestParam String newCategoryName){
        categoryService.addCategory(groupName, newCategoryName);
        return "redirect:/admin/categories";
    }

    @PutMapping("/properties")
    public void saveProperties(@RequestBody Category category){
        categoryService.saveProperties(category);
    }
}
