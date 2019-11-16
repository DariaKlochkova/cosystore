package com.store.cosystore.controller.admin;

import com.store.cosystore.domain.Color;
import com.store.cosystore.domain.Product;
import com.store.cosystore.domain.ProductVersion;
import com.store.cosystore.domain.User;
import com.store.cosystore.service.CategoryService;
import com.store.cosystore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/product")
@PreAuthorize("hasAuthority('ADMIN')")
public class ProductController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;


    @GetMapping
    public String addProductView(@AuthenticationPrincipal User user,
                                 @RequestParam(name="categoryId", required=false, defaultValue="0") int categoryId,
                                 Model model){
        model.addAttribute("user", user);
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        model.addAttribute("selectedCategory", categoryService.getCategory(categoryId));
        model.addAttribute("properties", categoryService.propertyList(categoryId));
        model.addAttribute("colors", Color.values());
        return "admin/add";
    }

    @PostMapping("/img")
    @ResponseBody
    public List<String> uploadImages(@RequestParam("files") MultipartFile[] files) throws IOException {
        return productService.uploadImages(files);
    }

    @PostMapping
    @ResponseBody
    public int addProduct(@RequestBody Product product){
        Product p = productService.addProduct(product);
        return p.getId();
    }

    @GetMapping("{productId}")
    public String addProductVersionView(@AuthenticationPrincipal User user,
                                        @PathVariable int productId,
                                        Model model){
        model.addAttribute("user", user);
        model.addAttribute("productVersions", productService.versionsOfProduct(productId));
        model.addAttribute("colors", Color.values());
        return "admin/version";
    }

    @PostMapping("{productId}")
    @ResponseBody
    public int addProductVersion(@RequestBody ProductVersion productVersion,
                                 @PathVariable int productId){
        productService.addProductVersion(productVersion, productId);
        return productId;
    }

    @GetMapping("editor")
    public String editProductView(@AuthenticationPrincipal User user,
                                  @RequestParam String article,
                                  Model model){
        model.addAttribute("user", user);
        model.addAttribute("categoryGroups", categoryService.categoryGroupList());
        model.addAttribute("productVersion", productService.getProductVersionByArticle(article));
        model.addAttribute("colors", Color.values());
        return "admin/edit";
    }

    @PostMapping("editor")
    @ResponseBody
    public int editProduct(@RequestBody Product product){
        productService.editProduct(product);
        return product.getId();
    }

}
