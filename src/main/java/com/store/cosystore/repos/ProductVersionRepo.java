package com.store.cosystore.repos;

import com.store.cosystore.domain.ProductVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ProductVersionRepo extends JpaRepository<ProductVersion, Integer> {
    ProductVersion findById(int id);
    ProductVersion findByArticle(String article);
    Set<ProductVersion> findByProductId(int productId);

    @Query("select p from ProductVersion p order by p.likes desc")
    List<ProductVersion> findPopular();

}
