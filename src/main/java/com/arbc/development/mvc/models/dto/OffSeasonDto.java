package com.arbc.development.mvc.models.dto;

import java.time.LocalDate;

public class OffSeasonDto {

    private LocalDate creation_date;
    private int amount;
    private String namePackage;
    private Double total;

    public OffSeasonDto (LocalDate creation_date, int amount, String namePackage, Double total) {
        this.creation_date = creation_date;
        this.amount = amount;
        this.namePackage = namePackage;
        this.total = total;
    }

    public LocalDate getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(LocalDate creation_date) {
        this.creation_date = creation_date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getNamePackage() {
        return namePackage;
    }

    public void setNamePackage(String namePackage) {
        this.namePackage = namePackage;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
