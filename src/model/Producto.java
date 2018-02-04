/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Victor
 */
public class Producto {
    
    private final StringProperty nombre;
    private final StringProperty descripcion;
    private final DoubleProperty precio;
    private final IntegerProperty stock;
    private final ObjectProperty fechaDeAlta;
    private final ObjectProperty fechaDeModificacion;

    public Producto(String nombre, String descripcion, double precio, int stock) {
        
        this.nombre = new SimpleStringProperty(nombre);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.precio = new SimpleDoubleProperty(precio);
        this.stock = new SimpleIntegerProperty(stock);
        this.fechaDeAlta = new SimpleObjectProperty(util.UtilidadFechas.getFechaActual());
        this.fechaDeModificacion = new SimpleObjectProperty(util.UtilidadFechas.getFechaActual());
        
    }

    public StringProperty getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public DoubleProperty getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio.set(precio);
    }

    public IntegerProperty getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    //@XmlJavaTypeAdapter(AdaptadorFechas.class)
    public LocalDate getFechaDeAlta() {
        return (LocalDate) fechaDeAlta.get();
    }

    public void setFechaDeAlta(LocalDate fechaDeAlta) {
        this.fechaDeAlta.set(fechaDeAlta);
    }

    //@XmlJavaTypeAdapter(AdaptadorFechas.class)
    public LocalDate getFechaDeModificacion() {
        return (LocalDate) fechaDeModificacion.get();
    }

    public void setFechaDeModificacion(LocalDate fechaDeModificacion) {
        this.fechaDeModificacion.set(fechaDeModificacion);
    }
    
}
