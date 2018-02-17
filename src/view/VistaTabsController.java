/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Inventario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import model.Producto;

/**
 * FXML Controller class
 *
 * @author vntnc
 */
// Este controlador es el intermediario entre todos los tabs (Producto, Informacion, Estadisticas) que a su vez tienen sus propios controladores
public class VistaTabsController implements Initializable {

    @FXML
    ImageView productosIcon;

    @FXML
    private TabPane Tabs;
    @FXML
    private Tab productosTab;
    @FXML
    private Tab informacionTab;
    @FXML
    private Tab estadisticasTab;

    public Inventario inventario;

    // controlador del tab de productos
    @FXML
    VistaProductosTabController productosController;

    // controlador del tab de informacion
    @FXML
    VistaInformacionTabController informacionController;

    // controlador del tab de estadisticas
    @FXML
    VistaEstadisticasTabController estadisticasController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        informacionTab.setDisable(true);
        estadisticasTab.setDisable(true);

        // envio este controlador a VistaProductosTabController y a VistaInformacionTabController
        productosController.comunicacionControlador(this);
        informacionController.comunicacionControlador(this);
    }

    // redirigido a VistaProductosTabController desde Inventario
    public void setInventarioVistaTabs(Inventario inventario) {

        this.inventario = inventario;
        System.out.println("Inventario capturado por VistaTabsController");

        productosController.setInventarioTabProductos(this.inventario);
        System.out.println("Inventario enviado a VistaProductosTabController");

        setToolTips();

    }

    public void activarTabs() {
        informacionTab.setDisable(false);
        estadisticasTab.setDisable(false);
    }

    public void setFilaInformacion(Producto newValue) {
        // envio objecto seleccionado de la tabla a VistaInformacionTabController
        informacionController.setFilaInformacion(newValue);
        // cambio de tab a informcion
        Tabs.getSelectionModel().select(informacionTab);

    }

    private void setToolTips() {
        Tooltip toolTipInformacion = new Tooltip();
        toolTipInformacion.setText("Informacion detallada del producto seleccionado y \nsus detalles.");
        informacionTab.setTooltip(toolTipInformacion);

        Tooltip toolTipProductos = new Tooltip();
        toolTipProductos.setText("Listado de todos los productos disponibles.");
        productosTab.setTooltip(toolTipProductos);

        Tooltip toolTipEstadisticas = new Tooltip();
        toolTipEstadisticas.setText("Graficos disponibles del producto seleccionado");
        estadisticasTab.setTooltip(toolTipEstadisticas);
    }

    public void eliminarProductoTabla(Producto filaSeleccionadaProducto) {
        // llamo a eliminar el producto de la tabla
        productosController.eliminarProductoTabla(filaSeleccionadaProducto);
        // cambio de tab a productos
        Tabs.getSelectionModel().select(productosTab);
        desactivarTabs();
    }

    public String getCodigoInformacionProducto() {
        return informacionController.getCodigo();
    }

    public void desactivarTabs() {
        informacionTab.setDisable(true);
        estadisticasTab.setDisable(true);
    }

}
