package uniquindio.edu.co.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import uniquindio.edu.co.App;

import java.io.IOException;

public class MenuController {

    @FXML
    void abrirTarifas(ActionEvent event) {
        cambiarVista("tarifa");
    }

    @FXML
    void abrirUsuarios(ActionEvent event) {
        cambiarVista("usuario");
    }

    @FXML
    void abrirEspacios(ActionEvent event) {
        cambiarVista("espacio");
    }

    @FXML
    void abrirVehiculos(ActionEvent event) {
        cambiarVista("vehiculo");
    }

    @FXML
    void abrirReportes(ActionEvent event) {
        cambiarVista("reporte");
    }

    private void cambiarVista(String fxml) {
        try {
            App.setRoot(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
