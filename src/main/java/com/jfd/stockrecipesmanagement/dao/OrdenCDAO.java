/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jfd.stockrecipesmanagement.dao;

import com.jfd.stockrecipesmanagement.connection.Conexion;
import com.jfd.stockrecipesmanagement.entities.OrdenCompra;
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
public class OrdenCDAO {
    Conexion cx;
    
    public OrdenCDAO() {
        cx = new Conexion();
    }
  

    public void insertarOrdenC(OrdenCompra ordenC){
        PreparedStatement ps = null;
        try {
            ps = cx.conectar().prepareStatement("INSERT INTO OrdenesCompra VALUES (null,?,?);");
            ps.setString(1, ordenC.getFechaEmision());
            ps.setDouble(2, ordenC.getPrecioTotal());
            ps.executeUpdate();
            System.out.println("CargaExitosa");
            cx.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(OrdenCDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertarTransaccC(OrdenCompra ordenC){
        PreparedStatement ps = null;
        try {
            ps = cx.conectar().prepareStatement("INSERT INTO TransaccionesCompras (id_ordenC,sku,costo_unitario,cantidad,unidad_cantidad) VALUES (?,?,?,?,?);");
            for (List<String> listaHija : ordenC.getDetalleProductos()) {
                ps.setInt(1, ordenC.getNumeroOrden());
                ps.setString(2,listaHija.get(7) ); //sku
                ps.setString(3,listaHija.get(8) ); //costo unitario
                ps.setString(4,listaHija.get(5) ); //cantidad
                ps.setString(5,listaHija.get(6) ); //unidad_cantidad
                ps.execute();
            }   
            System.out.println("CargaExitosa");
            cx.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(OrdenCDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
    public void actualizMasterMatPrimas(OrdenCompra ordenC){
        PreparedStatement ps = null;
        try {
            for (List<String> producto : ordenC.getDetalleProductos()) {
            //ps = cx.conectar().prepareStatement("INSERT INTO OrdenesCompra VALUES (?,?,?,?,?,?,?,?);");
            ps = cx.conectar().prepareStatement("INSERT INTO MasterMateriasPrimas (sku, categoria, subcategoria, marca, tipo, cantidad, unidad_Cantidad) VALUES (?,?,?,?,?,?,?) ON CONFLICT (sku) DO UPDATE SET cantidad = MasterMateriasPrimas.cantidad + excluded.cantidad;");
            ps.setString(1,producto.get(7) ); //sku
            ps.setString(2,producto.get(1) ); //categoria
            ps.setString(3,producto.get(2) ); //subcategoria
            ps.setString(4,producto.get(3) ); //marca
            ps.setString(5,producto.get(4) ); //tipo   
            ps.setDouble(6,Double.parseDouble(producto.get(5)) ); //cantidad
            ps.setString(7,producto.get(6) ); //unidad camtidad
            ps.execute();
            //ps.executeUpdate();
            }
            System.out.println("CargaExitosa");
            cx.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(OrdenCDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public List<List<String>> consultarOrdenC() {
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
            
            listaMadre.add(new ArrayList<String>());
            listaMadre.get(listaMadre.size() - 1).add(sku);
            listaMadre.get(listaMadre.size() - 1).add(categoria);
            listaMadre.get(listaMadre.size() - 1).add(subcategoria);
            listaMadre.get(listaMadre.size() - 1).add(marca);
            listaMadre.get(listaMadre.size() - 1).add(tipo);
            listaMadre.get(listaMadre.size() - 1).add(String.valueOf(cantidad));
            listaMadre.get(listaMadre.size() - 1).add(unidad);
            // Cierra la conexión
            cx.desconectar();        
            return listaMadre;
        }
        } catch (SQLException ex) {
        Logger.getLogger(OrdenCDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public void consultarInventario() {
        try {
        // Establece la conexión
        // Crea la consulta SQL para seleccionar todos los registros de la tabla Usuarios
        String consulta = "SELECT * FROM OrdenesCompra";
        // Crea un objeto Statement
        Statement stmt = cx.conectar().createStatement();
        // Ejecuta la consulta
        ResultSet rs = stmt.executeQuery(consulta);
        // Itera a través de los resultados e imprime los datos
        while (rs.next()) {
            int id = rs.getInt("id"); // Suponiendo que el campo de ID se llama "id"
            //String nombre = rs.getString("nombre");
            //String email = rs.getString("email");
            //String contrasena = rs.getString("contrasenia");
            //System.out.println("ID: " + id + ", Nombre: " + nombre + ", Email: " + email + ", Contraseña: " + contrasena);
        }
        // Cierra la conexión
        cx.desconectar();
        } catch (SQLException ex) {
        Logger.getLogger(OrdenCDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public int consultarOrdenCid(){
        try {
        String consulta = "SELECT * FROM OrdenesCompra ORDER BY id  DESC LIMIT 1;";
        Statement stmt = cx.conectar().createStatement();
        ResultSet rs = stmt.executeQuery(consulta);
        int id = rs.getInt("id");
        cx.desconectar();
        System.out.println(id);
        return id;
        } catch (SQLException ex) {
        Logger.getLogger(OrdenCDAO.class.getName()).log(Level.SEVERE, null, ex);
        return 0;
        } 
    }
    
}
