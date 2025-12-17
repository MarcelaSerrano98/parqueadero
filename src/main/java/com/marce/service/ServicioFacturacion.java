package com.marce.service;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.marce.model.Vehiculo;

public class ServicioFacturacion {
    private final String PATH_FACTURAS = "facturas";

    public void facturar(Vehiculo vehiculo, String placa, double total, long horas) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        File dir = new File(PATH_FACTURAS);
        if(!dir.exists()) dir.mkdirs();

        try (PrintWriter pw = new PrintWriter(new FileWriter(new File(dir, "factura_" + placa + "_" + timestamp + ".txt")))) {
            pw.println("=== RECIBO MARCE ===");
            pw.println("Placa: " + placa);
            pw.println("Cliente: " + vehiculo.getNombrePropietario());
            pw.println("Total: " + total);
        } catch (Exception e) { e.printStackTrace(); }
    }
}