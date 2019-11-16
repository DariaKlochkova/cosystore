package com.store.cosystore.repos;

import com.store.cosystore.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order, Integer> {
    Order findById(int id);
}
