package com.marce.factory;

import java.time.LocalDateTime;
import com.marce.enums.TipoVehiculo;
import com.marce.model.*;
import com.marce.model.builder.CarroBuilder;

public class VehiculoFactory {
   
    public static Vehiculo crearVehiculo(TipoVehiculo tipo, String placa, String modelo, LocalDateTime hora) throws Exception {
        if (hora == null) hora = LocalDateTime.now();
        
        return switch (tipo) {
            case MOTO, BICICLETA -> new Moto(placa, modelo, hora);
            case SUV, CAMIONETA -> new Suv(placa, modelo, hora);
            default -> new CarroBuilder().conPlaca(placa).conModelo(modelo).conHora(hora).build();
        };
    }
}