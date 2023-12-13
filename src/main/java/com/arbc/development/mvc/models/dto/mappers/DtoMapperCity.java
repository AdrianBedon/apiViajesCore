package com.arbc.development.mvc.models.dto.mappers;

import com.arbc.development.mvc.models.dto.CityDto;
import com.arbc.development.mvc.models.entities.City;

public class DtoMapperCity {

    private City city;

    private DtoMapperCity() {

    }

    public static DtoMapperCity builder() {
        return new DtoMapperCity();
    }

    public DtoMapperCity setCity(City city) {
        this.city = city;
        return this;
    }

    public CityDto build() {
        if(city == null) {
            throw new RuntimeException("Debe enviar el entity city!");
        }
        return new CityDto(this.city.getId(), this.city.getName());
    }

}
