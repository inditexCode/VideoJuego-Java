package com.lotr.ui;

import java.io.IOException;
import java.net.URL;

import com.lotr.logica.Ejercito;
import com.lotr.modelo.*;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class VentanaPrincipalController {

    @FXML
    private VBox VBox;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnAgregarBestia;

    @FXML
    private Button btnAgregarHeroe;

    @FXML
    private Button btnEmpezarLucha;

    @FXML
    private ComboBox<String> comboTipoBestia;

    @FXML
    private ComboBox<String> comboTipoHeroe;
    
    @FXML
    private Button btnEjercito;

    @FXML
    private ImageView imageViewBestia;

    @FXML
    private ImageView imageViewHeroe;

    @FXML
    private TextField txtArmaduraBestia;

    @FXML
    private TextField txtArmaduraHeroe;

    @FXML
    private TextField txtNombreBestia;

    @FXML
    private TextField txtNombreHeroe;

    @FXML
    private TextField txtVidaBestia;

    @FXML
    private TextField txtVidaHeroe;
    
    @FXML
   private Label lblErrorHeroe;

    @FXML
    private Label lblErrorBestia;

    // Labels de errores individuales para validar campo por campo
    @FXML
    private Label lblErrorVidaHeroe;

    @FXML
    private Label lblErrorArmaduraHeroe;

    @FXML
    private Label lblErrorVidaBestia;

    @FXML
    private Label lblErrorArmaduraBestia;

    @FXML
    private Button btnrefrescar;

    // Ejércitos
    private final Ejercito ejercitoHeroes = new Ejercito("Héroes");
    private final Ejercito ejercitoBestias = new Ejercito("Bestias");

    @FXML
    public void initialize() {
        comboTipoHeroe.getItems().addAll("Elfo", "Humano", "Hobbit");
        comboTipoBestia.getItems().addAll("Orco", "Trasgo");
        
        imageViewHeroe.setPreserveRatio(false);
        imageViewHeroe.setFitWidth(200);
        imageViewHeroe.setFitHeight(200);

        imageViewBestia.setPreserveRatio(false);
        imageViewBestia.setFitWidth(200);
        imageViewBestia.setFitHeight(200);
        btnEmpezarLucha.setDisable(true); // Desactivo el boton para que el usuario no pueda pasar de interfaz sin completar los datos
    }

    private boolean esEntero(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(texto.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void verificarEstadoBotonLucha() {
        boolean habilitar = !ejercitoHeroes.estaVacio() && !ejercitoBestias.estaVacio();
        btnEmpezarLucha.setDisable(!habilitar);
    }

    @FXML
    void onAgregarHeroe(ActionEvent event) {
        String tipo = comboTipoHeroe.getValue();
        String nombre = txtNombreHeroe.getText();
        String vidaTexto = txtVidaHeroe.getText();
        String armaduraTexto = txtArmaduraHeroe.getText();

        boolean vidaValida = esEntero(vidaTexto);
        boolean armaduraValida = esEntero(armaduraTexto);

        lblErrorVidaHeroe.setVisible(!vidaValida);
        lblErrorVidaHeroe.setManaged(!vidaValida);
        lblErrorArmaduraHeroe.setVisible(!armaduraValida);
        lblErrorArmaduraHeroe.setManaged(!armaduraValida);


        if (!vidaValida || !armaduraValida) {
            lblErrorHeroe.setText("❌ Corrige los errores de abajo.");
            lblErrorHeroe.setVisible(true);
            lblErrorHeroe.setManaged(true);

            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(e -> {
                lblErrorHeroe.setVisible(false);
                lblErrorVidaHeroe.setVisible(false);
                lblErrorVidaHeroe.setManaged(false);
                lblErrorArmaduraHeroe.setVisible(false);
                lblErrorArmaduraHeroe.setManaged(false);
                limpiarCamposHeroe();
            });
            pause.play();
            return;
        } else {
            lblErrorHeroe.setVisible(false);
        }

        try {
            int vida = Integer.parseInt(vidaTexto);
            int armadura = Integer.parseInt(armaduraTexto);

            Heroe heroe;
            switch (tipo) {
                case "Elfo": heroe = new Elfo(nombre, vida, armadura); break;
                case "Humano": heroe = new Humano(nombre, vida, armadura); break;
                case "Hobbit": heroe = new Hobbit(nombre, vida, armadura); break;
                default: throw new IllegalArgumentException("Tipo de héroe no válido");
            }

            ejercitoHeroes.agregar(heroe);
            cargarImagenHeroe(tipo);
            limpiarCamposHeroe();
            verificarEstadoBotonLucha();
        } catch (Exception e) {
            lblErrorHeroe.setText("⚠️ Error al agregar héroe: " + e.getMessage());
            lblErrorHeroe.setVisible(true);

            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(e2 -> {
                lblErrorHeroe.setVisible(false);
                limpiarCamposHeroe();
            });
            pause.play();
        }
    }

    @FXML
    void onAgregarBestia(ActionEvent event) {
        String tipo = comboTipoBestia.getValue();
        String nombre = txtNombreBestia.getText();
        String vidaTexto = txtVidaBestia.getText();
        String armaduraTexto = txtArmaduraBestia.getText();

        boolean vidaValida = esEntero(vidaTexto);
        boolean armaduraValida = esEntero(armaduraTexto);

        // Aquí el cambio: setManaged también
        lblErrorVidaBestia.setVisible(!vidaValida);
        lblErrorVidaBestia.setManaged(!vidaValida);

        lblErrorArmaduraBestia.setVisible(!armaduraValida);
        lblErrorArmaduraBestia.setManaged(!armaduraValida);

        if (!vidaValida || !armaduraValida) {
            lblErrorBestia.setText("❌ Corrige los errores de abajo.");
            lblErrorBestia.setVisible(true);
            lblErrorBestia.setManaged(true);

            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(e -> {
                lblErrorBestia.setVisible(false);
                lblErrorBestia.setManaged(false);
                lblErrorVidaBestia.setVisible(false);
                lblErrorVidaBestia.setManaged(false);
                lblErrorArmaduraBestia.setVisible(false);
                lblErrorArmaduraBestia.setManaged(false);
                limpiarCamposBestia();
            });
            pause.play();
            return;
        } else {
            lblErrorBestia.setVisible(false);
            lblErrorBestia.setManaged(false);
        }

        try {
            int vida = Integer.parseInt(vidaTexto);
            int armadura = Integer.parseInt(armaduraTexto);

            Bestia bestia;
            switch (tipo) {
                case "Orco": bestia = new Orco(nombre, vida, armadura); break;
                case "Trasgo": bestia = new Trasgo(nombre, vida, armadura); break;
                default: throw new IllegalArgumentException("Tipo de bestia no válido");
            }

            ejercitoBestias.agregar(bestia);
            cargarImagenBestia(tipo);
            limpiarCamposBestia();
            verificarEstadoBotonLucha();
        } catch (Exception e) {
            lblErrorBestia.setText("⚠️ Error al agregar bestia: " + e.getMessage());
            lblErrorBestia.setVisible(true);
            lblErrorBestia.setManaged(true);

            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(e2 -> {
                lblErrorBestia.setVisible(false);
                lblErrorBestia.setManaged(false);
                limpiarCamposBestia();
            });
            pause.play();
        }
    }

    @FXML
    void onEmpezarLucha(ActionEvent event) {
        try {
            Heroe heroe = (Heroe) ejercitoHeroes.getPersonajes().get(ejercitoHeroes.getPersonajes().size() - 1);
            Bestia bestia = (Bestia) ejercitoBestias.getPersonajes().get(ejercitoBestias.getPersonajes().size() - 1);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/batalla.fxml"));
            Parent root = loader.load();
          
            BatallaController controller = loader.getController();
            controller.setDatosBatalla(
                heroe.getNombre(),
                heroe.getVida(),
                heroe.getArmadura(),
                imageViewHeroe.getImage(),
                bestia.getNombre(),
                bestia.getVida(),
                bestia.getArmadura(),
                imageViewBestia.getImage()
            );

            Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
            URL cssUrl = getClass().getResource("/style/estilosBatalla.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            } else {
                System.err.println("⚠️ No se pudo encontrar /style/estilosBatalla.css");
            }
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.out.println("❌ Error al empezar la lucha: " + e.getMessage());
            e.printStackTrace();
        }
    }




    private void cargarImagenHeroe(String tipo) {
        String ruta = "/images/" + tipo.toLowerCase() + ".jpg";
        Image imagen = new Image(getClass().getResourceAsStream(ruta));
        imageViewHeroe.setImage(imagen);
    }

    private void cargarImagenBestia(String tipo) {
        String ruta = "/images/" + tipo.toLowerCase() + ".jpg";
        Image imagen = new Image(getClass().getResourceAsStream(ruta));
        imageViewBestia.setImage(imagen);
    }

    private void limpiarCamposHeroe() {
        txtNombreHeroe.clear();
        txtVidaHeroe.clear();
        txtArmaduraHeroe.clear();
        comboTipoHeroe.getSelectionModel().clearSelection();

        lblErrorVidaHeroe.setVisible(false);
        lblErrorVidaHeroe.setManaged(false);
        lblErrorArmaduraHeroe.setVisible(false);
        lblErrorArmaduraHeroe.setManaged(false);

    }

    private void limpiarCamposBestia() {
        txtNombreBestia.clear();
        txtVidaBestia.clear();
        txtArmaduraBestia.clear();
        comboTipoBestia.getSelectionModel().clearSelection();
        
        lblErrorVidaBestia.setVisible(false);
        lblErrorVidaBestia.setManaged(false);
        lblErrorArmaduraBestia.setVisible(false);
        lblErrorArmaduraBestia.setManaged(false);

    }
    @FXML
    void onrefrescar(ActionEvent event) {
        try {
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ventana.fxml"));
            AnchorPane root = loader.load();
            stage.getScene().setRoot(root);
        } catch (Exception ex) {
            System.err.println("❌ Error al refrescar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @FXML
    void OncambioInterfaz(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/BatallaEjercitos.fxml"));
            Parent root = loader.load();
            
            // Obtener la ventana actual desde el botón u otro componente
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Batalla entre Héroes y Bestias");
            stage.show();

            System.out.println("Interfaz cambiada a BatallaEjercito.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}