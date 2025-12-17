package com.marce.model;
import java.time.LocalDateTime;
import com.marce.enums.TipoVehiculo;

public class Suv extends Vehiculo {
    public Suv(String placa, String modelo, LocalDateTime horaIngreso) {
        super(placa, modelo, horaIngreso, TipoVehiculo.SUV);
    }
}