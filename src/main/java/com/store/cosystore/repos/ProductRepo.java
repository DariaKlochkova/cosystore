package com.store.cosystore.repos;

import com.store.cosystore.domain.Product;
import com.store.cosystore.domain.ProductVersion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public interface ProductRepo extends CrudRepository<Product, Integer> {
    Product findById(int id);
    Set<Product> findByCategoryId(int CategoryId);
    Set<Product> findByNameOrGeneralInfIgnoreCaseContaining(String s1, String s2);

    @Query(value = "select product_id from room where rooms = ?1", nativeQuery = true)
    Set<Integer> findByRoom(String room);

    Set<Product> findByIdIn(Set<Integer> ids);
}
