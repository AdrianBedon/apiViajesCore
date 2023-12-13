package com.arbc.development.mvc.models.dto.mappers;

import com.arbc.development.mvc.models.dto.HotelDto;
import com.arbc.development.mvc.models.entities.Hotel;

public class DtoMapperHotel {

    private Hotel hotel;

    private DtoMapperHotel() {

    }

    public static DtoMapperHotel builder() {
        return new DtoMapperHotel();
    }

    public DtoMapperHotel setHotel(Hotel hotel) {
        this.hotel = hotel;
        return this;
    }

    public HotelDto build() {
        if(hotel == null) {
            throw new RuntimeException("Debe enviar el entity hotel!");
        }
        return new HotelDto(this.hotel.getId(), this.hotel.getName(), this.hotel.getCity().getName(), this.hotel.getPrice());
    }
}
