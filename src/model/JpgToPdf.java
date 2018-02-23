/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Victor
 */
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JpgToPdf {


    public static void jpgToPdf(File carpetaImagenesCodigosBarras, File selectedDirectory, String codigo, String rutaAbsoluta, String nombre, String descripcion, String categoria, Double precio) throws FileNotFoundException, DocumentException, BadElementException, IOException {
        String outputFile = "CodigosBarras_" + codigo + "_" + new SimpleDateFormat("ddMMyyyHHmmssSS").format(Calendar.getInstance().getTime()) + ".pdf";
        List<String> files = new ArrayList<String>();
        for (final File fileEntry : carpetaImagenesCodigosBarras.listFiles()) {
            if (fileEntry.getName().contains("jpg")) {
                files.add(fileEntry.getName());
            }
        }

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(new File(selectedDirectory, outputFile)));
        document.open();
        
        document.newPage();
        Image imagenProducto = Image.getInstance(new File(rutaAbsoluta).getAbsolutePath());
        imagenProducto.setAbsolutePosition(75, 675);
        imagenProducto.setBorderWidth(0);
        imagenProducto.scaleAbsolute(100, 100);
        document.add(imagenProducto);
        
        Paragraph s;
        s = new Paragraph("Nombre: " + nombre);
        s.setSpacingBefore(150);
        s.setIndentationLeft(35);
        document.add(s);
        s = new Paragraph("Descripción: " + descripcion);
        s.setIndentationLeft(35);
        document.add(s);
        s = new Paragraph("Categoría: " + categoria);
        s.setIndentationLeft(35);
        document.add(s);
        s = new Paragraph("Precio: " + precio + "€");
        s.setIndentationLeft(35);
        document.add(s);
        
        document.newPage();
        int x = 0;
        int y = 650;
        for (String f : files) {
            Image imagenCodigoBarras = Image.getInstance(new File(carpetaImagenesCodigosBarras, f).getAbsolutePath());
            imagenCodigoBarras.setAbsolutePosition(x, y);
            imagenCodigoBarras.setBorderWidth(0);
            imagenCodigoBarras.scaleAbsolute(200, 200);
            document.add(imagenCodigoBarras);
            if (x == 400) {
                if (y == 50) {
                    document.newPage();
                    x = 0;
                    y = 650;
                } else {
                    x = 0;
                    y -= 150;
                }
            } else {
                x += 200;
            }
        }
        document.close();
    }
}
