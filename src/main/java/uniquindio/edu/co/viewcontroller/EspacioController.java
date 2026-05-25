package uniquindio.edu.co.viewcontroller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import uniquindio.edu.co.App;
import uniquindio.edu.co.model.EspacioParqueadero;
import uniquindio.edu.co.model.Parqueadero;

import java.io.IOException;

public class EspacioController {

    @FXML private TextField txtCodigoEspacio;
    @FXML private ComboBox<String> cbTipoEspacio;
    @FXML private Label lblTotal;
    @FXML private Label lblOcupados;
    @FXML private Label lblDisponibles;

    private Parqueadero parqueadero;

    @FXML
    public void initialize() {
        parqueadero = ViewController.getInstance().getParqueadero();
        cbTipoEspacio.setItems(FXCollections.observableArrayList("CARRO", "MOTO", "BICICLETA"));
    }

    @FXML
    void agregarEspacio(ActionEvent event) {
        String codigo = txtCodigoEspacio.getText();
        String tipo = cbTipoEspacio.getValue();

        if (codigo.isEmpty() || tipo == null) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.", Alert.AlertType.ERROR);
            return;
        }

        EspacioParqueadero espacio = new EspacioParqueadero(codigo, tipo);
        int sizeAntes = parqueadero.getEspacios().size();
        parqueadero.agregarEspacio(espacio);
        int sizeDespues = parqueadero.getEspacios().size();

        if (sizeDespues > sizeAntes) {
            mostrarAlerta("Éxito", "Espacio agregado correctamente.", Alert.AlertType.INFORMATION);
            txtCodigoEspacio.clear();
            cbTipoEspacio.setValue(null);
        } else {
            mostrarAlerta("Error", "Ya existe un espacio con ese código.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void consultarEspacios(ActionEvent event) {
        int totalCarros = 0, totalMotos = 0, totalBicicletas = 0;
        int ocupadosCarros = 0, ocupadosMotos = 0, ocupadasBicicletas = 0;
        int disponiblesCarros = 0, disponiblesMotos = 0, disponiblesBicicletas = 0;

        for (int i = 0; i < parqueadero.getEspacios().size(); i++) {
            uniquindio.edu.co.model.EspacioParqueadero e = parqueadero.getEspacios().get(i);
            String tipo = e.getTipoEspacio();
            String estado = e.getEstado();

            if (tipo.equals("CARRO")) totalCarros++;
            else if (tipo.equals("MOTO")) totalMotos++;
            else if (tipo.equals("BICICLETA")) totalBicicletas++;

            if (estado.equals("Ocupado")) {
                if (tipo.equals("CARRO")) ocupadosCarros++;
                else if (tipo.equals("MOTO")) ocupadosMotos++;
                else if (tipo.equals("BICICLETA")) ocupadasBicicletas++;
            } else if (estado.equals("Disponible")) {
                if (tipo.equals("CARRO")) disponiblesCarros++;
                else if (tipo.equals("MOTO")) disponiblesMotos++;
                else if (tipo.equals("BICICLETA")) disponiblesBicicletas++;
            }
        }

        lblTotal.setText("Total: " + parqueadero.getTotalEspacios() +
                " (Carros: " + totalCarros +
                ", Motos: " + totalMotos +
                ", Bicicletas: " + totalBicicletas + ")");

        lblOcupados.setText("Ocupados: " + parqueadero.getEspaciosOcupados() +
                " (Carros: " + ocupadosCarros +
                ", Motos: " + ocupadosMotos +
                ", Bicicletas: " + ocupadasBicicletas + ")");

        lblDisponibles.setText("Disponibles: " + parqueadero.getEspaciosDisponibles() +
                " (Carros: " + disponiblesCarros +
                ", Motos: " + disponiblesMotos +
                ", Bicicletas: " + disponiblesBicicletas + ")");
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
