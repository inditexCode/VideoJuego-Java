package com.lotr.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

import com.lotr.modelo.Bestia;
import com.lotr.modelo.Heroe;

public class BatallaVentana {

    // Nuevo método mostrar que recibe los personajes para pasar al controlador
    public static void mostrar(Stage stage, double ancho, double alto, Heroe heroe, Bestia bestia) {
        try {
            FXMLLoader loader = new FXMLLoader(BatallaVentana.class.getResource("/batalla.fxml"));
            Parent root = loader.load();

            BatallaController controller = loader.getController();
            controller.setDatosBatalla(
            	    heroe.getNombre(), heroe.getVida(), heroe.getArmadura(), heroe.getImagen(),
            	    bestia.getNombre(), bestia.getVida(), bestia.getArmadura(), bestia.getImagen()
            	);

            Scene scene = new Scene(root, 700, 500);

            URL cssUrl = BatallaVentana.class.getResource("/style/estilosBatalla.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            } else {
                System.err.println("⚠️ No se pudo encontrar /style/estilosBatalla.css");
            }

            stage.setTitle("⚔️ Batalla del Señor de los Anillos");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
