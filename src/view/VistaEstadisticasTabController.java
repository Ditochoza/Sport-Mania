/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Inventario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author CarlosLuisMiguelValentinVictor
 */
public class VistaEstadisticasTabController implements Initializable {
    private Inventario inventario;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
   
    
    public void setInventarioTabProductos(Inventario inventario) {

	        this.inventario = inventario;
	}
}
