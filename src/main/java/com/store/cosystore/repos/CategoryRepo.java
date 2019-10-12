package com.store.cosystore.repos;

import com.store.cosystore.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Integer> {
    Category findByName(String name);
}
