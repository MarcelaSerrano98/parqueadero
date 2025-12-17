package com.marce.factory;

import java.time.LocalDateTime;
import com.marce.enums.TipoVehiculo;
import com.marce.model.*;
import com.marce.model.builder.CarroBuilder;

public class VehiculoFactory {

    public static Vehiculo crearVehiculo(int opcion, String placa, String modelo) throws Exception {
         return crearVehiculo(TipoVehiculo.values()[opcion], placa, modelo, LocalDateTime.now());
    }
    
    public static Vehiculo crearVehiculo(TipoVehiculo tipo, String placa, String modelo, LocalDateTime hora) throws Exception {
        if (hora == null) hora = LocalDateTime.now();
        
        return switch (tipo) {
            // GRUPO 1: Dos Ruedas -> Usamos la clase Moto
            case MOTO, BICICLETA -> new Moto(placa, modelo, hora, tipo);
            
            // GRUPO 2: Grandes -> Usamos la clase Suv
           case SUV, CAMIONETA -> new Suv(placa, modelo, hora, tipo);
            
            // GRUPO 3: Estándar y Otros -> Usamos el Builder de Carro
            default -> new CarroBuilder()
                    .conPlaca(placa)
                    .conModelo(modelo)
                    .conHora(hora)
                    .conTipo(tipo) // <--- El builder le pone el tipo correcto (Coupe, Trici, etc)
                    .build();
        };
    }
}