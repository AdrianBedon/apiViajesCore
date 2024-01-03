package com.arbc.development.mvc.models.dto.mappers;

import com.arbc.development.mvc.models.dto.FlightDto;
import com.arbc.development.mvc.models.entities.Flight;

public class DtoMapperFlight {

    private Flight flight;

    private DtoMapperFlight() {

    }

    public static DtoMapperFlight builder() {
        return new DtoMapperFlight();
    }

    public DtoMapperFlight setFlight(Flight flight) {
        this.flight = flight;
        return this;
    }

    public FlightDto build() {
        if(flight == null) {
            throw new RuntimeException("Debe enviar el entity flight!");
        }
        return new FlightDto(this.flight.getId(), this.flight.getCode(), this.flight.getAirline(), this.flight.getCity_departure().getName(), this.flight.getCityArrival().getName(), this.flight.getPrice());
    }

}
