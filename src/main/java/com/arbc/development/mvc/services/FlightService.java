package com.arbc.development.mvc.services;

import java.util.List;

import com.arbc.development.mvc.models.dto.FlightDto;

public interface FlightService {
    List<FlightDto> findAll();

    List<FlightDto> findByCity(String city);
}
