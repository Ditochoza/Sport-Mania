/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.itextpdf.text.DocumentException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.Inventario;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import model.PDFHelper;
import model.Producto;
import net.sourceforge.jbarcodebean.JBarcodeBean;
import net.sourceforge.jbarcodebean.model.Interleaved25;

/**
 * FXML Controller class
 *
 * @author CarlosLuisMiguelValentinVictor
 */
public class VistaInformacionTabController implements Initializable {

    private Producto filaSeleccionadaProducto;
    private VistaTabsController tabsController;
    private Inventario inventario;

    public String isAddOrEdit = "";
    private File imagenElegida;

    // datos antes de la edición
    private String idOld;
    private String rutaOld;
    private String nombreOld;
    private String precioOld;
    private String descripcionOld;
    private String categoriaOld;
    private String stockOld;

    // datos
    @FXML
    private JFXTextField idProducto;
    @FXML
    private JFXTextField nombreProducto;
    @FXML
    private JFXTextField precioProducto;
    @FXML
    private JFXTextField descripcionProducto;
    @FXML
    private JFXTextField categoriaProducto;
    @FXML
    private JFXTextField stockProducto;
    @FXML
    private JFXTextField fechaAltaProducto;
    @FXML
    private JFXTextField fechaModificacionProducto;
    @FXML
    private ImageView imagenProducto;

    // botones
    @FXML
    Button anadir;
    @FXML
    Button borrar;
    @FXML
    Button editar;
    @FXML
    Button cancelar;
    @FXML
    Button addImagen;
    @FXML
    Button guardar;
    @FXML
    JFXButton crear;

