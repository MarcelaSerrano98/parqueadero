
---

# 🚗 Sistema de Parqueadero (Java + MySQL)

Este es un proyecto desarrollado en Java para gestionar el control de un parqueadero. El sistema permite registrar el ingreso de vehículos, asignarles una ubicación, calcular la tarifa a pagar según el tiempo y mantener un historial de ventas.

## 📋 Funcionalidades

El programa cuenta con las siguientes opciones:

1. **Ingreso de Vehículo:**
* Registro de placa, modelo y hora de entrada.
* **Datos del Cliente:** Se guarda Nombre, Cédula, Color del vehículo y Ubicación asignada.
* **Tipos de Vehículo:** Soporta Motos, Bicicletas, Sedán, SUV, Camionetas, Coupé, etc.


2. **Registrar Salida:**
* Cálculo automático del costo según el tiempo transcurrido.
* Tarifas diferenciadas para 2 ruedas y 4 ruedas.
* Generación de factura en archivo `.txt`.


3. **Listar Vehículos:**
* Muestra una tabla con todos los vehículos que están actualmente dentro del parqueadero.


4. **Historial de Ventas:**
* Consulta de todos los pagos realizados y finalizados.



## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java (JDK 17)
* **Base de Datos:** MySQL (Ejecutado en Docker)
* **Gestor de Dependencias:** Maven
* **Arquitectura:** MVC (Modelo-Vista-Controlador) con Patrón Facade y Factory.

## 🚀 Cómo Ejecutar el Proyecto

### 1. Base de Datos

Asegúrate de tener corriendo el contenedor de Docker con MySQL en el puerto **3307**.

Ejecuta este script SQL para crear las tablas necesarias:

```sql
CREATE DATABASE IF NOT EXISTS parqueadero;
USE parqueadero;

-- Tabla de vehículos activos
CREATE TABLE IF NOT EXISTS vehiculos (
    placa VARCHAR(20) PRIMARY KEY,
    modelo VARCHAR(50),
    tipo VARCHAR(50),
    hora_ingreso DATETIME,
    nombre_propietario VARCHAR(100),
    cedula VARCHAR(20),
    color VARCHAR(30),
    ubicacion VARCHAR(50)
);

-- Tabla de historial de ventas
CREATE TABLE IF NOT EXISTS historial_pagos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(20),
    tipo_vehiculo VARCHAR(50),
    hora_salida DATETIME,
    total_pagado DOUBLE
);

```

### 2. Ejecutar la Aplicación

Si estás usando VS Code:

1. Actualiza el proyecto Maven (Click derecho en `pom.xml` -> Update Project).
2. Abre el archivo `src/main/java/com/marce/Main.java`.
3. Presiona el botón **Run** (Play).

---

**Autor:** Marcela Albarracín