package com.marce.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.marce.enums.TipoVehiculo;

public abstract class Vehiculo implements Cloneable, Serializable {
    private String placa;
    private String modelo;
    protected LocalDateTime horaIngreso;
    private TipoVehiculo tipoVehiculo;
    
    // Datos Cliente (Requerimiento)
    private String nombrePropietario;
    private String cedula;
    private String color;
    private String ubicacion;

    public Vehiculo(String placa, String modelo, LocalDateTime horaIngreso, TipoVehiculo tipoVehiculo) {
        this.placa = placa;
        this.modelo = modelo;
        this.horaIngreso = horaIngreso;
        this.tipoVehiculo = tipoVehiculo;
    }

    // Getters y Setters
    public String getPlaca() { return placa; }
    public String getModelo() { return modelo; }
    public LocalDateTime getHoraIngreso() { return horaIngreso; }
    public TipoVehiculo getTipoVehiculo() { return tipoVehiculo; }

    public String getNombrePropietario() { return nombrePropietario; }
    public void setNombrePropietario(String n) { this.nombrePropietario = n; }
    
    public String getCedula() { return cedula; }
    public void setCedula(String c) { this.cedula = c; }
    
    public String getColor() { return color; }
    public void setColor(String c) { this.color = c; }
    
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String u) { this.ubicacion = u; }

    public void registrarIngreso() { horaIngreso = LocalDateTime.now(); }
    public void registrarSalida() { horaIngreso = LocalDateTime.now(); }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Vehiculo v = (Vehiculo) super.clone();
        v.horaIngreso = LocalDateTime.now();
        return v;
    }
}