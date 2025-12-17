package com.marce.model;
import java.time.LocalDateTime;
import com.marce.enums.TipoVehiculo;

public class Suv extends Vehiculo {
    public Suv(String placa, String modelo, LocalDateTime horaIngreso, TipoVehiculo tipo) {
        super(placa, modelo, horaIngreso, tipo);
    }

    public Suv(String placa, String modelo, LocalDateTime horaIngreso) {
        this(placa, modelo, horaIngreso, TipoVehiculo.SUV);
    }
}