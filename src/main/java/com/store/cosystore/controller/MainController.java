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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String main(@AuthenticationPrincipal User user,
                       Model model){
        model.addAttribute("user", userService.getUser(user));
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        model.addAttribute("rooms", Room.values());
        model.addAttribute("popularProducts", productService.getPopularProducts(0));
        return "index";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, Model model){
        model.addAttribute("error", error != null);
        return "login";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@RequestParam String firstname, @RequestParam String surname,
                           @RequestParam String username, @RequestParam String password,
                           Model model){
        boolean isAdded = userService.addUser(username, password, firstname, surname);
        if (isAdded)
            return "redirect:/login";
        model.addAttribute("error", "error");
        return "registration";
    }

    @GetMapping("/promotions")
    public String promotions(@AuthenticationPrincipal User user,
                       Model model){
        model.addAttribute("user", userService.getUser(user));
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        model.addAttribute("rooms", Room.values());
        return "promotions";
    }
}
