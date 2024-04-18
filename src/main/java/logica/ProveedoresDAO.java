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
 * @author Hector Mejia IT
 */
public class ProveedoresDAO {
    public List<Proveedores> obtenerProveedores(String nombreProv) {

        List<Proveedores> provs = new ArrayList<>();
        //Traer todos los registros de la vista
        try {
            // Obtener todos los registros de todas las farmacias vencidos desde hace 6 meses

            String sql = "SELECT DISTINCT ACCOUNTNUM, NAME FROM VENDTABLE WHERE DELETED = 0 AND NAME = ?";

            Connection conn = ConexionSQLServer.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombreProv);
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // mapear objetos
                Proveedores prov = new Proveedores();
                prov.setIdProv(rs.getString("ACCOUNTNUM"));
                prov.setNombre(rs.getString("NAME"));

                provs.add(prov);
            }
            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (conn != null) {
                conn.close();
            }

        } catch (SQLException ex) {
            // Manejar el error
            ex.printStackTrace();
        }

        return provs;

    }
}
