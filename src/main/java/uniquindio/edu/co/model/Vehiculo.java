package uniquindio.edu.co.model;

import java.time.Duration;
import java.time.LocalDateTime;

public abstract class Vehiculo {
    private String placa;
    private String tipoVehiculo;
    private String nombreConductor;
    private String identificacionConductor;
    private LocalDateTime horaIngreso;
    private LocalDateTime horaSalida;
    private EspacioParqueadero espacioAsignado;
    private String estado;
    private double totalPagado;

    /**
     * Construye un nuevo vehículo con su información básica.
     *
     * @param placa La placa del vehículo.
     * @param tipoVehiculo El tipo de vehículo ("CARRO", "MOTO", "BICICLETA").
     * @param nombreConductor El nombre del conductor.
     * @param identificacionConductor La identificación del conductor.
     */
    public Vehiculo(String placa, String tipoVehiculo, String nombreConductor, String identificacionConductor) {
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.nombreConductor = nombreConductor;
        this.identificacionConductor = identificacionConductor;
        this.horaIngreso = LocalDateTime.now();
        this.horaSalida = null;
        this.estado = "Dentro";
        this.totalPagado = 0.0;
    }

    /**
     * Calcula el costo total a pagar según la tarifa especificada.
     *
     * @param tarifa La tarifa a aplicar.
     * @return El costo total calculado.
     */
    public abstract double calcularCosto(Tarifa tarifa);

    /**
     * Obtiene la placa del vehículo.
     * @return La placa del vehículo.
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Establece la placa del vehículo.
     * @param placa La placa del vehículo.
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * Obtiene el tipo de vehículo.
     * @return El tipo de vehículo.
     */
    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    /**
     * Establece el tipo de vehículo.
     * @param tipoVehiculo El tipo de vehículo.
     */
    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    /**
     * Obtiene el nombre del conductor.
     * @return El nombre del conductor.
     */
    public String getNombreConductor() {
        return nombreConductor;
    }

    /**
     * Establece el nombre del conductor.
     * @param nombreConductor El nombre del conductor.
     */
    public void setNombreConductor(String nombreConductor) {
        this.nombreConductor = nombreConductor;
    }

    /**
     * Obtiene la identificación del conductor.
     * @return La identificación del conductor.
     */
    public String getIdentificacionConductor() {
        return identificacionConductor;
    }

    /**
     * Establece la identificación del conductor.
     * @param identificacionConductor La identificación del conductor.
     */
    public void setIdentificacionConductor(String identificacionConductor) {
        this.identificacionConductor = identificacionConductor;
    }

    /**
     * Obtiene la hora de ingreso.
     * @return La hora de ingreso.
     */
    public LocalDateTime getHoraIngreso() {
        return horaIngreso;
    }

    /**
     * Establece la hora de ingreso.
     * @param horaIngreso La hora de ingreso.
     */
    public void setHoraIngreso(LocalDateTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    /**
     * Obtiene la hora de salida.
     * @return La hora de salida.
     */
    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    /**
     * Establece la hora de salida.
     * @param horaSalida La hora de salida.
     */
    public void setHoraSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    /**
     * Obtiene el espacio asignado.
     * @return El espacio asignado.
     */
    public EspacioParqueadero getEspacioAsignado() {
        return espacioAsignado;
    }

    /**
     * Establece el espacio asignado.
     * @param espacioAsignado El espacio asignado.
     */
    public void setEspacioAsignado(EspacioParqueadero espacioAsignado) {
        this.espacioAsignado = espacioAsignado;
    }

    /**
     * Obtiene el estado del vehículo.
     * @return El estado ("Dentro" o "Salio").
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del vehículo.
     * @param estado El estado del vehículo.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el total pagado.
     * @return El total pagado.
     */
    public double getTotalPagado() {
        return totalPagado;
    }

    /**
     * Establece el total pagado.
     * @param totalPagado El total pagado.
     */
    public void setTotalPagado(double totalPagado) {
        this.totalPagado = totalPagado;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", tipoVehiculo='" + tipoVehiculo + '\'' +
                ", nombreConductor='" + nombreConductor + '\'' +
                ", identificacionConductor='" + identificacionConductor + '\'' +
                ", horaIngreso=" + horaIngreso +
                ", horaSalida=" + horaSalida +
                ", estado='" + estado + '\'' +
                ", totalPagado=" + totalPagado +
                '}';
    }
}
