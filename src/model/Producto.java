/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author vntnc
 */
public class Producto {

    private  StringProperty codigo;
    private  StringProperty nombre;
    private  IntegerProperty stock;
    private  DoubleProperty precios;
    
    private ObjectProperty foto;
    private StringProperty descripcion;
    private StringProperty codigoBarras;
    private StringProperty fechaAlta;
    private StringProperty fechaModificacion;
    
    public Producto(){
        this(null,null,null,null,null,null);
    }

    public Producto(String codigo, String nombre, Integer stock, Double precios,
                    ImageView foto, String descripcion) {

        this.codigo = new SimpleStringProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);
        this.stock = new SimpleIntegerProperty(stock);
        this.precios = new SimpleDoubleProperty(precios);
        
        this.foto = new SimpleObjectProperty(foto);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.fechaAlta = new SimpleStringProperty(new SimpleDateFormat("dd/MM/yyy HH:mm").format(Calendar.getInstance().getTime()));
        this.fechaModificacion = new SimpleStringProperty(new SimpleDateFormat("dd/MM/yyy HH:mm").format(Calendar.getInstance().getTime()));

    }

    // foto
    public ObjectProperty fotoProperty(){
        return this.foto;
    }
    
    public void setFoto(ImageView image){
        this.foto.set(image);
    }
      public Object getFoto(){
        return foto.get();
    }
    
   
    
     //descripcion
    public String getDescripcion(){
        return this.descripcion.get();
    }
     
    public void setDescripcion(String descripcion){
        this.descripcion.set(descripcion);
    }
    
    public StringProperty descripcionProperty(){
        return descripcion;
    }
    
    // codigo barras
    public String getcodigoBarras(){
        return this.codigoBarras.get();
    }
     
    public void setcodigoBarras(String codigoBarras){
        this.codigoBarras.set(codigoBarras);
    }
    
    public StringProperty codigoBarrasProperty(){
        return codigoBarras;
    }
    
    //fecha alta
    public String getFechaAlta(){
        return fechaAlta.get();
    }
    
    
    public void setFechaAlta(String date){
        this.fechaAlta.set(date);
    }
    
    public StringProperty fechaAltaProperty(){
        return fechaAlta;
    }
    
    //fecha modificacion
    
    public String getFechaModificacion(){
        return fechaModificacion.get();
    }
    
    
    public void setFechaModificacion(String date){
        this.fechaModificacion.set(date);
    }
    
    public StringProperty fechaModificacionProperty(){
        return fechaModificacion;
    }
    
    
    
    // nombre
    public String getNombre() {
        return this.nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    // precios
    public Double getPrecio() {
        return this.precios.get();
    }

    public void setPrecio(Double precio) {
        this.precios.set(precio);
    }

    public DoubleProperty preciosProperty() {
        return precios;
    }

    // stock
    public Integer getStock() {
        return this.stock.get();
    }

    public void setStock(Integer stock) {
        this.stock.set(stock);
    }

    public IntegerProperty stockProperty() {
        return stock;
    }

    // codigo
    public String getCodigo() {
        return codigo.get();
    }

    public void setCodigo(String codigo) {
        this.codigo.set(codigo);
    }

    public StringProperty codigoProperty() {
        return codigo;
    }

}
