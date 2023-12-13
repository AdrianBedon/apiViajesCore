package com.arbc.development.mvc.services;

import java.util.List;

import com.arbc.development.mvc.models.dto.HotelDto;

public interface HotelService {
    List<HotelDto> findAll();

    List<HotelDto> findByCity(String city);
}
