/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Producto;
import view.VistaTabsController;

/**
 *
 * @author vntnc
 */
public class Inventario extends Application {

    private ObservableList productos = FXCollections.observableArrayList();

    private Stage escenarioPrincipal;
    private BorderPane layoutPrincipal;
    private BorderPane vistaTabs;

    public Inventario() {

        // imagenes de los productos

        ImageView imagen3 = new ImageView(getClass().getResource("../img/tabs/sun.png").toExternalForm());
        imagen3.setFitHeight(50);
        imagen3.setFitWidth(50);
        imagen3.setPreserveRatio(true);

        ImageView imagen4 = new ImageView(getClass().getResource("../img/tabs/sun.png").toExternalForm());
        imagen4.setFitHeight(50);
        imagen4.setFitWidth(50);
        imagen4.setPreserveRatio(true);

        ImageView imagen5 = new ImageView(getClass().getResource("../img/tabs/sun.png").toExternalForm());
        imagen5.setFitHeight(50);
        imagen5.setFitWidth(50);
        imagen5.setPreserveRatio(true);

        // natacion
        productos.add(new Producto("NA456", "Aletas de buceo", "Deportes Acuáticos", 453, 21.49, crearImagen("aletasBuceo"), "Aletas de gran tamaño, perfectas para coger una gran velocidad", "../img/products/aletasBuceo.jpg"));
        productos.add(new Producto("NA589", "Gafas de buceo", "Deportes Acuáticos", 657, 15.99, crearImagen("gafasBuceo"), "Gafas de buceo profesionales", "../img/products/gafasBuceo.jpg"));
        productos.add(new Producto("NA254", "Gorro", "Deportes Acuáticos", 341, 6.59, crearImagen("gorroPiscina"), "Gorro de tamaño medio para competición", "../img/products/gorroPiscina.jpg"));
        productos.add(new Producto("NA857", "Bañador", "Deportes Acuáticos", 984, 8.99, crearImagen("bañador"), "Bañador para natación talla M. Muy cómodo", "../img/products/bañador.jpg"));
        productos.add(new Producto("NA514", "Manoplas", "Deportes Acuáticos", 567, 5.59, crearImagen("manoplasNatacion"), "Guantes para impulsarse a gran velocidad en el agua", "../img/products/manoplasNatacion.jpg"));
        
        // futbol
        productos.add(new Producto("FU867", "Botas de fútbol", "Fútbol", 345, 39.99, crearImagen("botasFutbol"), "Botas profesionales baratas y de gran calidad", "../img/products/botasFutbol.jpg"));
        productos.add(new Producto("FU958", "Pantalones cortos", "Fútbol", 875, 10.59, crearImagen("pantalonesCortosFutbol"), "Pantalones tamaño M para fútbol. Calidad algodon 100%", "../img/products/pantalonesCortosFutbol.jpg"));
        productos.add(new Producto("FU774", "Camiseta", "Fútbol", 156, 21.99, crearImagen("camisetaFutbol"), "Camiseta técnica", "../img/products/camisetaFutbol.jpg"));
        productos.add(new Producto("FU452", "Protectores", "Fútbol", 98, 39.99, crearImagen("protectoresFutbol"), "Tobilleras protectoras en caso de accidentes en el terreno de juego", "../img/products/protectoresFutbol.jpg"));
        productos.add(new Producto("FU882", "Medias", "Fútbol", 443, 13.29, crearImagen("mediasFutbol"), "Medias talla L enfocadas al fútbol", "../img/products/mediasFutbol.jpg"));
        
        // tenis
        /*productos.add(new Producto("TE456", "Raqueta", 665, 59.99, imagen, "Raqueta réplica de Rafael Nadal. Muy buen rendimiento y cordaje fuerte"));
        productos.add(new Producto("TE776", "Cordaje", 872, 19.99, imagen2, "Cordaje de repuesto para raquetas técnicas"));
        productos.add(new Producto("TE341", "Paletero", 123, 20.39, imagen3, "Mochila de gran tamaño para almacenar raquetas y bebidas"));
        productos.add(new Producto("TE021", "Antivibrador", 435, 3.99, imagen4, "Pedazo de goma para evitar que la raqueta vibre al golpear la pelota con la raqueta"));
        productos.add(new Producto("TE554", "Mochila", 618, 20.99, imagen5, "Mochila tamaño mediano para almacenar material deportivo"));
        
         // running
        productos.add(new Producto("RU562", "Cronómetro", 548, 10.49, imagen, "Cronometro de calidad, alta precisión"));
        productos.add(new Producto("RU771", "Malla corta", 994, 6.99, imagen2, "Mallas unisex para deportes de running"));
        productos.add(new Producto("RU873", "Zapatillas", 238, 49.99, imagen3, "Zapatillas de calidad, buena suel y amortiguación en la suela"));
        productos.add(new Producto("RU324", "Auriculares", 45, 25.49, imagen4, "Auriculares bluetooth, gran calidad y sonido estereo"));
        productos.add(new Producto("RU901", "Pulsera fit", 761, 13.49, imagen5, "Pulsera deportiva que mide el rítmo cardíaco, los pasos diarios y gráficos semanales"));
        
         // beisbol
        productos.add(new Producto("BE376", "Bate aluminio", 667, 10.49, imagen, "Bate de béisbol de aluminio Gran calidad y resistencia"));
        productos.add(new Producto("BE231", "Casco protector", 345, 6.99, imagen2, "Casco protector muy resistente. Incluye protección bucal"));
        productos.add(new Producto("BE609", "Muñequeras", 276, 49.99, imagen3, "Muñequeras para limpiarse el sudor"));
        productos.add(new Producto("BE546", "Gorra", 879, 25.49, imagen4, "Gorra para los deslumbramientos solares"));
        productos.add(new Producto("B553", "Guante catcher", 120, 13.49, imagen5, "Guante catcher de piel"));*/
        
        System.out.println("ObservableList Populated! " + productos.size());

    }

