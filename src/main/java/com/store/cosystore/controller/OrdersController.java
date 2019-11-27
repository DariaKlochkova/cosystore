package com.store.cosystore.controller;

import com.store.cosystore.domain.User;
import com.store.cosystore.service.OrderService;
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

    @GetMapping
    public String orders(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("orders", orderService.getOrders());
        return "orders";
    }

    @GetMapping("{orderId}")
    public String orderView(@AuthenticationPrincipal User user, Model model,
                            @PathVariable int orderId){
        model.addAttribute("user", user);
        model.addAttribute("order", orderService.getOrderById(orderId));
        return "ord";
    }

    @PutMapping("{orderId}")
    @ResponseBody
    public String changeOrderStatus(@PathVariable int orderId, @RequestParam int newStatus){
        return orderService.changeOrderStatus(orderId, newStatus);
    }
}
