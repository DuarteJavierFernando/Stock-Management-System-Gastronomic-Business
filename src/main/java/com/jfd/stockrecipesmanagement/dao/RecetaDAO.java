/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jfd.stockrecipesmanagement.dao;

import com.jfd.stockrecipesmanagement.connection.Conexion;
import com.jfd.stockrecipesmanagement.entities.OrdenCompra;
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
public class RecetaDAO {
    Conexion cx;

    public RecetaDAO() {
        cx = new Conexion();
    }
    
    public void insertarReceta(Receta recet){
        PreparedStatement ps = null;
        try {
            ps = cx.conectar().prepareStatement("INSERT INTO MasterRecetas VALUES (null,?,?,?);");
            ps.setString(1, recet.getNombreReceta());
            ps.setString(2, recet.getDetalleReceta());
            ps.setDouble(3, recet.getTiempoPreparacion());
            ps.executeUpdate();
            //System.out.println("CargaExitosa");
            cx.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Receta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertarIngred(Receta recet){
        PreparedStatement ps = null;
        try {
            ps = cx.conectar().prepareStatement("INSERT INTO Ingredientes (sku,id_receta,tipo,cantidad,unidad_cantidad) VALUES (?,?,?,?,?);");
            for (List<String> listaHija : recet.getIngredientes() ) {
                ps.setString(1,listaHija.get(0) ); // id_matprima (sku)
                ps.setInt(2, recet.getNumeroIDReceta()); // id_receta
                ps.setString(3,listaHija.get(1) ); //tipo
                ps.setString(4,listaHija.get(2) ); //cantidad
                ps.setString(5,listaHija.get(3) ); //unidad_cantidad
                ps.execute();
            }   
            //System.out.println("CargaExitosa");
            cx.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(Receta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public int consultarRecetaid(){
        try {
        String consulta = "SELECT * FROM MasterRecetas ORDER BY id  DESC LIMIT 1;";
        Statement stmt = cx.conectar().createStatement();
        ResultSet rs = stmt.executeQuery(consulta);
        int id = rs.getInt("id");
        cx.desconectar();
        //System.out.println(id);
        return id;
        } catch (SQLException ex) {
        Logger.getLogger(Receta.class.getName()).log(Level.SEVERE, null, ex);
        return 0;
        } 
    }
    
    
    public List<List<String>> consultarRecetas() {
        try {
            List<List<String>> listaMadre = new ArrayList<>();
            
            // Establece la conexión
            // Crea la consulta SQL para seleccionar todos los registros de la tabla Usuarios
            String consulta = "SELECT id, nombre, procedimiento, tiempo_elaboracion FROM MasterRecetas";
            // Crea un objeto Statement
            Statement stmt = cx.conectar().createStatement();
            // Ejecuta la consulta
            ResultSet rs = stmt.executeQuery(consulta);
            // Itera a través de los resultados e imprime los datos
            while (rs.next()) {
                int id = rs.getInt("id"); // Suponiendo que el campo de ID se llama "id"
                String nombre = rs.getString("nombre");  //nombre
                String procedimiento = rs.getString("procedimiento");  //procedimiento
                Double tiempo_elaboracion = rs.getDouble("tiempo_elaboracion");  //tiempo de elaboracion
                
                //System.out.println(sku);
                listaMadre.add(new ArrayList<String>());
                listaMadre.get(listaMadre.size() - 1).add(String.valueOf(id));
                listaMadre.get(listaMadre.size() - 1).add(nombre);
                listaMadre.get(listaMadre.size() - 1).add(String.valueOf(tiempo_elaboracion));
                //listaMadre.get(listaMadre.size() - 1).add(procedimiento);
                //listaMadre.get(listaMadre.size() - 1).add(tiempo_elaboracion);
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
