package uniquindio.edu.co.viewcontroller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import uniquindio.edu.co.App;
import uniquindio.edu.co.model.*;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class VehiculoController {

    @FXML private TextField txtPlacaIngreso;
    @FXML private ComboBox<String> cbTipoVehiculo;
    @FXML private TextField txtNombreConductor;
    @FXML private TextField txtIdentificacionConductor;
    
    @FXML private TextField txtPlacaSalida;

    @FXML private TableView<Vehiculo> tablaVehiculos;
    @FXML private TableColumn<Vehiculo, String> colPlaca;
    @FXML private TableColumn<Vehiculo, String> colTipo;
    @FXML private TableColumn<Vehiculo, String> colHoraIngreso;

    private Parqueadero parqueadero;

    @FXML
    public void initialize() {
        parqueadero = ViewController.getInstance().getParqueadero();
        
        cbTipoVehiculo.setItems(FXCollections.observableArrayList("CARRO", "MOTO", "BICICLETA"));
        
        colPlaca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlaca()));
        colTipo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipoVehiculo()));
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        colHoraIngreso.setCellValueFactory(cellData -> {
            if (cellData.getValue().getHoraIngreso() != null) {
                return new SimpleStringProperty(cellData.getValue().getHoraIngreso().format(formatter));
            }
            return new SimpleStringProperty("");
        });

        actualizarTabla();
    }

    @FXML
    void registrarIngreso(ActionEvent event) {
        String placa = txtPlacaIngreso.getText();
        String tipo = cbTipoVehiculo.getValue();
        String nombre = txtNombreConductor.getText();
        String id = txtIdentificacionConductor.getText();

        if (placa.isEmpty() || tipo == null || nombre.isEmpty() || id.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios para el ingreso.", Alert.AlertType.ERROR);
            return;
        }

        Vehiculo v;
        if (tipo.equals("CARRO")) {
            v = new Carro(placa, nombre, id);
        } else if (tipo.equals("MOTO")) {
            v = new Moto(placa, nombre, id);
        } else {
            v = new Bicicleta(placa, nombre, id);
        }

        int sizeAntes = parqueadero.getVehiculosAdentro().size();
        parqueadero.registrarIngresoVehiculo(v);
        int sizeDespues = parqueadero.getVehiculosAdentro().size();

        if (sizeDespues > sizeAntes) {
            mostrarAlerta("Éxito", "Vehículo ingresado correctamente.", Alert.AlertType.INFORMATION);
            limpiarCamposIngreso();
            actualizarTabla();
        } else {
            mostrarAlerta("Error", "No se pudo ingresar el vehículo. Verifique si la placa ya existe o si no hay espacio.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void registrarSalida(ActionEvent event) {
        String placa = txtPlacaSalida.getText();
        if (placa.isEmpty()) {
            mostrarAlerta("Error", "Debe ingresar una placa para registrar la salida.", Alert.AlertType.ERROR);
            return;
        }

        Vehiculo v = parqueadero.buscarVehiculo(placa);
        if (v == null) {
            mostrarAlerta("Error", "No se encontró un vehículo con esa placa adentro.", Alert.AlertType.ERROR);
            return;
        }

        parqueadero.registrarSalidaVehiculo(placa);
        mostrarAlerta("Éxito", "Vehículo retirado. Costo total: $" + v.getTotalPagado(), Alert.AlertType.INFORMATION);
        txtPlacaSalida.clear();
        actualizarTabla();
    }

    @FXML
    void volverMenu(ActionEvent event) {
        try {
            App.setRoot("menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actualizarTabla() {
        ObservableList<Vehiculo> lista = FXCollections.observableArrayList(parqueadero.getVehiculosAdentro());
        tablaVehiculos.setItems(lista);
    }

    private void limpiarCamposIngreso() {
        txtPlacaIngreso.clear();
        cbTipoVehiculo.setValue(null);
        txtNombreConductor.clear();
        txtIdentificacionConductor.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
