package com.unaidario.controlador;

import java.util.ArrayList;
import java.util.HashMap;

import com.unaidario.Interfaz.Observer;
import com.unaidario.Modelo.Enemigo;
import com.unaidario.Modelo.GestorMapa;
import com.unaidario.Modelo.Juego;
import com.unaidario.Modelo.Mapa;

import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class JuegoControlador implements Observer {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane gridPane;

    private HashMap<Integer, Image> imagenesEnemigos;
    private ArrayList<Enemigo> enemigos;

    GestorMapa gestorMapa;
    Juego juego;

    @FXML
    public void initialize() {
         imagenesEnemigos = new HashMap<>();
        // imagenesEnemigos.put(2, new Image(getClass().getResourceAsStream("Images/esbirro.jpg")));
        // imagenesEnemigos.put(3, new Image(getClass().getResourceAsStream("Images/esqueleto.jpg")));
        // imagenesEnemigos.put(4, new Image(getClass().getResourceAsStream("Images/zombie.jpg")));
        enemigos = Juego.getInstance().getEnemigos();
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

        VBox vbox = new VBox();
        vbox.setPrefWidth(200);
        vbox.setSpacing(10);

        splitPane.getItems().addAll(gridPane, vbox);

        AnchorPane.setTopAnchor(splitPane, 0.0);
        AnchorPane.setBottomAnchor(splitPane, 0.0);
        AnchorPane.setLeftAnchor(splitPane, 0.0);
        AnchorPane.setRightAnchor(splitPane, 0.0);

        anchorPane.getChildren().add(splitPane);
    }
    
    public void generarMapa() {
        
        gridPane.getChildren().clear();
        Mapa mapaActual = Juego.getInstance().getGestorMapas().getMapaActual();
        int[][] matriz = mapaActual.getMapa();
        int filas = matriz.length;
        int columnas = matriz[0].length;
        double anchoCelda = gridPane.getPrefWidth() / columnas;
        double altoCelda = gridPane.getPrefHeight() / filas;

        Image suelo = new Image(getClass().getResourceAsStream(mapaActual.getSuelo()));
        Image pared = new Image(getClass().getResourceAsStream(mapaActual.getPared()));

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

    public void cambiarMapa(){
        boolean haySiguiente=gestorMapa.avanzarAlSiguienteMapa();
        if (haySiguiente){
            HashMap<String,Mapa> mapas= juego.getGestorMapas().getMapas();
            mapas.clear();
            generarMapa();
            juego.iniciarentidades();
            pintarPersonajes();
        }
    }

    private void pintarPersonajes() {
        gridPane.getChildren().removeIf(node -> node instanceof ImageView && node.getUserData() != null);

        for (Enemigo enemigo : enemigos) {
            ImageView enemigoView = new ImageView(imagenesEnemigos.get(enemigo.getTipo()));
            enemigoView.setFitWidth(gridPane.getPrefWidth() / 20); 
            enemigoView.setFitHeight(gridPane.getPrefHeight() / 20);
            enemigoView.setPreserveRatio(true);
            enemigoView.setUserData("enemigo"); 
            gridPane.add(enemigoView, enemigo.getPosicionX(), enemigo.getPosicionY());
        }
    }

    private void moverEnemigos() {
        for (Enemigo enemigo : enemigos) {
            int nuevaX = enemigo.getPosicionX() + (int) (Math.random() * 3) - 1; 
            int nuevaY = enemigo.getPosicionY() + (int) (Math.random() * 3) - 1;

            nuevaX = Math.max(0, Math.min(nuevaX, 19));
            nuevaY = Math.max(0, Math.min(nuevaY, 19));

            enemigo.setPosicionX(nuevaX);
            enemigo.setPosicionY(nuevaY);
        }
    }

    @Override
    public void onChange() {
        moverEnemigos();
        pintarPersonajes();
    }

}
