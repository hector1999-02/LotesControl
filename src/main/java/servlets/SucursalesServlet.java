package servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import logica.Sucursales;
import logica.SucursalesDAO;

/**
 *
 * @author IT
 */
@WebServlet(name = "SucursalesServlet", urlPatterns = {"/SucursalesServlet"})
public class SucursalesServlet extends HttpServlet {

    private SucursalesDAO sucursalDAO;

    @Override
    public void init() throws ServletException {
        sucursalDAO = new SucursalesDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        List<Sucursales> sucursales = sucursalDAO.obtenerSucursales();
        request.setAttribute("sucursales", sucursales);

        //De la request traeme la session, como el Id del usuario.
        HttpSession misesion = request.getSession();
        misesion.setAttribute("sucursales", sucursales);

        //Enviar sesión para que los usuarios sean mostrados
        request.getRequestDispatcher("FormularioRegistro.jsp").forward(request, response);

        response.sendRedirect("FormularioRegistro.jsp");

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
        processRequest(request, response);
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
