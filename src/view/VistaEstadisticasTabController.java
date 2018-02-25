/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Inventario;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import model.Producto;

/**
 * FXML Controller class
 *
 * @author CarlosLuisMiguelValentinVictor
 */
public class VistaEstadisticasTabController implements Initializable {

    private Inventario inventario;

    @FXML
    private BarChart<String, Integer> grafica;

    @FXML
    private CategoryAxis ejeX;

    @FXML
    private NumberAxis ejeY;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setInventarioTabProductos(Inventario inventario) {

        this.inventario = inventario;
        List<Producto> datos = inventario.getProductos();
        ArrayList<String> nombresProducto = new ArrayList<String>();

        for (int i = 0; i < datos.size(); i++) {
            Producto producto = datos.get(i);
            nombresProducto.add(producto.getNombre());
        }

        // damos formato a los datos del eje X, convertimos de ArrayList a ObservableList, tiene que ser así porque la gráfica los requiere en dicho formato
        ObservableList<String> datosEjeX = FXCollections.observableArrayList(nombresProducto);
        ejeX.setCategories(datosEjeX);

        // ahora vamos con el eje Y
        ejeY.setLabel("Stock");
        XYChart.Series<String, Integer> serieDatos = new XYChart.Series<>();
        serieDatos.setName("Stock por producto");
        for (int i = 0; i < datos.size(); i++) {
            Producto producto = datos.get(i);
            serieDatos.getData().add(new XYChart.Data<>(producto.getNombre(), producto.getStock()));
        }

        ejeX.setTickLabelRotation(270);
        ejeX.setTickLabelFill(Color.CHOCOLATE);
        ejeY.setTickLabelFill(Color.CHOCOLATE);

        // añadimos los datos a la gráfica
        grafica.getData().add(serieDatos);
    }
}
