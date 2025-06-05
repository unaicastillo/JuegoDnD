package com.unaidario.controlador;

import java.util.ArrayList;
import java.util.HashMap;

import com.unaidario.App;
import com.unaidario.Interfaz.Observer;
import com.unaidario.Modelo.Enemigo;
import com.unaidario.Modelo.Entidad;
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
    javafx.scene.control.Label titulo;
    javafx.scene.control.Label ataque;
    javafx.scene.control.Label defensa;
    javafx.scene.control.Label evasion;
    javafx.scene.control.Label vida;
    javafx.scene.control.Label velocidad;
    @FXML
    private AnchorPane anchorPane;
    private GridPane gridPane;
    
    private HashMap<Integer, Image> imagenesEnemigos;
    private ArrayList<Enemigo> enemigos;
    VBox vbox = new VBox();

    Juego juego = Juego.getInstance();

    @FXML
    public void initialize() {

        prota = juego.getProta();
        imagenesEnemigos = new HashMap<>();
        imagenesEnemigos.put(2, new Image(App.class.getResourceAsStream("Images/esbirro.png")));
        imagenesEnemigos.put(3, new Image(App.class.getResourceAsStream("Images/esqueleto.png")));
        imagenesEnemigos.put(4, new Image(App.class.getResourceAsStream("Images/zombie.png")));

        enemigos = juego.getEnemigos();
        inicializarVista();
        generarMapa();
        llamadaTecla();
        juego.subscribe(this);
        pintarPersonajes();

    }

    public void llamadaTecla() {
        anchorPane.setOnKeyPressed(event -> {
            int tecla = -1;
            switch (event.getCode()) {
                case W:
                case UP:
                    tecla = 0; // Arriba
                    break;
                case A:
                case LEFT:
                    tecla = 1; // Izquierda
                    break;
                case S:
                case DOWN:
                    tecla = 2; // Abajo
                    break;
                case D:
                case RIGHT:
                    tecla = 3; // Derecha
                    break;
                default:
                    return;
            }
            if (tecla != -1) {
                juego.Turnos(tecla);
                generarMapa();
                pintarPersonajes();
                if (juego.finalizarPartida()) {
                    mostrarFinDelJuego();
                }
            }
        });

        anchorPane.setFocusTraversable(true);
        Platform.runLater(() -> anchorPane.requestFocus());
    }

    public void inicializarVista() {
        prota = juego.getProta();

        SplitPane splitPane = new SplitPane();
        splitPane.setDividerPositions(0.75);

        gridPane = new GridPane();
        gridPane.setPrefSize(600, 600);
        for (int i = 0; i < 20; i++) {
            gridPane.getRowConstraints().add(new javafx.scene.layout.RowConstraints());
            gridPane.getColumnConstraints().add(new javafx.scene.layout.ColumnConstraints());
        }
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
        Mapa mapaActual = juego.getGestorMapas().getMapaActual();
        int[][] matriz = mapaActual.getMapa();
        int filas = matriz.length;
        int columnas = matriz[0].length;
        double anchoCelda = gridPane.getPrefWidth() / columnas;
        double altoCelda = gridPane.getPrefHeight() / filas;

        Image suelo = new Image(App.class.getResourceAsStream("Images/suelo1.png"));
        Image pared = new Image(App.class.getResourceAsStream("Images/pared1.png"));

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
        prota = juego.getProta();
        gridPane.getChildren().removeIf(node -> node instanceof ImageView && node.getUserData() != null);



        for (Entidad entidad : juego.getEntidades()) {
            ImageView entidadView = new ImageView();
            Image image = null;
            if (entidad instanceof Enemigo) {
                Enemigo enemigo = (Enemigo) entidad;
                image = imagenesEnemigos.get(enemigo.getTipo());
            } else {
                image = new Image(App.class.getResourceAsStream("Images/ProtaHH.png"));
            }
            entidadView = new ImageView(image);

            entidadView.setFitWidth(gridPane.getPrefWidth() / 20);
            entidadView.setFitHeight(gridPane.getPrefHeight() / 20);

            entidadView.setPreserveRatio(true);
            gridPane.add(entidadView,
                    (entidad instanceof Enemigo) ? ((Enemigo) entidad).getPosicionX()
                            : prota.getPosicionX(),
                    (entidad instanceof Enemigo) ? ((Enemigo) entidad).getPosicionY()
                            : prota.getPosicionY());
        }
    }


    @Override
    public void onChange() {
        actualizarEstadisticas();
        pintarPersonajes();
    }

    public void actualizarEstadisticas() {
        // Actualizar las estadísticas del prota en la interfaz
        // Panel derecho para estadísticas
        vbox.getChildren().clear(); // Limpiar el VBox antes de agregar nuevos elementos
        // Instancia del prota (puedes obtenerla de tu modelo si ya existe)
        titulo = new javafx.scene.control.Label("Estadísticas del Protagonista");
        vida = new javafx.scene.control.Label("Vida: " + prota.getVida());
        ataque = new javafx.scene.control.Label("Ataque: " + prota.getAtaque());
        defensa = new javafx.scene.control.Label("Defensa: " + prota.getDefensa());
        evasion = new javafx.scene.control.Label("Evasión: " + prota.getEvasion());
        velocidad = new javafx.scene.control.Label("Velocidad: " + prota.getVelocidad());


        vbox.getChildren().addAll(titulo, vida, ataque, defensa, evasion, velocidad);
    }

    /*
     * Si el prota ha muerto o ha acabado con todos los enemigos. Sale esta pantalla
     * de fin de juego
     * la cual es igual en ambos casos
     */
    public void mostrarFinDelJuego() {
        anchorPane.getChildren().clear();

        ImageView finView = new ImageView(new Image(App.class.getResourceAsStream("Images/gameOver.png")));
        finView.setFitWidth(anchorPane.getWidth() / 2);
        finView.setFitHeight(anchorPane.getHeight() / 2);
        finView.setPreserveRatio(true);

        javafx.scene.control.Button btnSalir = new javafx.scene.control.Button("Salir");
        btnSalir.setOnAction(e -> {
            Platform.exit();
        });

        VBox vbox = new VBox(10, finView, btnSalir);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);

        anchorPane.getChildren().add(vbox);
        AnchorPane.setTopAnchor(vbox, 0.0);
        AnchorPane.setBottomAnchor(vbox, 0.0);
        AnchorPane.setLeftAnchor(vbox, 0.0);
        AnchorPane.setRightAnchor(vbox, 0.0);
    }

}
