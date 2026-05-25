package uniquindio.edu.co.viewcontroller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import uniquindio.edu.co.App;
import uniquindio.edu.co.model.*;

import java.io.IOException;

public class UsuarioController {

    @FXML private TextField txtIdentificacionUsuario;
    @FXML private TextField txtNombreUsuario;
    @FXML private ComboBox<String> cbTipoUsuario;

    private Parqueadero parqueadero;

    @FXML
    public void initialize() {
        parqueadero = ViewController.getInstance().getParqueadero();
        cbTipoUsuario.setItems(FXCollections.observableArrayList("ESTUDIANTE", "DOCENTE", "ADMINISTRATIVO", "VISITANTE"));
    }

    @FXML
    void agregarUsuario(ActionEvent event) {
        String id = txtIdentificacionUsuario.getText();
        String nombre = txtNombreUsuario.getText();
        String tipo = cbTipoUsuario.getValue();

        if (id.isEmpty() || nombre.isEmpty() || tipo == null) {
            mostrarAlerta("Error", "Todos los campos son obligatorios para agregar un usuario.", Alert.AlertType.ERROR);
            return;
        }

        Usuario u;
        switch (tipo) {
            case "ESTUDIANTE": u = new Estudiante(nombre, id); break;
            case "DOCENTE": u = new Docente(nombre, id); break;
            case "ADMINISTRATIVO": u = new Administrativo(nombre, id); break;
            default: u = new Visitante(nombre, id); break;
        }

        int sizeAntes = parqueadero.getUsuarios().size();
        parqueadero.agregarUsuario(u);
        int sizeDespues = parqueadero.getUsuarios().size();

        if (sizeDespues > sizeAntes) {
            mostrarAlerta("Éxito", "Usuario agregado correctamente.", Alert.AlertType.INFORMATION);
            txtIdentificacionUsuario.clear();
            txtNombreUsuario.clear();
            cbTipoUsuario.setValue(null);
        } else {
            mostrarAlerta("Error", "No se pudo agregar el usuario. Posiblemente ya exista.", Alert.AlertType.ERROR);
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
