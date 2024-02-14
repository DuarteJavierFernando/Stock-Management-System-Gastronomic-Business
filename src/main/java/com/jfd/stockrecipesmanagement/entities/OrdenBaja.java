/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jfd.stockrecipesmanagement.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author javie
 */
public class OrdenBaja {
    private String status= "OPEN";
    private String fechaEmision = "2000-01-01 0:00:00";
    private int numeroOrden = 00000000000000;
    private List<List<String>> detalleProductos = new ArrayList<>();
    private String observaciones = "";
    private Double precioTotal;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public int getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(int numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public List<List<String>> getDetalleProductos() {
        return detalleProductos;
    }

    public void setDetalleProductos(List<List<String>> detalleProductos) {
        this.detalleProductos = detalleProductos;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }
    
    
}
