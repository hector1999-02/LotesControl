<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="conexionSQL.ConexionSQLServer"%>
<% // Valida que la variable userId y tipoUsuario no sean nulas para el usuario admin
if (session.getAttribute("userId") == null || session.getAttribute("tipoUsuario") == null) {
    request.getRequestDispatcher("index.jsp").forward(request, response);
} else {
    String tipoUsuario = (String) session.getAttribute("tipoUsuario");
    if (!"admin".equals(tipoUsuario)) { // Comparación de cadenas utilizando equals
        request.getRequestDispatcher("index.jsp").forward(request, response);
    } 
    }    // Usuario es admin, continuar con el código para admin
%>



<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Información de Farmacia</title>
        <link rel="stylesheet"
              href="
              https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
        <link rel="stylesheet" href="stylesReport.css"/>
        <link rel="canonical shortcut icon" href="https://farmafacil.attachments.freshservice.com/data/helpdesk/attachments/production/22001435201/logo/logos_farmafacil-07.png?Expires=1709052529&Signature=b0Vt2OOAZeG7u8Vca0k3ryaUd2PyWyQ2y9iN3TCLEWN0fnUGc~VYJ7ZRIC5XL-WUEw2G-CFAijs25b8OX78LzBUX~EvRvEvwWVQ-wmyZsRVYfIVwbAIYUsF5XZ-Yx63j9t9XijS59CfvDKFCOBlavYDQ70TZp54d8UdZYnOBMpLHDCPfVgJHN3NyQfI324QkjGTrll~tmJru8Uqu9pRguJ67SiIJ-N2g7VoQz9Tsi~Z0zYE2EX~Tm6QRsKIelPhOe3CwhuHuklX8Zt5t6Ms8siaeNcG7PjzKu2D1C-Yt64Qco8wi4V8trefNyZkhqlHvJPTTijILiJUDLtOjpxY6HQ__&Key-Pair-Id=APKAIPHBXWY2KT5RCMPQ" />

    </head>
    <body class="d-flex align-items-center justify-content-center" style="height: 100vh;">
        <div id="cerrar-sesion-div">
            <form action="CerrarSesionServlet" method="post">
                <input type="submit" value="Cerrar Sesión" class="cerrar-sesion-btn">
            </form>
        </div>
        <%
            // Obtén el ID del usuario de la sesión
            int userId = (int) session.getAttribute("userId");

       
            Connection con = null;
            PreparedStatement pst = null;
            ResultSet rs = null;

            try {
                con = ConexionSQLServer.getConnection();
                // Realizar la consulta para obtener la información del usuario por ID
                String query = "SELECT id, nombre, farmaciaId, farmaciaName FROM TBL_COSAVAL_users_reporteria WHERE id=?";
                pst = con.prepareStatement(query);
                pst.setInt(1, userId);
                rs = pst.executeQuery();

                // Muestra la información del usuario
                if (rs.next()) {
        %>

        <div class="text-center mx-auto">
            <h2>Bienvenido Usuario : <%= rs.getString("nombre") %></h2>
            <h3>Farmacia/Bodega: <%= rs.getString("farmaciaName") %> </h3>
            <h4>Codigo de Farmacia: <%= rs.getString("farmaciaId") %></h4>
            <span>Confirme que la información sea correcta</span>
            <br/>


            <%-- Mostrar enlace a la página de informes si la sesión está activa --%>
            <% if (session != null && session.getAttribute("userId") != null) {
            
            
            %>
            <!--            Esta funcionalidad está disponible, solamente que se está trabajando en el control de Lotes -->
            <!--            <form action="ReportesServlet" method="POST">
                             Luego cambiar a hidden 
                            <input type="hidden" name="id" value="<%= rs.getInt("id") %>">
                            <input type="hidden" name="codigo_farmacia" value="<%= rs.getString("farmaciaId") %>">
                            <input type="hidden" name="nombre_farmacia" value="<%= rs.getString("farmaciaName") %>">
                            <button type="submit" class="btn btn-success btn-lg mt-4" style="transition: background-color 0.3s;">Acceder a Reportes</button> 
                        </form>-->


            <form action="ControlLotesServlet" method="GET">
                <!-- Luego cambiar a hidden -->
                <input type="hidden" name="id" value="<%= rs.getInt("id") %>">
                <input type="hidden" name="codigo_farmacia" value="<%= rs.getString("farmaciaId") %>">
                <input type="hidden" name="nombre_farmacia" value="<%= rs.getString("farmaciaName") %>">
                <button type="submit" class="btn btn-info btn-lg mt-4" style="transition: background-color 0.3s;">Control de Lotes</button> 
            </form>
            <% 
                
            } 
            %>

        </div>


        <!-- Puedes mostrar más información según sea necesario -->
        <%
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Cerrar recursos
                try {
                    if (rs != null) rs.close();
                    if (pst != null) pst.close();
                    if (con != null) con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        %>

    </body>
</html>
