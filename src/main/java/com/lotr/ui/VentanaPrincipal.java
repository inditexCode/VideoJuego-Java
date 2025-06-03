package com.lotr.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL; // ← Agrega esto para usar URL

// TODO: Auto-generated Javadoc
/**
 * The Class VentanaPrincipal.
 */
public class VentanaPrincipal extends Application {

    /**
     * Start.
     *
     * @param primaryStage the primary stage
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // 1. Cargar el archivo FXML
            Parent root = FXMLLoader.load(getClass().getResource("/ventana.fxml"));

            // 2. Crear la escena
            Scene scene = new Scene(root, 650, 750);

            // 3. Cargar el archivo CSS de forma segura
            URL cssUrl = getClass().getResource("/style/style.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            } else {
                System.err.println("⚠️ No se pudo encontrar /style/style.css");
            }

            // 4. Configurar la ventana
            primaryStage.setTitle("Batalla del Señor de los Anillos");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace(); // Log completo si algo falla
        }
    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
