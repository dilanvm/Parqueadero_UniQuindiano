package uniquindio.edu.co.model;

public class Docente extends Usuario {

    /**
     * Construye un nuevo docente.
     *
     * @param nombre El nombre del docente.
     * @param identificacion La identificación del docente.
     */
    public Docente(String nombre, String identificacion) {
        super(nombre, identificacion, "DOCENTE");
    }

    /**
     * Los docentes tienen un 10% de descuento.
     */
    @Override
    public double obtenerDescuento() {
        return 0.10;
    }
}
