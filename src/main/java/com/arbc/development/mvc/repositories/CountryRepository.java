package com.arbc.development.mvc.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.arbc.development.mvc.models.entities.Country;

public interface CountryRepository extends CrudRepository<Country, Long>{
    Optional<Country> findByName(String name);
}
