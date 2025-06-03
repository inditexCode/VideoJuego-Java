package com.lotr.ui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.lotr.modelo.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class BatallaEjercitoController.
 */
public class BatallaEjercitoController {

    /** The btn comenzar. */
    @FXML
    private Button btnComenzar;

    /** The btn reiniciar. */
    @FXML
    private Button btnReiniciar;

    /** The btn salir. */
    @FXML
    private Button btnSalir;

    /** The btn siguiente turno. */
    @FXML
    private Button btnSiguienteTurno;

    /** The list view bestias. */
    @FXML
    private ListView<String> listViewBestias;

    /** The list view heroes. */
    @FXML
    private ListView<String> listViewHeroes;

    /** The txt log batalla. */
    @FXML
    private TextArea txtLogBatalla;

    /** The vbox bestias. */
    @FXML
    private VBox vboxBestias;

    /** The vbox heroes. */
    @FXML
    private VBox vboxHeroes;

    /** The ejercito heroes. */
    private List<Personaje> ejercitoHeroes = new ArrayList<>();
    
    /** The ejercito bestias. */
    private List<Personaje> ejercitoBestias = new ArrayList<>();
    
    /** The turno actual. */
    private int turnoActual = 1;

    /**
     * On comenzar.
     *
     * @param event the event
     */
    @FXML
    void onComenzar(ActionEvent event) {
        ejercitoHeroes.clear();
        ejercitoBestias.clear();

        // Crear héroes reales
        ejercitoHeroes.add(new Humano("Aragorn", 150, 50));
        ejercitoHeroes.add(new Elfo("Legolas", 150, 30));
        ejercitoHeroes.add(new Humano("Gandalf", 300, 30));
        ejercitoHeroes.add(new Humano("Boromir", 100, 60));
        ejercitoHeroes.add(new Hobbit("Frodo", 20, 10));

        // Crear bestias reales
        ejercitoBestias.add(new Orco("Lurtz", 200, 60));
        ejercitoBestias.add(new Orco("Shagrat", 220, 50));
        ejercitoBestias.add(new Trasgo("Uglúk", 120, 30));
        ejercitoBestias.add(new Trasgo("Mauhúr", 100, 30));

        turnoActual = 1;
        btnSiguienteTurno.setDisable(false);
        actualizarListViews();

        txtLogBatalla.clear();
        txtLogBatalla.appendText("⚔️ ¡La batalla comienza entre héroes y bestias!\n");

        onSiguienteTurno(null);
    }

    /**
     * On reiniciar.
     *
     * @param event the event
     */
    @FXML
    void onReiniciar(ActionEvent event) {
        onComenzar(event);
    }

    /**
     * On siguiente turno.
     *
     * @param event the event
     */
    @FXML
    void onSiguienteTurno(ActionEvent event) {
        txtLogBatalla.appendText("\n🔁 Turno " + turnoActual++ + ":\n");

        int enfrentamientos = Math.min(ejercitoHeroes.size(), ejercitoBestias.size());

        for (int i = 0; i < enfrentamientos; i++) {
            Personaje heroe = ejercitoHeroes.get(i);
            Personaje bestia = ejercitoBestias.get(i);

            int ataqueHeroe = heroe.atacar(bestia);
            int danioARecibirBestia = bestia.recibirDanio(ataqueHeroe);

            int ataqueBestia = bestia.atacar(heroe);
            int danioARecibirHeroe = heroe.recibirDanio(ataqueBestia);

            txtLogBatalla.appendText(heroe.getNombre() + " ataca a " + bestia.getNombre()
                    + " con " + ataqueHeroe + " ➜ daño: " + danioARecibirBestia + "\n");
            txtLogBatalla.appendText(bestia.getNombre() + " ataca a " + heroe.getNombre()
                    + " con " + ataqueBestia + " ➜ daño: " + danioARecibirHeroe + "\n");
        }

        // Eliminar personajes muertos
        ejercitoHeroes.removeIf(p -> p.getVida() <= 0);
        ejercitoBestias.removeIf(p -> p.getVida() <= 0);

        actualizarListViews();

        if (ejercitoHeroes.isEmpty()) {
            txtLogBatalla.appendText("💀 ¡VICTORIA DE LAS BESTIAS!\n");
            btnSiguienteTurno.setDisable(true);
            guardarResultadoBatalla("BESTIAS");
            mostrarAlertaConBotonVerResultado("Resultado", "💀 ¡VICTORIA DE LAS BESTIAS!");
        } else if (ejercitoBestias.isEmpty()) {
            txtLogBatalla.appendText("✨ ¡VICTORIA DE LOS HÉROES!\n");
            btnSiguienteTurno.setDisable(true);
            guardarResultadoBatalla("HÉROES");
            mostrarAlertaConBotonVerResultado("Resultado", "✨ ¡VICTORIA DE LOS HÉROES!");
        }
    }

