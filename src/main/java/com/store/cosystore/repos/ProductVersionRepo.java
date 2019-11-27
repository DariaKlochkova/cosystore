package com.store.cosystore.repos;

import com.store.cosystore.domain.ProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ProductVersionRepo extends JpaRepository<ProductVersion, Integer> {
    ProductVersion findById(int id);
    ProductVersion findByArticle(String article);
    Set<ProductVersion> findByProductId(int productId);
}
