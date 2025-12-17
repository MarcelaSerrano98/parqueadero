package com.marce.model.builder;

import java.time.LocalDateTime;
import com.marce.model.Carro;
import com.marce.model.Vehiculo;

public class CarroBuilder implements VehiculoBuilder {
    private String placa = "NNN000";
    private String modelo = "2000";
    private LocalDateTime horaIngreso = LocalDateTime.now();

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

    @Override
    public Vehiculo build() {
        return new Carro(placa, modelo, horaIngreso);
    }
}