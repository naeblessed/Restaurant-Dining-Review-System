package com.example.DiningReview.repository;

import com.example.DiningReview.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    boolean existsByUsername(String name);

    List<User> findAll();
}
