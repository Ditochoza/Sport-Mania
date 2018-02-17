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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
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
    private VistaTabsController tabsController;

    // datos
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
    
    // botones
    @FXML
    Button anadir;
    @FXML
    Button borrar;
    @FXML
    Button editar;
    @FXML
    Button detalles;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listeners();
    }

    public void comunicacionControlador(VistaTabsController tabsController) {
        this.tabsController = tabsController;
    }

    // recibo la fila seleccionada de VistaTabController que a su vez lo ha recibido de VistaProductosTabController
    public void setFilaInformacion(Producto newValue) {
        this.filaSeleccionadaProducto = newValue;
        System.out.println(filaSeleccionadaProducto.getCodigo());
        
        imagenProducto.setImage(new Image(getClass().getResource(filaSeleccionadaProducto.getRutaFoto()).toExternalForm()));
        nombreProducto.setText(filaSeleccionadaProducto.getNombre());
        precioProducto.setText(String.valueOf(filaSeleccionadaProducto.getPrecio()));
        descripcionProducto.setText(filaSeleccionadaProducto.getDescripcion());
        categoriaProducto.setText(filaSeleccionadaProducto.getCategoria());
        stockProducto.setText(String.valueOf(filaSeleccionadaProducto.getStock()));
        fechaAltaProducto.setText(String.valueOf(filaSeleccionadaProducto.getFechaAlta()));
        fechaModificacionProducto.setText(String.valueOf(filaSeleccionadaProducto.getFechaModificacion()));
    }

    private void listeners() {
        borrar.setOnMouseClicked(e
                -> {
            String borrarString = " > Código: " + filaSeleccionadaProducto.getCodigo() + "\n > Nombre: " + filaSeleccionadaProducto.getNombre() + "\n > Stock: " + filaSeleccionadaProducto.getStock() + " uds."
                    + "\n > Precio: " + filaSeleccionadaProducto.getPrecio() + " €" + "\n > Fecha Alta: " + filaSeleccionadaProducto.getFechaAlta();

            Alert alert;

            alert = new Alert(Alert.AlertType.WARNING, "Contenido de la fila a borrar:\n\n" + borrarString + "\n\nBorrar definitivamente?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setHeaderText("Confirmación de borrado");

            //css dialog pane
            DialogPane dialogAlert = alert.getDialogPane();
            dialogAlert.getStylesheets().add(getClass().getResource("../css/modena_dark.css").toExternalForm());
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                tabsController.eliminarProductoTabla(filaSeleccionadaProducto);
            }

        });
    }
    
    public String getCodigo(){
        return filaSeleccionadaProducto.getCodigo();
    }

}
