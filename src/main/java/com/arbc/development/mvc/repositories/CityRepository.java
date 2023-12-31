package com.arbc.development.mvc.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.arbc.development.mvc.models.entities.City;

public interface CityRepository extends CrudRepository<City, Long>{
    Optional<City> findByName(String name);
}
