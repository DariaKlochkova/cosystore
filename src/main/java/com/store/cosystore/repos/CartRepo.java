package com.store.cosystore.repos;

import com.store.cosystore.domain.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CartRepo extends CrudRepository<Cart, Integer> {
    Set<Cart> findByUserId(int userId);
    Cart findByUserIdAndProductVersionId(int userId, int productId);
}
