package servlets;

import conexionSQL.ConexionSQLServer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("username");
        String password = request.getParameter("password");

        int userId = authenticate(nombre, password);
//        int userId = 2;
        if (userId != -1 && userId != 0) {
            // Aquí puedes agregar la lógica para obtener el tipo de usuario
            String tipoUsuario = obtenerTipoUsuario(userId); // obtener el tipo de usuario

            HttpSession session = request.getSession();
            session.removeAttribute("error");
            session.setAttribute("userId", userId);
            session.setAttribute("tipoUsuario", tipoUsuario); // Guarda el tipo de usuario en la sesión

            if ("admin".equals(tipoUsuario)) {
                response.sendRedirect("userinfo.jsp"); // Redirige a la página de administrador
            } else {
                response.sendRedirect("userinfoReporte.jsp"); // Redirige a la página de usuario normal
            }
      

        } else {
            HttpSession session = request.getSession();
            session.setAttribute("error", "Credenciales inválidas");
            response.sendRedirect("index.jsp"); // Redirige de nuevo al formulario de login si la autenticación falla       
        }
    }

    // Método para autenticar al usuario con la base de datos y obtener el ID del usuario
    private int authenticate(String nombre, String password) {
        int userId = -1; // Valor predeterminado si la autenticación falla
        Connection con = null;
        CallableStatement statement = null;

        try {
            // Establecer la conexión con la base de datos

//            con = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=HQ_CosavalLS_local;integratedSecurity=false;encrypt=false;trustServerCertificate=false;user=sa;password=sa$2000;");
            con = ConexionSQLServer.getConnection();
            // Realizar la consulta para verificar el usuario y contraseña y obtener el ID
            statement = con.prepareCall("{CALL sp_verificarInicioSesion(?, ?, ?, ?, ?)}");

            statement.setString(1, nombre);
            statement.setString(2, password);
            statement.registerOutParameter(3, Types.INTEGER);
            statement.registerOutParameter(4, Types.INTEGER);
            statement.registerOutParameter(5, Types.VARCHAR);

            statement.execute();
            int res1 = statement.getInt(3);
            int res2 = statement.getInt(4);
            String res3 = statement.getString(5);
            if (res1 == 0) {
                return userId; //Credenciales invalidas
            } else {

                return res2; // Obtener el resultado del parámetro de salida

            }
        } catch (SQLException e) {
        } finally {
            // Cerrar recursos
            try {
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
            }
        }
        return userId;
    }

    private String obtenerTipoUsuario(int userId) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String tipoUsuario = ""; // Valor predeterminado

        try {
            con = ConexionSQLServer.getConnection(); // Obtener la conexión a la base de datos
            String query = "SELECT TipoUsuario FROM TBL_COSAVAL_users_reporteria WHERE ID = ?";
            statement = con.prepareStatement(query);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                tipoUsuario = resultSet.getString("TipoUsuario");
            }
        } catch (SQLException e) {
            // Manejar la excepción
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tipoUsuario;
    }

}
