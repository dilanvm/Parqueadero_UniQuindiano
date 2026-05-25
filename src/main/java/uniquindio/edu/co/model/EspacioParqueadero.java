package uniquindio.edu.co.model;

public class EspacioParqueadero {
    private String codigo;
    private String tipoEspacio;
    private String estado;
    private Vehiculo vehiculoAsignado;

    /**
     * Construye un nuevo espacio de parqueadero.
     *
     * @param codigo El código identificador del espacio.
     * @param tipoEspacio El tipo de espacio ("CARRO", "MOTO", "BICICLETA").
     */
    public EspacioParqueadero(String codigo, String tipoEspacio) {
        this.codigo = codigo;
        this.tipoEspacio = tipoEspacio;
        this.estado = "Disponible";
        this.vehiculoAsignado = null;
    }

    /**
     * Ocupa el espacio con un vehículo especificado.
     *
     * @param vehiculo El vehículo que ocupará el espacio.
     */
    public void ocupar(Vehiculo vehiculo) {
        this.estado = "Ocupado";
        this.vehiculoAsignado = vehiculo;
    }

    /**
     * Libera el espacio, marcándolo como disponible.
     */
    public void ponerDisponible() {
        this.estado = "Disponible";
        this.vehiculoAsignado = null;
    }

    /**
     * Marca el espacio como fuera de servicio.
     */
    public void ponerFueraDeServicio() {
        this.estado = "Fuera de servicio";
        this.vehiculoAsignado = null;
    }

    /**
     * Obtiene el código del espacio.
     * @return El código.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el código del espacio.
     * @param codigo El código.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el tipo de espacio.
     * @return El tipo de espacio.
     */
    public String getTipoEspacio() {
        return tipoEspacio;
    }

    /**
     * Establece el tipo de espacio.
     * @param tipoEspacio El tipo de espacio.
     */
    public void setTipoEspacio(String tipoEspacio) {
        this.tipoEspacio = tipoEspacio;
    }

    /**
     * Obtiene el estado del espacio.
     * @return El estado del espacio.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del espacio.
     * @param estado El estado ("Disponible", "Ocupado", "Fuera de servicio").
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el vehículo asignado al espacio.
     * @return El vehículo asignado.
     */
    public Vehiculo getVehiculoAsignado() {
        return vehiculoAsignado;
    }

    /**
     * Establece el vehículo asignado al espacio.
     * @param vehiculoAsignado El vehículo.
     */
    public void setVehiculoAsignado(Vehiculo vehiculoAsignado) {
        this.vehiculoAsignado = vehiculoAsignado;
    }

    @Override
    public String toString() {
        return "EspacioParqueadero{" +
                "codigo='" + codigo + '\'' +
                ", tipoEspacio='" + tipoEspacio + '\'' +
                ", estado='" + estado + '\'' +
                ", vehiculoAsignado=" + vehiculoAsignado +
                '}';
    }
}
