package com.arbc.development.mvc.models.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServersEncrypted {
    @JsonProperty("servidores")
    private List<ServerEncrypted> servidores;

    private int total_count;

    public List<ServerEncrypted> getServidores() {
        return servidores;
    }

    public void setServidores(List<ServerEncrypted> servidores) {
        this.servidores = servidores;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
}
