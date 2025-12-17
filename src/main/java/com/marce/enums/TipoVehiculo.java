package com.marce.enums;

public enum TipoVehiculo {
    MOTO(3500, 125000),
    BICICLETA(3500, 125000),
    TRICIMOTO(4500, 250000),
    CUATRIMOTO(4500, 250000),
    SEDAN(4500, 250000),
    SUV(4500, 250000),
    COUPE(4500, 250000),
    CAMIONETA(4500, 250000);

    private final double tarifaPorTiempo;
    private final double tarifaPorMensual;

    private TipoVehiculo(double tarifaPorTiempo, double tarifaPorMensual) {
        this.tarifaPorTiempo = tarifaPorTiempo;
        this.tarifaPorMensual = tarifaPorMensual;
    }

    public double getTarifaPorTiempo() { return tarifaPorTiempo; }
    public double getTarifaPorMensual() { return tarifaPorMensual; }
}