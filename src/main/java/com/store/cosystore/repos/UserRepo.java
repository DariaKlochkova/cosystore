package com.store.cosystore.repos;

import com.store.cosystore.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findById(int id);

    //@Query("select u.id, u.username, u.password from User u where u.username=?1")
    User findByUsername(String username);
}
