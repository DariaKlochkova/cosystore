package com.store.cosystore.service;

import com.store.cosystore.domain.*;
import com.store.cosystore.repos.CategoryRepo;
import com.store.cosystore.repos.ProductRepo;
import com.store.cosystore.repos.ProductVersionRepo;
import com.store.cosystore.repos.ValueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public Set<ProductVersion> productVersionList(int categoryId, String[] colors, Integer minPrice, Integer maxPrice){
        Stream<ProductVersion> productVersions = productRepo.findByCategoryId(categoryId).stream()
                .flatMap(p -> p.getProductVersions().stream());
        if(colors != null)
            productVersions = productVersions.filter(p -> Arrays.asList(colors).contains(p.getColor().getName()));
        if(minPrice != null)
            productVersions = productVersions.filter(p -> minPrice <= p.getProduct().getPrice());
        if(maxPrice != null)
            productVersions = productVersions.filter(p -> maxPrice >= p.getProduct().getPrice());
        return productVersions.collect(Collectors.toSet());
    }

    public ProductVersion productVersionByArticle(String article){
        return productVersionRepo.findByArticle(article);
    }

    public ProductVersion productVersionById(int productId) {
        return productVersionRepo.findById(productId);
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

    public String addProduct(Product product){
        for (ProductVersion pv : product.getProductVersions()) {
            if(productVersionRepo.findByArticle(pv.getArticle()) != null)
                return "Товар с данным артикулом уже существует";
        }
        product.setCategory(categoryRepo.findById(product.getCategoryId()));
        Product p = productRepo.save(product);
        for (ProductVersion pv : product.getProductVersions()){
            pv.setProduct(product);
            productVersionRepo.save(pv);
            Category category = categoryRepo.findById(product.getCategoryId());
            category.getColors().add(pv.getColor());
            categoryRepo.save(category);
        }
        for (com.store.cosystore.domain.Value v : product.getValues()){
            v.getId().setProductId(p.getId());
            valueRepo.save(v);
        }
        return "Товар добавлен в каталог";
    }

    public void addProductVersion(ProductVersion productVersion, int productId){
        productVersion.setProduct(productRepo.findById(productId));
        productVersionRepo.save(productVersion);
        Category category = categoryRepo.findById(productVersion.getProduct().getCategoryId());
        category.getColors().add(productVersion.getColor());
        categoryRepo.save(category);
    }

    public void editProduct(Product product){
        product.setCategory(categoryRepo.findById(product.getCategoryId()));
        valueRepo.deleteAll(valueRepo.findByProductId(product.getId()));
        for (com.store.cosystore.domain.Value v : product.getValues()) {
            v.getId().setProductId(product.getId());
            valueRepo.save(v);
        }
        productRepo.save(product);
        for (ProductVersion pv : product.getProductVersions()){
            pv.setProduct(product);
            productVersionRepo.save(pv);
        }
    }

    public void deleteProduct(int productVersionId) {
        ProductVersion pv = productVersionRepo.findById(productVersionId);
        productVersionRepo.deleteById(productVersionId);
        if (pv.getProduct().getProductVersions().size() == 0)
            productRepo.deleteById(pv.getProduct().getId());
    }

    public List<ProductVersion> getPopularProducts(int count) {
        return productVersionRepo.findPopular().subList(0, count);
    }

    public List<ProductVersion> productsByProperties(List<com.store.cosystore.domain.Value> values) {
        List<Product> products = new ArrayList<>();
        for (int id : valueRepo.findProductsByValues(values)){
            products.add(productRepo.findById(id));
        }
        return products.stream()
                .flatMap(p -> p.getProductVersions().stream())
                .collect(Collectors.toList());
    }

    public List<ProductVersion> getProductsByRoom(String room) {
        return productRepo.findByIdIn(productRepo.findByRoom(room)).stream()
                .flatMap(p -> p.getProductVersions().stream())
                .collect(Collectors.toList());
    }
}
