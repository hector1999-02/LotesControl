
package logica;

import conexionSQL.ConexionSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RetailItemDAO {
    
    public List<RetailItem> obtenerProducto(String itemID) {

        List<RetailItem> productos = new ArrayList<>();
        //Traer todos los registros de la vista
        try{
            // Obtener todos los registros de todas las farmacias
            String sql = "SELECT ITEMID, NAMEALIAS, EXTENDEDDESCRIPTION, DELETED FROM RETAILITEM WHERE DELETED = 0 and ITEMID = ? ";

            Connection conn = ConexionSQLServer.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, itemID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // mapear objetos
                RetailItem producto = new RetailItem();
                producto.setITEMID(rs.getString("ITEMID"));
                producto.setNAMEALIAS(rs.getString("NAMEALIAS"));
                producto.setEXTENDEDDESCRIPTION(rs.getString("EXTENDEDDESCRIPTION"));

                productos.add(producto);
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

        return productos;
    
    }
    
    
}
