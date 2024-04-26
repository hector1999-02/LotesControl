package logica;

import conexionSQL.ConexionSQLServer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticulosPorLotesDAO {

    public List<ArticulosPorLotes> obtenerProductosVencidos() {

        List<ArticulosPorLotes> arts = new ArrayList<>();
        //Traer todos los registros de la vista
        try {
            // Obtener todos los registros de todas las farmacias vencidos desde hace 6 meses

            String sql = "SELECT ret.itemid as itemid, ret.SEARCHKEYWORDS AS sap, art_lotes.FECHA_CADUCIDAD AS fecha_caducidad, lote, ret.EXTENDEDDESCRIPTION AS descripcion, "
                    + " observacion, cantidad, precio_etiquetado, VT.name AS proveedor, art_lotes.reg_san registro, art_lotes.devolutivo as devolutivo, "
                    + " LT.name AS laboratorio, art_lotes.fecha_registro AS fecha_registro"
                    + " FROM TBL_COSAVAL_ARTICULOSPORLOTES art_lotes "
                    + " INNER JOIN RETAILITEM ret ON art_lotes.ITEMID = ret.ITEMID "
                    + " INNER JOIN VENDTABLE VT ON art_lotes.id_vend = vt.ACCOUNTNUM "
                    + " INNER JOIN RETAILLABORATORIOS RL ON RL.ITEMID = ret.ITEMID "
                    + " INNER JOIN LABTABLE LT ON RL.FABRICANTE = LT.ACCOUNTNUM "
                    + " WHERE fecha_caducidad BETWEEN DATEADD(MONTH, -6, CAST(GETDATE() AS DATE)) AND CAST(GETDATE() AS DATE) order by fecha_caducidad";

            Connection conn = ConexionSQLServer.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // mapear objetos
                ArticulosPorLotes art = new ArticulosPorLotes();
                art.setItemId(rs.getString("itemid"));
                art.setSap(rs.getString("sap"));
                art.setFechaCaducidad(rs.getDate("fecha_caducidad"));
                art.setLote(rs.getString("lote"));
                art.setDescripcion(rs.getString("descripcion"));
                art.setObservacion(rs.getString("observacion"));
                art.setCantidad(rs.getInt("cantidad"));
                art.setPrecio_etiquetado(rs.getDouble("precio_etiquetado"));
                art.setVendedor(rs.getString("proveedor"));
                art.setLaboratorio(rs.getString("laboratorio"));
                art.setRegistro_sanitario(rs.getString("registro"));
                art.setDevolutivo(rs.getString("devolutivo"));
                art.setFechaRegistro(rs.getDate("fecha_registro"));

                arts.add(art);
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

        return arts;

    }

    public List<ArticulosPorLotes> obtenerProductosPorFecha1m() {

        List<ArticulosPorLotes> arts = new ArrayList<>();
        //Traer todos los registros de la vista
        try {
            // Obtener todos los registros de todas las farmacias
            String sql = "SELECT ret.itemid as itemid, ret.SEARCHKEYWORDS AS sap, art_lotes.FECHA_CADUCIDAD AS fecha_caducidad, lote, ret.EXTENDEDDESCRIPTION AS descripcion, "
                    + " observacion, cantidad, precio_etiquetado, VT.name AS proveedor, art_lotes.reg_san registro, art_lotes.devolutivo as devolutivo, "
                    + " LT.name AS laboratorio, art_lotes.fecha_registro AS fecha_registro"
                    + " FROM TBL_COSAVAL_ARTICULOSPORLOTES art_lotes "
                    + " INNER JOIN RETAILITEM ret ON art_lotes.ITEMID = ret.ITEMID "
                    + " INNER JOIN VENDTABLE VT ON art_lotes.id_vend = vt.ACCOUNTNUM "
                    + " INNER JOIN RETAILLABORATORIOS RL ON RL.ITEMID = ret.ITEMID "
                    + " INNER JOIN LABTABLE LT ON RL.FABRICANTE = LT.ACCOUNTNUM "
                    + "WHERE fecha_caducidad BETWEEN GETDATE()  AND DATEADD(MONTH, 1, GETDATE()) order by fecha_caducidad";

            Connection conn = ConexionSQLServer.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // mapear objetos
                ArticulosPorLotes art = new ArticulosPorLotes();
                art.setItemId(rs.getString("itemid"));
                art.setSap(rs.getString("sap"));
                art.setFechaCaducidad(rs.getDate("fecha_caducidad"));
                art.setLote(rs.getString("lote"));
                art.setDescripcion(rs.getString("descripcion"));
                art.setObservacion(rs.getString("observacion"));
                art.setCantidad(rs.getInt("cantidad"));
                art.setPrecio_etiquetado(rs.getDouble("precio_etiquetado"));
                art.setVendedor(rs.getString("proveedor"));
                art.setLaboratorio(rs.getString("laboratorio"));
                art.setRegistro_sanitario(rs.getString("registro"));
                art.setDevolutivo(rs.getString("devolutivo"));
                art.setFechaRegistro(rs.getDate("fecha_registro"));

                arts.add(art);
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

        return arts;

    }

    public List<ArticulosPorLotes> obtenerProductosPorFecha3m() {

        List<ArticulosPorLotes> arts = new ArrayList<>();
        //Traer todos los registros de la vista
        try {
            // Obtener todos los registros de todas las farmacias
            String sql = "SELECT ret.itemid as itemid, ret.SEARCHKEYWORDS AS sap, art_lotes.FECHA_CADUCIDAD AS fecha_caducidad, lote, ret.EXTENDEDDESCRIPTION AS descripcion, "
                    + " observacion, cantidad, precio_etiquetado, VT.name AS proveedor, art_lotes.reg_san registro, art_lotes.devolutivo as devolutivo, "
                    + " LT.name AS laboratorio, art_lotes.fecha_registro AS fecha_registro"
                    + " FROM TBL_COSAVAL_ARTICULOSPORLOTES art_lotes "
                    + " INNER JOIN RETAILITEM ret ON art_lotes.ITEMID = ret.ITEMID "
                    + " INNER JOIN VENDTABLE VT ON art_lotes.id_vend = vt.ACCOUNTNUM "
                    + " INNER JOIN RETAILLABORATORIOS RL ON RL.ITEMID = ret.ITEMID "
                    + " INNER JOIN LABTABLE LT ON RL.FABRICANTE = LT.ACCOUNTNUM "
                    + "WHERE fecha_caducidad BETWEEN  DATEADD(MONTH, 1, GETDATE())  AND DATEADD(MONTH, 3, GETDATE()) order by FECHA_CADUCIDAD";

            Connection conn = ConexionSQLServer.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // mapear objetos
                ArticulosPorLotes art = new ArticulosPorLotes();
                art.setItemId(rs.getString("itemid"));
                art.setSap(rs.getString("sap"));
                art.setFechaCaducidad(rs.getDate("fecha_caducidad"));
                art.setLote(rs.getString("lote"));
                art.setDescripcion(rs.getString("descripcion"));
                art.setObservacion(rs.getString("observacion"));
                art.setCantidad(rs.getInt("cantidad"));
                art.setPrecio_etiquetado(rs.getDouble("precio_etiquetado"));
                art.setVendedor(rs.getString("proveedor"));
                art.setLaboratorio(rs.getString("laboratorio"));
                art.setRegistro_sanitario(rs.getString("registro"));
                art.setDevolutivo(rs.getString("devolutivo"));
                art.setFechaRegistro(rs.getDate("fecha_registro"));

                arts.add(art);
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

        return arts;

    }

    public List<ArticulosPorLotes> obtenerProductosPorFecha6m() {

        List<ArticulosPorLotes> arts = new ArrayList<>();
        //Traer todos los registros de la vista
        try {
            // Obtener todos los registros de todas las farmacias
            String sql = "SELECT ret.itemid as itemid, ret.SEARCHKEYWORDS AS sap, art_lotes.FECHA_CADUCIDAD AS fecha_caducidad, lote, ret.EXTENDEDDESCRIPTION AS descripcion, "
                    + " observacion, cantidad, precio_etiquetado, VT.name AS proveedor, art_lotes.reg_san registro, art_lotes.devolutivo as devolutivo, "
                    + " LT.name AS laboratorio, art_lotes.fecha_registro AS fecha_registro"
                    + " FROM TBL_COSAVAL_ARTICULOSPORLOTES art_lotes "
                    + " INNER JOIN RETAILITEM ret ON art_lotes.ITEMID = ret.ITEMID "
                    + " INNER JOIN VENDTABLE VT ON art_lotes.id_vend = vt.ACCOUNTNUM "
                    + " INNER JOIN RETAILLABORATORIOS RL ON RL.ITEMID = ret.ITEMID "
                    + " INNER JOIN LABTABLE LT ON RL.FABRICANTE = LT.ACCOUNTNUM "
                    + "WHERE fecha_caducidad BETWEEN  DATEADD(MONTH, 3, GETDATE())  AND DATEADD(MONTH, 6, GETDATE()) order by FECHA_CADUCIDAD";

            Connection conn = ConexionSQLServer.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // mapear objetos
                ArticulosPorLotes art = new ArticulosPorLotes();
                art.setItemId(rs.getString("itemid"));
                art.setSap(rs.getString("sap"));
                art.setFechaCaducidad(rs.getDate("fecha_caducidad"));
                art.setLote(rs.getString("lote"));
                art.setDescripcion(rs.getString("descripcion"));
                art.setObservacion(rs.getString("observacion"));
                art.setCantidad(rs.getInt("cantidad"));
                art.setPrecio_etiquetado(rs.getDouble("precio_etiquetado"));
                art.setVendedor(rs.getString("proveedor"));
                art.setLaboratorio(rs.getString("laboratorio"));
                art.setRegistro_sanitario(rs.getString("registro"));
                art.setDevolutivo(rs.getString("devolutivo"));
                art.setFechaRegistro(rs.getDate("fecha_registro"));

                arts.add(art);
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

        return arts;

    }

    public List<ArticulosPorLotes> obtenerProductosPorFecha8m() {

        List<ArticulosPorLotes> arts = new ArrayList<>();
        //Traer todos los registros de la vista
        try {
            // Obtener todos los registros de todas las farmacias
            String sql = "SELECT ret.itemid as itemid, ret.SEARCHKEYWORDS AS sap, art_lotes.FECHA_CADUCIDAD AS fecha_caducidad, lote, ret.EXTENDEDDESCRIPTION AS descripcion, "
                    + " observacion, cantidad, precio_etiquetado, VT.name AS proveedor, art_lotes.reg_san registro, art_lotes.devolutivo as devolutivo, "
                    + " LT.name AS laboratorio, art_lotes.fecha_registro AS fecha_registro"
                    + " FROM TBL_COSAVAL_ARTICULOSPORLOTES art_lotes "
                    + " INNER JOIN RETAILITEM ret ON art_lotes.ITEMID = ret.ITEMID "
                    + " INNER JOIN VENDTABLE VT ON art_lotes.id_vend = vt.ACCOUNTNUM "
                    + " INNER JOIN RETAILLABORATORIOS RL ON RL.ITEMID = ret.ITEMID "
                    + " INNER JOIN LABTABLE LT ON RL.FABRICANTE = LT.ACCOUNTNUM "
                    + "WHERE fecha_caducidad BETWEEN  DATEADD(MONTH, 6, GETDATE())  AND DATEADD(MONTH, 8, GETDATE()) order by FECHA_CADUCIDAD";

            Connection conn = ConexionSQLServer.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // mapear objetos
                ArticulosPorLotes art = new ArticulosPorLotes();
                art.setItemId(rs.getString("itemid"));
                art.setSap(rs.getString("sap"));
                art.setFechaCaducidad(rs.getDate("fecha_caducidad"));
                art.setLote(rs.getString("lote"));
                art.setDescripcion(rs.getString("descripcion"));
                art.setObservacion(rs.getString("observacion"));
                art.setCantidad(rs.getInt("cantidad"));
                art.setPrecio_etiquetado(rs.getDouble("precio_etiquetado"));
                art.setVendedor(rs.getString("proveedor"));
                art.setLaboratorio(rs.getString("laboratorio"));
                art.setRegistro_sanitario(rs.getString("registro"));
                art.setDevolutivo(rs.getString("devolutivo"));
                art.setFechaRegistro(rs.getDate("fecha_registro"));

                arts.add(art);
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

        return arts;

    }

    public List<ArticulosPorLotes> obtenerProductoParaModificacion(String itemId, String lote, String fecha) {

        List<ArticulosPorLotes> arts = new ArrayList<>();
        //Traer todos los registros de la vista
        try {
            // Obtener todos los registros de todas las farmacias
            String sql = "SELECT ret.itemid as itemid, ret.SEARCHKEYWORDS AS sap, art_lotes.FECHA_CADUCIDAD AS fecha_caducidad, lote, ret.EXTENDEDDESCRIPTION AS descripcion, "
                    + " observacion, cantidad, precio_etiquetado, VT.name AS proveedor, art_lotes.reg_san registro, art_lotes.devolutivo as devolutivo, "
                    + " LT.name AS laboratorio, art_lotes.fecha_registro AS fecha_registro "
                    + " FROM TBL_COSAVAL_ARTICULOSPORLOTES art_lotes "
                    + " INNER JOIN RETAILITEM ret ON art_lotes.ITEMID = ret.ITEMID "
                    + " INNER JOIN VENDTABLE VT ON art_lotes.id_vend = vt.ACCOUNTNUM "
                    + " INNER JOIN RETAILLABORATORIOS RL ON RL.ITEMID = ret.ITEMID "
                    + " INNER JOIN LABTABLE LT ON RL.FABRICANTE = LT.ACCOUNTNUM "
                    + "WHERE RET.itemId = ? AND art_lotes.lote = ? AND art_lotes.fecha_caducidad = ?";

            Connection conn = ConexionSQLServer.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, itemId);
            stmt.setString(2, lote);
            stmt.setString(3, fecha);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // mapear objetos
                ArticulosPorLotes art = new ArticulosPorLotes();
                art.setItemId(rs.getString("itemid"));
                art.setSap(rs.getString("sap"));
                art.setFechaCaducidad(rs.getDate("fecha_caducidad"));
                art.setLote(rs.getString("lote"));
                art.setDescripcion(rs.getString("descripcion"));
                art.setObservacion(rs.getString("observacion"));
                art.setCantidad(rs.getInt("cantidad"));
                art.setPrecio_etiquetado(rs.getDouble("precio_etiquetado"));
                art.setVendedor(rs.getString("proveedor"));
                art.setLaboratorio(rs.getString("laboratorio"));
                art.setRegistro_sanitario(rs.getString("registro"));
                art.setDevolutivo(rs.getString("devolutivo"));
                art.setFechaRegistro(rs.getDate("fecha_registro"));

                arts.add(art);
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

        return arts;

    }

}
