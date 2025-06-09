package com.unaidario;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    /** 
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.init(primaryStage);

        sceneManager.setScene(SceneID.VistaEstadisticas, "VistaEstadisticas", 800, 600);
        sceneManager.setScene(SceneID.VistaJuego, "VistaJuego", 800, 600);

        sceneManager.loadScene(SceneID.VistaEstadisticas);
        
    }

    /** 
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}