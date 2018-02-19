/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Inventario;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.Producto;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * FXML Controller class
 *
 * @author vntnc
 */
public class VistaPrincipalController implements Initializable {

    //Referencia a la clase principal
    private Inventario inventario;
    
    @FXML MenuBar menuBar;
    @FXML Menu primerMenu;
    @FXML Menu segundoMenu;
    @FXML Menu terceroMenu;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void acercaDe() {
        //Muestro alerta
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Acerca de");
        alerta.setContentText("AUTORES:  "+"\n\n"+"Carlos Cifuentes "+"\n"+ "Valentín Circo"+"\n"+"Victor Choza" +"\n"+ "Miguel Escanciano" +"\n"+ "Luis Gonzaga Muñoz");
        alerta.showAndWait();
    }
    
     //PDF
    @FXML
    public void pdf() throws IOException {
        System.out.println("**************************************");        
        //Creo un nuevo documento, una página y la añado
        
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        PDDocument documento = new PDDocument();
        PDPage pagina = new PDPage();
        documento.addPage(pagina);
        documento.getPage(0);
        
        //Inicio un nuevo stream de contenido
        PDPageContentStream contentStream = new PDPageContentStream(documento, pagina);
        
        //Establezco la posición Y de la primera línea y el tipo de fuente
        int linea = 700;
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

        //Recorro la lista de productos
        ArrayList<Producto> prods = new ArrayList<>(inventario.getProductos());
        for (Producto p : prods) {
            //Inicio un nuevo texto y escribo los datos
            contentStream.beginText();
                contentStream.newLineAtOffset(25, linea);
                contentStream.showText(p.getCodigo()+" ");
                contentStream.showText(p.getNombre()+" ");
                contentStream.showText(p.getStock()+" ");
            contentStream.endText();
            //Cambio de línea
            linea -= 25;
        }
        
        //Cierro el content stream
        contentStream.close();

        //Inicio el file chooser
        FileChooser fileChooser = new FileChooser();

        //Filtro para la extensión
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        //Muestro el diálogo de guardar
        File archivo = fileChooser.showSaveDialog(inventario.getPrimaryStage());

        
        if (archivo != null) {
            
            //Me aseguro de que tiene la extensión correcta y si no la cambio
            String extension = "";
            if (!archivo.getPath().endsWith(extension)){
                extension = ".pdf";
            }
            //Escribo en el archivo y lo cierro
            archivo = new File(archivo.getPath() + extension);
            documento.save(archivo);
            documento.close();                
            
        }
        
        //Abro el archivo en el visor de PDF del sistema
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(archivo);
            } 
            catch (IOException ex) {
           }
        }
            }
     //Salir
    @FXML
    private void salir() {
        System.exit(0);
    }
}
