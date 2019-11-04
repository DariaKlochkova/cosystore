package com.store.cosystore.repos;

import com.store.cosystore.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findById(int id);
    User findByUsername(String username);
}
