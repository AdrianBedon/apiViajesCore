package com.arbc.development.mvc.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "flights_core")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String airline;

    @OneToOne
    @JoinColumn(name = "id_city_departure", referencedColumnName = "id")
    private City city_departure;

    @OneToOne
    @JoinColumn(name = "id_city_arrival", referencedColumnName = "id")
    private City cityArrival;

    private double price;

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

    public City getCity_departure() {
        return city_departure;
    }

    public void setCity_departure(City city_departure) {
        this.city_departure = city_departure;
    }

    public City getCityArrival() {
        return cityArrival;
    }

    public void setCity_arrival(City city_arrival) {
        this.cityArrival = city_arrival;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
