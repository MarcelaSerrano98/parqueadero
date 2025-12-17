package com.marce.model;
import java.time.LocalDateTime;
import com.marce.enums.TipoVehiculo;

public class Moto extends Vehiculo {
    public Moto(String placa, String modelo, LocalDateTime horaIngreso) {
        super(placa, modelo, horaIngreso, TipoVehiculo.MOTO);
    }
}