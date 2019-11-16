package com.store.cosystore.repos;

import com.store.cosystore.domain.CategoryGroup;
import org.springframework.data.repository.CrudRepository;

public interface CategoryGroupRepo extends CrudRepository<CategoryGroup, Integer> {
    CategoryGroup findById(int id);
    CategoryGroup findByName(String name);
}