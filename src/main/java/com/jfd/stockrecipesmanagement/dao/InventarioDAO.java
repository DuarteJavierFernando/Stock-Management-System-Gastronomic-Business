/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jfd.stockrecipesmanagement.dao;

import com.jfd.stockrecipesmanagement.connection.Conexion;
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
public class InventarioDAO {
    Conexion cx;
    
    public InventarioDAO(){
         cx = new Conexion();
    }
    
    public List<List<String>> consultarInventario() {
        try {
            List<List<String>> listaMadre = new ArrayList<>();
            
            // Establece la conexión
            // Crea la consulta SQL para seleccionar todos los registros de la tabla Usuarios
            String consulta = "SELECT * FROM MasterMateriasPrimas";
            // Crea un objeto Statement
            Statement stmt = cx.conectar().createStatement();
            // Ejecuta la consulta
            ResultSet rs = stmt.executeQuery(consulta);
            // Itera a través de los resultados e imprime los datos
            while (rs.next()) {
                //int id = rs.getInt("id"); // Suponiendo que el campo de ID se llama "id"
                String sku = rs.getString("sku");  //sku
                String categoria = rs.getString("categoria");  //categoria
                String subcategoria = rs.getString("subcategoria");  //subcategoria
                String marca = rs.getString("marca");  //marca
                String tipo = rs.getString("tipo");  //tipo
                Float cantidad = rs.getFloat("cantidad");  //cantidad
                String unidad = rs.getString("unidad_cantidad");  //unidad_cantidad
                //System.out.println(sku);
                listaMadre.add(new ArrayList<String>());
                listaMadre.get(listaMadre.size() - 1).add(sku);
                listaMadre.get(listaMadre.size() - 1).add(categoria);
                listaMadre.get(listaMadre.size() - 1).add(subcategoria);
                listaMadre.get(listaMadre.size() - 1).add(marca);
                listaMadre.get(listaMadre.size() - 1).add(tipo);
                listaMadre.get(listaMadre.size() - 1).add(String.valueOf(cantidad));
                listaMadre.get(listaMadre.size() - 1).add(unidad);
            // Cierra la conexión
            }
            cx.desconectar();        
            return listaMadre;
        } catch (SQLException ex) {
        Logger.getLogger(OrdenCDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
