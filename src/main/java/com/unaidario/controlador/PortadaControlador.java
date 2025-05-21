package com.unaidario.controlador;

import com.unaidario.SceneID;
import com.unaidario.SceneManager;
import com.unaidario.Modelo.Prota;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PortadaControlador {

    @FXML
    private TextField vidaField;

    @FXML
    private TextField ataqueField;

    @FXML
    private TextField defensaField;

    @FXML
    private TextField evasionField;

    @FXML
    private TextField velocidadField;

    @FXML
    private VBox cajavertical;
    @FXML
    private HBox cajaHorizontal1;
    @FXML
    private HBox cajaHorizontal2;
    @FXML
    private HBox cajaHorizontal3;
    @FXML
    private HBox cajaHorizontal4;
    @FXML
    private HBox cajaHorizontal5;
    @FXML
    private Label labelVida;
    @FXML
    private Label labelAtaque;
    @FXML
    private Label labelDefensa;
    @FXML
    private Label labelEvasion;
    @FXML
    private Label labelVelocidad;

    @FXML
    private Button confirmButton;
    @FXML
    private AnchorPane anchorPane2;

    private Prota prota;

    @FXML
    public void initialize() {
        // Inicializar el protagonista
        prota = new Prota(0, 0, 0, 0, 0, 1, 1);

        // Añadir listeners para habilitar el botón solo cuando todos los campos estén
        // rellenados
        vidaField.textProperty().addListener((observable, oldValue, newValue) -> validarCampos());
        ataqueField.textProperty().addListener((observable, oldValue, newValue) -> validarCampos());
        defensaField.textProperty().addListener((observable, oldValue, newValue) -> validarCampos());
        evasionField.textProperty().addListener((observable, oldValue, newValue) -> validarCampos());
        velocidadField.textProperty().addListener((observable, oldValue, newValue) -> validarCampos());
    }

    private void validarCampos() {
        boolean camposValidos = !vidaField.getText().isEmpty() &&
                !ataqueField.getText().isEmpty() &&
                !defensaField.getText().isEmpty() &&
                !evasionField.getText().isEmpty() &&
                !velocidadField.getText().isEmpty();
        confirmButton.setDisable(!camposValidos);
    }

    @FXML
    private void cambiarAVistaJuego() {
        try {
            // Asignar valores a los atributos del protagonista
            prota.setVida(Integer.parseInt(vidaField.getText()));
            prota.setAtaque(Integer.parseInt(ataqueField.getText()));
            prota.setDefensa(Integer.parseInt(defensaField.getText()));
            prota.setEvasion(Integer.parseInt(evasionField.getText()));
            prota.setVelocidad(Integer.parseInt(velocidadField.getText()));

            // Cambiar a la vista del juego
            SceneManager.getInstance().loadScene(SceneID.VistaJuego);
        } catch (NumberFormatException e) {
            System.err.println("Error: Asegúrate de que todos los campos contienen números válidos.");
        }
    }
}
