package com.store.cosystore.repos;

import com.store.cosystore.domain.Value;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValueRepo extends JpaRepository<Value, Integer>, MyValueRepo {
    Iterable<Value> findByProductId(int id);
}
