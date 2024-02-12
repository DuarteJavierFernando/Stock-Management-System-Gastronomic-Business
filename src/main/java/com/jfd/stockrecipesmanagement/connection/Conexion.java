/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jfd.stockrecipesmanagement.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author javie
 */
public class Conexion {
    Connection conexion = null;
    
    String sqliteConector = "org.sqlite.JDBC";
    String baseDatos = "jdbc:sqlite:BaseDatos.db"; //url de conexion
    
    public Connection conectar(){
        try {
            Class.forName(sqliteConector);
            conexion = DriverManager.getConnection(baseDatos);
            System.out.println("Conexion Exitosa!!!!");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexion;
    }
    
    public void desconectar(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

