/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.VistaProductoController;

/**
 *
 * @author dam
 */
public class PracticaFinalFX extends Application {
    
    private Stage escenarioPrincipal;
    private AnchorPane layoutPrincipal, interfaz;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage escenarioPrincipal) {
        
        this.escenarioPrincipal = escenarioPrincipal;        
        //Establezco el título
        this.escenarioPrincipal.setTitle("Sport-Mania");
        //Inicializa el layout principal
        initLayoutPrincipal();
        //Muestra la vista persona
        muestraVistaProducto();       
    }
    
    public void initLayoutPrincipal(){
        
        //Cargo el layout principal a partir de la vista VistaPrincipal.fxml
        FXMLLoader loader = new FXMLLoader();
        URL location = PracticaFinalFX.class.getResource("/view/Interfaz.fxml");
        loader.setLocation(location);
        try {
            layoutPrincipal = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(PracticaFinalFX.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Cargo la escena que contiene ese layout principal
        Scene escena = new Scene(layoutPrincipal);
        escenarioPrincipal.setScene(escena);
        
        //Doy al controlador acceso a la aplicación principal
        PracticaFinalFX controller = loader.getController();
        controller.setPracticaFinalFX(this);
        
        //Muestro la escena
        escenarioPrincipal.show();
        
        /*
        //Intento cargar el último archivo abierto
        File archivo = getRutaArchivoProductos();
        if (archivo != null){
            cargaProductos(archivo);
        }*/               
    }
    
    public void muestraVistaProducto(){
        
        //Cargo la vista persona a partir de la vista VistaPersona.fxml || comentario de referencia
        FXMLLoader loader = new FXMLLoader();
        URL location = PracticaFinalFX.class.getResource("/view/Interfaz.fxml");
        loader.setLocation(location);
        try {
            interfaz = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(PracticaFinalFX.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Añado al centro del layoutPrincipal 
        layoutPrincipal.setCenter(interfaz);
        
        //Doy acceso al controlador VistaPersonaCOntroller a LibretaDirecciones
        VistaProductoController controller = loader.getController();
        controller.setVistaProductoController(this);
    }

    private void setPracticaFinalFX(PracticaFinalFX aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
