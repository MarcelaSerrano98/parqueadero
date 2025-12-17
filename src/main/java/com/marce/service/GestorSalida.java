package com.marce.service;
import java.time.Duration;
import java.time.LocalDateTime;
import com.marce.model.Vehiculo;

public class GestorSalida {
    private IValidator vIValidator;
    private ServicioFacturacion sFacturacion;

    public GestorSalida(IValidator vIValidator) {
        this.vIValidator = vIValidator;
        sFacturacion = new ServicioFacturacion();
    }

    public double calcularCosto(Vehiculo vehiculo) throws Exception {
        var hIngreso = vehiculo.getHoraIngreso();
        var hSalida = LocalDateTime.now();
        if (hSalida.isBefore(hIngreso)) hSalida = hIngreso.plusMinutes(60); 

        long minutos = Duration.between(hIngreso, hSalida).toMinutes();
        long horas = (long) Math.ceil(minutos / 60.0);
        if (horas == 0) horas = 1;

        double total = vehiculo.getTipoVehiculo().getTarifaPorTiempo() * horas;
        sFacturacion.facturar(vehiculo, vehiculo.getPlaca(), total, horas);
        return total;
    }

    public void procesarSalida(String placa) {
        vIValidator.realizarSalida(placa);
    }
    
    public boolean validarSalida(String placa) {
        return vIValidator.exitePlaca(placa);
    }
}