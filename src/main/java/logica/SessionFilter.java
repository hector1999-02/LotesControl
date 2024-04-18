
package logica;
import jakarta.servlet.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Verificar si la solicitud es una solicitud HTTP
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            // Verificar si la URL solicitada no es el índice o el servlet para cerrar sesión
            String requestURI = httpRequest.getRequestURI();
            if (!requestURI.endsWith("/index.jsp") && !requestURI.endsWith("/CerrarSesionServlet")) {
                // Verificar si la sesión está activa
                HttpSession session = httpRequest.getSession(false);
                if (session == null) {
                    // La sesión no está activa, redirigir al índice
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.jsp");
                    return;
                }
            }
        }

        // Continuar con la cadena de filtros (o el servlet)
        chain.doFilter(request, response);
    }

    // Implementar los métodos restantes de la interfaz Filter (init y destroy)
}