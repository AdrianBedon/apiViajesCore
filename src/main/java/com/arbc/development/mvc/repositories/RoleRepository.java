package com.arbc.development.mvc.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.arbc.development.mvc.models.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
    Optional<Role> findByName(String name);
}
