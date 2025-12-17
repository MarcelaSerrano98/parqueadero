package com.marce.model;
import java.time.LocalDateTime;
import com.marce.enums.TipoVehiculo;

public class Moto extends Vehiculo {
    // Constructor flexible
    public Moto(String placa, String modelo, LocalDateTime horaIngreso, TipoVehiculo tipo) {
        super(placa, modelo, horaIngreso, tipo);
    }
    
    // Constructor por defecto (para compatibilidad)
    public Moto(String placa, String modelo, LocalDateTime horaIngreso) {
        this(placa, modelo, horaIngreso, TipoVehiculo.MOTO);
    }
}