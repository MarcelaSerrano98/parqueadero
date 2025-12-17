package com.marce.model;
import java.time.LocalDateTime;
import com.marce.enums.TipoVehiculo;

public class Carro extends Vehiculo {
    public Carro(String placa, String modelo, LocalDateTime horaIngreso, TipoVehiculo tipo) {
        super(placa, modelo, horaIngreso, tipo);
    }

    public Carro(String placa, String modelo, LocalDateTime horaIngreso) {
        this(placa, modelo, horaIngreso, TipoVehiculo.SEDAN);
    }
}