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

// TODO: Auto-generated Javadoc
/**
 * The Class BatallaController.
 */
public class BatallaController {

    /** The img bestia. */
    @FXML
    private ImageView imgBestia;

    /** The img heroe. */
    @FXML
    private ImageView imgHeroe;

    /** The lbl armadura bestia. */
    @FXML
    private Label lblArmaduraBestia;

    /** The lbl armadura heroe. */
    @FXML
    private Label lblArmaduraHeroe;

    /** The lbl nombre bestia. */
    @FXML
    private Label lblNombreBestia;

    /** The lbl nombre heroe. */
    @FXML
    private Label lblNombreHeroe;

    /** The lbl vida bestia. */
    @FXML
    private Label lblVidaBestia;

    /** The lbl vida heroe. */
    @FXML
    private Label lblVidaHeroe;

    /** The txt batalla inferior. */
    @FXML
    private TextArea txtBatallaInferior;

    /** The btn atacar. */
    @FXML
    private Button btnAtacar;

    /** The btn reiniciar. */
    @FXML
    private Button btnReiniciar;

    /** The btn volver. */
    @FXML
    private Button btnVolver;

    /** The nombre heroe. */
    private String nombreHeroe;
    
    /** The vida heroe. */
    private int vidaHeroe;
    
    /** The armadura heroe. */
    private int armaduraHeroe;
    
    /** The imagen heroe. */
    private Image imagenHeroe;

    /** The nombre bestia. */
    private String nombreBestia;
    
    /** The vida bestia. */
    private int vidaBestia;
    
    /** The armadura bestia. */
    private int armaduraBestia;
    
    /** The imagen bestia. */
    private Image imagenBestia;

    /** The heroe obj. */
    private Personaje heroeObj;
    
    /** The bestia obj. */
    private Personaje bestiaObj;

    /**
     * Sets the datos batalla.
     *
     * @param nombreHeroe the nombre heroe
     * @param vidaHeroe the vida heroe
     * @param armaduraHeroe the armadura heroe
     * @param imagenHeroe the imagen heroe
     * @param nombreBestia the nombre bestia
     * @param vidaBestia the vida bestia
     * @param armaduraBestia the armadura bestia
     * @param imagenBestia the imagen bestia
     */
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

        // Creo instancias reales de personajes
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

    /**
     * Actualizar vista.
     */
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

    /**
     * Actualizar vida.
     */
    private void actualizarVida() {
        lblVidaHeroe.setText("Vida: " + heroeObj.getVida());
        lblVidaBestia.setText("Vida: " + bestiaObj.getVida());
    }

    /**
     * On atacar.
     *
     * @param event the event
     */
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

    /**
     * On reiniciar.
     *
     * @param event the event
     */
    @FXML
    void onReiniciar(ActionEvent event) {
        setDatosBatalla(nombreHeroe, vidaHeroe, armaduraHeroe, imagenHeroe,
                        nombreBestia, vidaBestia, armaduraBestia, imagenBestia);
        txtBatallaInferior.setText("🔄 ¡Batalla reiniciada!");
    }

    /**
     * On volver.
     *
     * @param event the event
     */
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

    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        
    }
}
