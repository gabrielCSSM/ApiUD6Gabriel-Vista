package gabrielud6.vista;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControladorAPI6 implements Initializable {

    ObservableList listaJuegos;
    @FXML
    StackPane contenidoJuego, contenidoJugador;
    @FXML
    TableView tablaJuegos, tablaJugador;
    @FXML
    TableColumn<?, ?> colJuegos, colNombre, colPuntos;
    @FXML
    TextField campoJuego, campoJugador, campoPuntos;


    public void obtenerDatos() {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("http://127.0.0.1:8080/api6/juego")
                    .method("GET", null)
                    .build();

            Response response = client.newCall(request).execute();
            Gson nuevoson = new Gson();
            Juego[] lista = nuevoson.fromJson(response.body().string(), Juego[].class);

            listaJuegos = FXCollections.observableArrayList(lista);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList obtenerJugadores(long id) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("http://127.0.0.1:8080/api6/puntuacion/byGame/" + id)
                    .method("GET", null)
                    .build();

            Response response = client.newCall(request).execute();
            Gson nuevoson = new Gson();

            Puntuacion[] lista = nuevoson.fromJson(response.body().string(), Puntuacion[].class);
            ObservableList listaJugadores;
            listaJugadores = FXCollections.observableArrayList(lista);
            return listaJugadores;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        campoPuntos.setEditable(false);
        campoJuego.setEditable(false);
        campoJugador.setEditable(false);

        obtenerDatos();

        contenidoJuego.setVisible(false);
        contenidoJugador.setVisible(false);

        tablaJuegos.getItems().addAll(listaJuegos);

        this.colJuegos.setCellValueFactory(new PropertyValueFactory("nombre"));

        tablaJuegos.setOnMousePressed(event -> {
            obtenerJugadores(juegoSeleccionado().id);
        });
    }

    Juego juegoSeleccionado() {
        contenidoJuego.setVisible(true);
        contenidoJugador.setVisible(true);
        Juego j = (Juego) tablaJuegos.getSelectionModel().getSelectedItem();
        campoJuego.setText(j.getNombre());
        rellenarTablaJugadores(j);
        return j;
    }

    void rellenarTablaJugadores(Juego j) {
        tablaJugador.getItems().clear();
        tablaJugador.getItems().addAll(obtenerJugadores(j.id));
        this.colNombre.setCellValueFactory(new PropertyValueFactory("jugador"));
        this.colPuntos.setCellValueFactory(new PropertyValueFactory("puntuacion"));
        tablaJugador.setOnMousePressed(event -> {
            jugadorSeleccionado();
        });

    }

    Puntuacion jugadorSeleccionado() {
        Puntuacion p = (Puntuacion) tablaJugador.getSelectionModel().getSelectedItem();
        campoJugador.setText(p.getJugador());
        campoPuntos.setText(String.valueOf(p.getPuntuacion()));
        return p;
    }
}