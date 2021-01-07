package com.sapo.edu.jwt.repository;

import com.sapo.edu.jwt.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface UserRepository {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    List<User> getAllUser();
    void save(User user);
    User findLastUser();

}