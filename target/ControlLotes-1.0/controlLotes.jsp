<%@ page import="logica.RetailItem"%>

<%@ page import="logica.ArticulosPorLotes"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet" %>
<%@ page import="conexionSQL.ConexionSQLServer"%>
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
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Control de Lotes</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <!-- Custom Styles -->
        <link rel="stylesheet" href="styles/styleControlLotes.css"/>
        <!-- Favicon -->
        <link rel="icon" type="image/png" sizes="32x32" href="https://farmafacil.attachments.freshservice.com/data/helpdesk/attachments/production/22001435201/logo/logos_farmafacil-07.png?Expires=1709052529&Signature=b0Vt2OOAZeG7u8Vca0k3ryaUd2PyWyQ2y9iN3TCLEWN0fnUGc~VYJ7ZRIC5XL-WUEw2G-CFAijs25b8OX78LzBUX~EvRvEvwWVQ-wmyZsRVYfIVwbAIYUsF5XZ-Yx63j9t9XijS59CfvDKFCOBlavYDQ70TZp54d8UdZYnOBMpLHDCPfVgJHN3NyQfI324QkjGTrll~tmJru8Uqu9pRguJ67SiIJ-N2g7VoQz9Tsi~Z0zYE2EX~Tm6QRsKIelPhOe3CwhuHuklX8Zt5t6Ms8siaeNcG7PjzKu2D1C-Yt64Qco8wi4V8trefNyZkhqlHvJPTTijILiJUDLtOjpxY6HQ__&Key-Pair-Id=APKAIPHBXWY2KT5RCMPQ">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-...tu-integrity-hash..." crossorigin="anonymous" />

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


    </head>
    <body>
        <div class="container">
            <!-- Header -->
            <header class="text-center mt-3">
                <a href="userinfo.jsp">
                    <img src="https://placetopay-static-prod-bucket.s3.us-east-2.amazonaws.com/bancatlan-hn/microsites/images/lKvG0Fmj74XHXYrfYr5RMfi6qGLP5zg3hF1uzgm6.png" width="200px" height="auto" alt="Logo">
                </a>
            </header>

            <!-- Main Content -->
            <main>
                <!-- Control de Lotes Section -->
                <section class="control-lotes-section">
                    <!-- Title and Logout Button -->
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h2>Control de Lotes | Nombre de Farmacia: CEDI <strong></strong></h2>
                        <form action="CerrarSesionServlet" method="post">
                            <input type="submit" value="Cerrar Sesión" class="btn btn-danger">
                        </form>
                    </div>

                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <form action="ControlLotesServlet" method="post">
                            <input type="submit" value="Ver Articulos" class="btn btn-success">
                        </form>
                    </div>


                    <!-- Fecha Actual -->
                    <div>
                        <p id="fechaActual" class="fw-bold"></p>
                    </div>

                    <div class="search-form-section" style="height: 70px; width: 1500px;">
                        <form id="miFormulario" action="BuscarProductoServlet" method="POST">
                            <label for="item_id">Código del Producto:</label>
                            <input type="text" id="item_id" name="item_id" placeholder="Ingrese el código del producto" required style="width: 233px;">
                            <button type="submit" class="btn btn-primary">Buscar Producto</button>
                        </form>
                    </div>

                    <!-- Product Table -->
                    <div class="table-wrapper-scroll-y" ">
                        <% 
                        List<RetailItem> retails = (List<RetailItem>) request.getAttribute("retails");

                        if (retails != null && !retails.isEmpty()) { %>
                        <table id="miTabla" class="table table-bordered table-hover table-sm table-striped mb-0">
                            <!-- Table Head -->
                            <thead>
                                <tr>
                                    <th scope="col" class="text-center">Código de Producto</th>
                                    <th scope="col" class="text-center">Descripción</th>
                                    <th scope="col" class="text-center">Registrar</th>
                                </tr>
                            </thead>
                            <!-- Table Body -->
                            <tbody class="scrollable-tbody">
                                <% for (RetailItem producto : retails) { 
                                    boolean tieneRegistro = true;
                                %>
                                <tr>
                                    <td class="text-center"><%= producto.getITEMID()%></td>
                                    <td class="text-center"><%= producto.getNAMEALIAS()%></td>
                                    <td class="text-center ajustar-columna" style="
                                        padding-right: 70px;
                                        width: 814px;
                                        height: 208px;
                                        padding-left: 70px;
                                        ">
                                        <% if (tieneRegistro) { %>
                                        <!-- Add lote and fecha de caducidad form -->
                                        <form id="miFormulario2" action="AgregarLoteServlet" method="POST">
                                            <input type="hidden" name="itemId" value="<%= producto.getITEMID()%>">
                                            <input type="hidden" name="descripcion" value="<%= producto.getNAMEALIAS()%>">


                                            <div class="form-group">
                                                <label for="Vendedor" class="mb-2 label-rojo">Proveedor</label>
                                                <select id="id_vend" name="id_vend" class="form-control mb-3" required>
                                                    <option value="">Seleccione el Proveedor</option>
                                                    <% 
                                                    Connection con_2 = null;
                                                    PreparedStatement pst_2 = null;
                                                    ResultSet rs_2 = null;

                                                    try {
                                                        con_2 = ConexionSQLServer.getConnection();
                                                        String query_2 = "SELECT DISTINCT ACCOUNTNUM, NAME FROM VENDTABLE  where DELETED = 0";
                                                        pst_2 = con_2.prepareStatement(query_2);
                                                        rs_2 = pst_2.executeQuery();

                                                        while(rs_2.next()) {
                                                            String Id_vend = rs_2.getString("ACCOUNTNUM");
                                                            String name_vend = rs_2.getString("NAME");
                                                    %>
                                                    <option value="<%= Id_vend %>"><%= name_vend %></option>
                                                    <% 
                                                        }
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    } finally {
                                                        try {
                                                            if (rs_2 != null) rs_2.close();
                                                            if (pst_2 != null) pst_2.close();
                                                            if (con_2 != null) con_2.close();
                                                        } catch (SQLException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                    %>
                                                </select>
                                            </div>

                                            <!--                                            <div class="form-group">
                                                                                            <label for="Labortorio" class="mb-2">Laboratorio</label>
                                                                                            <select id="id_lab" name="id_lab" class="form-control mb-3" required>
                                                                                                <option value="">Seleccione el laboratorio</option>
                                            <% 
                                            PreparedStatement pst = null;
                                            ResultSet rs = null;
                                            Connection con = null;

                                            try {
                                                con = ConexionSQLServer.getConnection();

                                                String query = "SELECT DISTINCT RETAILLABORATORIOS.FABRICANTE AS Id_lab, LABTABLE.NAME AS Nombre_Lab FROM RETAILLABORATORIOS INNER JOIN RETAILITEM ON RETAILLABORATORIOS.ITEMID = RETAILITEM.ITEMID INNER JOIN LABTABLE ON RETAILLABORATORIOS.FABRICANTE = LABTABLE.ACCOUNTNUM";
                                                pst = con.prepareStatement(query);
                                                rs = pst.executeQuery();

                                                while(rs.next()) {
                                                    String Id_lab = rs.getString("Id_lab");
                                                    String name = rs.getString("Nombre_Lab");
                                            %>
                                            <option value="<%= Id_lab %>"><%= name %></option>
                                            <% 
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            } finally {
                                                try {
                                                    if (rs != null) rs.close();
                                                    if (pst != null) pst.close();
                                                    if (con != null) con.close();
                                                } catch (SQLException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                            %>
                                        </select>
                                    </div>

                                            -->

                                            <div class="form-group">
                                                <label for="cantidad" class="label-rojo">Cantidad</label>
                                                <input type="number" id="cantidad" name="cantidad" class="form-control" placeholder="Ingrese la cantidad" required min="1">
                                            </div>

                                            <div class="form-group">
                                                <label for="precio_etiquetado" class="label-rojo">Precio Etiquetado</label>
                                                <input type="text" id="precio_etiquetado" name="precio_etiquetado" class="form-control" placeholder="Ingrese el precio de Etiqueta" required pattern="\d+(\.\d{1,2})?">
                                                <small>Debe ser un número positivo con hasta dos decimales.</small>
                                            </div>
                                            <div class="form-group">
                                                <label for="observacion">Observación</label>
                                                <textarea class="form-control" rows="5" id="observacion" name="observacion" class="form-control" placeholder="Breve Observación""></textarea>
                                            </div>
                                            <div class="form-group">
                                                <label for="lote" class="label-rojo">Lote</label>
                                                <input type="text" id="lote" name="lote" class="form-control" placeholder="Ingrese el Lote" pattern="[a-zA-Z0-9]{1,15}"  title="Ingrese letras y números, máximo 15 caracteres" required>
                                            </div>

                                            <div class="form-group">
                                                <label for="fechaCaducidad" class="label-rojo">Fecha de Caducidad</label>
                                                <input type="date" id="fechaCaducidad" name="fechaCaducidad" class="form-control" required>
                                                <small>Debe ser una fecha mayor o igual a la actual.</small>

                                            </div>


                                            <div class="form-group tex-center">
                                                <label for="devolutivo" class="mb-2 label-rojo">Devolutivo</label>
                                                <select id="devolutivo" name="devolutivo" class="form-control" required>
                                                    <option value="Si" selected>Si</option>
                                                    <option value="No">No</option>          
                                                </select>
                                            </div>



                                            <div class="form-group">
                                                <label for="reg_san" class="label-rojo">Reg_SAN</label>
                                                <input type="text" id="reg_san" name="reg_san" class="form-control" placeholder="Ingrese Registro Sanitario" pattern="[a-zA-Z0-9]{1,15}" title="Ingrese letras y números, máximo 15 caracteres" required>
                                            </div>





                                            <button class="btn btn-success mb-4" type="submit" style="
                                                    margin-top: 24px;
                                                    ">Agregar Lote</button>



                                            <!--                                            <label for="lote">Lote:</label>
                                                                                        <input type="text" id="lote" name="lote" placeholder="Ingrese el Lote" required>
                                                                                        <label for="fechaCaducidad">Fecha de Caducidad:</label>
                                                                                        <input type="date" id="fechaCaducidad" name="fechaCaducidad" required>
                                                                                        <button class="btn-success" type="submit">Agregar Lote</button>-->
                                        </form>
                                        <% } else { %>
                                        <!-- Display message if no registro -->
                                        No hay registro de este producto.
                                        <% } %>
                                    </td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                        <% } else { %>
                        <!-- Display message if no records -->
                        <!-- Ventana Modal de Alerta -->
                        <p>Producto no encontrado</p>


                        <% } %>
                    </div>





                </section>

                <!-- Vencimiento de Productos Section (guardado en bloc de notas)-->

            </main>

            <!-- JavaScript -->
        </div>
        <script src="js/controladorLotes.js"></script>

        <!-- Enlaces a jQuery, Popper.js y Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-...tu-integrity-hash..." crossorigin="anonymous"></script>



    </body>
</html>
