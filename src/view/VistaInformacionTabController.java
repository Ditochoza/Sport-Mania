/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import model.Producto;

/**
 * FXML Controller class
 *
 * @author vntnc
 */
public class VistaInformacionTabController implements Initializable {

   
   private Producto filaSeleccionadaProducto;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }   
        
    
    // recibo la fila seleccionada de VistaTabController que a su vez lo ha recibido de VistaProductosTabController
    public void setFilaInformacion(Producto newValue){
        this.filaSeleccionadaProducto = newValue;
        System.out.println(filaSeleccionadaProducto.getCodigo());
    }
    
    
}
