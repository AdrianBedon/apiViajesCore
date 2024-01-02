package com.arbc.development.mvc.models.dto;

public class FlightDto {
    private Long id;
    private String code;
    private String airline;
    private String city_departure;
    private String city_arrival;
    private double price;
    
    public FlightDto(Long id, String code, String airline, String city_departure, String city_arrival, double price) {
        this.id = id;
        this.code = code;
        this.airline = airline;
        this.city_departure = city_departure;
        this.city_arrival = city_arrival;
        this.price = price;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getAirline() {
        return airline;
    }
    public void setAirline(String airline) {
        this.airline = airline;
    }
    public String getCity_departure() {
        return city_departure;
    }
    public void setCity_departure(String city_departure) {
        this.city_departure = city_departure;
    }
    public String getCity_arrival() {
        return city_arrival;
    }
    public void setCity_arrival(String city_arrival) {
        this.city_arrival = city_arrival;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
