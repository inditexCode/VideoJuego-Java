package com.lotr.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button; 
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    // Nuevos botones
    @FXML
    private Button btnAtacar;

    @FXML
    private Button btnReiniciar;

    @FXML
    private Button btnVolver;

    // Datos que llegan desde BatallaVentana
    private String nombreHeroe;
    private int vidaHeroe;
    private int armaduraHeroe;
    private Image imagenHeroe;

    private String nombreBestia;
    private int vidaBestia;
    private int armaduraBestia;
    private Image imagenBestia;

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
    }

    @FXML
    public void initialize() {
        // No hacemos nada aquí. Los datos llegan luego con setDatosBatalla()
    }

    // Nuevos métodos para los botones, vacíos para completar luego

    @FXML
    void onAtacar(ActionEvent event) {
        // Lógica de ataque va aquí
    }

    @FXML
    void onReiniciar(ActionEvent event) {
        // Lógica para reiniciar la batalla va aquí
    }

    @FXML
    void onVolver(ActionEvent event) {
        // Lógica para volver a la interfaz anterior va aquí
    }
}
