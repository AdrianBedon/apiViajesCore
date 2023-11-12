package com.arbc.development.mvc.services;

import java.util.List;
import java.util.Optional;

import com.arbc.development.mvc.models.entities.User;

public interface UserService {
    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    void remove(Long id);
}
