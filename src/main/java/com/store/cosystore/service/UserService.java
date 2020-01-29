package com.store.cosystore.service;

import com.store.cosystore.domain.User;
import com.store.cosystore.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public User saveUserData(User user, User userData) {
        userData.setUsername(user.getUsername());
        userData.setPassword(user.getPassword());
        userData.setRoles(user.getRoles());
        userData.setWishList(user.getWishList());
        return userRepo.save(userData);
    }

    public boolean addUser(String username, String password, String firstname, String surname) {
        if(userRepo.findByUsername(username) != null)
            return false;
        userRepo.save(new User(username, passwordEncoder.encode(password), firstname, surname));
        return true;
    }

    public User getUser(User user) {
        if(user == null) return null;
        Optional<User> us = userRepo.findById(user.getId());
        return userRepo.findById(user.getId()).get();
    }
}
