/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Inventario;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author CarlosLuisMiguelValentinVictor
 */
public class VistaPrincipalController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
     //Referencia a la clase principal
    private Inventario inventario;

    //Es llamada por la clase Principal para tener una referencia de vuelta de si misma
    public void setInventarioReferencia(Inventario inventario) {
        this.inventario = inventario;
    }
    
    
     //Creo una nueva libreta de direcciones en XML vacía
    @FXML
    private void nuevo() {
        inventario.getProductos().clear();
        inventario.setRutaArchivoProductos(null);
    }

    //Abro un File Chooser para que el usario seleccione una libreta
    @FXML
    private void abrir() {
        FileChooser fileChooser = new FileChooser();

        //Filtro para la extensión
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        //Muestro el diálogo de guardar
        File archivo = fileChooser.showOpenDialog(inventario.getPrimaryStage());

        if (archivo != null) {
            inventario.cargaProductos(archivo);
        }
    }

    //Guardar
    @FXML
    private void guardar() {
        File archivo = inventario.getRutaArchivoProductos();
        if (archivo != null) {
            inventario.guardaProductos(archivo);
        } else {
            guardarComo();
        }
    }

    //Abro un File Chooser para guardar como
    @FXML
    private void guardarComo() {
        
        FileChooser fileChooser = new FileChooser();

        //Filtro para la extensión
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        //Muestro el diálogo de guardar
        File archivo = fileChooser.showSaveDialog(inventario.getPrimaryStage());

        if (archivo != null) {
            //Me aseguro de que tiene la extensión correcta
            if (!archivo.getPath().endsWith(".xml")) {
                archivo = new File(archivo.getPath() + ".xml");
            }
            inventario.guardaProductos(archivo);
        }
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
        alerta.setContentText("Carlos Cicuentes " + "\n" + "Luis Gonzaga Muñoz" + "\n" + "Miguel Escanciano" + "\n" + "Valentín Circo" + "\n" + "Víctor Choza");
        alerta.setHeaderText("Autores");

        //css dialog pane
        DialogPane dialogAlert = alerta.getDialogPane();
        dialogAlert.getStylesheets().add(getClass().getResource("../css/modena_dark.css").toExternalForm());
        alerta.showAndWait();
    }

}
