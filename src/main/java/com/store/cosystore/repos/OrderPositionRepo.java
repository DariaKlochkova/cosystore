package com.store.cosystore.repos;

import com.store.cosystore.domain.OrderPosition;
import org.springframework.data.repository.CrudRepository;

public interface OrderPositionRepo extends CrudRepository<OrderPosition, Integer> {

}
