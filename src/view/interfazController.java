/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

/**
 *
 * @author dam
 */
public class interfazController {
    
    //Acerca de
    @FXML
    private void acercaDe() {
        //Muestro alerta
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Acerca de");
        //alerta.setContentText("Autor: Marco Jakob\nWebsite: http://code.makery.ch\nAdaptación 2018: Jairo García Rincón");
        alerta.showAndWait();
    }
    
}
