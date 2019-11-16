package com.store.cosystore.controller.admin;

import com.store.cosystore.domain.Order;
import com.store.cosystore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    @ResponseBody
    public String addOrder(@RequestBody Order order){
        orderService.addOrder(order);
        return "Заказ отправлен";
    }
}
