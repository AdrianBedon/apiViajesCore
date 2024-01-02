package com.arbc.development.mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arbc.development.mvc.models.dto.FlightDto;
import com.arbc.development.mvc.services.FlightService;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService service;

    @GetMapping
    public List<FlightDto> list() {
        return service.findAll();
    }

}
