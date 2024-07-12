package com.arbc.development.mvc.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.protobuf.ByteString;

public class ServerEncrypted {

    private byte[] nombre;
    
    private byte[] direccionIP;

    @JsonProperty("SO")
    private byte[] so;

    private byte[] motorBase;

    public byte[] getNombre() {
        return nombre;
    }

    public void setNombre(byte[] nombre) {
        this.nombre = nombre;
    }

    public byte[] getDireccionIP() {
        return direccionIP;
    }

    public void setDireccionIP(byte[] direccionIP) {
        this.direccionIP = direccionIP;
    }

    public byte[] getSO() {
        return so;
    }

    public void setSO(byte[] sO) {
        so = sO;
    }

    public byte[] getMotorBase() {
        return motorBase;
    }

    public void setMotorBase(byte[] motorBase) {
        this.motorBase = motorBase;
    }  
}
