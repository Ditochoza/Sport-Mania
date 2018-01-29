/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejecuta;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Cicu
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loaderMenu = new FXMLLoader(getClass().getResource("/vista/MenuVista.fxml"));
            FXMLLoader loaderListado = new FXMLLoader(getClass().getResource("/vista/ListadoProductos.fxml"));
            BorderPane menu = loaderMenu.load();
            AnchorPane listado = loaderListado.load();
            
            Scene sceneMenu = new Scene(menu);
            
            primaryStage.setTitle("GestorCarlosVictorValentinLuisX");
            primaryStage.setScene(sceneMenu);
            primaryStage.show();
            
            menu.setCenter(listado);
            
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
