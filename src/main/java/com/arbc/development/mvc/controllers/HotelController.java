package com.arbc.development.mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arbc.development.mvc.models.dto.HotelDto;
import com.arbc.development.mvc.services.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService service;

    @GetMapping
    public List<HotelDto> list() {
        return service.findAll();
    }

    @GetMapping("/{city}")
    public List<HotelDto> listCity(@PathVariable String city) {
        return service.findByCity(city);
    }

}
