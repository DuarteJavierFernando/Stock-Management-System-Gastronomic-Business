/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jfd.stockrecipesmanagement.files;

import com.jfd.stockrecipesmanagement.entities.OrdenCompra;
import com.jfd.stockrecipesmanagement.gui.MainMenu;
import java.io.IOException;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
/**
 *
 * @author javie
 */
public class OrdenVFIle {
    
    public static void generarOrden(String fecha, String numero, String observ, List<String> detalleProductos ) throws FileNotFoundException, IOException{
        
        
        //PDF FORMATING AND CREATION
        //String home = System.getProperty("user.home");
        
        //String desktop = home + "/Escritorio/";
        //String pdfPath = home + "\\OrdenC"+numero+".pdf";
        String pdfPath = "Factura"+numero+".pdf";

        PdfWriter pdfwriter = new PdfWriter(pdfPath); //create PDF file
        PdfDocument pdfdocument = new PdfDocument(pdfwriter); //create a Java Class that defines the pdf structure that will be materialized un a concrete file by the writer
        pdfdocument.setDefaultPageSize(PageSize.A5); 
        Document document = new Document(pdfdocument); //create a blank canvas to write on the PDFDocument Java object
        document.setMargins(20, 20, 15, 20); // TOP,RIGHT,BOTTOM,LEFT
        
        
        //HEADER
        float columnHead1Dim = 170f;
        float columnHead2Dim = 40f;
        float columnHead3Dim = 170f;
        float columnsHeadDim[]= {columnHead1Dim,columnHead2Dim,columnHead3Dim };
        Table headerTable = new Table(columnsHeadDim).setHeight(60f);
        
        headerTable.addCell(headerLeftPane());
        headerTable.addCell(new Cell().add(""));
        headerTable.addCell(headerRightPane(fecha ,numero ));
        
        
        //HEADER - PRODUCT DETAILS DIVIDER 
        float columnFullWidth = 380f;
        float columnsFullWidth[] = {columnFullWidth};
        Table dividerTable = new Table(columnsFullWidth);
            
        Border dividerGrayBorder = new SolidBorder(Color.GRAY,1f/2f);
        dividerTable.setBorder(dividerGrayBorder);
        
        Paragraph spacingParagraph = new Paragraph("\n");  //create a blank spacing
        
        
        //PRODUCT DETAILS
        float colummProdDet1Dim = 20f;
        float colummProdDet2Dim = 285f;
        float colummProdDet3Dim = 65f;
        float columnsProdDetDim[]= {colummProdDet1Dim,colummProdDet2Dim,colummProdDet3Dim };
        Table prodDetTable = new Table(columnsProdDetDim);
        //TABLE TITLES
        prodDetTable.addCell(titleProductPane("Comprado?"));
        prodDetTable.addCell(titleProductPane("Detalle"));
        prodDetTable.addCell(titleProductPane("Precio"));
        
        
        
        //TABLE CONTENT
        for (int i = 0; i < detalleProductos.size(); i++) {
    // Código que se ejecuta en cada iteración
        
        prodDetTable.addCell(newProductPane("□"));
        prodDetTable.addCell(newProductPane(detalleProductos.get(i)));
        prodDetTable.addCell(newProductPane("    "));

        }
        
        //OBSERVATIONS
        float columnObservationsDim = 380f;
        float columnsObservationsDim[] = {columnObservationsDim};
        Table observationsTable = new Table(columnsObservationsDim);
        //TABLE TITLES
        observationsTable.addCell(titleObservationsPane("Observaciones:"));
        
        Paragraph observacParagraph = newObservationsPane(observ);
        
        
        //ADDITIONS TO DOCUMENT
        document.add(headerTable);
        document.add(dividerTable);
        document.add(spacingParagraph);
        document.add(prodDetTable);
        document.add(spacingParagraph);
        document.add(dividerTable);
        document.add(spacingParagraph);
        document.add(observationsTable);
        document.add(observacParagraph);
        document.close();
    }

    private static Cell headerLeftPane() {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        return new Cell().add("Factura").setBold().setTextAlignment(TextAlignment.CENTER).setFontSize(18f).setVerticalAlignment(VerticalAlignment.MIDDLE);
    }

    
    private static Table headerRightPane(String date, String number) throws IOException {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        float column1Dim = 45f; 
        float column2Dim = 125f;
        float columnsDim[]= {column1Dim,column2Dim};
        Table nestedTable = new Table(columnsDim).setHeight(55f);
        
        PdfFont fontContent = PdfFontFactory.createFont(FontConstants.COURIER);
        nestedTable.addCell(new Cell().add("Fecha:").setBold().setFontSize(8f).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        nestedTable.addCell(new Cell().add(date).setFontSize(10f).setTextAlignment(TextAlignment.RIGHT).setFont(fontContent).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        nestedTable.addCell(new Cell().add("N°:").setBold().setFontSize(8f).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        nestedTable.addCell(new Cell().add(number).setFontSize(10f).setTextAlignment(TextAlignment.RIGHT).setFont(fontContent).setBorder(Border.NO_BORDER).setVerticalAlignment(VerticalAlignment.MIDDLE));
        return nestedTable.setVerticalAlignment(VerticalAlignment.MIDDLE);
    }

    
    private static Cell newProductPane(String detalle) throws IOException {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody        
        Border dividerGrayDashedBorder = new DashedBorder(Color.GRAY,1f/2f);
        PdfFont fontContent2 = PdfFontFactory.createFont(FontConstants.COURIER);
        return new Cell().add(detalle).setMarginLeft(5f).setMarginRight(5f).setFontSize(8f).setFont(fontContent2).setBorder(dividerGrayDashedBorder).setHeight(12f);
    }

    private static Cell titleProductPane(String comprado) throws IOException {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        PdfFont fontContent2 = PdfFontFactory.createFont(FontConstants.COURIER);
        return new Cell().add(comprado).setBold().setMarginTop(5f).setMarginLeft(5f).setMarginRight(5f).setFont(fontContent2).setTextAlignment(TextAlignment.CENTER).setFontSize(10f).setBorder(Border.NO_BORDER);
    }

    private static Cell titleObservationsPane(String observaciones) throws IOException {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        PdfFont fontContent2 = PdfFontFactory.createFont(FontConstants.COURIER);
        return new Cell().add(observaciones).setBold().setMarginTop(5f).setMarginLeft(5f).setMarginRight(5f).setFont(fontContent2).setTextAlignment(TextAlignment.LEFT).setFontSize(10f).setBorder(Border.NO_BORDER);
    }

    private static Paragraph newObservationsPane(String observacion) throws IOException {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        PdfFont fontContent2 = PdfFontFactory.createFont(FontConstants.COURIER);
        return new Paragraph(observacion).setFontSize(8f).setFont(fontContent2).setFontColor(Color.GRAY).setMarginLeft(10f).setMarginRight(5f).setMarginLeft(10f).setMarginLeft(5f);
        
    }
 }
