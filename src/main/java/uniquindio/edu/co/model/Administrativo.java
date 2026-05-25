package uniquindio.edu.co.model;

public class Administrativo extends Usuario {

    /**
     * Construye un nuevo administrativo.
     *
     * @param nombre El nombre del administrativo.
     * @param identificacion La identificación del administrativo.
     */
    public Administrativo(String nombre, String identificacion) {
        super(nombre, identificacion, "ADMINISTRATIVO");
    }

    /**
     * Los administrativos tienen un 5% de descuento.
     */
    @Override
    public double obtenerDescuento() {
        return 0.05;
    }
}
