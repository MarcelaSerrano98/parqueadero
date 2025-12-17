package com.marce.model.builder;
import java.time.LocalDateTime;
import com.marce.enums.TipoVehiculo; // Importar Enum

public interface VehiculoBuilder {
    public VehiculoBuilder conPlaca(String placa);
    public VehiculoBuilder conModelo(String modelo);
    public VehiculoBuilder conHora(LocalDateTime hora);
    public VehiculoBuilder conTipo(TipoVehiculo tipo); 
    public com.marce.model.Vehiculo build();
}