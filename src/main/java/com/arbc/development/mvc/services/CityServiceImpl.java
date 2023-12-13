package com.arbc.development.mvc.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arbc.development.mvc.models.dto.CityDto;
import com.arbc.development.mvc.models.dto.mappers.DtoMapperCity;
import com.arbc.development.mvc.models.entities.City;
import com.arbc.development.mvc.repositories.CityRepository;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private CityRepository cityRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CityDto> findAll() {
        List<City> cities = (List<City>) cityRepository.findAll();
        return cities
                .stream()
                .map(c -> DtoMapperCity.builder().setCity(c).build())
                .collect(Collectors.toList());
    }

}
