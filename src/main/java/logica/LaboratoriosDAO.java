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
public class LaboratoriosDAO {

    public List<Laboratorios> obtenerLaboratorios(String nombreLab) {

        List<Laboratorios> labs = new ArrayList<>();
        //Traer todos los registros de la vista
        try {
            // Obtener todos los registros de todas las farmacias vencidos desde hace 6 meses

            String sql = "SELECT DISTINCT RETAILLABORATORIOS.FABRICANTE AS Id_lab, "
                    + " LABTABLE.NAME AS Nombre_Lab FROM RETAILLABORATORIOS "
                    + "INNER JOIN RETAILITEM ON RETAILLABORATORIOS.ITEMID = RETAILITEM.ITEMID "
                    + "INNER JOIN LABTABLE ON RETAILLABORATORIOS.FABRICANTE = LABTABLE.ACCOUNTNUM"
                    + " WHERE LABTABLE.NAME = ?";

            Connection conn = ConexionSQLServer.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombreLab);
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // mapear objetos
                Laboratorios lab = new Laboratorios();
                lab.setId_lab(rs.getString("Id_lab"));
                lab.setNombre(rs.getString("Nombre_Lab"));

                labs.add(lab);
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

        return labs;

    }

}
