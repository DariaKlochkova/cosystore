package com.store.cosystore.service;

import com.store.cosystore.domain.Color;
import com.store.cosystore.domain.Product;
import com.store.cosystore.domain.ProductVersion;
import com.store.cosystore.domain.Room;
import com.store.cosystore.repos.CategoryRepo;
import com.store.cosystore.repos.ProductRepo;
import com.store.cosystore.repos.ProductVersionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductVersionRepo productVersionRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public Set<ProductVersion> productVersionList(int categoryId){
        return productRepo.findByCategoryId(categoryId).stream()
                .flatMap(p -> p.getProductVersions().stream())
                .collect(Collectors.toSet());
    }

    public ProductVersion getProductVersionByArticle(String article){
        return productVersionRepo.findByArticle(article);
    }

    public Set<ProductVersion> versionsOfProduct(int productId){
        return productVersionRepo.findByProductId(productId);
    }

    public List<String> uploadImages(MultipartFile[] files) throws IOException {
        List<String> images = new ArrayList<>();
        for(int i = 0; i < files.length; i++){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String fileName = UUID.randomUUID().toString() +"." + files[i].getOriginalFilename();
            files[i].transferTo(new File(uploadPath + "/" + fileName));
            images.add(fileName);
        }
        return images;
    }

    public Product addProduct(Product product){
        product.setCategory(categoryRepo.findById(product.getCategoryId()));
        Product p = productRepo.save(product);
        for (ProductVersion pv : product.getProductVersions()){
            pv.setProduct(product);
            productVersionRepo.save(pv);
        }
        return p;
    }

    public void addProductVersion(ProductVersion productVersion, int productId){
        productVersion.setProduct(productRepo.findById(productId));
        productVersionRepo.save(productVersion);
    }

    public void editProduct(Product product){
        product.setCategory(categoryRepo.findById(product.getCategoryId()));
        productRepo.save(product);
        for (ProductVersion pv : product.getProductVersions()){
            pv.setProduct(product);
            productVersionRepo.save(pv);
        }
    }


}