    // comboBox
    @FXML
    JFXComboBox comboBoxCodigosBarras;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listeners();
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
        System.out.println("Inventario recibido " + inventario);

    }

    public void comunicacionControlador(VistaTabsController tabsController) {
        this.tabsController = tabsController;
    }

    //Es llamado por la apliación principal para tener una referencia de vuelta de si mismo
    public void setInventarioTabInformacion(Inventario inventario) {
        this.inventario = inventario;
    }

    // recibo la fila seleccionada de VistaTabController que a su vez lo ha recibido de VistaProductosTabController
    public void setFilaInformacion(Producto newValue) {
        if (!isAddOrEdit.equals("Add")) {
            this.filaSeleccionadaProducto = newValue;
            System.out.println(filaSeleccionadaProducto.getCodigo());

            imagenProducto.setImage(new Image(getClass().getResource(filaSeleccionadaProducto.getRutaFoto()).toExternalForm()));
            idProducto.setText(filaSeleccionadaProducto.getCodigo());
            nombreProducto.setText(filaSeleccionadaProducto.getNombre());
            precioProducto.setText(String.valueOf(filaSeleccionadaProducto.getPrecio()));
            descripcionProducto.setText(filaSeleccionadaProducto.getDescripcion());
            categoriaProducto.setText(filaSeleccionadaProducto.getCategoria());
            stockProducto.setText(String.valueOf(filaSeleccionadaProducto.getStock()));
            fechaAltaProducto.setText(String.valueOf(filaSeleccionadaProducto.getFechaAlta()));
            fechaModificacionProducto.setText(String.valueOf(filaSeleccionadaProducto.getFechaModificacion()));

            int numeroCodigosBarras = 100;
            if (filaSeleccionadaProducto.getStock() < numeroCodigosBarras) {
                numeroCodigosBarras = filaSeleccionadaProducto.getStock();
            }
            for (int i = 0; i < numeroCodigosBarras; i++) {
                comboBoxCodigosBarras.getItems().add(i + 1);
            }
        }
    }

    private void listeners() {
        borrar.setOnMouseClicked(e
                -> {
            String borrarString = " > Código: " + filaSeleccionadaProducto.getCodigo() + "\n > Nombre: " + filaSeleccionadaProducto.getNombre() + "\n > Stock: " + filaSeleccionadaProducto.getStock() + " uds."
                    + "\n > Precio: " + filaSeleccionadaProducto.getPrecio() + " €" + "\n > Fecha Alta: " + filaSeleccionadaProducto.getFechaAlta();

            Alert alert;

            alert = new Alert(Alert.AlertType.WARNING, "Contenido de la fila a borrar:\n\n" + borrarString + "\n\n¿Borrar definitivamente?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.setHeaderText("CONFIRMACIÓN DE BORRADO");

            //css dialog pane
            DialogPane dialogAlert = alert.getDialogPane();
            dialogAlert.getStylesheets().add(getClass().getResource("../css/modena_dark.css").toExternalForm());
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                tabsController.eliminarProductoTabla(filaSeleccionadaProducto);
            }

        });

        editar.setOnMouseClicked(e -> {
            isAddOrEdit = "Edit";
            modoEditAdd(true);
        });

        anadir.setOnMouseClicked(e -> {
            isAddOrEdit = "Add";
            modoEditAdd(true);
        });

        addImagen.setOnMouseClicked(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccione una imagen");

            //Filtro para la extensión
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                    "JPG files (*.jpg)", "*.jpg");
            fileChooser.getExtensionFilters().add(extFilter);

            //Muestro el diálogo de guardar
            imagenElegida = fileChooser.showOpenDialog(inventario.getPrimaryStage());

            if (imagenElegida != null) {
                try {
                    System.out.println(imagenElegida);
                    Files.copy(imagenElegida.toPath(), (new File(getRutaAbsoluta(imagenElegida.getAbsolutePath()))).toPath(), REPLACE_EXISTING);
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
        });

        cancelar.setOnMouseClicked(e -> {
            if (isAddOrEdit.equals("Add")) {
                tabsController.setTabProductos();
                tabsController.desactivarTabs();
            } else {
                imagenProducto.setImage(new Image(getClass().getResource(rutaOld).toExternalForm()));
                idProducto.setText(idOld);
                nombreProducto.setText(nombreOld);
                precioProducto.setText(precioOld);
                descripcionProducto.setText(descripcionOld);
                categoriaProducto.setText(categoriaOld);
                stockProducto.setText(stockOld);
            }

            isAddOrEdit = "";
            modoEditAdd(false);
        });

        guardar.setOnMouseClicked((MouseEvent e) -> {
            System.out.println("Guardar");
            String erroresString = "";

            // errores imagen
            if (imagenElegida == null) {
                erroresString += " - No se ha elegido ninguna imagen\n";
            }

            // errores id
            ArrayList<Producto> productos = new ArrayList<>(inventario.getProductos());
            if (idProducto.getText().isEmpty()) {
                erroresString += " - El ID no puede quedar vacío\n";
                idProducto.setUnFocusColor(Color.RED);
            } else {
                if (isAddOrEdit.equals("Add")) {
                    boolean repetido = false;
                    for (int i = 0; i < productos.size(); i++) {
                        if (productos.get(i).getCodigo().equals(idProducto.getText())) {
                            repetido = true;
                        }
                    }
                    if (repetido) {
                        erroresString += " - El ID debe ser único\n";
                        idProducto.setUnFocusColor(Color.RED);
                    } else {
                        if (idProducto.getText().length() != 7) {
                            erroresString += " - El ID debe ser de 7 caracteres\n";
                            idProducto.setUnFocusColor(Color.RED);
                        } else {
                            System.out.println(idProducto.getText().substring(0, 2) + " " + idProducto.getText().substring(2));
                            System.out.println(idProducto.getText().substring(0, 2).matches(".*[a-zA-Z].*"));
                            System.out.println(idProducto.getText().substring(2).matches(".*[0-9].*"));
                            if (idProducto.getText().substring(0, 2).matches(".*[a-zA-Z].*") && idProducto.getText().substring(2).matches(".*[0-9].*")) {
                                idProducto.setUnFocusColor(Color.rgb(42, 46, 55));
                            } else {
                                erroresString += " - El ID debe contener 2 letras y 5 números\n";
                                idProducto.setUnFocusColor(Color.RED);
                            }
                        }
                    }
                } else {
                    nombreProducto.setUnFocusColor(Color.rgb(42, 46, 55));
                }

            }

            // errores nombre producto
            if (nombreProducto.getText().isEmpty()) {
                erroresString += " - El nombre no puede quedar vacío\n";
                nombreProducto.setUnFocusColor(Color.RED);
            }

            // errores precio producto
            if (!precioProducto.getText().isEmpty()) {
                try {
                    Double valor = Double.valueOf(precioProducto.getText());
                    precioProducto.setUnFocusColor(Color.rgb(42, 46, 55));
                } catch (NumberFormatException ex) {
                    erroresString += " - El precio debe ser un número\n";
                    precioProducto.setUnFocusColor(Color.RED);
                }
            } else {
                erroresString += " - El precio no puede quedar vacío\n";
                precioProducto.setUnFocusColor(Color.RED);
            }

            //errores descripcion producto
            if (descripcionProducto.getText().isEmpty()) {
                erroresString += " - La descripción no puede quedar vacía\n";
                descripcionProducto.setUnFocusColor(Color.RED);
            } else {
                descripcionProducto.setUnFocusColor(Color.rgb(42, 46, 55));
            }

            //errores categoria producto
            if (categoriaProducto.getText().isEmpty()) {
                erroresString += " - La categoría no puede quedar vacía\n";
                categoriaProducto.setUnFocusColor(Color.RED);
            } else {
                categoriaProducto.setUnFocusColor(Color.rgb(42, 46, 55));
            }

            //errores stock producto
            if (!stockProducto.getText().isEmpty()) {
                try {
                    int valor = Integer.valueOf(stockProducto.getText());
                    stockProducto.setUnFocusColor(Color.rgb(42, 46, 55));
                } catch (NumberFormatException ex) {
                    erroresString += " - El stock debe ser un número\n";
                    stockProducto.setUnFocusColor(Color.RED);
                }
            } else {
                erroresString += " - El stock no puede quedar vacío\n";
                stockProducto.setUnFocusColor(Color.RED);
            }

            if (!erroresString.isEmpty()) {
                Alert alert;
                alert = new Alert(Alert.AlertType.WARNING, "Se han encontrado los siguientes errores:\n\n" + erroresString + "\n\nResuelva los errores para poder continuar", ButtonType.OK);
                alert.setHeaderText("ERROR");
                DialogPane dialogAlert = alert.getDialogPane();
                dialogAlert.getStylesheets().add(VistaInformacionTabController.this.getClass().getResource("../css/modena_dark.css").toExternalForm());
                alert.showAndWait();
            } else {
                if (isAddOrEdit.equals("Add")) {
                    inventario.getProductos().add(new Producto(idProducto.getText(), nombreProducto.getText(), categoriaProducto.getText(), Integer.parseInt(stockProducto.getText()), Double.parseDouble(precioProducto.getText()), inventario.crearImagenAlAnadirProducto(imagenElegida.getName()), descripcionProducto.getText(), "../img/products/" + imagenElegida.getName()));

                    isAddOrEdit = "";
                    modoEditAdd(false);
                    tabsController.actualizarTabla();
                    tabsController.desactivarTabs();
                    tabsController.setTabProductos();
                } else {
                    System.out.println("Sin errores. Guardando...");
                    filaSeleccionadaProducto.setNombre(nombreProducto.getText());
                    filaSeleccionadaProducto.setPrecio(Double.valueOf(precioProducto.getText()));
                    filaSeleccionadaProducto.setDescripcion(descripcionProducto.getText());
                    filaSeleccionadaProducto.setCategoria(categoriaProducto.getText());
                    filaSeleccionadaProducto.setStock(Integer.valueOf(stockProducto.getText()));
                    filaSeleccionadaProducto.setFechaModificacion(new SimpleDateFormat("dd/MM/yyy HH:mm").format(Calendar.getInstance().getTime()));

                    fechaModificacionProducto.setText(String.valueOf(filaSeleccionadaProducto.getFechaModificacion()));

                    isAddOrEdit = "";
                    modoEditAdd(false);
                    tabsController.actualizarTabla();
                }
            }
        });

        crear.setOnMouseClicked((MouseEvent e) -> {
            String erroresString = "";

            //errores cantidad código de barras
            if (comboBoxCodigosBarras.getValue() != null) {
                if (Integer.valueOf(comboBoxCodigosBarras.getValue().toString()) <= filaSeleccionadaProducto.getStock()) {
                    try {
                        int valor = Integer.valueOf(comboBoxCodigosBarras.getValue().toString());
                        comboBoxCodigosBarras.setUnFocusColor(Color.rgb(42, 46, 55));
                    } catch (NumberFormatException ex) {
                        erroresString += " - La cantdad debe ser un número\n";
                        comboBoxCodigosBarras.setUnFocusColor(Color.RED);
                    }
                } else {
                    erroresString += " - La cantidad no puede ser mayor que el stock\n";
                    comboBoxCodigosBarras.setUnFocusColor(Color.RED);
                }
            } else {
                erroresString += " - La cantidad no puede quedar vacía\n";
                comboBoxCodigosBarras.setUnFocusColor(Color.RED);
            }

            if (!erroresString.isEmpty()) {
                Alert alert;
                alert = new Alert(Alert.AlertType.WARNING, "Se han encontrado los siguientes errores:\n\n" + erroresString + "\n\nResuelva los errores para poder continuar", ButtonType.OK);
                alert.setHeaderText("ERROR");
                DialogPane dialogAlert = alert.getDialogPane();
                dialogAlert.getStylesheets().add(VistaInformacionTabController.this.getClass().getResource("../css/modena_dark.css").toExternalForm());
                alert.showAndWait();
            } else {
                int numCodigos = Integer.parseInt((String) comboBoxCodigosBarras.getValue());

                DirectoryChooser chooser = new DirectoryChooser();
                chooser.setTitle("JavaFX Projects");
                File defaultDirectory = new File("C:/");
                chooser.setInitialDirectory(defaultDirectory);
                File selectedDirectory = chooser.showDialog(null);

                if (selectedDirectory != null && selectedDirectory.isDirectory()) {
                    File carpetaImagenesCodigosBarras = new File("ImagenesCodigosBarras/" + numCodigos + "CodigosBarras_" + new SimpleDateFormat("ddMMyyyHHmmssSS").format(Calendar.getInstance().getTime()));
                    carpetaImagenesCodigosBarras.mkdirs();
                    for (int i = 0; i < numCodigos; i++) {
                        try {

                            String numeroCodigo = (filaSeleccionadaProducto.getCodigo()).substring(2);
                            for (int j = 0; j < 10; j++) {
                                numeroCodigo += 0 + (int) (Math.random() * ((9 - 0) + 1));
                            }

                            JBarcodeBean barcode = new JBarcodeBean();
                            barcode.setCodeType(new Interleaved25());
                            barcode.setCode(numeroCodigo);
                            barcode.setCheckDigit(true);

                            BufferedImage bufferedImage = barcode.draw(new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB));
                            File file = new File(carpetaImagenesCodigosBarras.getPath() + "/" + filaSeleccionadaProducto.getCodigo() + "_" + (i + 1) + ".jpg");
                            ImageIO.write(bufferedImage, "jpg", file);

                        } catch (IOException ex) {
                        }

                    }
                    try {
                        PDFHelper.informacionProductoPDF(carpetaImagenesCodigosBarras, selectedDirectory,
                                filaSeleccionadaProducto.getCodigo(), getRutaAbsoluta(filaSeleccionadaProducto.getRutaFoto()),
                                filaSeleccionadaProducto.getNombre(), filaSeleccionadaProducto.getDescripcion(),
                                filaSeleccionadaProducto.getCategoria(), filaSeleccionadaProducto.getPrecio());
                    } catch (DocumentException ex) {
                    } catch (IOException ex) {
                    }
                }

            }
        });
    }

    public String getCodigo() {
        String codigo = "";
        try {
            codigo = filaSeleccionadaProducto.getCodigo();
        } catch (NullPointerException ex) {
        }
        return codigo;
    }

    public void modoEditAdd(boolean mode) {
        System.out.println(isAddOrEdit);
        if (mode) {
            // se guardan los datos anteriores a la edición
            if (!isAddOrEdit.equals("Add")) {
                idOld = filaSeleccionadaProducto.getCodigo();
                rutaOld = filaSeleccionadaProducto.getRutaFoto();
                nombreOld = nombreProducto.getText();
                precioOld = precioProducto.getText();
                descripcionOld = descripcionProducto.getText();
                categoriaOld = categoriaProducto.getText();
                stockOld = stockProducto.getText();
            }

            idProducto.setFocusColor(Color.rgb(230, 230, 0));
            idProducto.setUnFocusColor(Color.rgb(42, 46, 55));
            nombreProducto.setFocusColor(Color.rgb(230, 230, 0));
            nombreProducto.setUnFocusColor(Color.rgb(42, 46, 55));
            precioProducto.setFocusColor(Color.rgb(230, 230, 0));
            precioProducto.setUnFocusColor(Color.rgb(42, 46, 55));
            descripcionProducto.setFocusColor(Color.rgb(230, 230, 0));
            descripcionProducto.setUnFocusColor(Color.rgb(42, 46, 55));
            categoriaProducto.setFocusColor(Color.rgb(230, 230, 0));
            categoriaProducto.setUnFocusColor(Color.rgb(42, 46, 55));
            stockProducto.setFocusColor(Color.rgb(230, 230, 0));
            stockProducto.setUnFocusColor(Color.rgb(42, 46, 55));
        } else {
            idProducto.setFocusColor(Color.TRANSPARENT);
            idProducto.setUnFocusColor(Color.TRANSPARENT);
            nombreProducto.setFocusColor(Color.TRANSPARENT);
            nombreProducto.setUnFocusColor(Color.TRANSPARENT);
            precioProducto.setFocusColor(Color.TRANSPARENT);
            precioProducto.setUnFocusColor(Color.TRANSPARENT);
            descripcionProducto.setFocusColor(Color.TRANSPARENT);
            descripcionProducto.setUnFocusColor(Color.TRANSPARENT);
            categoriaProducto.setFocusColor(Color.TRANSPARENT);
            categoriaProducto.setUnFocusColor(Color.TRANSPARENT);
            stockProducto.setFocusColor(Color.TRANSPARENT);
            stockProducto.setUnFocusColor(Color.TRANSPARENT);
        }

        if (isAddOrEdit.equals("Add")) {
            imagenProducto.setImage(null);
            idProducto.setText("");
            nombreProducto.setText("");
            precioProducto.setText("");
            descripcionProducto.setText("");
            categoriaProducto.setText("");
            stockProducto.setText("");
            fechaAltaProducto.setText("");
            fechaModificacionProducto.setText("");
            comboBoxCodigosBarras.setVisible(false);
            crear.setVisible(false);
        } else {
            comboBoxCodigosBarras.setVisible(true);
            crear.setVisible(true);
        }

        idProducto.setEditable(mode);
        nombreProducto.setEditable(mode);
        precioProducto.setEditable(mode);
        descripcionProducto.setEditable(mode);
        categoriaProducto.setEditable(mode);
        stockProducto.setEditable(mode);

        anadir.setVisible(!mode);
        borrar.setVisible(!mode);
        editar.setVisible(!mode);
        if (mode) {
            if (isAddOrEdit.equals("Add")) {
                addImagen.setVisible(true);
            } else {
                addImagen.setVisible(false);
            }
        } else {
            addImagen.setVisible(false);
        }
        cancelar.setVisible(mode);
        guardar.setVisible(mode);
    }

    private String getRutaAbsoluta(String rutaFoto) {
        File f = new File(rutaFoto);
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        s += "\\src\\img\\products\\" + f.getName();
        System.out.println(s);
        return s;
    }

}
