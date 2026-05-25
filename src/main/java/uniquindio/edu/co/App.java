package uniquindio.edu.co;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uniquindio.edu.co.model.Parqueadero;
import uniquindio.edu.co.viewcontroller.MenuController;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/uniquindio/edu/co/menu.fxml"));
        Parent root = fxmlLoader.load();

        scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Sistema de Parqueadero UQ");
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/uniquindio/edu/co/" + fxml + ".fxml"));
        Parent root = fxmlLoader.load();
        scene.setRoot(root);
    }


    public static void main(String[] args) {
        launch();
    }
}
