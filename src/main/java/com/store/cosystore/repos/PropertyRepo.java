package com.store.cosystore.repos;

import com.store.cosystore.domain.Property;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepo extends CrudRepository<Property, Integer> {
    Iterable<Property> findByCategoryId(int category_id);
}
