package logica;

import conexionSQL.ConexionSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportesDocsFiscalesDAO {

    public List<ReportesDocsFiscales> obtenerReportesPorFecha(String fecha) {

        List<ReportesDocsFiscales> reportes = new ArrayList<>();
        //Traer todos los registros de la vista
        try{
            // Obtener todos los registros de todas las farmacias
            String sql = "SELECT * FROM VW_RPTCOSAVAL_DOCUMENTOSFISCALES WHERE fecha = ?";

            Connection conn = ConexionSQLServer.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, fecha);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // mapear objetos
                ReportesDocsFiscales reporte = new ReportesDocsFiscales();
                reporte.setNinterno(rs.getString("ninterno"));
                reporte.setTdocumento(rs.getString("tdocumento"));
                reporte.setTreferencia(rs.getString("treferencia"));
                reporte.setDfiscal(rs.getString("dfiscal"));
                reporte.setFecha(rs.getDate("fecha"));
                reporte.setTienda(rs.getString("tienda"));
                reporte.setName(rs.getString("name"));
                reportes.add(reporte);
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

        return reportes;
    }

    //Metodo para filtrado por fecha.
    public List<ReportesDocsFiscales> obtenerReportesPorFechaYSucursal(String fecha, String codigoSucursal) {

        List<ReportesDocsFiscales> reportes = new ArrayList<>();

        try {

            String sql = "SELECT * FROM VW_RPTCOSAVAL_DOCUMENTOSFISCALES WHERE fecha = ? AND tienda = ?";

            Connection conn = ConexionSQLServer.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, fecha);
            stmt.setString(2, codigoSucursal);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // mapear objetos
                ReportesDocsFiscales reporte = new ReportesDocsFiscales();
                reporte.setNinterno(rs.getString("ninterno"));
                reporte.setTdocumento(rs.getString("tdocumento"));
                reporte.setTreferencia(rs.getString("treferencia"));
                reporte.setDfiscal(rs.getString("dfiscal"));
                reporte.setFecha(rs.getDate("fecha"));
                reporte.setTienda(rs.getString("tienda"));
                reporte.setName(rs.getString("name"));
                reportes.add(reporte);
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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reportes;

    }

}
