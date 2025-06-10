package com.unaidario;


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
    /** 
     * @return SceneManager
     */
    public static SceneManager getInstance(){
        if (instance==null){
            instance= new SceneManager();
        }
        return instance;
    }
    /** 
     * @param stage
     */
    @SuppressWarnings("exports")
    public void init(Stage stage){
        this.stage=stage;
        SceneManager sceneManager = SceneManager.getInstance();
        sceneManager.setScene(SceneID.VistaEstadisticas, "VistaEstadisticas", 800, 600);
        sceneManager.setScene(SceneID.VistaJuego, "VistaJuego", 800, 600);
        sceneManager.loadScene(SceneID.VistaEstadisticas);
    }



    /** 
     * @param sceneID
     * @param fxml
     * @param width
     * @param height
     */
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
    /** 
     * @param sceneID
     */
    public void removeScene(SceneID sceneID){
        scenes.remove(sceneID); 
    }

    /** 
     * @param sceneID
     */
    public void loadScene(SceneID sceneID) {
        if (scenes.containsKey(sceneID)) {
            stage.setScene(scenes.get(sceneID));
            stage.show(); // Asegúrate de que el escenario se muestre
        }
    }

}