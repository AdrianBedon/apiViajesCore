package com.arbc.development.mvc.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arbc.development.mvc.models.dto.HotelDto;
import com.arbc.development.mvc.models.dto.mappers.DtoMapperHotel;
import com.arbc.development.mvc.models.entities.Hotel;
import com.arbc.development.mvc.repositories.CityRepository;
import com.arbc.development.mvc.repositories.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService{

    @Autowired
    private HotelRepository hotelRepository;
    
    @Autowired
    private CityRepository cityRepository;
    
    @Override
    @Transactional(readOnly = true)
    public List<HotelDto> findAll() {
        List<Hotel> hotels = (List<Hotel>) hotelRepository.findAll();
        return hotels
                .stream()
                .map(h -> DtoMapperHotel.builder().setHotel(h).build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelDto> findByCity(String city) {
        List<Hotel> hotels = (List<Hotel>) hotelRepository.findByCity(cityRepository.findByName(city).get());
        return hotels
                .stream()
                .map(h -> DtoMapperHotel.builder().setHotel(h).build())
                .collect(Collectors.toList());
    }
}
