/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jfd.stockrecipesmanagement.dao;

import com.jfd.stockrecipesmanagement.connection.Conexion;
import com.jfd.stockrecipesmanagement.entities.Gastos;
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
public class GastosDAO {
    Conexion cx;

    public GastosDAO() {
        cx = new Conexion();
    }
    
    
    public List<List<String>> consultarGastos() {
        try {
            List<List<String>> listaMadre = new ArrayList<>();
            
            // Establece la conexión
            // Crea la consulta SQL para seleccionar todos los registros de la tabla Usuarios
            String consulta = "SELECT * FROM Gastos";
            // Crea un objeto Statement
            Statement stmt = cx.conectar().createStatement();
            // Ejecuta la consulta
            ResultSet rs = stmt.executeQuery(consulta);
            // Itera a través de los resultados e imprime los datos
            while (rs.next()) {
                int id = rs.getInt("id");
                String concepto = rs.getString("concepto");
                Double monto = rs.getDouble("Monto");
                listaMadre.add(new ArrayList<String>());
                listaMadre.get(listaMadre.size() - 1).add(String.valueOf(id));
                listaMadre.get(listaMadre.size() - 1).add(concepto);
                listaMadre.get(listaMadre.size() - 1).add(String.valueOf(monto));
            // Cierra la conexión
            }
            cx.desconectar();        
            return listaMadre;
        } catch (SQLException ex) {
        Logger.getLogger(GastosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
    public void actualizInsertarGastos(Gastos gasto){
        PreparedStatement ps = null;
        try {
            ps = cx.conectar().prepareStatement("INSERT INTO Gastos (id, concepto, Monto) VALUES (?,?,?) ON CONFLICT (id) DO UPDATE SET Monto = ? ;");
            ps.setInt(1,gasto.getId() );
            ps.setString(2,gasto.getConcepto() );
            ps.setDouble(3,gasto.getMonto() );
            ps.setDouble(4,gasto.getMonto() );
            ps.execute();
            //ps.executeUpdate();   
            //System.out.println("CargaExitosa");
            cx.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(OrdenCDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
