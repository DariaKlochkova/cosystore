package com.store.cosystore.repos;

import com.store.cosystore.domain.Property;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepo extends CrudRepository<Property, Integer> {
    Property findByName(String name);
    Iterable<Property> findByCategory_Id(int category_id);
}
