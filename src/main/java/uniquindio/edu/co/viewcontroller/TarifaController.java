package uniquindio.edu.co.viewcontroller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import uniquindio.edu.co.App;
import uniquindio.edu.co.model.Parqueadero;
import uniquindio.edu.co.model.Tarifa;

import java.io.IOException;

public class TarifaController {

    @FXML private ComboBox<String> cbTipoVehiculo;
    @FXML private TextField txtValorHora;

    private Parqueadero parqueadero;

    @FXML
    public void initialize() {
        parqueadero = ViewController.getInstance().getParqueadero();
        cbTipoVehiculo.setItems(FXCollections.observableArrayList("CARRO", "MOTO", "BICICLETA"));
    }

    @FXML
    void agregarTarifa(ActionEvent event) {
        String tipo = cbTipoVehiculo.getValue();
        String valorStr = txtValorHora.getText();

        if (tipo == null || valorStr.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.", Alert.AlertType.ERROR);
            return;
        }

        try {
            double valor = Double.parseDouble(valorStr);
            Tarifa tarifa = new Tarifa(tipo, valor, 0);
            parqueadero.agregarTarifa(tarifa);
            
            mostrarAlerta("Éxito", "Tarifa agregada/actualizada correctamente.", Alert.AlertType.INFORMATION);
            txtValorHora.clear();
            cbTipoVehiculo.setValue(null);
            
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El valor de la hora debe ser numérico.", Alert.AlertType.ERROR);
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
