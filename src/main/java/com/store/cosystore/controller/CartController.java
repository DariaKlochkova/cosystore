package com.store.cosystore.controller;

import com.itextpdf.text.DocumentException;
import com.store.cosystore.domain.*;
import com.store.cosystore.service.CartService;
import com.store.cosystore.service.CategoryService;
import com.store.cosystore.service.MailSender;
import com.store.cosystore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CartService cartService;
    @Autowired
    OrderService orderService;
    @Autowired
    MailSender mailSender;

    @GetMapping
    public String products(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        model.addAttribute("rooms", Room.values());
        model.addAttribute("cartPositions", cartService.cart(user.getId()));
        return "cart";
    }

    @PostMapping
    @ResponseBody
    public boolean addOrder(@AuthenticationPrincipal User user,
                            @RequestBody Order order) throws DocumentException, MessagingException, IOException {
        Order ord = orderService.addOrder(order, user);
        mailSender.sendWithCheck(ord);
        return true;
    }

    @PutMapping
    @ResponseBody
    public boolean editPositionCount(@AuthenticationPrincipal User user,
                                     @RequestParam int productVersionId,
                                     @RequestParam int count){
        cartService.editProductCount(user.getId(), productVersionId, count);
        return true;
    }

    @DeleteMapping
    @ResponseBody
    public String deletePosition(@AuthenticationPrincipal User user,
                                 @RequestParam int productVersionId){
        cartService.deleteProduct(user.getId(), productVersionId);
        return "Товар удалён из корзины";
    }
}
