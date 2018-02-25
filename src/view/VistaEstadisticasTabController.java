/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import controller.Inventario;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;

import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
import model.Producto;

/**
 * FXML Controller class
 *
 * @author CarlosLuisMiguelValentinVictor
 */
public class VistaEstadisticasTabController implements Initializable {

    private Inventario inventario;
    
    VistaTabsController tabsController;

    @FXML
    private BarChart<String, Integer> grafica;

    @FXML
    private CategoryAxis ejeX;

    @FXML
    private NumberAxis ejeY;
    
     @FXML
    private Button btnPdf;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void comunicacionControlador(VistaTabsController tabsController){
        this.tabsController = tabsController;
    }

    public void setInventarioTabProductos(Inventario inventario) {

        this.inventario = inventario;
        addDatosGrafica();
        
        
    }
    
    
    List<Producto> productos_datos;
    ArrayList<String> nombresProducto;
    XYChart.Series<String, Integer> serieDatos;
    ObservableList<String> datosEjeX;
    
    public void quitarProducto(Producto producto){
        
        serieDatos.getData().clear();
        this.productos_datos.remove(producto);
        this.nombresProducto.clear();
        System.out.println(producto.getNombre());
        datosEjeX.clear();
        grafica.getData().clear();
        
        System.out.println("Producto quitado");
        addDatosGrafica();
    }
    
    public void actualizarStock(int stock){
                serieDatos.getData().clear();
                grafica.getData().clear();
                addDatosGrafica();

    }
    
    public void addDatosGrafica(){
        
        productos_datos = inventario.getProductos();
        nombresProducto = new ArrayList<>();
        
        System.out.println("Tamaño actual " + productos_datos.size());

        for (int i = 0; i < productos_datos.size(); i++) {
            Producto producto = productos_datos.get(i);
            nombresProducto.add(producto.getNombre());
        }

        // damos formato a los datos del eje X, convertimos de ArrayList a ObservableList, tiene que ser así porque la gráfica los requiere en dicho formato
        datosEjeX = FXCollections.observableArrayList(nombresProducto);
        ejeX.setCategories(datosEjeX);

        // ahora vamos con el eje Y
        ejeY.setLabel("Stock");
        serieDatos = new XYChart.Series<>();
        serieDatos.setName("Stock por producto");
        for (int i = 0; i < productos_datos.size(); i++) {
            Producto producto = productos_datos.get(i);
            serieDatos.getData().add(new XYChart.Data<>(producto.getNombre(), producto.getStock()));
        }

        ejeX.setTickLabelRotation(270);
        ejeX.setTickLabelFill(Color.CHOCOLATE);
        ejeY.setTickLabelFill(Color.CHOCOLATE);

        // añadimos los datos a la gráfica
        grafica.getData().add(serieDatos);
    }
    @FXML
    void btnPdf_MouseClicked(MouseEvent event) {
        try {
            //Generamos una imagen a partir de la grafica
            File file;
            String pathImagenGrafica = "./grafica_productos_stock.png";
            String pathPDF = "./grafica_productos_stock.pdf";

            // I. GENERAMOS UN FICHERO DE IMAGEN a partir de la gráfica
            // --------------------------------------------------------
            WritableImage image = grafica.snapshot(new SnapshotParameters(), null);
            file = new File(pathImagenGrafica);
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);

            //Generamos un pdf a partir de la imagen creada anteriormente
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(new File(pathPDF)));
            document.open();

            Image imagenCodigoBarras = Image.getInstance(new File(pathImagenGrafica).getAbsolutePath());
            imagenCodigoBarras.setAbsolutePosition(10, 500);
            imagenCodigoBarras.setBorderWidth(0);
            imagenCodigoBarras.scaleAbsolute(500, 200);
            document.add(imagenCodigoBarras);
            document.close();

            //Abrimos el pdf que se acaba de generar
            if (Desktop.isDesktopSupported()) {
                file = new File(pathPDF);
                Desktop.getDesktop().open(file);
            }
        } catch (IOException ex) {
            Logger.getLogger(VistaEstadisticasTabController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(VistaEstadisticasTabController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
