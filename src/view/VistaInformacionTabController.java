/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.Producto;

/**
 * FXML Controller class
 *
 * @author vntnc
 */
public class VistaInformacionTabController implements Initializable {

    private Producto filaSeleccionadaProducto;

    @FXML
    private JFXTextField nombreProducto;
    @FXML
    private JFXTextField precioProducto;
    @FXML
    private JFXTextField descripcionProducto;
    @FXML
    private JFXTextField categoriaProducto;
    @FXML
    private JFXTextField stockProducto;
    @FXML
    private JFXTextField fechaAltaProducto;
    @FXML
    private JFXTextField fechaModificacionProducto;
    @FXML
    private ImageView imagenProducto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*imagenProducto = new ImageView(getClass().getResource("../img/tabs/sun.png").toExternalForm());
        imagenProducto.setFitHeight(50);
        imagenProducto.setPreserveRatio(true);
        imagenProducto.setFitWidth(50);*/

    }

    // recibo la fila seleccionada de VistaTabController que a su vez lo ha recibido de VistaProductosTabController
    public void setFilaInformacion(Producto newValue) {
        this.filaSeleccionadaProducto = newValue;
        System.out.println(filaSeleccionadaProducto.getCodigo());
        
        imagenProducto.setImage(new Image((new File(filaSeleccionadaProducto.getRutaFoto())).toURI().toString()));
        nombreProducto.setText(filaSeleccionadaProducto.getNombre());
        precioProducto.setText(String.valueOf(filaSeleccionadaProducto.getPrecio()));
        descripcionProducto.setText(filaSeleccionadaProducto.getDescripcion());
        categoriaProducto.setText(filaSeleccionadaProducto.getCategoria());
        stockProducto.setText(String.valueOf(filaSeleccionadaProducto.getStock()));
        fechaAltaProducto.setText(String.valueOf(filaSeleccionadaProducto.getFechaAlta()));
        fechaModificacionProducto.setText(String.valueOf(filaSeleccionadaProducto.getFechaModificacion()));
        
        /*imagenProducto = new ImageView(getClass().getResource(filaSeleccionadaProducto.getRutaFoto()).toExternalForm());
        imagenProducto.setFitHeight(50);
        imagenProducto.setPreserveRatio(true);
        imagenProducto.setFitWidth(50);*/
    }

}
