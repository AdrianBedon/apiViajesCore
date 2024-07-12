package com.arbc.development.mvc.models.entities;

public class Server {

    private String nombre;
    
    private String direccionIP;

    private String SO;

    private String motorBase;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccionIP() {
        return direccionIP;
    }

    public void setDireccionIP(String direccionIP) {
        this.direccionIP = direccionIP;
    }

    public String getSO() {
        return SO;
    }

    public void setSO(String sO) {
        SO = sO;
    }

    public String getMotorBase() {
        return motorBase;
    }

    public void setMotorBase(String motorBase) {
        this.motorBase = motorBase;
    }    
}
