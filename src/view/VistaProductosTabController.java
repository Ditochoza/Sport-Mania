/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXComboBox;
import controller.Inventario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.Producto;

/**
 * FXML Controller class
 *
 * @author vntnc
 */
public class VistaProductosTabController implements Initializable {

    private VistaTabsController tabsControler;

    private Producto filaSeleccionada;

    private Inventario inventario;

    // tabla
    @FXML
    private TableView tablaProductos;
    @FXML
    private TableColumn<Producto, String> codigoColumn;
    @FXML
    private TableColumn<Producto, Object> imagenProducto;
    @FXML
    private TableColumn<Producto, String> nombreColumn;
    @FXML
    private TableColumn<Producto, Number> precioColumn;
    @FXML
    private TableColumn<Producto, Number> stockColumn;
    @FXML
    private TableColumn<Producto, String> fechaAlta;

    // botones
    @FXML
    Button anadir;
    @FXML
    Button borrar;
    @FXML
    Button editar;
    @FXML
    Button detalles;
    
    // combo box
    @FXML
    JFXComboBox categoria;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // desativo boton detalles si no hay seleccion en la tabla
        detalles.setDisable(true);
        editar.setDisable(true);
        borrar.setDisable(true);

        // relleno filas         
        System.out.println("init: " + location);
        codigoColumn.setCellValueFactory(cellData -> cellData.getValue().codigoProperty());
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        stockColumn.setCellValueFactory(cellData -> cellData.getValue().stockProperty());
        precioColumn.setCellValueFactory(cellData -> cellData.getValue().preciosProperty());
        fechaAlta.setCellValueFactory(cellData -> cellData.getValue().fechaAltaProperty());
        imagenProducto.setCellValueFactory(cellData -> cellData.getValue().fotoProperty());
        
        /*for (int i = 0; i < ; i++) {
            comboBoxCodigosBarras.getItems().add(i + 1);
        }*/

        listeners();
        

    }

    public void listeners() {

        tablaProductos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue)
                -> {
            // activo boton al hacer seleccion en tabla
            detalles.setDisable(false);
            editar.setDisable(false);
            borrar.setDisable(false);

            this.filaSeleccionada = (Producto) newValue;

        });

        // doble click en la fila cambia de tab
        tablaProductos.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        // activo los tabs
                        tabsControler.activarTabs();
                        tabsControler.setFilaInformacion(filaSeleccionada);
                        tabsControler.editarProducto(false);
                        System.out.println("Double clicked");
                    }
                }
            }
        });

        detalles.setOnMouseClicked(e
                -> {
            // cambio de tab al hacer click en ir a detalles boton, llamando al controller de los tabs general (VistaTabsController)
            // envio el objeto con los datos a VistaTabsControler para despues reenviarlo a VistaInformacionTabController
            // activo los tabs
            tabsControler.activarTabs();
            tabsControler.setFilaInformacion(this.filaSeleccionada);
            tabsControler.editarProducto(false);

        });

        borrar.setOnMouseClicked((MouseEvent e)
                -> {
            String borrarString = " > Código: " + filaSeleccionada.getCodigo() + "\n > Nombre: " + filaSeleccionada.getNombre() + "\n > Stock: " + filaSeleccionada.getStock() + " uds."
                    + "\n > Precio: " + filaSeleccionada.getPrecio() + " €" + "\n > Fecha Alta: " + filaSeleccionada.getFechaAlta();
            System.out.println(tablaProductos.getItems().size());

            Alert alert;

            alert = new Alert(AlertType.WARNING, "Contenido de la fila a borrar:\n\n" + borrarString + "\n\nBorrar definitivamente?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setHeaderText("Confirmación de borrado");

            //css dialog pane
            DialogPane dialogAlert = alert.getDialogPane();
            dialogAlert.getStylesheets().add(getClass().getResource("../css/modena_dark.css").toExternalForm());
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                // si se selecciona un producto que está abierto en la tab información, se deshabilitan las tabs
                if (filaSeleccionada.getCodigo().equals(tabsControler.getCodigoInformacionProducto())) {
                    tabsControler.desactivarTabs();
                }
                tablaProductos.getItems().remove(tablaProductos.getSelectionModel().getSelectedIndex());
            }

        });
        
        
        
        editar.setOnMouseClicked(e ->{
            tabsControler.activarTabs();
            tabsControler.setFilaInformacion(filaSeleccionada);
            tabsControler.editarProducto(true);
        });

    }

    //Es llamado por la apliación principal para tener una referencia de vuelta de si mismo
    public void setInventarioTabProductos(Inventario inventario) {

        this.inventario = inventario;

        //Añado la lista obervable a la tabla
        tablaProductos.setItems(this.inventario.getProductos());

    }

    public void comunicacionControlador(VistaTabsController tabsController) {
        this.tabsControler = tabsController;
    }

    public void eliminarProductoTabla(Producto filaSeleccionadaProducto) {
        tablaProductos.getItems().remove(filaSeleccionadaProducto);
    }
    
    public void actualizarTabla(){
        tablaProductos.refresh();
    }
}
