package com.store.cosystore.repos;

import com.store.cosystore.domain.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.LinkedHashSet;

public interface OrderRepo extends CrudRepository<Order, Integer> {
    Order findById(int id);
    LinkedHashSet<Order> findAllByOrderByDateDesc();
}
