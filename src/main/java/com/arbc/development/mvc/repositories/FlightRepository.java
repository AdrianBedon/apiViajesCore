package com.arbc.development.mvc.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.arbc.development.mvc.models.entities.City;
import com.arbc.development.mvc.models.entities.Flight;

public interface FlightRepository extends CrudRepository<Flight, Long>{
    Optional<Flight> findByCode(String code);

    List<Flight> findByCityArrival(City city);
}
