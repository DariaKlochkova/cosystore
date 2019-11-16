package com.store.cosystore.controller.admin;

import com.store.cosystore.domain.Order;
import com.store.cosystore.domain.User;
import com.store.cosystore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/orders")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class OrderController {

    @Autowired
    OrderService orderService;


}
