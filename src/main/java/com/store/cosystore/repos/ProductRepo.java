package com.store.cosystore.repos;

import com.store.cosystore.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<Product, Integer> {
}
