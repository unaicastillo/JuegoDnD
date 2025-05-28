package com.unaidario.controlador;

import java.util.ArrayList;
import java.util.HashMap;

import com.unaidario.App;
import com.unaidario.Interfaz.Observer;
import com.unaidario.Modelo.Enemigo;
import com.unaidario.Modelo.Entidad;
import com.unaidario.Modelo.GestorMapa;
import com.unaidario.Modelo.Juego;
import com.unaidario.Modelo.Mapa;
import com.unaidario.Modelo.Prota;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class JuegoControlador implements Observer {

        Prota prota;
    @FXML
    private AnchorPane anchorPane;
    private GridPane estadisticasGridPane;
    private GridPane gridPane;

    private HashMap<Integer, Image> imagenesEnemigos;
    private ArrayList<Enemigo> enemigos;

    Juego juego2 = Juego.getInstance();

    @FXML
    public void initialize() {
        imagenesEnemigos = new HashMap<>();
        imagenesEnemigos.put(2, new Image(App.class.getResourceAsStream("Images/esbirro.jpg")));
        imagenesEnemigos.put(3, new Image(App.class.getResourceAsStream("Images/esqueleto.jpg")));
        imagenesEnemigos.put(4, new Image(App.class.getResourceAsStream("Images/zombie.jpg")));
        enemigos = juego2.getEnemigos();
        inicializarVista();
        generarMapa();
        pintarPersonajes();

        anchorPane.setOnKeyPressed(event -> manejarTecla(event));
        anchorPane.requestFocus(); // Asegura que el AnchorPane tenga el foco
        Platform.runLater(() -> anchorPane.requestFocus()); // <-- Esto asegura el foco tras cargar la vista

    }

    public void inicializarVista() {

        SplitPane splitPane = new SplitPane();
        splitPane.setDividerPositions(0.75);

        gridPane = new GridPane();
        gridPane.setPrefSize(600, 600);
        for (int i = 0; i < 20; i++) {
            gridPane.getRowConstraints().add(new javafx.scene.layout.RowConstraints());
            gridPane.getColumnConstraints().add(new javafx.scene.layout.ColumnConstraints());
        }

        // Panel derecho para estadísticas
        VBox vbox = new VBox();
        vbox.setPrefWidth(200);
        vbox.setSpacing(10);

        // Instancia del prota (puedes obtenerla de tu modelo si ya existe)
        prota = juego2.getProta();

        // Añadir estadísticas del prota
        javafx.scene.control.Label titulo = new javafx.scene.control.Label("Estadísticas del Protagonista");
        javafx.scene.control.Label vida = new javafx.scene.control.Label("Vida: " + prota.getVida());
        javafx.scene.control.Label ataque = new javafx.scene.control.Label("Ataque: " + prota.getAtaque());
        javafx.scene.control.Label defensa = new javafx.scene.control.Label("Defensa: " + prota.getDefensa());
        javafx.scene.control.Label evasion = new javafx.scene.control.Label("Evasión: " + prota.getEvasion());
        javafx.scene.control.Label velocidad = new javafx.scene.control.Label("Velocidad: " + prota.getVelocidad());

        vbox.getChildren().addAll(titulo, vida, ataque, defensa, evasion, velocidad
        
        
        );

        splitPane.getItems().addAll(gridPane, vbox);

        AnchorPane.setTopAnchor(splitPane, 0.0);
        AnchorPane.setBottomAnchor(splitPane, 0.0);
        AnchorPane.setLeftAnchor(splitPane, 0.0);
        AnchorPane.setRightAnchor(splitPane, 0.0);

        anchorPane.getChildren().add(splitPane);
    }

    public void generarMapa() {

        gridPane.getChildren();
        Mapa mapaActual = juego2.getGestorMapas().getMapaActual();
        int[][] matriz = mapaActual.getMapa();
        int filas = matriz.length;
        int columnas = matriz[0].length;
        double anchoCelda = gridPane.getPrefWidth() / columnas;
        double altoCelda = gridPane.getPrefHeight() / filas;

        Image suelo = new Image(App.class.getResourceAsStream("Images/suelo1.jpg"));
        Image pared = new Image(App.class.getResourceAsStream("Images/pared1.jpg"));

        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                int valor = matriz[fila][columna];
                ImageView imageView;
                if (valor == 0) {
                    imageView = new ImageView(suelo);
                } else {
                    imageView = new ImageView(pared);
                }
                imageView.setFitWidth(anchoCelda);
                imageView.setFitHeight(altoCelda);
                imageView.setPreserveRatio(false);
                gridPane.add(imageView, columna, fila);
            }
        }
    }

    // public void cambiarMapa(){
    // boolean haySiguiente=gestorMapa.avanzarAlSiguienteMapa();
    // if (haySiguiente){
    // HashMap<String,Mapa> mapas= juego.getGestorMapas().getMapas();
    // mapas.clear();
    // generarMapa();
    // juego.iniciarentidades();
    // pintarPersonajes();
    // }
    // }

    private void pintarPersonajes() {
        gridPane.getChildren().removeIf(node -> node instanceof ImageView && node.getUserData() != null);

        // Crear lista de entidades (enemigos + prota)
        ArrayList<Entidad> entidades = new ArrayList<>(enemigos);
        // Suponiendo que tienes una instancia de Prota, por ejemplo:

        entidades.add(prota);

        for (Entidad entidad : entidades) {
            ImageView entidadView = new ImageView();
            Image image = null;
            if (entidad instanceof Enemigo) {
                Enemigo enemigo = (Enemigo) entidad;
                image = imagenesEnemigos.get(enemigo.getTipo());
            } else {
                image = new Image(App.class.getResourceAsStream("Images/ProtaHH.jpg"));
            }
            entidadView = new ImageView(image);

            entidadView.setFitWidth(gridPane.getPrefWidth() / 20);
            entidadView.setFitHeight(gridPane.getPrefHeight() / 20);

            entidadView.setPreserveRatio(true);
            gridPane.add(entidadView,
                    (entidad instanceof Enemigo) ? ((Enemigo) entidad).getPosicionX()
                            : ((Prota) entidad).getPosicionX(),
                    (entidad instanceof Enemigo) ? ((Enemigo) entidad).getPosicionY()
                            : ((Prota) entidad).getPosicionY());
        }
    }

    // private void moverEnemigos() {
    // for (Enemigo enemigo : enemigos) {
    // int nuevaX = enemigo.getPosicionX() + (int) (Math.random() * 3) - 1;
    // int nuevaY = enemigo.getPosicionY() + (int) (Math.random() * 3) - 1;

    // nuevaX = Math.max(0, Math.min(nuevaX, 19));
    // nuevaY = Math.max(0, Math.min(nuevaY, 19));

    // enemigo.setPosicionX(nuevaX);
    // enemigo.setPosicionY(nuevaY);
    // }
    // }

    private void manejarTecla(javafx.scene.input.KeyEvent event) {
        int tecla = -1;
        switch (event.getCode()) {
            case W: tecla = 0; break;
            case A: tecla = 1; break;
            case S: tecla = 2; break;
            case D: tecla = 3; break;
            default: return;
        }
        if (tecla != -1) {
            prota.movimientoProta(juego2.getGestorMapas().getMapaActual().getMapa(), tecla, enemigos);
            pintarPersonajes();
        }
    }

    @Override
    public void onChange() {
        // moverEnemigos();
        pintarPersonajes();
    }

}
