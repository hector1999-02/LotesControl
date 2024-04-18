package servlets;

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


@WebServlet(name = "ControlLotesServlet", urlPatterns = {"/ControlLotesServlet"})
public class ControlLotesServlet extends HttpServlet {

    private ArticulosPorLotesDAO ArticuloDAO;

    @Override
    public void init() throws ServletException {
        ArticuloDAO = new ArticulosPorLotesDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControlLotesServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlLotesServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("controlLotes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //         traer los registros que vencen en 6 meses
        //codigo, descripcion, lote y fecha de caducidad
        
        List<ArticulosPorLotes> artsVenc= ArticuloDAO.obtenerProductosVencidos();
        request.setAttribute("artsVenc", artsVenc);
        
        //Vencen entre 0 a 1 mes
        List<ArticulosPorLotes> arts1m = ArticuloDAO.obtenerProductosPorFecha1m();
        request.setAttribute("artsPorVencer1", arts1m);
        
        //Vencen entre 1 a 3 meses
        List<ArticulosPorLotes> arts3m = ArticuloDAO.obtenerProductosPorFecha3m();
        request.setAttribute("artsPorVencer3", arts3m);
        
        //Vencen entre 3 a 6 meses
        List<ArticulosPorLotes> arts6m = ArticuloDAO.obtenerProductosPorFecha6m();
        request.setAttribute("artsPorVencer6", arts6m);
        
        List<ArticulosPorLotes> arts8m = ArticuloDAO.obtenerProductosPorFecha8m();
        request.setAttribute("artsPorVencer8", arts8m);
        
        //         traer los registros que vencen en 3 meses

        
//         traer los registros que vencen en 1 mes
        request.getRequestDispatcher("DetalleArticulosLotes.jsp").forward(request, response);
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
