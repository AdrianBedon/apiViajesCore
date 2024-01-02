package com.arbc.development.mvc.models.dto.mappers;

import com.arbc.development.mvc.models.dto.TravelPackageDto;
import com.arbc.development.mvc.models.entities.TravelPackage;

public class DtoMapperTravelPackage {

    private TravelPackage tPackage;

    private DtoMapperTravelPackage() {

    }

    public static DtoMapperTravelPackage builder() {
        return new DtoMapperTravelPackage();
    }

    public DtoMapperTravelPackage setTPackage(TravelPackage tPackage) {
        this.tPackage = tPackage;
        return this;
    }

    public TravelPackageDto build() {
        if(tPackage == null) {
            throw new RuntimeException("Debe enviar el entity travel package");
        }
        return new TravelPackageDto(this.tPackage.getId(), this.tPackage.getName(), this.tPackage.getHotel().getName(), this.tPackage.getFlight().getCode(), this.tPackage.getInitDate(), this.tPackage.getEndDate());
    }



}
