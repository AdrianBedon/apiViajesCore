package com.arbc.development.mvc.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.arbc.development.mvc.models.entities.User;

public interface UserRepository extends CrudRepository<User, Long>{
    Optional<User> findByUsername(String username);
}
