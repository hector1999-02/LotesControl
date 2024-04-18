package servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import logica.ReportesDocsFiscales;
import logica.ReportesDocsFiscalesDAO;

/**
 *
 * @author IT
 */
@WebServlet(name = "ReportesServlet", urlPatterns = {"/ReportesServlet"})
public class ReportesDocsFiscalesServlet extends HttpServlet {

    private ReportesDocsFiscalesDAO reporteDAO;

    @Override
    public void init() throws ServletException {
        reporteDAO = new ReportesDocsFiscalesDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //int id = Integer.parseInt(request.getParameter("id"));
        String codigofarmacia = request.getParameter("codigo_farmacia");
        String nombrefarmacia = request.getParameter("nombre_farmacia");
        String fechaParam = request.getParameter("fecha");
        // Si la fecha viene null, que es lo por defecto, asignamos a la fecha el valor del día anterior
        if (null == fechaParam) {
            LocalDate now = LocalDate.now();
            LocalDate yesterday = now.minusDays(1);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            fechaParam = yesterday.format(formatter);

            List<ReportesDocsFiscales> reportes = reporteDAO.obtenerReportesPorFechaYSucursal(fechaParam, codigofarmacia);
            request.setAttribute("reportes", reportes);
            request.setAttribute("defaultDate", fechaParam);
            request.setAttribute("defaultPharmacy", codigofarmacia);
            request.setAttribute("nombreFarmacia", nombrefarmacia);
            request.getRequestDispatcher("reporte.jsp").forward(request, response);

        } else // Si es distinto de null, asignamos a la fecha el valor del día anterior
        {
            switch (fechaParam) {
                case "": {
                    //Traer del dia anterior
                    List<ReportesDocsFiscales> reportes = reporteDAO.obtenerReportesPorFechaYSucursal(fechaParam, codigofarmacia);
                    request.setAttribute("reportes", reportes);
                    request.getRequestDispatcher("reporte.jsp").forward(request, response);
//              response.sendRedirect("reporte.jsp");
                    break;
                }
                default: {
                    // Si la fecha es distinto de null, cuando entra al jsp de reportes y que el usuario asigna, entonces lotoma del parametro
                    List<ReportesDocsFiscales> reportes = reporteDAO.obtenerReportesPorFechaYSucursal(fechaParam, codigofarmacia);
                    request.setAttribute("reportes", reportes);
                    request.setAttribute("defaultDate", fechaParam);
                    request.setAttribute("defaultPharmacy", codigofarmacia);
                    request.setAttribute("nombreFarmacia", nombrefarmacia);
                    request.getRequestDispatcher("reporte.jsp").forward(request, response);
                    break;
                }
            }
        }
    }

    //    Probar si los parametros no se muestran con el metodo Post
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigofarmacia = request.getParameter("codigo_farmacia");
        String nombrefarmacia = request.getParameter("nombre_farmacia");
        String fechaParam = request.getParameter("fecha");
        List<ReportesDocsFiscales> reportes;
        // Si la fecha viene null, que es lo por defecto, asignamos a la fecha el valor del día anterior
        if (null == fechaParam) {
            // Si codigofarmacia de farmacia es igual='All@farmas'
            if ("All@farmas".equals(codigofarmacia)) {
                fechaParam = obtenerDiaAnterior();
                reportes = reporteDAO.obtenerReportesPorFecha(fechaParam);
                request.setAttribute("reportes", reportes);
                request.setAttribute("defaultDate", fechaParam);
                request.setAttribute("defaultPharmacy", codigofarmacia);
                request.setAttribute("nombreFarmacia", nombrefarmacia);
                request.getRequestDispatcher("reporte.jsp").forward(request, response);
            } else {
                fechaParam = obtenerDiaAnterior();
                reportes = reporteDAO.obtenerReportesPorFechaYSucursal(fechaParam, codigofarmacia);
                request.setAttribute("reportes", reportes);
                request.setAttribute("defaultDate", fechaParam);
                request.setAttribute("defaultPharmacy", codigofarmacia);
                request.setAttribute("nombreFarmacia", nombrefarmacia);
                request.getRequestDispatcher("reporte.jsp").forward(request, response);
            }

        } else {

            switch (fechaParam) {
                case "": {
                    //Traer del dia anterior
                    // Si codigofarmacia de farmacia es igual='All@farmas'
                    if ("All@farmas".equals(codigofarmacia)) {
                        reportes = reporteDAO.obtenerReportesPorFecha(fechaParam);
                        request.setAttribute("reportes", reportes);
                        request.setAttribute("defaultDate", fechaParam);
                        request.setAttribute("defaultPharmacy", codigofarmacia);
                        request.setAttribute("nombreFarmacia", nombrefarmacia);
                        request.getRequestDispatcher("reporte.jsp").forward(request, response);
                    } else {
                        reportes = reporteDAO.obtenerReportesPorFechaYSucursal(fechaParam, codigofarmacia);
                        request.setAttribute("reportes", reportes);
                        request.getRequestDispatcher("reporte.jsp").forward(request, response);

                    }
//                  response.sendRedirect("reporte.jsp");
                    break;
                }
                default: {
                    // Si la fecha es distinto de null, cuando entra al jsp de reportes y que el usuario asigna, entonces lo toma del parametro
                    if ("All@farmas".equals(codigofarmacia)) {
                        reportes = reporteDAO.obtenerReportesPorFecha(fechaParam);
                        request.setAttribute("reportes", reportes);
                        request.setAttribute("defaultDate", fechaParam);
                        request.setAttribute("defaultPharmacy", codigofarmacia);
                        request.setAttribute("nombreFarmacia", nombrefarmacia);
                        request.getRequestDispatcher("reporte.jsp").forward(request, response);
                    } else {
                        reportes = reporteDAO.obtenerReportesPorFechaYSucursal(fechaParam, codigofarmacia);
                        request.setAttribute("reportes", reportes);
                        request.setAttribute("defaultDate", fechaParam);
                        request.setAttribute("defaultPharmacy", codigofarmacia);
                        request.setAttribute("nombreFarmacia", nombrefarmacia);
                        request.getRequestDispatcher("reporte.jsp").forward(request, response);
                        break;
                    }

                }
            }

        }

    }

 
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public String obtenerDiaAnterior() {
        String ayer;
        LocalDate now = LocalDate.now();
        LocalDate yesterday = now.minusDays(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        ayer = yesterday.format(formatter);
        return ayer;
    }

}
