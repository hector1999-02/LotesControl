<%@ page import="logica.RetailItem"%>
<%@ page import="logica.Laboratorios"%>
<%@ page import="logica.ArticulosPorLotes"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.sql.SQLException" %>

<%
    // Validating user session
    if(session.getAttribute("userId") == null){
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalles de Articulos y sus Lotes</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <!-- Custom Styles -->
        <link rel="stylesheet" href="styles/styleControlLotes.css"/>
        <!-- Favicon -->
        <link rel="icon" type="image/png" sizes="32x32" href="https://farmafacil.attachments.freshservice.com/data/helpdesk/attachments/production/22001435201/logo/logos_farmafacil-07.png?Expires=1709052529&Signature=b0Vt2OOAZeG7u8Vca0k3ryaUd2PyWyQ2y9iN3TCLEWN0fnUGc~VYJ7ZRIC5XL-WUEw2G-CFAijs25b8OX78LzBUX~EvRvEvwWVQ-wmyZsRVYfIVwbAIYUsF5XZ-Yx63j9t9XijS59CfvDKFCOBlavYDQ70TZp54d8UdZYnOBMpLHDCPfVgJHN3NyQfI324QkjGTrll~tmJru8Uqu9pRguJ67SiIJ-N2g7VoQz9Tsi~Z0zYE2EX~Tm6QRsKIelPhOe3CwhuHuklX8Zt5t6Ms8siaeNcG7PjzKu2D1C-Yt64Qco8wi4V8trefNyZkhqlHvJPTTijILiJUDLtOjpxY6HQ__&Key-Pair-Id=APKAIPHBXWY2KT5RCMPQ">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-...tu-integrity-hash..." crossorigin="anonymous" />

        <!--        Para exportación a excel-->
        <script src="js/exportarExcelLotes.js"></script>

        <style>
            .scrollable-tbody-v {
                --bs-table-accent-bg: rgb(240, 14, 14, 0.70);
            }
        </style>

        <style>
            .scrollable-tbody-1m {
                --bs-table-accent-bg: rgb(255, 192, 0, 0.70);
            }
        </style>

        <style>
            .scrollable-tbody-3m {
                --bs-table-accent-bg: rgb(255, 255, 0, 0.70);
            }
        </style>
        <style>
            .scrollable-tbody-6m {
                --bs-table-accent-bg: rgb(71, 211, 89, 0.70);
            }
        </style>


        <style>
            .scrollable-tbody-8m {
                --bs-table-accent-bg: rgb(131, 204, 235, 0.70);
            }
        </style>


    </head>
    <body>
        <div class="container">
            <header class="text-center mt-3">
                <a href="userinfoReporte.jsp">
                    <img src="https://placetopay-static-prod-bucket.s3.us-east-2.amazonaws.com/bancatlan-hn/microsites/images/lKvG0Fmj74XHXYrfYr5RMfi6qGLP5zg3hF1uzgm6.png" width="200px" height="auto" alt="Logo">
                </a>
            </header>

            <main>

                <!-- Vendimiento de Productos Section -->
                <section class="vendimiento-section" style="width: 1400px;">
                    <!-- Content for Vendimiento de Productos Section -->
                    <!-- ... -->

                    <div class="text-center mx-auto">


                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h2>Control de Lotes | Nombre de Farmacia: CEDI <strong></strong></h2>

                            
                            <form action="CerrarSesionServlet" method="post">
                                <input type="submit" value="Cerrar Sesión" class="btn btn-danger">
                            </form>

                        </div>




                    </div>

                    <!--                    SE PUEDE UTILIZAR DESPUES-->




                    <!-- Pestañas -->
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="vencidos-tab" data-toggle="tab" href="#vencidos" role="tab" aria-controls="vencidos" aria-selected="true">Vencidos</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="un_mes-tab" data-toggle="tab" href="#un_mes" role="tab" aria-controls="un_mes" aria-selected="false">Vencen dentro de 1 Mes</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="tres_meses-tab" data-toggle="tab" href="#tres_meses" role="tab" aria-controls="tres_meses" aria-selected="false">Vencen dentro de 3 Meses</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="seis_meses-tab" data-toggle="tab" href="#seis_meses" role="tab" aria-controls="seis_meses" aria-selected="false">Vencen dentro de 6 Meses</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link" id="ocho_meses-tab" data-toggle="tab" href="#ocho_meses" role="tab" aria-controls="ocho_meses" aria-selected="false">Vencen dentro de 8 Meses</a>
                        </li>
                    </ul>
                </section>
            </main>
        </div>


        <div align="center">
            <!-- Contenido de las pestañas -->
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="vencidos" role="tabpanel" aria-labelledby="vencidos-tab">
                    <!-- Tabla de Vencidos -->
                    <div class="table-responsive">
                        <!-- Tu tabla de Vencidos va aquí -->
                        <div class="table-wrapper-scroll-y my-custom-scrollbar">
                            <div>
                                <h2 class="table-title"> </h2>
                            </div>

                            <form id="miFormulario1" class='mb-4'> 
                                <button type="button" onclick="exportarExcelArticulosLotes('miTabla0')" class=""><i class="fas fa-file-excel"></i> Exportar a Excel</button>
                            </form>
                            <table id="miTabla0" class="table table-bordered table-hover table-sm table-striped mb-0">
                                <thead>
                                    <tr>
                                        <th scope="col" class="text-center">Código</th>
                                        <th scope="col" class="text-center">Lote</th>
                                        <th scope="col" class="text-center">Fecha Caducidad</th>
                                        <th scope="col" class="text-center">Descripción</th>
                                        <th scope="col" class="text-center">Cantidad</th>
                                        <th scope="col" class="text-center">Precio Etiqueta</th>
                                        <th scope="col" class="text-center">Laboratorio</th>
                                        <th scope="col" class="text-center">Proveedor</th>
                                        <th scope="col" class="text-center">Observación</th>
                                        <th scope="col" class="text-center">Reg_SAN</th>
                                        <th scope="col" class="text-center">Devolutivo</th>
                                    </tr>
                                </thead>

                                <tbody class="scrollable-tbody-v">
                                    <% List<ArticulosPorLotes> articulosVencidos = (List<ArticulosPorLotes>) request.getAttribute("artsVenc");
        for (ArticulosPorLotes artVenc : articulosVencidos) { %>

                                    <tr>
                                        <td class="text-center"><%= artVenc.getItemId()%></td>
                                        <td class="text-center"><%= artVenc.getLote()%></td>
                                        <td class="text-center"><%= artVenc.getFechaCaducidad()%></td>
                                        <td class="text-center"><%= artVenc.getDescripcion()%></td>
                                        <td class="text-center"><%= artVenc.getCantidad()%></td>
                                        <td class="text-center"><%= artVenc.getPrecio_etiquetado()%></td>
                                        <td class="text-center"><%= artVenc.getLaboratorio()%></td>
                                        <td class="text-center"><%= artVenc.getVendedor()%></td>
                                        <td class="text-center"><%= artVenc.getObservacion()%></td>          
                                        <td class="text-center"><%= artVenc.getRegistro_sanitario()%></td>
                                        <td class="text-center"><%= artVenc.getDevolutivo()%></td>      
                                    </tr>   

                                    <% } %>
                                </tbody>
                            </table>

                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="un_mes" role="tabpanel" aria-labelledby="un_mes-tab">
                    <!-- Tabla de Vencen dentro de 1 Mes -->
                    <div class="table-responsive">
                        <!-- Tu tabla de Vencen dentro de 1 Mes va aquí, acá lo que Galo Me ayudó-->
                        <div class="table-wrapper-scroll-y my-custom-scrollbar">
                            <div>
                                <h2 class="table-title"> </h2>
                            </div>

                            <form id="miFormulario1" class='mb-4'> 
                                <button type="button" onclick="exportarExcelArticulosLotes('miTabla1')" class=""><i class="fas fa-file-excel"></i> Exportar a Excel</button>
                            </form>
                            <table id="miTabla1" class="table table-bordered table-hover table-sm table-striped mb-0">
                                <thead>
                                    <tr>
                                        <th scope="col" class="text-center">Código</th>
                                        <th scope="col" class="text-center">Lote</th>
                                        <th scope="col" class="text-center">Fecha Caducidad</th>
                                        <th scope="col" class="text-center">Descripción</th>
                                        <th scope="col" class="text-center">Cantidad</th>
                                        <th scope="col" class="text-center">Precio Etiqueta</th>
                                        <th scope="col" class="text-center">Laboratorio</th>
                                        <th scope="col" class="text-center">Proveedor</th>
                                        <th scope="col" class="text-center">Observación</th>
                                        <th scope="col" class="text-center">Reg_SAN</th>
                                        <th scope="col" class="text-center">Devolutivo</th>

                                    </tr>
                                </thead>

                                <!--                //Implementar y traer los datos de la vista -->
                                <tbody class="scrollable-tbody-1m" bs-table-accent-bg="rgb(255 192 0 / 39%)">
                                    <%
                                            List<ArticulosPorLotes> articulosUnMes = (List<ArticulosPorLotes>) request.getAttribute("artsPorVencer1");
                                            for (ArticulosPorLotes art1m : articulosUnMes) {
                                                // 
        //                                        boolean tieneRegistro = true;
                                    %>

                                    <tr>
                                        <td class="text-center"><%= art1m.getItemId()%></td>
                                        <td class="text-center"><%= art1m.getLote()%></td>
                                        <td class="text-center"><%= art1m.getFechaCaducidad()%></td>
                                        <td class="text-center"><%= art1m.getDescripcion()%></td>
                                        <td class="text-center"><%= art1m.getCantidad()%></td>
                                        <td class="text-center"><%= art1m.getPrecio_etiquetado()%></td>
                                        <td class="text-center"><%= art1m.getLaboratorio()%></td>
                                        <td class="text-center"><%= art1m.getVendedor()%></td>
                                        <td class="text-center"><%= art1m.getObservacion()%></td>
                                        <td class="text-center"><%= art1m.getRegistro_sanitario()%></td>
                                        <td class="text-center"><%= art1m.getDevolutivo()%></td>
                                    </tr>                           

                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="tres_meses" role="tabpanel" aria-labelledby="tres_meses-tab">
                    <!-- Tabla de Vencen dentro de 3 Meses -->
                    <div class="table-responsive">
                        <!-- Tu tabla de Vencen dentro de 3 Meses va aquí -->
                        <div class="table-wrapper-scroll-y my-custom-scrollbar">
                            <div>
                                <h2 class="table-title"> </h2>
                            </div>

                            <form id="miFormulario1" class="mb-4"> 
                                <button type="button" onclick="exportarExcelArticulosLotes('miTabla2')" class=""><i class="fas fa-file-excel"></i> Exportar a Excel</button>
                            </form>
                            <table id="miTabla2" class="table table-bordered table-hover table-sm table-striped mb-0">
                                <thead>
                                    <tr>
                                        <th scope="col" class="text-center">Código</th>
                                        <th scope="col" class="text-center">Lote</th>
                                        <th scope="col" class="text-center">Fecha Caducidad</th>
                                        <th scope="col" class="text-center">Descripción</th>
                                        <th scope="col" class="text-center">Cantidad</th>
                                        <th scope="col" class="text-center">Precio Etiqueta</th>
                                        <th scope="col" class="text-center">Laboratorio</th>
                                        <th scope="col" class="text-center">Proveedor</th>
                                        <th scope="col" class="text-center">Observación</th>
                                        <th scope="col" class="text-center">Reg_SAN</th>
                                        <th scope="col" class="text-center">Devolutivo</th>
                                    </tr>
                                </thead>

                                <!--                //Implementar y traer los datos de la vista -->
                                <tbody class="scrollable-tbody-3m">
                                    <%
                                            List<ArticulosPorLotes> articulosTresMeses = (List<ArticulosPorLotes>) request.getAttribute("artsPorVencer3");
                                            for (ArticulosPorLotes art3m : articulosTresMeses) {
                                                // 
        //                                        boolean tieneRegistro = true;
                                    %>

                                    <tr>
                                        <td class="text-center"><%= art3m.getItemId()%></td>
                                        <td class="text-center"><%= art3m.getLote()%></td>
                                        <td class="text-center"><%= art3m.getFechaCaducidad()%></td>
                                        <td class="text-center"><%= art3m.getDescripcion()%></td>
                                        <td class="text-center"><%= art3m.getCantidad()%></td>
                                        <td class="text-center"><%= art3m.getPrecio_etiquetado()%></td>
                                        <td class="text-center"><%= art3m.getLaboratorio()%></td>
                                        <td class="text-center"><%= art3m.getVendedor()%></td>
                                        <td class="text-center"><%= art3m.getObservacion()%></td>
                                        <td class="text-center"><%= art3m.getRegistro_sanitario()%></td>
                                        <td class="text-center"><%= art3m.getDevolutivo()%></td>
                                    </tr>                          

                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="seis_meses" role="tabpanel" aria-labelledby="seis_meses-tab">
                    <!-- Tabla de Vencen dentro de 6 Meses -->
                    <div class="table-responsive">
                        <!-- Tu tabla de Vencen dentro de 6 Meses va aquí -->
                        <div class="table-wrapper-scroll-y my-custom-scrollbar">
                            <div class='mb-4'>
                                <h2 class="table-title"> </h2>
                                <form id="miFormulario1"> 
                                    <button type="button" onclick="exportarExcelArticulosLotes('miTabla3')" class=""><i class="fas fa-file-excel"></i> Exportar a Excel</button>
                                </form>
                            </div>


                            <table id="miTabla3" class="table table-bordered table-hover table-sm table-striped mb-0">
                                <thead>
                                    <tr>
                                        <th scope="col" class="text-center">Código</th>
                                        <th scope="col" class="text-center">Lote</th>
                                        <th scope="col" class="text-center">Fecha Caducidad</th>
                                        <th scope="col" class="text-center">Descripción</th>
                                        <th scope="col" class="text-center">Cantidad</th>
                                        <th scope="col" class="text-center">Precio Etiqueta</th>
                                        <th scope="col" class="text-center">Laboratorio</th>
                                        <th scope="col" class="text-center">Proveedor</th>
                                        <th scope="col" class="text-center">Observación</th>
                                        <th scope="col" class="text-center">Reg_SAN</th>
                                        <th scope="col" class="text-center">Devolutivo</th>
                                    </tr>
                                </thead>

                                <!--                //Implementar y traer los datos de la vista -->
                                <tbody class="scrollable-tbody-6m">
                                    <%
                                            List<ArticulosPorLotes> articulosSeisMeses = (List<ArticulosPorLotes>) request.getAttribute("artsPorVencer6");
                                            for (ArticulosPorLotes art6m : articulosSeisMeses) {
                                                // 
        //                                        boolean tieneRegistro = true;
                                    %>

                                    <tr>
                                        <td class="text-center"><%= art6m.getItemId()%></td>
                                        <td class="text-center"><%= art6m.getLote()%></td>
                                        <td class="text-center"><%= art6m.getFechaCaducidad()%></td>
                                        <td class="text-center"><%= art6m.getDescripcion()%></td>
                                        <td class="text-center"><%= art6m.getCantidad()%></td>
                                        <td class="text-center"><%= art6m.getPrecio_etiquetado()%></td>
                                        <td class="text-center"><%= art6m.getLaboratorio()%></td>
                                        <td class="text-center"><%= art6m.getVendedor()%></td>
                                        <td class="text-center"><%= art6m.getObservacion()%></td>
                                        <td class="text-center"><%= art6m.getRegistro_sanitario()%></td>
                                        <td class="text-center"><%= art6m.getDevolutivo()%></td>
                                    </tr>                          

                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="tab-pane fade" id="ocho_meses" role="tabpanel" aria-labelledby="ocho_meses-tab">
                    <!-- Tabla de Vencen dentro de 8 Meses -->
                    <div class="table-responsive">
                        <!-- Tu tabla de Vencen dentro de 6 Meses va aquí -->
                        <div class="table-wrapper-scroll-y my-custom-scrollbar">
                            <div>
                                <h2 class="table-title"> </h2>
                            </div>

                            <form id="miFormulario1" class='mb-4'> 
                                <button type="button" onclick="exportarExcelArticulosLotes('miTabla4')" class=""><i class="fas fa-file-excel"></i> Exportar a Excel</button>
                            </form>
                            <table id="miTabla4" class="table table-bordered table-hover table-sm table-striped mb-0">
                                <thead>
                                    <tr>
                                        <th scope="col" class="text-center">Código</th>
                                        <th scope="col" class="text-center">Lote</th>
                                        <th scope="col" class="text-center">Fecha Caducidad</th>
                                        <th scope="col" class="text-center">Descripción</th>
                                        <th scope="col" class="text-center">Cantidad</th>
                                        <th scope="col" class="text-center">Precio Etiqueta</th>
                                        <th scope="col" class="text-center">Laboratorio</th>
                                        <th scope="col" class="text-center">Proveedor</th>
                                        <th scope="col" class="text-center">Observación</th>
                                        <th scope="col" class="text-center">Reg_SAN</th>
                                        <th scope="col" class="text-center">Devolutivo</th>
                                    </tr>
                                </thead>

                                <!--                //Implementar y traer los datos de la vista -->
                                <tbody class="scrollable-tbody-8m">
                                    <%
                                            List<ArticulosPorLotes> articulosOchoMeses = (List<ArticulosPorLotes>) request.getAttribute("artsPorVencer8");
                                            for (ArticulosPorLotes art8m : articulosOchoMeses) {
                                                // 
        //                                        boolean tieneRegistro = true;
                                    %>

                                    <tr>
                                        <td class="text-center"><%= art8m.getItemId()%></td>
                                        <td class="text-center"><%= art8m.getLote()%></td>
                                        <td class="text-center"><%= art8m.getFechaCaducidad()%></td>
                                        <td class="text-center"><%= art8m.getDescripcion()%></td>
                                        <td class="text-center"><%= art8m.getCantidad()%></td>
                                        <td class="text-center"><%= art8m.getPrecio_etiquetado()%></td>
                                        <td class="text-center"><%= art8m.getLaboratorio()%></td>
                                        <td class="text-center"><%= art8m.getVendedor()%></td>
                                        <td class="text-center"><%= art8m.getObservacion()%></td>
                                        <td class="text-center"><%= art8m.getRegistro_sanitario()%></td>
                                        <td class="text-center"><%= art8m.getDevolutivo()%></td>
                                    </tr>                          

                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <!--        Biblioteca para la exportación de excel-->
        <script src="https://unpkg.com/xlsx/dist/xlsx.full.min.js"></script>
        <script src="js/controladorLotes.js"></script>

        <!-- Enlaces a jQuery, Popper.js y Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    </body>
</html>
