package uniquindio.edu.co.model;

import java.time.Duration;

public class Carro extends Vehiculo {

    /**
     * Construye un nuevo carro.
     *
     * @param placa La placa del carro.
     * @param nombreConductor El nombre del conductor.
     * @param identificacionConductor La identificación del conductor.
     */
    public Carro(String placa, String nombreConductor, String identificacionConductor) {
        super(placa, "CARRO", nombreConductor, identificacionConductor);
    }

    /**
     * Calcula el costo de parqueo para un carro.
     *
     * @param tarifa La tarifa a aplicar.
     * @return El costo total calculado.
     */
    @Override
    public double calcularCosto(Tarifa tarifa) {
        if (getHoraSalida() == null || getHoraIngreso() == null) {
            return 0.0;
        }
        long horas = Duration.between(getHoraIngreso(), getHoraSalida()).toHours();
        if (horas == 0) {
            horas = 1; // Cobrar al menos una hora
        }
        double costoBase = horas * tarifa.getValorPorHora();
        return tarifa.calcularValorConDescuento(costoBase);
    }
}
