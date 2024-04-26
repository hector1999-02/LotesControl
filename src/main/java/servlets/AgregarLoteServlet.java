/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import conexionSQL.ConexionSQLServer;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

/**
 *
 * @author Hector Mejia IT
 */
@WebServlet(name = "AgregarLoteServlet", urlPatterns = {"/AgregarLoteServlet"})
public class AgregarLoteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AgregarLoteServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AgregarLoteServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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

        String itemId = request.getParameter("itemId");
        String lote = request.getParameter("lote");
        String fechaCaducidad = request.getParameter("fechaCaducidad");
        String observacion = request.getParameter("observacion");
        String cantidad = request.getParameter("cantidad");
        String precio_etiquetado = request.getParameter("precio_etiquetado");
        String id_lab = request.getParameter("id_lab");
        String id_vend = request.getParameter("id_vend");
        String descripcion = request.getParameter("descripcion");
        String reg_san = request.getParameter("reg_san");
        String devolutivo = request.getParameter("devolutivo");
        //AGREGAR LA FECHA DE REGISTRO
        String fecha_reg = request.getParameter("fechaRegistro");
        
        
        
        

        // Convierte la fecha de caducidad a un objeto Date
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date fechaCaducidadDate;
//        try {
//            fechaCaducidadDate = (Date) dateFormat.parse(fechaCaducidad);
//        } catch (Exception e) {
//            // Manejo de excepción (puedes redirigir a una página de error)
//            response.sendRedirect("error.jsp");
//            return;
//        }
        // Realiza la inserción en la base de datos
        try {
            // Prepara la sentencia SQL para la inserción
            try ( // Establece la conexión a la base de datos (ajusta la URL, usuario y contraseña)
                    Connection conn = ConexionSQLServer.getConnection()) {
                // Prepara la sentencia SQL para la inserción
                String sql = "INSERT INTO TBL_COSAVAL_ARTICULOSPORLOTES (itemId, lote, fecha_Caducidad, observacion, cantidad , precio_etiquetado, id_vend, reg_san, devolutivo, fecha_registro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    // Establece los parámetros en la sentencia SQL
                    pstmt.setString(1, itemId);
                    pstmt.setString(2, lote);
                    pstmt.setString(3, fechaCaducidad);
                    pstmt.setString(4, observacion);
                    pstmt.setString(5, cantidad);
                    pstmt.setString(6, precio_etiquetado);
                    
                    pstmt.setString(7, id_vend);
                    pstmt.setString(8, reg_san);
                    pstmt.setString(9, devolutivo);
                    pstmt.setString(10, fecha_reg);

                    // Ejecuta la inserción
                    pstmt.executeUpdate();
                    // Cierra la conexión
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                }
            }

            // Redirige a una página de éxito o a la página principal
            // Después de la inserción en el servlet
            request.setAttribute("mensaje", "¡El producto: " + descripcion + " con código: " + itemId + " y lote: " + lote + ", se ha agregado correctamente!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/exito.jsp");
            dispatcher.forward(request, response);
        } catch (SQLServerException sqlException) {
            // Manejo de excepción específica para SQL Server
            int errorCode = sqlException.getErrorCode();
            if (errorCode == 2627) {
                // Violación de clave primaria
                // Realiza el manejo necesario, como redirigir a una página de error específica
                request.setAttribute("mensaje", "¡No se puede agregar el producto: " + descripcion + " con código: " + itemId + " y lote: " + lote + ", porque ya existe ese articulo con el mismo lote!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
                dispatcher.forward(request, response);
            } else {
                // Otro tipo de excepción de SQL Server
                // Realiza el manejo necesario, como redirigir a una página de error general
                request.setAttribute("mensaje", "No se ha podido completar el proceso, pongase en contacto con IT");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            // Manejo de excepciones generales
            request.setAttribute("mensaje", "Ocurrió un error para ingresar el articulo");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
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
