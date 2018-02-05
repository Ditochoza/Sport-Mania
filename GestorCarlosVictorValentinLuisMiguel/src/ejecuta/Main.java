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

/*
1. Futbol
    1.1 pelota_futbol
    1.2 zapatilla_futbol
    1.3 equipacion_futbol
    1.4 cono_futbol
    1.5 guantes_futbol
2.Beisbol
    2.1 bate_beisbol
    2.2 guantes_beisbol
    2.3 pelota_beisbol
    2.4 pechera_beisbol
    2.5 casco_beisbol
3.Golf
    3.1 pelota_golf
    3.2 palo_golf
    3.3 banderin_golf
    3.4 guantes_golf
    3.5 caddy_golf
*/