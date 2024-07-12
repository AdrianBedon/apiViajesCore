package com.arbc.development.mvc.models.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Servers {
    @JsonProperty("servidores")
    private List<Server> servidores;

    private int total_count;

    public List<Server> getServidores() {
        return servidores;
    }

    public void setServidores(List<Server> servidores) {
        this.servidores = servidores;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
}
