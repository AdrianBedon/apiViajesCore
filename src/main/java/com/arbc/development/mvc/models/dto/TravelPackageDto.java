package com.arbc.development.mvc.models.dto;

import java.time.LocalDate;

public class TravelPackageDto {

    private Long id;
    private String name;
    private String hotel;
    private String flight;
    private LocalDate initDate;
    private LocalDate endDate;
    private Double price;

    public TravelPackageDto(Long id, String name, String hotel, String flight, LocalDate initDate, LocalDate endDate, Double price) {
        this.id = id;
        this.name = name;
        this.hotel = hotel;
        this.flight = flight;
        this.initDate = initDate;
        this.endDate = endDate;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public LocalDate getInitDate() {
        return initDate;
    }

    public void setInitDate(LocalDate initDate) {
        this.initDate = initDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
