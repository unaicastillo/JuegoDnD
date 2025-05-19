package com.unaidarioefra;


import java.io.IOException;
import java.util.HashMap;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private static SceneManager instance;
    private Stage stage;
    private HashMap<SceneID, Scene> scenes;
    private SceneManager() {
        scenes = new HashMap<>();
    }
    public static SceneManager getInstance(){
        if (instance==null){
            instance= new SceneManager();
        }
        return instance;
    }
    @SuppressWarnings("exports")
    public void init(Stage stage){
        this.stage=stage;
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.setScene(SceneID.VistaEstadisticas, "VistaEstadisticas", 800, 600);
        sceneManager.setScene(SceneID.VistaJuego, "VistaJuego", 800, 600);
        sceneManager.loadScene(SceneID.VistaEstadisticas);
    }



    public void setScene(SceneID sceneID, String fxml, int width, int height){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/" + fxml + ".fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, width, height); 
            scenes.put(sceneID, scene);
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
    public void removeScene(SceneID sceneID){
        scenes.remove(sceneID); 
    }

    public void loadScene(SceneID sceneID) {
        if (scenes.containsKey(sceneID)) {
            stage.setScene(scenes.get(sceneID));
            stage.show(); // Asegúrate de que el escenario se muestre
        }
    }

}