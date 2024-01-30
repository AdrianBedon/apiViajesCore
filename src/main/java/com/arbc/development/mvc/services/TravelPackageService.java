package com.arbc.development.mvc.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.arbc.development.mvc.models.dto.TravelPackageDto;
import com.arbc.development.mvc.models.entities.TravelPackageRequest;
import com.arbc.development.mvc.models.entities.TravelPackageSave;

public interface TravelPackageService {
    List<TravelPackageDto> findAll();

    Optional<TravelPackageDto> findById(Long id);

    Optional<TravelPackageDto> save(TravelPackageSave tPackage);

    Optional<TravelPackageDto> update(TravelPackageRequest tpRequest);

    void remove(Long id);

    List<TravelPackageDto> findByDate(LocalDate date);

    List<TravelPackageDto> findByPrice(Double priceInitial, Double priceFinal);
}
