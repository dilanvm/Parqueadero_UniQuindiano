package uniquindio.edu.co.model;

public class Tarifa {
    private String tipoVehiculo;
    private double valorPorHora;
    private double descuento;

    /**
     * Construye una nueva tarifa.
     *
     * @param tipoVehiculo El tipo de vehículo (ej. "CARRO").
     * @param valorPorHora El costo por hora.
     * @param descuento El porcentaje de descuento.
     */
    public Tarifa(String tipoVehiculo, double valorPorHora, double descuento) {
        this.tipoVehiculo = tipoVehiculo;
        this.valorPorHora = valorPorHora;
        this.descuento = descuento;
    }

    /**
     * Calcula el valor aplicando el porcentaje de descuento.
     *
     * @param valorTotal El valor total calculado previamente.
     * @return El valor final después del descuento.
     */
    public double calcularValorConDescuento(double valorTotal) {
        return valorTotal - (valorTotal * (descuento / 100));
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
     * Obtiene el valor por hora.
     * @return El valor por hora.
     */
    public double getValorPorHora() {
        return valorPorHora;
    }

    /**
     * Establece el valor por hora.
     * @param valorPorHora El valor por hora.
     */
    public void setValorPorHora(double valorPorHora) {
        this.valorPorHora = valorPorHora;
    }

    /**
     * Obtiene el porcentaje de descuento.
     * @return El porcentaje de descuento.
     */
    public double getDescuento() {
        return descuento;
    }

    /**
     * Establece el porcentaje de descuento.
     * @param descuento El porcentaje de descuento.
     */
    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "Tarifa{" +
                "tipoVehiculo='" + tipoVehiculo + '\'' +
                ", valorPorHora=" + valorPorHora +
                ", descuento=" + descuento +
                '}';
    }
}
