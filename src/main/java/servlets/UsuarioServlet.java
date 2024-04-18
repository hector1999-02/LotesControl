package servlets;

import conexionSQL.ConexionSQLServer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;



@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UsuarioServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UsuarioServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
//        List<Usuarios> usuarios = usuarioDAO.obtenerUsuarios();
//        request.setAttribute("usuarios", usuarios);

//      obtener conexión a sql server 
        String usuario = request.getParameter("usuario");
        String pass = request.getParameter("pass"); 
        String url;
        url = "SELECT usuario, pass ,ID_Sucursal FROM Usuarios WHERE Usuario='"+usuario+"'";

        // Realizar la inserción en la base de datos
        try {
            // Establecer la conexión a la base de datos
            Connection conn = ConexionSQLServer.getConnection();

            // Preparar la sentencia SQL parametrizada para la inserción
            PreparedStatement stmt = conn.prepareStatement(url);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                // Existe el usuario
                //
                String usuario_sql = rs.getString("usuario");
                String pass_sql = rs.getString("pass");
                String idsucursal_sql = rs.getString("ID_Sucursal");
                
                
            }else{
                //Hacer un alert donde muestre que el usuaio no existe
                
            }
        

            // Ejecutar la sentencia SQL
            stmt.executeUpdate();

            try {
                // Cerrar la conexión y la declaración
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Redireccionar a una página de confirmación
            response.sendRedirect("confirmacionCreacionUsuario.jsp");

        } catch (SQLException e) {
            // Manejar cualquier error de la base de datos
            e.printStackTrace();
            // Redireccionar a una página de error
//            response.sendRedirect("error.jsp");
        }

        //De la request traeme la session, como el Id del usuario.
//        HttpSession misesion = request.getSession();
//        misesion.setAttribute("usuarios", usuarios);

        //Enviar sesión para que los usuarios sean mostrados
        request.getRequestDispatcher("reporte.jsp").forward(request, response);

        response.sendRedirect("reporte.jsp");
        
        
        
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String Usuario = request.getParameter("Usuario");
        String pass = request.getParameter("pass");
        String Nombre = request.getParameter("Nombre");
        String ID_Sucursal = request.getParameter("Nombre");
        // Obtener los parámetros del formulario
        String fechaRegistroString = request.getParameter("fecha_registro");

        // Convertir el valor a LocalDateTime
        LocalDateTime fechaRegistro = LocalDateTime.parse(fechaRegistroString);

        // Realizar la inserción en la base de datos
        try {
            // Establecer la conexión a la base de datos
            Connection conn = ConexionSQLServer.getConnection();

            // Preparar la sentencia SQL parametrizada para la inserción
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO   Usuarios "
                    + "(Usuario, pass, Nombre, ID_Sucursal, ID_Nivel, uuid) VALUES(?, ?, ?, ?, ?, ?)");
            stmt.setString(1,Usuario);
            stmt.setString(2, pass);
            stmt.setString(3, Nombre);
            stmt.setObject(4, ID_Sucursal);
            stmt.setObject(5, "2");
            stmt.setObject(6, "123");
            // Ejecutar la sentencia SQL
            stmt.executeUpdate();

            try {
                // Cerrar la conexión y la declaración
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Redireccionar a una página de confirmación
            response.sendRedirect("index.jsp");

        } catch (SQLException e) {
            // Manejar cualquier error de la base de datos
            e.printStackTrace();
            // Redireccionar a una página de error
//            response.sendRedirect("error.jsp");
        }
        
        
        
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
