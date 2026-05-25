package uniquindio.edu.co;

import uniquindio.edu.co.model.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Iniciando prueba del Parqueadero UQ ---\n");

        // 1. Crear el parqueadero
        Parqueadero parqueadero = new Parqueadero("Parqueadero Central UQ");

        // 2. Agregar espacios
        parqueadero.agregarEspacio(new EspacioParqueadero("C1", "CARRO"));
        parqueadero.agregarEspacio(new EspacioParqueadero("C2", "CARRO"));
        parqueadero.agregarEspacio(new EspacioParqueadero("M1", "MOTO"));

        // 3. Configurar tarifas
        parqueadero.agregarTarifa(new Tarifa("CARRO", 2500, 0));
        parqueadero.agregarTarifa(new Tarifa("MOTO", 1000, 0));

        // 4. Registrar usuarios
        Estudiante estudiante1 = new Estudiante("Juan Perez", "1094000111");
        parqueadero.agregarUsuario(estudiante1);

        // 5. Registrar ingreso de vehículos
        Carro carroJuan = new Carro("ABC-123", "Juan Perez", "1094000111");
        
        // Modificamos un poco la hora de ingreso para simular que lleva 2 horas parqueado
        carroJuan.setHoraIngreso(LocalDateTime.now().minusHours(2)); 
        
        System.out.println(">> Registrando ingreso del vehículo:");
        parqueadero.registrarIngresoVehiculo(carroJuan);

        System.out.println("\n>> Listando estado del parqueadero:");
        parqueadero.listarVehiculosAdentro();
        parqueadero.listarEspaciosDisponibles();

        // 6. Registrar salida de vehículos
        System.out.println("\n>> Registrando salida del vehículo:");
        parqueadero.registrarSalidaVehiculo("ABC-123");

        System.out.println("\n--- Fin de la prueba ---");
    }
}
