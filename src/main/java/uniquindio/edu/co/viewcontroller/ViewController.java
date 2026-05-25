package uniquindio.edu.co.viewcontroller;

import uniquindio.edu.co.model.EspacioParqueadero;
import uniquindio.edu.co.model.Parqueadero;
import uniquindio.edu.co.model.Tarifa;

public class ViewController {

    // Instancia única (Singleton)
    private static ViewController instance;
    private Parqueadero parqueadero;
    private String rolUsuarioActual; // "ADMINISTRADOR" o "OPERADOR"

    // Constructor privado para que nadie más pueda hacer un "new ViewController()"
    private ViewController() {
        inicializarDatos();
    }

    // Método para obtener la única instancia de esta clase madre
    public static ViewController getInstance() {
        if (instance == null) {
            instance = new ViewController();
        }
        return instance;
    }

    private void inicializarDatos() {
        // Inicializar el parqueadero base
        parqueadero = new Parqueadero("Parqueadero UQ");
        
        // Datos de prueba iniciales
        parqueadero.agregarTarifa(new Tarifa("CARRO", 2500, 0));
        parqueadero.agregarTarifa(new Tarifa("MOTO", 1000, 0));
        
        parqueadero.agregarEspacio(new EspacioParqueadero("C1", "CARRO"));
        parqueadero.agregarEspacio(new EspacioParqueadero("C2", "CARRO"));
        parqueadero.agregarEspacio(new EspacioParqueadero("M1", "MOTO"));
    }

    public Parqueadero getParqueadero() {
        return parqueadero;
    }

    public String getRolUsuarioActual() {
        return rolUsuarioActual;
    }

    public void setRolUsuarioActual(String rolUsuarioActual) {
        this.rolUsuarioActual = rolUsuarioActual;
    }
}
