package com.arbc.development.mvc.services;

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
}
