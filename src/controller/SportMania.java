/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.VistaPrincipalController;

/**
 *
 * @author dam
 */
public class SportMania extends Application {

    private Stage pantallaPrincipal;
    private BorderPane layoutPrincipal;

    @Override
    public void start(Stage pantallaPrincipal) throws Exception {
        this.pantallaPrincipal.setTitle("SportMania - Tu mundo de productos deportivos");
        initLayoutPrincipal();
    }

    public void initLayoutPrincipal() {
        FXMLLoader loader = new FXMLLoader();
        URL location = SportMania.class.getResource("/view/VistaPrincipal.fxml");
        loader.setLocation(location);
        try {
            layoutPrincipal = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(SportMania.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Cargo la escena que contiene ese layout principal
        Scene escena = new Scene(layoutPrincipal);
        pantallaPrincipal.setScene(escena);

        //Doy al controlador acceso a la aplicación principal
        VistaPrincipalController controller = loader.getController();
            controller.setSportMania(this);

        //Muestro la escena
        pantallaPrincipal.show();
    }

}
