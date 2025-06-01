package com.lotr.ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BatallaEjercitoController {

    @FXML
    private Button btnComenzar;

    @FXML
    private Button btnReiniciar;

    @FXML
    private Button btnSalir;

    @FXML
    private Button btnSiguienteTurno;

    @FXML
    private ListView<String> listViewBestias;

    @FXML
    private ListView<String> listViewHeroes;

    @FXML
    private TextArea txtLogBatalla;

    @FXML
    private VBox vboxBestias;

    @FXML
    private VBox vboxHeroes;

   


    @FXML
    void onComenzar(ActionEvent event) {
        // Lista de ejemplo para héroes
        listViewHeroes.getItems().clear();
        listViewHeroes.getItems().addAll(
            "👨‍✈️ Aragorn - Vida: 150 - Armadura: 50",
            "🧝‍♂️ Legolas - Vida: 150 - Armadura: 30",
            "🧙‍♂️ Gandalf - Vida: 300 - Armadura: 30",
            "🧔 Boromir - Vida: 100 - Armadura: 60",
            "🧑‍🌾 Frodo - Vida: 20 - Armadura: 10"
        );

        // Lista de ejemplo para bestias
        listViewBestias.getItems().clear();
        listViewBestias.getItems().addAll(
            "👹 Lurtz (Orco) - Vida: 200 - Armadura: 60",
            "👹 Shagrat (Orco) - Vida: 220 - Armadura: 50",
            "👺 Uglúk (Trasgo) - Vida: 120 - Armadura: 30",
            "👺 Mauhúr (Trasgo) - Vida: 100 - Armadura: 30"
        );

        // Limpiar log de batalla
        txtLogBatalla.clear();
        txtLogBatalla.appendText("⚔️ ¡La batalla comienza entre héroes y bestias!\n");
    }


    @FXML
    void onReiniciar(ActionEvent event) {

    }

    @FXML
    void onSiguienteTurno(ActionEvent event) {

    }

    @FXML
    void onVolverAlMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ventana.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Menú Principal");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void initialize() {
    }

}
