/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
import java.sql.PreparedStatement;

/**
 *
 * @author Hector Mejia IT
 */
@WebServlet(name = "EliminarProductoServlet", urlPatterns = {"/EliminarProductoServlet"})
public class EliminarProductoServlet extends HttpServlet {

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
            out.println("<title>Servlet EliminarProductoServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EliminarProductoServlet at " + request.getContextPath() + "</h1>");
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
        // Obtener los parámetros del formulario
        String itemId = request.getParameter("itemId");
        String descripcion = request.getParameter("descripcion");
        String id_lab = request.getParameter("id_lab");
        String id_vend = request.getParameter("id_vend");
        String cantidad = request.getParameter("cantidad");
        String precio_etiquetado = request.getParameter("precio_etiquetado");
        String observacion = request.getParameter("observacion");
        String lote = request.getParameter("lote");
        String fechaCaducidad = request.getParameter("fechaCaducidad");

        // Ejecutar la lógica para actualizar los datos en la base de datos
        try {

            // Establecer la conexión a la base de datos y preparar la consulta SQL
            PreparedStatement pst = null;
            Connection conn = ConexionSQLServer.getConnection();
            String query = "DELETE TBL_COSAVAL_ARTICULOSPORLOTES WHERE itemId=? AND lote = ?";
            pst = conn.prepareStatement(query);

            // Establecer los valores en la consulta preparada
            pst.setString(1, itemId);
            pst.setString(2, lote);

            // Ejecutar la consulta
            pst.executeUpdate();

            // Cerrar la conexión y redirigir a una página de éxito o mostrar un mensaje
            conn.close();
            request.setAttribute("mensaje", "¡El producto se ha sido Eliminado correctamente!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/exito.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "¡No se puede Eliminar el producto: " + descripcion + " con código: " + itemId + " y lote: " + lote + ".");
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
