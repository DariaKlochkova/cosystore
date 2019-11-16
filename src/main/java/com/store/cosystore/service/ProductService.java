package com.store.cosystore.service;

import com.store.cosystore.domain.Product;
import com.store.cosystore.domain.ProductVersion;
import com.store.cosystore.repos.CategoryRepo;
import com.store.cosystore.repos.ProductRepo;
import com.store.cosystore.repos.ProductVersionRepo;
import com.store.cosystore.repos.ValueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductVersionRepo productVersionRepo;
    @Autowired
    private ValueRepo valueRepo;

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
        for (com.store.cosystore.domain.Value v : product.getValues()){
            v.getId().setProductId(p.getId());
            valueRepo.save(v);
        }
        return p;
    }

    public void addProductVersion(ProductVersion productVersion, int productId){
        productVersion.setProduct(productRepo.findById(productId));
        productVersionRepo.save(productVersion);
    }

    public void editProduct(Product product){
        product.setCategory(categoryRepo.findById(product.getCategoryId()));
        product.setValues(null);
        productRepo.save(product);
        for (ProductVersion pv : product.getProductVersions()){
            pv.setProduct(product);
            productVersionRepo.save(pv);
        }
    }

    public void deleteProduct(int productVersionId) {
        ProductVersion pv = productVersionRepo.findById(productVersionId);
        productVersionRepo.deleteById(productVersionId);
        if (pv.getProduct().getProductVersions().size() == 1)
            productRepo.deleteById(pv.getProduct().getId());
    }
}
