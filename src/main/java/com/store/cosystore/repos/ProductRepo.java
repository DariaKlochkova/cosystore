package com.store.cosystore.repos;

import com.store.cosystore.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ProductRepo extends CrudRepository<Product, Integer> {
    Product findById(int id);
    Set<Product> findByCategoryId(int CategoryId);
}
