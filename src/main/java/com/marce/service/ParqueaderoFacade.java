package com.marce.service;

import com.marce.factory.VehiculoFactory;
import com.marce.model.Vehiculo;
import com.marce.repository.ParqueaderoDatos;
import com.marce.view.IValidarPago;
import com.marce.view.IValidarTipo;
import com.marce.enums.TipoVehiculo;

public class ParqueaderoFacade {
    private final GestorIngreso gIngreso;
    private final GestorSalida gSalida;
    private final ParqueaderoDatos db;
    private final IValidarPago onValidPayment;
    private final IValidarTipo onRequestType;

    public ParqueaderoFacade(IValidarPago pago, IValidarTipo tipo) {
        this.gIngreso = new GestorIngreso();
        this.gSalida = new GestorSalida(gIngreso);
        this.db = ParqueaderoDatos.getInstance();
        this.onValidPayment = pago;
        this.onRequestType = tipo;
    }

    public String registrarIngreso(String placa, String nombre, String cedula, String color, String ubicacion) {
        if (db.existePlaca(placa)) return "Error: Placa ya registrada.";

        String modelo = onRequestType.seleccionarModelo(placa);
        int tipoIdx = onRequestType.seleccionarTipoVehiculo();
        TipoVehiculo tipo = TipoVehiculo.values()[tipoIdx];

        try {
            Vehiculo v = VehiculoFactory.crearVehiculo(tipo, placa, modelo, null);
            v.setNombrePropietario(nombre);
            v.setCedula(cedula);
            v.setColor(color);
            v.setUbicacion(ubicacion);
            
            if(db.guardar(v)) return "Ingreso Exitoso.";
            else return "Error Crítico: No se pudo guardar en Base de Datos (Revise Driver).";
            
        } catch (Exception e) { return "Error: " + e.getMessage(); }
    }

    public String procesarSalida(String placa) {
        Vehiculo v = db.buscar(placa);
        if (v == null) return "Vehículo no encontrado.";
        
        try {
            double total = gSalida.calcularCosto(v);
            if (onValidPayment.validarPago(total) == 1) {
                gSalida.procesarSalida(placa);
                db.registrarPagoHistorial(v, total);
                return "Salida y Pago registrados.";
            }
            return "Pago cancelado.";
        } catch (Exception e) { return "Error: " + e.getMessage(); }
    }
    
    public String procesarSalidaPorUbicacion(String ubi) {
        Vehiculo v = db.buscarPorUbicacion(ubi);
        return (v != null) ? procesarSalida(v.getPlaca()) : "Ubicación vacía.";
    }
    
    public void verHistorial() { db.mostrarHistorial(); }

    public void verVehiculosActivos() {
        db.listarVehiculosActivos();
    }
}