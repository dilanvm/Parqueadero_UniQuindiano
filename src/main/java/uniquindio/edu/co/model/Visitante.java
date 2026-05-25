package uniquindio.edu.co.model;

public class Visitante extends Usuario {

    /**
     * Construye un nuevo visitante.
     *
     * @param nombre El nombre del visitante.
     * @param identificacion La identificación del visitante.
     */
    public Visitante(String nombre, String identificacion) {
        super(nombre, identificacion, "VISITANTE");
    }

    /**
     * Los visitantes no tienen descuento.
     */
    @Override
    public double obtenerDescuento() {
        return 0.0;
    }
}
