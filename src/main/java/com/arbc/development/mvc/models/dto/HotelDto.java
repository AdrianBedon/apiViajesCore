package com.arbc.development.mvc.models.dto;

public class HotelDto {
    private Long id;
    private String name;
    private String city;
    private double price;

    public HotelDto(Long id, String name, String city, double price) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.price = price;
    }

    public HotelDto() {
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
