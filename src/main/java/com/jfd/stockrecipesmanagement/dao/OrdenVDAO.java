/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jfd.stockrecipesmanagement.dao;

import com.jfd.stockrecipesmanagement.connection.Conexion;
import com.jfd.stockrecipesmanagement.entities.OrdenVenta;
import com.jfd.stockrecipesmanagement.entities.Receta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javie
 */
public class OrdenVDAO {
    Conexion cx;

    public OrdenVDAO() {
        cx = new Conexion();
    }
    
    public List<List<Object>> consultarIngredientesReceta(int idReceta,Double x) {
        List<List<Object>> ingredientes = new ArrayList<>();
        try {
        // Establece la conexión
        // Crea la consulta SQL para seleccionar todos los registros de la tabla Usuarios
        String consulta = "SELECT sku,cantidad,unidad_cantidad FROM Ingredientes WHERE id_receta = ?";
        // Crea un objeto Statement
        //Statement stmt = cx.conectar().createStatement();
        PreparedStatement pstmt = cx.conectar().prepareStatement(consulta);
        pstmt.setInt(1, idReceta); // Asigna el valor del parámetro
        // Ejecuta la consulta
        ResultSet rs = pstmt.executeQuery();
        // Itera a través de los resultados e imprime los datos
        while (rs.next()) {
            String sku = rs.getString("sku");
            Double cantidad = rs.getDouble("cantidad")*x;
            String unidad_cantidad = rs.getString("unidad_cantidad");            
            ingredientes.add(new ArrayList<Object>());
            ingredientes.get(ingredientes.size() - 1).add(sku);
            ingredientes.get(ingredientes.size() - 1).add(cantidad);
            ingredientes.get(ingredientes.size() - 1).add(unidad_cantidad);            
        }
        // Cierra la conexión
        cx.desconectar();
        } catch (SQLException ex) {
        Logger.getLogger(OrdenVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ingredientes;
    }
    
    
    public List<Object> consultarStockIngredientes(String sku) {
        List<Object> ingredientes_stock = new ArrayList<>();
        try {
        // Establece la conexión
        // Crea la consulta SQL para seleccionar todos los registros de la tabla Usuarios
        String consulta = "SELECT sku,cantidad,unidad_cantidad FROM MasterMateriasPrimas WHERE sku = ?";
        // Crea un objeto Statement
        //Statement stmt = cx.conectar().createStatement();
        PreparedStatement pstmt = cx.conectar().prepareStatement(consulta);
        pstmt.setString(1, sku); // Asigna el valor del parámetro
        // Ejecuta la consulta
        ResultSet rs = pstmt.executeQuery();
        // Itera a través de los resultados e imprime los datos
        while (rs.next()) {
            String sku2 = rs.getString("sku");
            Double cantidad = rs.getDouble("cantidad");
            String unidad_cantidad = rs.getString("unidad_cantidad");
            ingredientes_stock.add(sku2);
            ingredientes_stock.add(cantidad);
            ingredientes_stock.add(unidad_cantidad);
            //System.out.println(ingredientes_stock);
        }
        // Cierra la conexión
        cx.desconectar();
        } catch (SQLException ex) {
        Logger.getLogger(OrdenVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ingredientes_stock;
    }
    
    
    public Double consultarCostoStockIngredientes(String sku) {
        Double costo_unitario = 0.0;
        try {
        // Establece la conexión
        // Crea la consulta SQL para seleccionar todos los registros de la tabla Usuarios
        String consulta = "SELECT max(id_ordenC),sku,costo_unitario FROM TransaccionesCompras WHERE sku = ?";
        // Crea un objeto Statement
        //Statement stmt = cx.conectar().createStatement();
        PreparedStatement pstmt = cx.conectar().prepareStatement(consulta);
        pstmt.setString(1, sku); // Asigna el valor del parámetro
        // Ejecuta la consulta
        ResultSet rs = pstmt.executeQuery();
        // Itera a través de los resultados e imprime los datos
        while (rs.next()) {
            String sku2 = rs.getString("sku");
            //String unidad_cantidad = rs.getString("unidad_cantidad");
            costo_unitario = rs.getDouble("costo_unitario");
        }
        // Cierra la conexión
        cx.desconectar();
        } catch (SQLException ex) {
        Logger.getLogger(OrdenVenta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return costo_unitario;
    }
    
    
    public int consultarOrdenVid(){
        try {
        String consulta = "SELECT * FROM OrdenesVenta ORDER BY id  DESC LIMIT 1;";
        Statement stmt = cx.conectar().createStatement();
        ResultSet rs = stmt.executeQuery(consulta);
        int id = rs.getInt("id");
        cx.desconectar();
        //System.out.println(id);
        return id;
        } catch (SQLException ex) {
        Logger.getLogger(OrdenVDAO.class.getName()).log(Level.SEVERE, null, ex);
        return 0;
        } 
    }
    
}
