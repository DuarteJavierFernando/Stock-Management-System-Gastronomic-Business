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
public class Receta {
    private int numeroIDReceta;
    private double tiempoPreparacion = 0.0;
    private String nombreReceta = "";
    private String detalleReceta = "";
    private List<List<String>> ingredientes = new ArrayList<>();
    //private Double precioTotal;

    public int getNumeroIDReceta() {
        return numeroIDReceta;
    }

    public void setNumeroIDReceta(int numeroIDReceta) {
        this.numeroIDReceta = numeroIDReceta;
    }

    public double getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public void setTiempoPreparacion(double tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    public String getDetalleReceta() {
        return detalleReceta;
    }

    public void setDetalleReceta(String detalleReceta) {
        this.detalleReceta = detalleReceta;
    }

    public List<List<String>> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<List<String>> ingredientes) {
        this.ingredientes = ingredientes;
    }
    
    
}
