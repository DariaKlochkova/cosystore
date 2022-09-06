package com.store.cosystore.controller;

import com.store.cosystore.domain.Room;
import com.store.cosystore.domain.User;
import com.store.cosystore.service.CategoryService;
import com.store.cosystore.service.UserService;
import com.store.cosystore.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wishes")
public class WishListController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private WishListService wishListService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String wishesView(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", userService.getUser(user));
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        model.addAttribute("rooms", Room.values());
        model.addAttribute("wishList", userService.getUser(user).getWishList());
        return "wishes";
    }

    @PostMapping("{productId}")
    @ResponseBody
    public String addProductToWishList(@AuthenticationPrincipal User user,
                                       @PathVariable int productId){
        wishListService.addProduct(userService.getUser(user), productId);
        return "Товар добавлен в список желаний";
    }

    @DeleteMapping("{productId}")
    @ResponseBody
    public String deleteProductFromWishList(@AuthenticationPrincipal User user,
                                 @PathVariable int productId){
        wishListService.deleteProduct(userService.getUser(user), productId);
        return "Товар удалён из списка желаний";
    }
}
