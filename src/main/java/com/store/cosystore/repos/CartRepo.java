package com.store.cosystore.repos;

import com.store.cosystore.domain.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepo extends CrudRepository<Cart, Integer> {
    Iterable<Cart> findByUserId(int userId);
    Cart findByUserIdAndProductVersionId(int userId, int productId);
}