    /**
     * Guardar resultado batalla.
     *
     * @param ganador the ganador
     */
    private void guardarResultadoBatalla(String ganador) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("resultado_batalla.txt", true)))) {
            writer.println("🗓️ Batalla finalizada: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            writer.println("🏆 Ganador: " + ganador);
            writer.println("💪 Sobrevivientes:");

            List<Personaje> sobrevivientes = ganador.equals("HÉROES") ? ejercitoHeroes : ejercitoBestias;
            for (Personaje p : sobrevivientes) {
                writer.println("- " + p.getClass().getSimpleName() + " " + p.getNombre() + " | Vida: " + p.getVida());
            }

            writer.println("----------------------------------------------------\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Actualizar list views.
     */
    private void actualizarListViews() {
        listViewHeroes.getItems().clear();
        for (Personaje p : ejercitoHeroes) {
            listViewHeroes.getItems().add(p.toString());
        }

        listViewBestias.getItems().clear();
        for (Personaje p : ejercitoBestias) {
            listViewBestias.getItems().add(p.toString());
        }
    }

    /**
     * On volver al menu.
     *
     * @param event the event
     */
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
    }

    /**
     * Mostrar alerta.
     *
     * @param titulo the titulo
     * @param mensaje the mensaje
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.getDialogPane().getStylesheets().add(getClass().getResource("/style/batallaEjercitoEstilos.css").toExternalForm());
        alerta.showAndWait();
    }

    /**
     * Mostrar alerta con boton ver resultado.
     *
     * @param titulo the titulo
     * @param mensaje the mensaje
     */
    private void mostrarAlertaConBotonVerResultado(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.getDialogPane().getStylesheets().add(
        	    getClass().getResource("/style/batallaEjercitoEstilos.css").toExternalForm()
        	);


        ButtonType botonCerrar = new ButtonType("Cerrar", ButtonData.CANCEL_CLOSE);
        ButtonType botonVerResultado = new ButtonType("Ver Resultado");

        alerta.getButtonTypes().setAll(botonVerResultado, botonCerrar);

        alerta.showAndWait().ifPresent(response -> {
            if (response == botonVerResultado) {
                try {
                    File file = new File("resultado_batalla.txt");
                    if (file.exists()) {
                        String contenido = new String(Files.readAllBytes(file.toPath()));
                        mostrarAlertaTexto("Resultados de la batalla", contenido);
                    } else {
                        mostrarAlerta("Error", "El archivo no existe.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    mostrarAlerta("Error", "No se pudo leer el archivo.");
                }
            }
        });
    }

    /**
     * Mostrar alerta texto.
     *
     * @param titulo the titulo
     * @param texto the texto
     */
    private void mostrarAlertaTexto(String titulo, String texto) {
        Alert alertaTexto = new Alert(Alert.AlertType.INFORMATION);
        alertaTexto.setTitle(titulo);
        alertaTexto.setHeaderText(null);

        TextArea textArea = new TextArea(texto);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);

        alertaTexto.getDialogPane().setContent(textArea);

        
        alertaTexto.getDialogPane().getStylesheets().add(
            getClass().getResource("/style/batallaEjercitoEstilos.css").toExternalForm()
        );

        alertaTexto.showAndWait();
    }

}
