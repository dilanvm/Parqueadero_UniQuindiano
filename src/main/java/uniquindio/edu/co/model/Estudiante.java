package uniquindio.edu.co.model;

public class Estudiante extends Usuario {

    /**
     * Construye un nuevo estudiante.
     *
     * @param nombre El nombre del estudiante.
     * @param identificacion La identificación del estudiante.
     */
    public Estudiante(String nombre, String identificacion) {
        super(nombre, identificacion, "ESTUDIANTE");
    }

    /**
     * Los estudiantes tienen un 20% de descuento.
     */
    @Override
    public double obtenerDescuento() {
        return 0.20;
    }
}
