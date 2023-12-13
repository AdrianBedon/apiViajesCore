package com.arbc.development.mvc.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.arbc.development.mvc.models.entities.City;
import com.arbc.development.mvc.models.entities.Hotel;

public interface HotelRepository extends CrudRepository<Hotel, Long> {
    Optional<Hotel> findByName(String name);

    List<Hotel> findByCity(City city);
}
