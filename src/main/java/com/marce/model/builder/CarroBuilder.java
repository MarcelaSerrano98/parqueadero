package com.marce.model.builder;

import java.time.LocalDateTime;
import com.marce.enums.TipoVehiculo;
import com.marce.model.Carro;
import com.marce.model.Vehiculo;

public class CarroBuilder implements VehiculoBuilder {
    private String placa = "NNN000";
    private String modelo = "2000";
    private LocalDateTime horaIngreso = LocalDateTime.now();
    private TipoVehiculo tipo = TipoVehiculo.SEDAN; // Valor por defecto

    public CarroBuilder() {}

    @Override
    public VehiculoBuilder conPlaca(String placa) {
        this.placa = placa;
        return this;
    }

    @Override
    public VehiculoBuilder conModelo(String modelo) {
        this.modelo = modelo;
        return this;
    }

    @Override
    public VehiculoBuilder conHora(LocalDateTime hora) {
        this.horaIngreso = hora;
        return this;
    }
    
    // ESTE ES EL MÉTODO IMPORTANTE
    @Override
    public VehiculoBuilder conTipo(TipoVehiculo tipo) {
        this.tipo = tipo;
        return this;
    }

    @Override
    public Vehiculo build() {
        return new Carro(placa, modelo, horaIngreso, tipo);
    }
}