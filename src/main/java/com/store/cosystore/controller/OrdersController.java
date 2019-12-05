package com.store.cosystore.controller;

import com.store.cosystore.domain.Status;
import com.store.cosystore.domain.User;
import com.store.cosystore.service.OrderService;
import com.store.cosystore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String orders(@AuthenticationPrincipal User user,
                         @RequestParam(required = false) Integer status, Model model){
        model.addAttribute("user", userService.getUserById(user.getId()));
        model.addAttribute("statuses", Status.values());
        model.addAttribute("newCount", orderService.getNewCount());
        if (status == null)
            model.addAttribute("orders", orderService.getOrders());
        else
            model.addAttribute("orders", orderService.getOrdersByStatus(status));
        return "orders";
    }

    @GetMapping("{orderId}")
    public String orderView(@AuthenticationPrincipal User user, Model model,
                            @PathVariable int orderId){
        model.addAttribute("user", user);
        model.addAttribute("statuses", Status.values());
        model.addAttribute("order", orderService.getOrderById(orderId));
        return "orderPage";
    }

    @PutMapping("{orderId}")
    @ResponseBody
    public String changeOrderStatus(@PathVariable int orderId, @RequestParam int newStatus){
        return orderService.changeOrderStatus(orderId, newStatus);
    }

    @PostMapping("{orderId}")
    @ResponseBody
    public String denyOrder(@PathVariable int orderId, @RequestParam String message){
        return orderService.denyOrder(orderId, message);
    }
}
