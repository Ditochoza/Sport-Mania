package model;

import java.util.List;
import javafx.collections.ObservableList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "productos")
public class Empaquetador {
    
    private List<Producto> productos;
    
    @XmlElement(name = "producto") //Opcional para el elemento especificado
    public List<Producto> getProductos() {
        return this.productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

}
