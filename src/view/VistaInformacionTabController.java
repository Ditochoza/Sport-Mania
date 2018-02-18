/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseEvent;
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

    private String nombreOld;
    private String precioOld;
    private String descripcionOld;
    private String categoriaOld;
    private String stockOld;

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
    Button cancelar;
    @FXML
    Button guardar;

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

        editar.setOnMouseClicked(e -> {
            nombreOld = nombreProducto.getText();
            precioOld = precioProducto.getText();
            descripcionOld = descripcionProducto.getText();
            categoriaOld = categoriaProducto.getText();
            stockOld = stockProducto.getText();
            
            modoEditar(true);
        });

        cancelar.setOnMouseClicked(e -> {
            nombreProducto.setText(nombreOld);
            precioProducto.setText(precioOld);
            descripcionProducto.setText(descripcionOld);
            categoriaProducto.setText(categoriaOld);
            stockProducto.setText(stockOld);
            
            modoEditar(false);
        });

        guardar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("Guardar");
                String erroresString = "";

                // errores nombre producto
                if (nombreProducto.getText().isEmpty()) {
                    erroresString += " - El nombre no puede quedar vacío\n";
                    nombreProducto.setUnFocusColor(Color.RED);
                } else {
                    nombreProducto.setUnFocusColor(Color.rgb(42, 46, 55));
                }

                // errores precio producto
                if (!precioProducto.getText().isEmpty()) {
                    try {
                        Double valor = Double.valueOf(precioProducto.getText());
                        precioProducto.setUnFocusColor(Color.rgb(42, 46, 55));
                    } catch (NumberFormatException ex) {
                        erroresString += " - El precio debe ser un número\n";
                        precioProducto.setUnFocusColor(Color.RED);
                    }
                } else {
                    erroresString += " - El precio no puede quedar vacío\n";
                    precioProducto.setUnFocusColor(Color.RED);
                }

                //errores descripcion producto
                if (descripcionProducto.getText().isEmpty()) {
                    erroresString += " - La descripción no puede quedar vacía\n";
                    descripcionProducto.setUnFocusColor(Color.RED);
                } else {
                    descripcionProducto.setUnFocusColor(Color.rgb(42, 46, 55));
                }

                //errores categoria producto
                if (categoriaProducto.getText().isEmpty()) {
                    erroresString += " - La categoría no puede quedar vacía\n";
                    categoriaProducto.setUnFocusColor(Color.RED);
                } else {
                    categoriaProducto.setUnFocusColor(Color.rgb(42, 46, 55));
                }

                //errores stock producto
                if (!stockProducto.getText().isEmpty()) {
                    try {
                        int valor = Integer.valueOf(stockProducto.getText());
                        stockProducto.setUnFocusColor(Color.rgb(42, 46, 55));
                    } catch (NumberFormatException ex) {
                        erroresString += " - El stock debe ser un número\n";
                        stockProducto.setUnFocusColor(Color.RED);
                    }
                } else {
                    erroresString += " - El stock no puede quedar vacío\n";
                    stockProducto.setUnFocusColor(Color.RED);
                }

                if (!erroresString.isEmpty()) {
                    Alert alert;
                    alert = new Alert(Alert.AlertType.WARNING, "Se han encontrado los siguientes errores:\n\n" + erroresString, ButtonType.OK);
                    alert.setHeaderText("Confirmación de borrado");
                    DialogPane dialogAlert = alert.getDialogPane();
                    dialogAlert.getStylesheets().add(VistaInformacionTabController.this.getClass().getResource("../css/modena_dark.css").toExternalForm());
                    alert.showAndWait();
                } else {
                    System.out.println("Sin errores. Guardando...");
                    filaSeleccionadaProducto.setNombre(nombreProducto.getText());
                    filaSeleccionadaProducto.setPrecio(Double.valueOf(precioProducto.getText()));
                    filaSeleccionadaProducto.setDescripcion(descripcionProducto.getText());
                    filaSeleccionadaProducto.setCategoria(categoriaProducto.getText());
                    filaSeleccionadaProducto.setStock(Integer.valueOf(stockProducto.getText()));
                    filaSeleccionadaProducto.setFechaModificacion(new SimpleDateFormat("dd/MM/yyy HH:mm").format(Calendar.getInstance().getTime()));

                    tabsController.actualizarTabla();
                }
            }
        });
    }

    public String getCodigo() {
        return filaSeleccionadaProducto.getCodigo();
    }

    public void modoEditar(boolean mode) {
        if (mode) {
            nombreProducto.setFocusColor(Color.rgb(230, 230, 0));
            nombreProducto.setUnFocusColor(Color.rgb(42, 46, 55));
            precioProducto.setFocusColor(Color.rgb(230, 230, 0));
            precioProducto.setUnFocusColor(Color.rgb(42, 46, 55));
            descripcionProducto.setFocusColor(Color.rgb(230, 230, 0));
            descripcionProducto.setUnFocusColor(Color.rgb(42, 46, 55));
            categoriaProducto.setFocusColor(Color.rgb(230, 230, 0));
            categoriaProducto.setUnFocusColor(Color.rgb(42, 46, 55));
            stockProducto.setFocusColor(Color.rgb(230, 230, 0));
            stockProducto.setUnFocusColor(Color.rgb(42, 46, 55));
        } else {
            nombreProducto.setFocusColor(Color.TRANSPARENT);
            nombreProducto.setUnFocusColor(Color.TRANSPARENT);
            precioProducto.setFocusColor(Color.TRANSPARENT);
            precioProducto.setUnFocusColor(Color.TRANSPARENT);
            descripcionProducto.setFocusColor(Color.TRANSPARENT);
            descripcionProducto.setUnFocusColor(Color.TRANSPARENT);
            categoriaProducto.setFocusColor(Color.TRANSPARENT);
            categoriaProducto.setUnFocusColor(Color.TRANSPARENT);
            stockProducto.setFocusColor(Color.TRANSPARENT);
            stockProducto.setUnFocusColor(Color.TRANSPARENT);
        }

        nombreProducto.setEditable(mode);
        precioProducto.setEditable(mode);
        descripcionProducto.setEditable(mode);
        categoriaProducto.setEditable(mode);
        stockProducto.setEditable(mode);

        anadir.setVisible(!mode);
        borrar.setVisible(!mode);
        editar.setVisible(!mode);
        cancelar.setVisible(mode);
        guardar.setVisible(mode);
    }

}
