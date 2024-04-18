/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionSQLServer {
    //Cadena de Conexíon local
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=HQ_CosavalLS_local;integratedSecurity=false;encrypt=false;trustServerCertificate=false;user=sa;password=sa$2000;";
    //Cadena de conexión a servidor
//    private static final String DB_URL = "jdbc:sqlserver://192.168.50.9;databaseName=HQ_CosavalLS;integratedSecurity=false;encrypt=false;trustServerCertificate=false;user=user_report;password=C0ntraseñaC0mpleja!;";
    
    //Usuario de reportería para que tenga acceso de lectura 
    //user=user_reporteria, contraseña: C0ntraseñaC0mpleja!
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException ex) {
            throw new SQLException("Error al conectar a la base de datos", ex);
        }
    }
    public static boolean probarConexion() {
        try (Connection conn = getConnection()) {
            return true; // La conexión se estableció exitosamente
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false; // Hubo un error en la conexión
        }
    }
    
}
