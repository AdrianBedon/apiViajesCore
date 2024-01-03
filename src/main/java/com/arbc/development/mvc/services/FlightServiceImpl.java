package com.arbc.development.mvc.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arbc.development.mvc.models.dto.FlightDto;
import com.arbc.development.mvc.models.dto.mappers.DtoMapperFlight;
import com.arbc.development.mvc.models.entities.Flight;
import com.arbc.development.mvc.repositories.CityRepository;
import com.arbc.development.mvc.repositories.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private CityRepository cityRepository;

    @Override
    @Transactional(readOnly = true)
    public List<FlightDto> findAll() {
        List<Flight> flights = (List<Flight>) flightRepository.findAll();
        return flights
                .stream()
                .map(f -> DtoMapperFlight.builder().setFlight(f).build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<FlightDto> findByCity(String city) {
        List<Flight> flights = (List<Flight>) flightRepository.findByCityArrival(cityRepository.findByName(city).get());
        return flights
                .stream()
                .map(f -> DtoMapperFlight.builder().setFlight(f).build())
                .collect(Collectors.toList());
    }

}
