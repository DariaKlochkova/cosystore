package com.store.cosystore.controller;

import com.store.cosystore.domain.Order;
import com.store.cosystore.domain.Room;
import com.store.cosystore.domain.User;
import com.store.cosystore.service.CartService;
import com.store.cosystore.service.CategoryService;
import com.store.cosystore.service.OrderService;
import com.store.cosystore.service.UserService;
import com.store.cosystore.session.SessionCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/cart")
@SessionAttributes("cart")
public class CartController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @ModelAttribute("cart")
    public SessionCart cart() {
        return new SessionCart();
    }

    @GetMapping
    public String cartView(@AuthenticationPrincipal User user, Model model,
                           @ModelAttribute("cart") SessionCart sessionCart){
        model.addAttribute("user", userService.getUser(user));
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        model.addAttribute("rooms", Room.values());
        if(user != null)
            model.addAttribute("cartPositions", cartService.cart(user.getId()));
        else
            model.addAttribute("cartPositions", cartService.sessionCart(sessionCart));
        return "cart";
    }

    @PostMapping("{productId}")
    @ResponseBody
    public String addProductToCart(@AuthenticationPrincipal User user,
                                   @PathVariable int productId,
                                   @ModelAttribute("cart") SessionCart sessionCart){
        if(user != null)
            cartService.addProduct(user, productId);
        else
            sessionCart.addProduct(productId);
        return "Товар добавлен в корзину";
    }

    @PutMapping("{productId}")
    @ResponseBody
    public boolean editPositionCount(@AuthenticationPrincipal User user,
                                     @PathVariable int productId,
                                     @RequestParam int count,
                                     @ModelAttribute("cart") SessionCart sessionCart){
        if(user != null)
            cartService.editProductCount(user.getId(), productId, count);
        else
            sessionCart.editCount(productId, count);
        return true;
    }

    @PostMapping
    @ResponseBody
    public String addOrder(@AuthenticationPrincipal User user,
                           @RequestBody Order order,
                           @ModelAttribute("cart") SessionCart sessionCart) {
        String response = orderService.addOrder(order, user, sessionCart);
        if(user != null)
            cartService.cleanUserCart(user);
        else
            sessionCart.clear();
        return response;
    }

    @DeleteMapping("{productId}")
    @ResponseBody
    public String deletePosition(@AuthenticationPrincipal User user,
                                 @PathVariable int productId,
                                 @ModelAttribute("cart") SessionCart sessionCart){
        if(user != null)
            cartService.deleteProduct(user.getId(), productId);
        else
            sessionCart.deleteProduct(productId);
        return "Товар удалён из корзины";
    }
}
