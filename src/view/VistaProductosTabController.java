/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXComboBox;
import controller.Inventario;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
 * @author CarlosLuisMiguelValentinVictor
 */
public class VistaProductosTabController implements Initializable {

    private VistaTabsController tabsControler;

    private Producto filaSeleccionada;

    private Inventario inventario;

    private FilteredList<Producto> filteredData;

    // tabla
    @FXML
    private TableView<Producto> tablaProductos;
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
                        tabsControler.editAddProducto(false, "");
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
            tabsControler.editAddProducto(false, "");

        });
        
        anadir.setOnMouseClicked(e -> {
            tabsControler.editAddProducto(true, "Add");
            tabsControler.activarTabs();
            tabsControler.setFilaInformacion(filaSeleccionada);
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

                Producto selectedItem = tablaProductos.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    inventario.getProductos().remove(selectedItem);
                }
            }

        });

        editar.setOnMouseClicked(e -> {
            tabsControler.editAddProducto(true, "Edit");
            tabsControler.activarTabs();
            tabsControler.setFilaInformacion(filaSeleccionada);
        });

    }

    //Es llamado por la apliación principal para tener una referencia de vuelta de si mismo
    public void setInventarioTabProductos(Inventario inventario) {

        this.inventario = inventario;
        //Añado la lista obervable a la tabla
        tablaProductos.setItems(this.inventario.getProductos());

        crearFilteredData();
        rellenarComboBox();

    }

    public void comunicacionControlador(VistaTabsController tabsController) {
        this.tabsControler = tabsController;
    }

    public void eliminarProductoTabla(Producto filaSeleccionadaProducto) {
        inventario.getProductos().remove(filaSeleccionadaProducto);
    }

    public void actualizarTabla() {
        tablaProductos.refresh();
    }

    private void rellenarComboBox() {
        ArrayList<Producto> productos = new ArrayList<>(inventario.getProductos());
        ArrayList<String> categorias = new ArrayList<>();

        categorias.add("Todas");
        for (int i = 0; i < productos.size(); i++) {
            boolean repetido = false;
            for (int j = 0; j < categorias.size(); j++) {
                if (categorias.get(j).equals(productos.get(i).getCategoria())) {
                    repetido = true;
                }
            }
            if (!repetido) {
                categorias.add(productos.get(i).getCategoria());
            }
        }

        for (int i = 0; i < categorias.size(); i++) {
            categoria.getItems().add(categorias.get(i));
        }
    }

    private void crearFilteredData() {

        filteredData = new FilteredList<>(inventario.getProductos());

        categoria.setOnAction((t) -> {

            //Se hace scroll hasta arriba para evitar errores
            tablaProductos.scrollTo(0);

            String categoriaElegida = categoria.getValue().toString();
            filteredData.setPredicate(product -> {
                if (categoriaElegida == null || categoriaElegida.isEmpty() || categoriaElegida.toLowerCase().equals("todas")) {
                    return true;
                }

                if (product.getCategoria().toLowerCase().contains(categoriaElegida.toLowerCase())) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Producto> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tablaProductos.comparatorProperty());
        tablaProductos.setItems(sortedData);

    }
}
