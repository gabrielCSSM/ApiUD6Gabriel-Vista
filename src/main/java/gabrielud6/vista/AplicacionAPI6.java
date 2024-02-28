package gabrielud6.vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AplicacionAPI6 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AplicacionAPI6.class.getResource("vistaAPI6.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("FrontEnd - API6 - Gabriel");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}