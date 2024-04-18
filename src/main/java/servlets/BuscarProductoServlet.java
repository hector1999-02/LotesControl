/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.PrintWriter;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import logica.ArticulosPorLotes;
import logica.ArticulosPorLotesDAO;
import logica.Laboratorios;
import logica.LaboratoriosDAO;
import logica.Proveedores;
import logica.ProveedoresDAO;
import logica.RetailItem;
import logica.RetailItemDAO;

/**
 *
 * @author Hector Mejia IT
 */
@WebServlet(name = "BuscarProductoServlet", urlPatterns = {"/BuscarProductoServlet"})
public class BuscarProductoServlet extends HttpServlet {

    private RetailItemDAO ProductoDAO;
    private ArticulosPorLotesDAO Art_lotes;
    private LaboratoriosDAO Lab;
    private ProveedoresDAO prov;

    @Override
    public void init() throws ServletException {
        ProductoDAO = new RetailItemDAO();
        Art_lotes = new ArticulosPorLotesDAO();
        Lab = new LaboratoriosDAO();
        prov = new ProveedoresDAO();
    }

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
        //para actualizar necesitamos una clase art_por_lotes
        String itemID = request.getParameter("item_id");
        String lote = request.getParameter("lote");
        String nombreProv = request.getParameter("nombreProv");
        String nombreLab = request.getParameter("nameLab");

        List<Laboratorios> labs = Lab.obtenerLaboratorios(nombreLab);
        List<Proveedores> provs = prov.obtenerProveedores(nombreProv);
        
        List<ArticulosPorLotes> art_por_lotes = Art_lotes.obtenerProductoParaModificacion(itemID, lote);
        
        request.setAttribute("labs", labs);
        request.setAttribute("provs", provs);
        request.setAttribute("art_por_lotes", art_por_lotes);
        request.getRequestDispatcher("ActualizarProducto.jsp").forward(request, response);
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

        String itemID = request.getParameter("item_id");

        List<RetailItem> retails = ProductoDAO.obtenerProducto(itemID);
        request.setAttribute("retails", retails);
        request.getRequestDispatcher("controlLotes.jsp").forward(request, response);

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
