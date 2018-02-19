/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

/**
 * FXML Controller class
 *
 * @author CarlosLuisMiguelValentinVictor
 */
public class VistaPrincipalController implements Initializable {

    
    
    @FXML MenuBar menuBar;
    @FXML Menu primerMenu;
    @FXML Menu segundoMenu;
    @FXML Menu terceroMenu;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
      //Salir
    @FXML
    private void salir() {
        System.exit(0);
    }
    
     @FXML
    private void acercaDe() {
        //Muestro alerta
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Acerca de");
        alerta.setContentText("AUTORES:  "+"\n\n"+"Carlos Cifuentes "+"\n"+ "Luis Gonzaga Muñoz"+"\n"+"Miguel Escanciano" +"\n"+ "Valentín Circo" +"\n"+ "Victor Choza");
        alerta.showAndWait();
    }
    
}
