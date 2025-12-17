package com.marce.repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

import com.marce.configuration.ConnectionDB;
import com.marce.enums.TipoVehiculo;
import com.marce.factory.VehiculoFactory;
import com.marce.model.Vehiculo;

public class ParqueaderoDatos {

    private static ParqueaderoDatos instancia;
    private final Set<String> placas = new HashSet<>();
    private final Map<String, Vehiculo> mapaPlacas = new HashMap<>();

    public static ParqueaderoDatos getInstance() {
        if (instancia == null) instancia = new ParqueaderoDatos();
        return instancia;
    }

    private ParqueaderoDatos() {
        loadParqueaderoData();
    }

    private void loadParqueaderoData() {
        String sql = "SELECT * FROM vehiculos WHERE hora_ingreso IS NOT NULL";
        
        try (Connection db = ConnectionDB.connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vehiculo v = VehiculoFactory.crearVehiculo(
                    TipoVehiculo.valueOf(rs.getString("tipo")),
                    rs.getString("placa"),
                    rs.getString("modelo"),
                    rs.getObject("hora_ingreso", LocalDateTime.class)
                );
                if (v != null) {
                    v.setNombrePropietario(rs.getString("nombre_propietario"));
                    v.setCedula(rs.getString("cedula"));
                    v.setColor(rs.getString("color"));
                    v.setUbicacion(rs.getString("ubicacion"));
                    
                    placas.add(v.getPlaca());
                    mapaPlacas.put(v.getPlaca(), v);
                }
            }
        } catch (Exception e) {
            System.out.println("Nota: Base de datos vacía o error de conexión inicial.");
        }
    }

    public boolean guardar(Vehiculo vehiculo) {
        String sql = "INSERT INTO vehiculos(placa, modelo, tipo, hora_ingreso, nombre_propietario, cedula, color, ubicacion) VALUES(?,?,?,?,?,?,?,?)";
        try (Connection db = ConnectionDB.connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setString(1, vehiculo.getPlaca());
            stmt.setString(2, vehiculo.getModelo());
            stmt.setString(3, vehiculo.getTipoVehiculo().name());
            stmt.setObject(4, vehiculo.getHoraIngreso(), Types.TIMESTAMP);
            stmt.setString(5, vehiculo.getNombrePropietario());
            stmt.setString(6, vehiculo.getCedula());
            stmt.setString(7, vehiculo.getColor());
            stmt.setString(8, vehiculo.getUbicacion());
            stmt.executeUpdate();

            placas.add(vehiculo.getPlaca());
            mapaPlacas.put(vehiculo.getPlaca(), vehiculo);
            return true;
        } catch (Exception e) {
            System.out.println("Error SQL al guardar: " + e.getMessage());
            e.printStackTrace(); 
            return false;
        }
    }

    public void registrarSalida(String placa) {
        Vehiculo v = mapaPlacas.get(placa);
        if (v != null) {
            v.registrarSalida();
            String sql = "UPDATE vehiculos SET hora_ingreso = NULL WHERE placa = ?";
            try (Connection db = ConnectionDB.connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
                stmt.setString(1, placa);
                stmt.executeUpdate();
                placas.remove(placa);
                mapaPlacas.remove(placa);
            } catch (Exception e) { e.printStackTrace(); }
        }
    }
    
   // Método para GUARDAR en el historial 
    public void registrarPagoHistorial(Vehiculo v, double total) {
       
        String sql = "INSERT INTO historial_pagos (placa, tipo_vehiculo, hora_salida, total_pagado) VALUES (?, ?, ?, ?)";
        try (Connection db = ConnectionDB.connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            stmt.setString(1, v.getPlaca());
            stmt.setString(2, v.getTipoVehiculo().name());
            stmt.setObject(3, LocalDateTime.now());
            stmt.setDouble(4, total);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error guardando historial: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Método para LEER el historial
    public void mostrarHistorial() {
        String sql = "SELECT * FROM historial_pagos";
        try (Connection db = ConnectionDB.connect(); PreparedStatement stmt = db.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            System.out.println("--- HISTORIAL DE VENTAS ---");
            boolean hayDatos = false;
            while(rs.next()) {
                hayDatos = true;
                System.out.println("Placa: " + rs.getString("placa") + 
                                   " | Tipo: " + rs.getString("tipo_vehiculo") + 
                                   " | Total: $" + rs.getDouble("total_pagado") +
                                   " | Fecha: " + rs.getString("hora_salida"));
            }
            if (!hayDatos) {
                System.out.println("No hay registros en el historial aún.");
            }
            System.out.println("---------------------------");
        } catch (Exception e) {
            System.out.println("Error leyendo historial: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean existePlaca(String placa) { return placas.contains(placa); }
    public Vehiculo buscar(String placa) { return mapaPlacas.get(placa); }
    
    public Vehiculo buscarPorUbicacion(String ubi) {
        for (Vehiculo v : mapaPlacas.values()) 
            if (v.getUbicacion() != null && v.getUbicacion().equalsIgnoreCase(ubi)) return v;
        return null;
    }

    public void listarVehiculosActivos() {
        if (mapaPlacas.isEmpty()) {
            System.out.println("El parqueadero está vacío.");
            return;
        }
        
        System.out.println("--- 🅿️ VEHÍCULOS ACTUALMENTE EN EL PARQUEADERO ---");
        System.out.printf("%-10s | %-15s | %-15s | %-10s\n", "PLACA", "TIPO", "PROPIETARIO", "UBICACIÓN");
        System.out.println("------------------------------------------------------------");
        
        for (Vehiculo v : mapaPlacas.values()) {
            System.out.printf("%-10s | %-15s | %-15s | %-10s\n", 
                v.getPlaca(), 
                v.getTipoVehiculo(), 
                (v.getNombrePropietario() != null ? v.getNombrePropietario() : "N/A"),
                (v.getUbicacion() != null ? v.getUbicacion() : "Sin Asignar")
            );
        }
        System.out.println("------------------------------------------------------------");
    }


}