package com.store.cosystore.controller;

import com.store.cosystore.domain.User;
import com.store.cosystore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public String main(@AuthenticationPrincipal User user,
                       Model model){
        model.addAttribute("user", user);
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        return "index";
    }
}
