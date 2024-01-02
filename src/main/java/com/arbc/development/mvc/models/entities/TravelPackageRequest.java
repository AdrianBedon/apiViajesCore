package com.arbc.development.mvc.models.entities;

import jakarta.validation.constraints.NotBlank;

public class TravelPackageRequest {

    @NotBlank
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private int amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
