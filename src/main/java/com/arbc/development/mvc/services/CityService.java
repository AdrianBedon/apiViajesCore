package com.arbc.development.mvc.services;

import java.util.List;

import com.arbc.development.mvc.models.dto.CityDto;

public interface CityService {
    List<CityDto> findAll();
}
