package com.lotr.ui;

import com.lotr.modelo.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class BatallaController {

    @FXML
    private ImageView imgBestia;

    @FXML
    private ImageView imgHeroe;

    @FXML
    private Label lblArmaduraBestia;

    @FXML
    private Label lblArmaduraHeroe;

    @FXML
    private Label lblNombreBestia;

    @FXML
    private Label lblNombreHeroe;

    @FXML
    private Label lblVidaBestia;

    @FXML
    private Label lblVidaHeroe;

    @FXML
    private TextArea txtBatallaInferior;

    @FXML
    private Button btnAtacar;

    @FXML
    private Button btnReiniciar;

    @FXML
    private Button btnVolver;

    private String nombreHeroe;
    private int vidaHeroe;
    private int armaduraHeroe;
    private Image imagenHeroe;

    private String nombreBestia;
    private int vidaBestia;
    private int armaduraBestia;
    private Image imagenBestia;

    private Personaje heroeObj;
    private Personaje bestiaObj;

    public void setDatosBatalla(String nombreHeroe, int vidaHeroe, int armaduraHeroe, Image imagenHeroe,
                                 String nombreBestia, int vidaBestia, int armaduraBestia, Image imagenBestia) {
        this.nombreHeroe = nombreHeroe;
        this.vidaHeroe = vidaHeroe;
        this.armaduraHeroe = armaduraHeroe;
        this.imagenHeroe = imagenHeroe;

        this.nombreBestia = nombreBestia;
        this.vidaBestia = vidaBestia;
        this.armaduraBestia = armaduraBestia;
        this.imagenBestia = imagenBestia;

        // Crear instancias reales de personajes
        if (nombreHeroe.toLowerCase().contains("legolas") || nombreHeroe.toLowerCase().contains("elfo")) {
            heroeObj = new Elfo(nombreHeroe, vidaHeroe, armaduraHeroe);
        } else if (nombreHeroe.toLowerCase().contains("frodo") || nombreHeroe.toLowerCase().contains("hobbit")) {
            heroeObj = new Hobbit(nombreHeroe, vidaHeroe, armaduraHeroe);
        } else {
            heroeObj = new Humano(nombreHeroe, vidaHeroe, armaduraHeroe);
        }

        if (nombreBestia.toLowerCase().contains("lurtz") || nombreBestia.toLowerCase().contains("shagrat") || nombreBestia.toLowerCase().contains("orco")) {
            bestiaObj = new Orco(nombreBestia, vidaBestia, armaduraBestia);
        } else {
            bestiaObj = new Trasgo(nombreBestia, vidaBestia, armaduraBestia);
        }

        heroeObj.setImagen(imagenHeroe);
        bestiaObj.setImagen(imagenBestia);

        actualizarVista();
    }

    private void actualizarVista() {
        lblNombreHeroe.setText(nombreHeroe);
        lblVidaHeroe.setText("Vida: " + vidaHeroe);
        lblArmaduraHeroe.setText("Armadura: " + armaduraHeroe);
        imgHeroe.setImage(imagenHeroe);

        lblNombreBestia.setText(nombreBestia);
        lblVidaBestia.setText("Vida: " + vidaBestia);
        lblArmaduraBestia.setText("Armadura: " + armaduraBestia);
        imgBestia.setImage(imagenBestia);

        txtBatallaInferior.setText("💥 ¡La batalla está por comenzar!");
        btnAtacar.setDisable(false);
    }

    private void actualizarVida() {
        lblVidaHeroe.setText("Vida: " + heroeObj.getVida());
        lblVidaBestia.setText("Vida: " + bestiaObj.getVida());
    }

    @FXML
    void onAtacar(ActionEvent event) {
        if (!heroeObj.estaVivo() || !bestiaObj.estaVivo()) {
            txtBatallaInferior.appendText("\n⚔️ La batalla ya terminó.");
            return;
        }

        int ataqueHeroe = heroeObj.atacar(bestiaObj);
        if (ataqueHeroe > 0) {
            bestiaObj.reducirVida(ataqueHeroe);
            txtBatallaInferior.appendText("\n🛡️ " + heroeObj.getNombre() + " ataca con " + ataqueHeroe +
                                          " y le quita " + ataqueHeroe + " de vida a " + bestiaObj.getNombre());
        } else {
            txtBatallaInferior.appendText("\n🛡️ " + heroeObj.getNombre() + " ataca con " + ataqueHeroe + " pero no hace daño.");
        }

        if (!bestiaObj.estaVivo()) {
            txtBatallaInferior.appendText("\n☠️ ¡Muere " + bestiaObj.getClass().getSimpleName() + " " + bestiaObj.getNombre() + "!");
            btnAtacar.setDisable(true);
        }

        if (bestiaObj.estaVivo()) {
            int ataqueBestia = bestiaObj.atacar(heroeObj);
            if (ataqueBestia > 0) {
                heroeObj.reducirVida(ataqueBestia);
                txtBatallaInferior.appendText("\n💥 " + bestiaObj.getNombre() + " responde con " + ataqueBestia +
                                              " y le quita " + ataqueBestia + " de vida a " + heroeObj.getNombre());
            } else {
                txtBatallaInferior.appendText("\n💥 " + bestiaObj.getNombre() + " responde con " + ataqueBestia + " pero no hace daño.");
            }

            if (!heroeObj.estaVivo()) {
                txtBatallaInferior.appendText("\n☠️ ¡Muere " + heroeObj.getClass().getSimpleName() + " " + heroeObj.getNombre() + "!");
                btnAtacar.setDisable(true);
            }
        }

        actualizarVida();
    }

    @FXML
    void onReiniciar(ActionEvent event) {
        setDatosBatalla(nombreHeroe, vidaHeroe, armaduraHeroe, imagenHeroe,
                        nombreBestia, vidaBestia, armaduraBestia, imagenBestia);
        txtBatallaInferior.setText("🔄 ¡Batalla reiniciada!");
    }

    @FXML
    void onVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ventana.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        // Esperamos los datos desde setDatosBatalla
    }
}
