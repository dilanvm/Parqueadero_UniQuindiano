package uniquindio.edu.co.model;

public abstract class Usuario {
    private String nombre;
    private String identificacion;
    private String tipoUsuario;

    /**
     * Construye un nuevo usuario.
     *
     * @param nombre El nombre del usuario.
     * @param identificacion La identificación del usuario.
     * @param tipoUsuario El tipo de usuario.
     */
    public Usuario(String nombre, String identificacion, String tipoUsuario) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * Obtiene el nombre del usuario.
     * @return El nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     * @param nombre El nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la identificación del usuario.
     * @return La identificación.
     */
    public String getIdentificacion() {
        return identificacion;
    }

    /**
     * Establece la identificación del usuario.
     * @param identificacion La identificación.
     */
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    /**
     * Obtiene el tipo de usuario.
     * @return El tipo de usuario.
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * Establece el tipo de usuario.
     * @param tipoUsuario El tipo de usuario.
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", identificacion='" + identificacion + '\'' +
                ", tipoUsuario='" + tipoUsuario + '\'' +
                '}';
    }

    /**
     * Método abstracto para obtener el porcentaje de descuento aplicable al usuario.
     * Es implementado por las clases hijas usando polimorfismo.
     * @return El porcentaje de descuento (ej. 0.20 para 20%).
     */
    public abstract double obtenerDescuento();
}
