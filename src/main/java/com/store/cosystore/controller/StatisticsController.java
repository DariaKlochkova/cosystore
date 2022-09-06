package com.store.cosystore.controller;

import com.store.cosystore.domain.User;
import com.store.cosystore.service.StatisticsService;
import com.store.cosystore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String statisticsView(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", userService.getUser(user));
        model.addAttribute("sumStatistics", statisticsService.getSumStatistics());
        model.addAttribute("categoryStatistics", statisticsService.getCategoryStatistics());
        return "admin/statistics";
    }
}
