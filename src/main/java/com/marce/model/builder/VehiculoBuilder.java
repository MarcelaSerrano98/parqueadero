package com.marce.model.builder;

import java.time.LocalDateTime;
import com.marce.model.Vehiculo;

public interface VehiculoBuilder {
    public VehiculoBuilder conPlaca(String placa);
    public VehiculoBuilder conModelo(String modelo);
    public VehiculoBuilder conHora(LocalDateTime hora);
    public Vehiculo build();
}