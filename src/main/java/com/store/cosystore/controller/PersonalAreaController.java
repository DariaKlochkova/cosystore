package com.store.cosystore.controller;

import com.store.cosystore.domain.Room;
import com.store.cosystore.domain.Status;
import com.store.cosystore.domain.User;
import com.store.cosystore.service.CategoryService;
import com.store.cosystore.service.OrderService;
import com.store.cosystore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lk")
public class PersonalAreaController {

    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OrderService orderService;

    @GetMapping
    public String personalArea(@AuthenticationPrincipal User user,
                               Model model){
        model.addAttribute("user", userService.getUserById(user.getId()));
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        model.addAttribute("rooms", Room.values());
        model.addAttribute("orders", orderService.getUsersOrders(user.getId()));
        return "lk";
    }

    @PostMapping
    public User editUserData(@AuthenticationPrincipal User user,
                             @RequestBody User userData){
        return userService.saveUserData(user, userData);
    }

    @GetMapping("/order")
    @ResponseBody
    public String sendCheck(@RequestParam int orderId){
        return orderService.sendCheck(orderId);
    }

    @PostMapping("/order")
    @ResponseBody
    public String cancelOrder(@RequestParam int orderId){
        orderService.cancelOrder(orderId);
        return "Заказ отменён";
    }
}
