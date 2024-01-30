package com.arbc.development.mvc.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.arbc.development.mvc.models.entities.TravelPackage;

public interface TravelPackageRepository extends CrudRepository<TravelPackage, Long>{
    List<TravelPackage> findByInitDate(LocalDate date);

    List<TravelPackage> findByPrice(Double price);
}
