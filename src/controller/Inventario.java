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
 * @author CarlosLuisMiguelValentinVictor
 */
public class Inventario extends Application {

    private ObservableList<Producto> productos = FXCollections.observableArrayList();
    

    private Stage escenarioPrincipal;
    private BorderPane layoutPrincipal;
    private BorderPane vistaTabs;

    public Inventario() {

        // natacion
        productos.add(new Producto("NA01011", "Aletas de buceo", "Deportes Acuáticos", 453, 21.49, crearImagen("aletasBuceo"), "Aletas de gran tamaño, perfectas para coger una gran velocidad", "../img/products/aletasBuceo.jpg"));
        productos.add(new Producto("NA01012", "Gafas de buceo", "Deportes Acuáticos", 657, 15.99, crearImagen("gafasBuceo"), "Gafas de buceo profesionales", "../img/products/gafasBuceo.jpg"));
        productos.add(new Producto("NA01013", "Gorro", "Deportes Acuáticos", 341, 6.59, crearImagen("gorroPiscina"), "Gorro de tamaño medio para competición", "../img/products/gorroPiscina.jpg"));
        productos.add(new Producto("NA01014", "Bañador", "Deportes Acuáticos", 984, 8.99, crearImagen("bañador"), "Bañador para natación talla M. Muy cómodo", "../img/products/bañador.jpg"));
        productos.add(new Producto("NA01014", "Manoplas", "Deportes Acuáticos", 567, 5.59, crearImagen("manoplasNatacion"), "Guantes para impulsarse a gran velocidad en el agua", "../img/products/manoplasNatacion.jpg"));

        // futbol
        productos.add(new Producto("FU02011", "Botas de fútbol", "Fútbol", 345, 39.99, crearImagen("botasFutbol"), "Botas profesionales baratas y de gran calidad", "../img/products/botasFutbol.jpg"));
        productos.add(new Producto("FU02012", "Pantalones cortos", "Fútbol", 875, 10.59, crearImagen("pantalonesCortosFutbol"), "Pantalones tamaño M para fútbol. Calidad algodon 100%", "../img/products/pantalonesCortosFutbol.jpg"));
        productos.add(new Producto("FU02013", "Camiseta", "Fútbol", 156, 21.99, crearImagen("camisetaFutbol"), "Camiseta técnica", "../img/products/camisetaFutbol.jpg"));
        productos.add(new Producto("FU02014", "Protectores", "Fútbol", 98, 39.99, crearImagen("protectoresFutbol"), "Tobilleras protectoras en caso de accidentes en el terreno de juego", "../img/products/protectoresFutbol.jpg"));
        productos.add(new Producto("FU02015", "Medias", "Fútbol", 443, 13.29, crearImagen("mediasFutbol"), "Medias talla L enfocadas al fútbol", "../img/products/mediasFutbol.jpg"));

        // tenis
        productos.add(new Producto("TE03011", "Raqueta", "Tenis", 665, 59.99, crearImagen("raquetaTenis"), "Raqueta réplica de Rafael Nadal. Muy buen rendimiento y cordaje fuerte", "../img/products/raquetaTenis.jpg"));
        productos.add(new Producto("TE03012", "Cordaje", "Tenis", 872, 19.99, crearImagen("cordajeRaqueta"), "Cordaje de repuesto para raquetas técnicas", "../img/products/cordajeRaqueta.jpg"));
        productos.add(new Producto("TE03013", "Paletero", "Tenis", 123, 20.39, crearImagen("paleteroTenis"), "Mochila de gran tamaño para almacenar raquetas y bebidas", "../img/products/paleteroTenis.jpg"));
        productos.add(new Producto("TE03014", "Antivibrador", "Tenis", 435, 3.99, crearImagen("antivibradorRaqueta"), "Pedazo de goma para evitar que la raqueta vibre al golpear la pelota con la raqueta", "../img/products/antivibradorRaqueta.jpg"));
        productos.add(new Producto("TE03015", "Mochila", "Tenis", 618, 20.99, crearImagen("mochilaTenis"), "Mochila tamaño mediano para almacenar material deportivo", "../img/products/mochilaTenis.jpg"));

        // running
        productos.add(new Producto("RU04011", "Cronómetro", "Running", 548, 10.49, crearImagen("cronometroRunning"), "Cronometro de calidad, alta precisión", "../img/products/cronometroRunning.jpg"));
        productos.add(new Producto("RU04012", "Malla corta", "Running", 994, 6.99, crearImagen("mallaCortaRunning"), "Mallas unisex para deportes de running", "../img/products/mallaCortaRunning.jpg"));
        productos.add(new Producto("RU04013", "Zapatillas", "Running", 238, 49.99, crearImagen("zapatillasRunning"), "Zapatillas de calidad, buena suel y amortiguación en la suela", "../img/products/zapatillasRunning.jpg"));
        productos.add(new Producto("RU04014", "Auriculares", "Running", 45, 25.49, crearImagen("auricularesRunning"), "Auriculares bluetooth, gran calidad y sonido estereo", "../img/products/auricularesRunning.jpg"));
        productos.add(new Producto("RU04015", "Pulsera fit", "Running", 761, 13.49, crearImagen("pulseraFit"), "Pulsera deportiva que mide el rítmo cardíaco, los pasos diarios y gráficos semanales", "../img/products/pulseraFit.jpg"));

        // beisbol
        productos.add(new Producto("BE050011", "Bate aluminio", "Beisbol", 667, 10.49, crearImagen("bateBeisbolAluminio"), "Bate de béisbol de aluminio Gran calidad y resistencia", "../img/products/bateBeisbolAluminio.jpg"));
        productos.add(new Producto("BE050012", "Casco protector", "Beisbol", 345, 6.99, crearImagen("cascoBeisbol"), "Casco protector muy resistente. Incluye protección bucal", "../img/products/cascoBeisbol.jpg"));
        productos.add(new Producto("BE050013", "Muñequeras", "Beisbol", 276, 49.99, crearImagen("muñequerasDeporte"), "Muñequeras para limpiarse el sudor", "../img/products/muñequerasDeporte.jpg"));
        productos.add(new Producto("BE050014", "Gorra", "Beisbol", 879, 25.49, crearImagen("gorraBeisbol"), "Gorra para los deslumbramientos solares", "../img/products/gorraBeisbol.jpg"));
        productos.add(new Producto("BE050015", "Guante catcher", "Beisbol", 120, 13.49, crearImagen("guanteBeisbol"), "Guante catcher de piel", "../img/products/guanteBeisbol.jpg"));

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

    private ImageView crearImagen(String nombre) {
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
