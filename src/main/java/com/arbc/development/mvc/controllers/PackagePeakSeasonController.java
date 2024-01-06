package com.arbc.development.mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arbc.development.mvc.models.dto.PeakSeasonDto;
import com.arbc.development.mvc.models.entities.PeakReport;
import com.arbc.development.mvc.services.PeakSeasonService;

@RestController
@RequestMapping("/peak")
public class PackagePeakSeasonController {

    @Autowired
    private PeakSeasonService service;

    @GetMapping
    public List<PeakSeasonDto> list() {
        return service.findAll();
    }

    @GetMapping("/reportSale")
    public Double difference(@RequestBody PeakReport peakReport) {
        System.out.println(peakReport.getStartDate());
        return service.getReport(peakReport.getStartDate(), peakReport.getEndDate());
    }
}
