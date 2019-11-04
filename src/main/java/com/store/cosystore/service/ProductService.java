package com.store.cosystore.service;

import com.store.cosystore.domain.Color;
import com.store.cosystore.domain.Product;
import com.store.cosystore.domain.Room;
import com.store.cosystore.repos.CategoryRepo;
import com.store.cosystore.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class ProductService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ProductRepo productRepo;

    @Value("${upload.path}")
    private String uploadPath;

    public Iterable<Product> productList(){
        return productRepo.findAll();
    }

    public Product findProduct(int id){
        return productRepo.findById(id);
    }

    public void addProduct(int categoryId, String article, String name, int price, int count, String information, String description, MultipartFile[] files, String colorName, int height, int width, int depth, List<String> roomNames) throws IOException {
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

        Set<Room> rooms = new HashSet<>();
        for (String room : roomNames) {
            rooms.add(Room.valueOf(room));
        }

        Product product = new Product(categoryRepo.findById(categoryId), article, name, price, count, information, description, Color.getByName(colorName), height, width, depth, images, rooms);
        productRepo.save(product);
    }

//    public void addProduct(Product product, int categoryId, MultipartFile[] files, List<String> roomNames) throws IOException {
//        List<String> images = new ArrayList<>();
//        for(int i = 0; i < files.length; i++){
//            File uploadDir = new File(uploadPath);
//            if(!uploadDir.exists()){
//                uploadDir.mkdir();
//            }
//            String fileName = UUID.randomUUID().toString() +"." + files[i].getOriginalFilename();
//            files[i].transferTo(new File(uploadPath + "/" + fileName));
//            images.add(fileName);
//        }
//
//        Set<Room> rooms = new HashSet<>();
//        for (String room : roomNames) {
//            rooms.add(Room.valueOf(room));
//        }
//
//        product.setCategory(categoryRepo.findById(categoryId));
//        product.setImages(images);
//        product.setRooms(rooms);
//        productRepo.save(product);
//    }
}
