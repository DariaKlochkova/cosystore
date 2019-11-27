package com.store.cosystore.controller;

import com.store.cosystore.domain.Room;
import com.store.cosystore.domain.User;
import com.store.cosystore.service.CategoryService;
import com.store.cosystore.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String searchResult(@AuthenticationPrincipal User user,
                               @RequestParam String query, Model model){
        model.addAttribute("user", user);
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        model.addAttribute("rooms", Room.values());
        model.addAttribute("header", "Результаты поиска");
        model.addAttribute("productVersions", searchService.findProducts(query));
        return "search";
    }
}
