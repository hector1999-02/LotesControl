<%@page import="logica.ReportesDocsFiscales"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@page import ="java.util.List" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<% // valida que la variable usuario no venga nula en caso de venir nula regresa al index
if(session.getAttribute("userId") == null){
request.getRequestDispatcher("index.jsp").forward(request, response);
}
%> 


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalle Reporte</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
              rel="stylesheet" 
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
              crossorigin="anonymous">
        <link rel="stylesheet" href="stylesReport.css"/>
        <link rel="canonical shortcut icon" href="https://farmafacil.attachments.freshservice.com/data/helpdesk/attachments/production/22001435201/logo/logos_farmafacil-07.png?Expires=1709052529&Signature=b0Vt2OOAZeG7u8Vca0k3ryaUd2PyWyQ2y9iN3TCLEWN0fnUGc~VYJ7ZRIC5XL-WUEw2G-CFAijs25b8OX78LzBUX~EvRvEvwWVQ-wmyZsRVYfIVwbAIYUsF5XZ-Yx63j9t9XijS59CfvDKFCOBlavYDQ70TZp54d8UdZYnOBMpLHDCPfVgJHN3NyQfI324QkjGTrll~tmJru8Uqu9pRguJ67SiIJ-N2g7VoQz9Tsi~Z0zYE2EX~Tm6QRsKIelPhOe3CwhuHuklX8Zt5t6Ms8siaeNcG7PjzKu2D1C-Yt64Qco8wi4V8trefNyZkhqlHvJPTTijILiJUDLtOjpxY6HQ__&Key-Pair-Id=APKAIPHBXWY2KT5RCMPQ" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-...tu-integrity-hash..." crossorigin="anonymous" />
        <script src="js/controladorReporte.js"></script>
        <!--        Biblioteca para la exportación de excel-->
        <script src="https://unpkg.com/xlsx/dist/xlsx.full.min.js"></script>
    </head>
    <body>
        <div>
            <a href="userinfo.jsp">
                <img src="https://placetopay-static-prod-bucket.s3.us-east-2.amazonaws.com/bancatlan-hn/microsites/images/lKvG0Fmj74XHXYrfYr5RMfi6qGLP5zg3hF1uzgm6.png" width="200px" height="auto"/>
            </a>
        </div>
        <%
            String defaultDate = (String)request.getAttribute("defaultDate");
            String defaultPharmacy = (String)request.getAttribute("defaultPharmacy");
            String nombreFarmacia = (String)request.getAttribute("nombreFarmacia");

        %>
        <div>
            <div class="d-flex justify-content-around mb-3">
                <div>
                    <form id="miFormulario" action="ReportesServlet" method="POST"> 
                        <input type="date" name="fecha" value="<%= defaultDate %>">
                        <input type="hidden" name="codigo_farmacia" value="<%= defaultPharmacy %>">
                        <input type="hidden" name="nombre_farmacia" value="<%= nombreFarmacia %>">
                        <button type="submit" class="buscar-btn">Buscar</button>
                        <button type="button" onclick="exportarExcel()" class=""><i class="fas fa-file-excel"></i> Exportar a Excel</button>
                    </form>
                </div>

                <div>
                    <span>Nombre de Almacén / Farmacia: <%= defaultPharmacy %> <strong><%= nombreFarmacia %></strong></span>
                </div>

                <div>
                    <form action="CerrarSesionServlet" method="post">
                        <input type="submit" value="Cerrar Sesión" class="cerrar-sesion-btn">
                    </form>
                </div>
            </div>

            <div class="table-wrapper-scroll-y my-custom-scrollbar">


                <table id="miTabla" class="table table-bordered table-hover table-sm table-striped mb-0">
                    <thead>
                        <tr>
                            <th scope="col" class="text-center"># Recibo</th>
                            <th scope="col" class="text-center">Nombre del Documento/Movimiento</th>
                            <th scope="col" class="text-center">Referencia</th>
                            <th scope="col" class="text-center"># Documento Impreso (Fiscal)</th>
                            <th scope="col" class="text-center"># Fecha</th>
                            <th scope="col" class="text-center"># Código de Tienda</th>
                            <th scope="col" class="text-center"># Nombre de Tienda</th>

                        </tr>
                    </thead>

                    <!--                //Implementar y traer los datos de la vista -->
                    <tbody class="scrollable-tbody">

                        <%
                            List<ReportesDocsFiscales> reportes = (List<ReportesDocsFiscales>) request.getAttribute("reportes");
                            for (ReportesDocsFiscales reporte : reportes) {
                        %>
                        <tr>
                            <td class="text-center"><%= reporte.getNinterno()%></td>
                            <td class="text-center"><%= reporte.getTdocumento()%></td>
                            <td class="text-center"><%= reporte.getTreferencia()%></td>
                            <td class="text-center"><%= reporte.getDfiscal()%></td>
                            <td class="text-center"><%= reporte.getFecha()%></td>
                            <td class="text-center"><%= reporte.getTienda()%></td>
                            <td class="text-center"><%= reporte.getName()%></td>

                        </tr>
                        <% }%>         
                    </tbody>
                </table>


            </div>


        </div>
    </body>
</html>
