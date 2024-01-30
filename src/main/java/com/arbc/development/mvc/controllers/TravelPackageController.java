package com.arbc.development.mvc.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arbc.development.mvc.models.dto.TravelPackageDto;
import com.arbc.development.mvc.models.entities.TravelPackageRequest;
import com.arbc.development.mvc.models.entities.TravelPackageSave;
import com.arbc.development.mvc.services.TravelPackageService;

@RestController
@RequestMapping("/packages")
public class TravelPackageController {

    @Autowired
    private TravelPackageService service;

    @GetMapping
    public List<TravelPackageDto> list() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TravelPackageSave tPackage) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(tPackage));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody TravelPackageRequest tpRequest) {
        Optional<TravelPackageDto> o = service.update(tpRequest);
        if (o.isPresent())
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<TravelPackageDto> o =  service.findById(id);
        if (o.isPresent())
        {
            service.remove(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/date/{date}")
    public List<TravelPackageDto> listDate(@PathVariable LocalDate date) {
        return service.findByDate(date);
    }

    @GetMapping("/price/{priceInitial}/{priceFinal}")
    public List<TravelPackageDto> listPrice(@PathVariable("priceInitial") Double priceInitial, @PathVariable("priceFinal") Double priceFinal) {
        return service.findByPrice(priceInitial, priceFinal);
    }

}
