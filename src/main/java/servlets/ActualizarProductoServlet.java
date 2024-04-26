/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
import java.sql.PreparedStatement;

/**
 *
 * @author Hector Mejia IT
 */
@WebServlet(name = "ActualizarProductoServlet", urlPatterns = {"/ActualizarProductoServlet"})
public class ActualizarProductoServlet extends HttpServlet {

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
        response.sendRedirect("index.jsp");
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
        // Obtener los parámetros del formulario
        String itemId = request.getParameter("itemId");
        String descripcion = request.getParameter("descripcion");
//        String id_lab = request.getParameter("id_lab");
        String id_vend = request.getParameter("id_vend");
        String cantidad = request.getParameter("cantidad");
        String precio_etiquetado = request.getParameter("precio_etiquetado");
        String observacion = request.getParameter("observacion");
        String lote_nuevo = request.getParameter("lote");
        String fechaCaducidad = request.getParameter("fechaCaducidad");
        String lote_ant = request.getParameter("lote_ant");
        String reg_san = request.getParameter("reg_san");
        String devolutivo = request.getParameter("devolutivo");
        String fechaRegistro = request.getParameter("fechaRegistro");
        String fechaCaducidadNuevo = request.getParameter("fechaCaducidadActualizable");
//ojoooooooooooooooooooooooooooooooooo ver la parte de fecha anterior para no confundir con la fecha de caducidad actual para hacer el filtro
        // Ejecutar la lógica para actualizar los datos en la base de datos
        try {

            // Establecer la conexión a la base de datos y preparar la consulta SQL
            PreparedStatement pst = null;
            Connection conn = ConexionSQLServer.getConnection();
            String query = "UPDATE TBL_COSAVAL_ARTICULOSPORLOTES SET id_vend=?, cantidad=?, precio_etiquetado=?, observacion=?, lote=?, fecha_Caducidad=?, reg_san=?, devolutivo=?, fecha_registro=? WHERE itemId=? AND lote = ? AND fecha_caducidad = ?";
            pst = conn.prepareStatement(query);

            // Establecer los valores en la consulta preparada
//            pst.setString(1, id_lab);
            pst.setString(1, id_vend);
            pst.setString(2, cantidad);
            pst.setString(3, precio_etiquetado);
            pst.setString(4, observacion);
            pst.setString(5, lote_nuevo);
            pst.setString(6, fechaCaducidadNuevo);
            pst.setString(7, reg_san); //se agrega registro sanitario, ver en el actualizable
            pst.setString(8, devolutivo); //se agrega devolutivo, ver en el actualizable
            pst.setString(9, fechaRegistro); //se agrega devolutivo, ver en el actualizable
            pst.setString(10, itemId);
            pst.setString(11, lote_ant);
            pst.setString(12, fechaCaducidad);

            // Ejecutar la consulta
            pst.executeUpdate();

            // Cerrar la conexión y redirigir a una página de éxito o mostrar un mensaje
            conn.close();
            request.setAttribute("mensaje", "¡El producto: " + descripcion + " con código: " + itemId + " y lote: " + lote_nuevo + ", ha sido actualizado correctamente!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/exito.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "¡No se puede actualizar el producto: " + descripcion + " con código: " + itemId + " y lote: " + lote_ant + ".");
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
