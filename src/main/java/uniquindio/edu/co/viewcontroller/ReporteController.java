package uniquindio.edu.co.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import uniquindio.edu.co.App;
import uniquindio.edu.co.model.Parqueadero;
import uniquindio.edu.co.model.Vehiculo;

import java.io.IOException;
import java.util.ArrayList;

public class ReporteController {

    @FXML private Label lblVehiculosHoy;
    @FXML private Label lblIngresos;
    @FXML private Label lblPromedio;
    @FXML private Label lblResultadoTiempo;
    @FXML private TextField txtMinutos;

    private Parqueadero parqueadero;

    @FXML
    public void initialize() {
        parqueadero = ViewController.getInstance().getParqueadero();
    }

    @FXML
    void actualizarReporte(ActionEvent event) {
        lblVehiculosHoy.setText("Vehículos ingresados hoy: " + parqueadero.getVehiculosIngresadosHoy());
        lblIngresos.setText("Ingresos generados: $" + parqueadero.getIngresosGenerados());
        lblPromedio.setText("Tiempo promedio de permanencia: " + parqueadero.getTiempoPromedioPermancencia() + " minutos");
    }

    @FXML
    void buscarPorTiempo(ActionEvent event) {
        String texto = txtMinutos.getText();
        if (texto.isEmpty()) {
            mostrarAlerta("Error", "Ingresa los minutos para buscar.", Alert.AlertType.ERROR);
            return;
        }
        try {
            int minutos = Integer.parseInt(texto);
            ArrayList<Vehiculo> resultado = parqueadero.getVehiculosMasDe(minutos);
            if (resultado.isEmpty()) {
                lblResultadoTiempo.setText("No hay vehículos con más de " + minutos + " minutos.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (Vehiculo v : resultado) {
                    sb.append(v.getPlaca()).append(" | ").append(v.getNombreConductor()).append("\n");
                }
                lblResultadoTiempo.setText(sb.toString());
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Ingresa un número válido.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void volverMenu(ActionEvent event) {
        try {
            App.setRoot("menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}