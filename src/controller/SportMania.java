/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.Producto;

/**
 *
 * @author Victor
 */
public class SportMania extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private ObservableList datosProducto = FXCollections.observableArrayList();

    public SportMania() {

        datosProducto.add(new Producto("Patines Negros FILA", "Patines de color Negro de la marca FILA", 25.99, 200));
        datosProducto.add(new Producto("Patines Blancos FILA", "Patines de color Blancos de la marca FILA", 28.99, 500));
        datosProducto.add(new Producto("Patines Rojos Oxelo", "Patines de color Rojos de la marca Oxelo", 65.99, 150));
        datosProducto.add(new Producto("Patines Azules FILA", "Patines de color Azules de la marca FILA", 85.99, 20));
        datosProducto.add(new Producto("Patines Morados FILA", "Patines de color Morados de la marca FILA", 15.99, 600));

    }

    public ObservableList getDatosProducto() {
        return datosProducto;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

}
