/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import conexionSQL.ConexionSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IT
 */
public class SucursalesDAO {
    public List<Sucursales> obtenerSucursales() {
         
         List<Sucursales> sucursales = new ArrayList<>();

         
        try (Connection conn = ConexionSQLServer.getConnection();
            PreparedStatement stmt = conn.prepareStatement("select STOREID, NAME from RBOSTORETABLEZONA")) {
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Sucursales sucursal = new Sucursales();
                sucursal.setSTOREID(rs.getString("STOREID"));
                sucursal.setNAME(rs.getString("NAME"));       
                sucursales.add(sucursal);
            }
        } catch (SQLException ex) {
            // Manejar el error
            ex.printStackTrace();
        }

        return sucursales;
    }  
    
    
    
    
}
