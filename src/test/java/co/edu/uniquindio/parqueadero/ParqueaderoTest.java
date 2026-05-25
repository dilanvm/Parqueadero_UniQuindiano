package co.edu.uniquindio.parqueadero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniquindio.edu.co.model.*;

import static org.junit.jupiter.api.Assertions.*;

public class ParqueaderoTest {

    private Parqueadero parqueadero;

    @BeforeEach
    public void setUp() {
        parqueadero = new Parqueadero("ParkUQ");
        parqueadero.agregarEspacio(new EspacioParqueadero("C1", "CARRO"));
        parqueadero.agregarEspacio(new EspacioParqueadero("C2", "CARRO"));
        parqueadero.agregarEspacio(new EspacioParqueadero("M1", "MOTO"));
        parqueadero.agregarTarifa(new Tarifa("CARRO", 2000, 0));
        parqueadero.agregarTarifa(new Tarifa("MOTO", 1000, 0));
    }

    @Test
    public void testIngresarVehiculoExitoso() {
        Carro carro = new Carro("XYZ-123", "Juan Perez", "12345");
        parqueadero.registrarIngresoVehiculo(carro);
        assertEquals(1, parqueadero.getVehiculosAdentro().size());
        assertNotNull(carro.getEspacioAsignado());
    }

    @Test
    public void testVehiculoDuplicado() {
        Carro carro1 = new Carro("XYZ-123", "Juan Perez", "12345");
        Carro carro2 = new Carro("XYZ-123", "Maria Gomez", "54321");
        parqueadero.registrarIngresoVehiculo(carro1);
        parqueadero.registrarIngresoVehiculo(carro2);
        assertEquals(1, parqueadero.getVehiculosAdentro().size());
    }

    @Test
    public void testRegistrarSalida() {
        Carro carro = new Carro("XYZ-123", "Juan Perez", "12345");
        parqueadero.registrarIngresoVehiculo(carro);
        parqueadero.registrarSalidaVehiculo("XYZ-123");
        assertEquals(0, parqueadero.getVehiculosAdentro().size());
    }

    @Test
    public void testDescuentoEstudiante() {
        Usuario estudiante = new Estudiante("Carlos", "111");
        parqueadero.agregarUsuario(estudiante);
        Carro carro = new Carro("ABC-999", "Carlos", "111");
        parqueadero.registrarIngresoVehiculo(carro);
        assertEquals(1, parqueadero.getVehiculosAdentro().size());
        assertNotNull(carro.getEspacioAsignado());
    }

    @Test
    public void testConsultaEspacios() {
        assertEquals(3, parqueadero.getTotalEspacios());
        assertEquals(3, parqueadero.getEspaciosDisponibles());
        assertEquals(0, parqueadero.getEspaciosOcupados());
    }

    @Test
    public void testReporteIngresos() {
        assertEquals(0.0, parqueadero.getIngresosGenerados());
    }
}