    @Override
    public void start(Stage escenarioPrincipal) {

        //Debo hacerlo para que luego me funcione en l carga de escenas
        this.escenarioPrincipal = escenarioPrincipal;

        //Establezco el título
        this.escenarioPrincipal.setTitle("Gestión de inventariado");

        //Inicializo el layout principal
        initLayoutPrincipal();

        //Muestro la vista persona
        muestraVistaTabs();

    }

    public void initLayoutPrincipal() {

        //Cargo el layout principal a partir de la vista VistaPrincipal.fxml
        FXMLLoader loader = new FXMLLoader();
        URL location = Inventario.class.getResource("../view/VistaPrincipal.fxml");
        loader.setLocation(location);
        try {
            layoutPrincipal = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Cargo y muestro la escena que contiene ese layout principal
        Scene escena = new Scene(layoutPrincipal);
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.show();

    }

    public void muestraVistaTabs() {

        FXMLLoader loader = new FXMLLoader();
        URL location = Inventario.class.getResource("../view/VistaTabs.fxml");
        loader.setLocation(location);
        try {
            vistaTabs = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(Inventario.class.getName()).log(Level.SEVERE, null, ex);
        }

        layoutPrincipal.setCenter(vistaTabs);

        // get del controlador de VistaTabs para despues mandarlo al controlador del tab productos (nested controllers para cada Tab)
        VistaTabsController controller = loader.getController();
        controller.setInventarioVistaTabs(this);

    }

    public ObservableList getProductos() {
        return productos;
    }

    public Stage getPrimaryStage() {
        return escenarioPrincipal;
    }
    
    private ImageView crearImagen(String nombre){
        ImageView imagen = new ImageView(getClass().getResource("../img/products/" + nombre + ".jpg").toExternalForm());
        imagen.setFitHeight(50);
        imagen.setFitWidth(50);
        imagen.setPreserveRatio(true);
        
        return imagen;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
