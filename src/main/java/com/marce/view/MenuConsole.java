package com.marce.view;

import java.util.Scanner;
import com.marce.enums.TipoVehiculo;
import com.marce.service.ParqueaderoFacade;

public class MenuConsole implements IValidarPago, IValidarTipo {
    Scanner scan;
    ParqueaderoFacade facade;

    public MenuConsole() {
        scan = new Scanner(System.in);
        facade = new ParqueaderoFacade(this, this);
    }

    public void iniciar() {
        int op;
        do {
            System.out.println("\n--- PARQUEADERO MARCE ---");
            System.out.println("1. Ingresar Vehículo (Cliente)");
            System.out.println("2. Registrar Salida");
            System.out.println("3. Historial");
            System.out.println("4. Vehículos Activos");
            System.out.println("0. Salir");
            op = leerEntero("Opción:");
            
            switch (op) {
                case 1 -> ingresar();
                case 2 -> salir();
                case 3 -> facade.verHistorial();
                case 4 -> facade.verVehiculosActivos();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }

    private void ingresar() {
        String placa = leerTexto("Placa:");
        String nombre = leerTexto("Nombre Propietario:");
        String cedula = leerTexto("Cédula:");
        String color = leerTexto("Color:");
        String ubi = leerTexto("Ubicación:");
        System.out.println(facade.registrarIngreso(placa, nombre, cedula, color, ubi));
    }

    private void salir() {
        int op = leerEntero("1. Por Placa | 2. Por Ubicación");
        if (op == 1) System.out.println(facade.procesarSalida(leerTexto("Placa:")));
        else if (op == 2) System.out.println(facade.procesarSalidaPorUbicacion(leerTexto("Ubicación:")));
    }

    @Override
    public int seleccionarTipoVehiculo() {
        System.out.println("Tipos: 1.MOTO 2.BICI 3.TRICIMOTO 4.CUATRI 5.SEDAN 6.SUV 7.COUPE 8.CAMIONETA");
        int sel = leerEntero("Seleccione #:") - 1;
        if (sel < 0 || sel >= TipoVehiculo.values().length) return 0; 
        return sel;
    }

    @Override
    public String seleccionarModelo(String placa) { return leerTexto("Modelo:"); }
    
    @Override
    public int validarPago(double total) { return leerEntero("Pagar $" + total + "? (Escriba el numero de la opcion: 1.SI / 0.NO)"); }

    private int leerEntero(String m) {
        System.out.println(m);
        try { return Integer.parseInt(scan.nextLine()); } catch (Exception e) { return -1; }
    }
    private String leerTexto(String m) {
        System.out.println(m);
        return scan.nextLine();
    }

}