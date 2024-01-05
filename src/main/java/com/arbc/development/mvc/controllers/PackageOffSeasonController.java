package com.arbc.development.mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arbc.development.mvc.models.dto.OffSeasonDto;
import com.arbc.development.mvc.services.OffSeasonService;

@RestController
@RequestMapping("/off")
public class PackageOffSeasonController {

    @Autowired
    private OffSeasonService service;

    @GetMapping
    public List<OffSeasonDto> list() {
        return service.findAll();
    }

}
