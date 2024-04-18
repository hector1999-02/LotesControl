<%@ page import="logica.ArticulosPorLotes"%>
<%@ page import="logica.Laboratorios"%>
<%@ page import="logica.Proveedores"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="conexionSQL.ConexionSQLServer"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.sql.SQLException" %>
<%
    // Validating user session
    if(session.getAttribute("userId") == null){
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Actualizar Producto</title>
        <!-- Agrega tus estilos CSS si es necesario -->
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
        <h1 class="text-center">Actualizar Producto</h1>

        <!-- Product Table -->
        <div class="table-wrapper-scroll-y" ">
            <% 
            List<ArticulosPorLotes> art_lote = (List<ArticulosPorLotes>) request.getAttribute("art_por_lotes");

            if (art_lote != null && !art_lote.isEmpty()) { %>
            <div class="container d-flex align-items-center justify-content-center">


                <table id="miTabla" class="table table-bordered table-hover table-sm table-striped mb-0  ">
                    <!-- Table Head -->
                    <thead>
                        <tr>
                            <th scope="col" class="text-center">Código</th>
                            <th scope="col" class="text-center">Descripción</th>
                            <th scope="col" class="text-center">Modificar</th>
                        </tr>
                    </thead>
                    <!-- Table Body -->
                    <tbody class="scrollable-tbody">
                        <% for (ArticulosPorLotes producto : art_lote) { 
                            boolean tieneRegistro = true;
                        %>
                        <tr>
                            <td class="text-center"><%= producto.getItemId()%></td>
                            <td class="text-center"><%= producto.getDescripcion()%></td>
                            <td class="text-center">
                                <% if (tieneRegistro) { %>
                                <!-- Add lote and fecha de caducidad form -->
                                <form id="miFormulario2" action="ActualizarProductoServlet" method="POST">
                                    <input type="hidden" name="itemId" value="<%= producto.getItemId()%>">
                                    <input type="hidden" name="descripcion" value="<%= producto.getDescripcion()%>">
                                    <input type="hidden" name="lote_ant" value="<%= producto.getLote()%>">


                                    <!--                                    Select de Proveedores-->
                                    <%
                                    List<Proveedores> provs = (List<Proveedores>) request.getAttribute("provs");

                                    // Obtener el ID del laboratorio por defecto de labs
                                    String idProveedorDefecto = ""; // Aquí debes asignar el ID del laboratorio por defecto de labs

                                    // Obtener el nombre del laboratorio por defecto de labs
                                    String nombreProveedorDefecto = ""; // Aquí debes asignar el nombre del laboratorio por defecto de labs

                                    for (Proveedores prov : provs) {
                                        // Asignar el ID y nombre del laboratorio por defecto de labs
                                        idProveedorDefecto = prov.getIdProv();
                                        nombreProveedorDefecto = prov.getNombre();
                                    }
                                    %>

                                    <div class="form-group tex-center">
                                        <label for="Vendedor" class="mb-2 label-rojo">Proveedor</label>
                                        <select id="id_vend" name="id_vend" class="form-control" required>
                                            <option value="">Seleccione el Proveedor</option>
                                            <%
                                            Connection con_1 = null;
                                            PreparedStatement pst_1  = null;
                                            ResultSet rs_1  = null;

                                            try {
                                                con_1  = ConexionSQLServer.getConnection();
                                                String query = "SELECT DISTINCT ACCOUNTNUM, NAME FROM VENDTABLE WHERE DELETED = 0";
                                                pst_1  = con_1 .prepareStatement(query);
                                                rs_1  = pst_1.executeQuery();

                                                while(rs_1.next()) {
                                                    String Id_prov = rs_1.getString("ACCOUNTNUM");
                                                    String name = rs_1.getString("NAME");
                                            %>
                                            <option value="<%= Id_prov %>" <% if (Id_prov.equals(idProveedorDefecto)) { %> selected <% } %>><%= name %></option>
                                            <%
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            } finally {
                                                try {
                                                    if (rs_1 != null) rs_1.close();
                                                    if (pst_1 != null) pst_1.close();
                                                    if (con_1 != null) con_1.close();
                                                } catch (SQLException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                            %>
                                        </select>
                                    </div>






                                    <!--                                                                        Select de laboratorios
                                    <%
                                    List<Laboratorios> labs = (List<Laboratorios>) request.getAttribute("labs");

                                    // Obtener el ID del laboratorio por defecto de labs
                                    String idLaboratorioDefecto = ""; // Aquí debes asignar el ID del laboratorio por defecto de labs

                                    // Obtener el nombre del laboratorio por defecto de labs
                                    String nombreLaboratorioDefecto = ""; // Aquí debes asignar el nombre del laboratorio por defecto de labs

                                    for (Laboratorios lab : labs) {
                                        // Asignar el ID y nombre del laboratorio por defecto de labs
                                        idLaboratorioDefecto = lab.getId_lab();
                                        nombreLaboratorioDefecto = lab.getNombre();
                                    }
                                    %>

                                    <div class="form-group text-center">
                                        <label for="laboratorio" class="mb-2">Laboratorio</label>
                                        <select id="id_lab" name="id_lab" class="form-control" required>
                                            <option value="">Seleccione el Laboratorio</option>
                                    <%
                                    Connection con = null;
                                    PreparedStatement pst = null;
                                    ResultSet rs = null;

                                    try {
                                        con = ConexionSQLServer.getConnection();
                                        String query = "SELECT DISTINCT RETAILLABORATORIOS.FABRICANTE AS Id_lab, LABTABLE.NAME AS Nombre_Lab FROM RETAILLABORATORIOS INNER JOIN RETAILITEM ON RETAILLABORATORIOS.ITEMID = RETAILITEM.ITEMID INNER JOIN LABTABLE ON RETAILLABORATORIOS.FABRICANTE = LABTABLE.ACCOUNTNUM";
                                        pst = con.prepareStatement(query);
                                        rs = pst.executeQuery();

                                        while(rs.next()) {
                                            String Id_lab = rs.getString("Id_lab");
                                            String name = rs.getString("Nombre_Lab");
                                    %>
                                    <option value="<%= Id_lab %>" <% if (Id_lab.equals(idLaboratorioDefecto)) { %> selected <% } %>><%= name %></option>
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
                                        <input type="number" id="cantidad" name="cantidad" value="<%= producto.getCantidad()%>" class="form-control" placeholder="Ingrese la cantidad" required min="1">
                                    </div>

                                    <div class="form-group">
                                        <label for="precio_etiquetado" class="label-rojo">Precio Etiquetado</label>
                                        <input type="text" id="precio_etiquetado" name="precio_etiquetado" value="<%= producto.getPrecio_etiquetado()%>" class="form-control" placeholder="Ingrese el precio de Etiqueta" required pattern="\d+(\.\d{1,2})?">
                                        <small>Debe ser un número positivo con hasta dos decimales.</small>
                                    </div>

                                    <div class="form-group">
                                        <label for="observacion">Observación</label>
                                        <textarea class="form-control" rows="5" id="observacion" name="observacion" class="form-control" value="<%= producto.getObservacion()%>" placeholder=""><%= producto.getObservacion()%></textarea>
                                    </div>

                                    <div class="form-group">
                                        <label for="lote" class="label-rojo">Lote</label>
                                        <input type="text" id="lote" name="lote" class="form-control" value="<%= producto.getLote()%>" placeholder="Ingrese el Lote" pattern="[a-zA-Z0-9]{1,15}" title="Ingrese letras y números, máximo 15 caracteres" required>
                                    </div>

                                    <div class="form-group">
                                        <label for="fechaCaducidad" class="label-rojo">Fecha de Caducidad</label>
                                        <input type="date" id="fechaCaducidadActualizable" name="fechaCaducidad" value="<%= producto.getFechaCaducidad()%>" class="form-control" required>
                                    </div>


                                    <div class="form-group tex-center">
                                        <label for="devolutivo" class="mb-2 label-rojo">Devolutivo</label>
                                        <select id="devolutivo" name="devolutivo" class="form-control" required>
                                            <% if (producto.getDevolutivo().equals("Si")) { %>
                                            <option value="Si" selected>Si</option>
                                            <option value="No">No</option>
                                            <% } else { %>
                                            <option value="Si">Si</option>
                                            <option value="No" selected>No</option>
                                            <% } %>
                                        </select>
                                    </div>



                                    <div class="form-group">
                                        <label for="reg_san" class="label-rojo">Reg_SAN</label>
                                        <input type="text" id="reg_san" name="reg_san" class="form-control" value="<%= producto.getRegistro_sanitario()%>" placeholder="Ingrese Registro Sanitario" pattern="[a-zA-Z0-9]{1,15}" title="Ingrese letras y números, máximo 15 caracteres" required>
                                    </div>





                                    <div class="mb-4">
                                        <button class="btn btn-success mt-3" type="submit">Actualizar Producto</button>
                                    </div>
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
            </div>
            <% } else { %>
            <!-- Display message if no records -->
            <p>No hay registros de este Producto.</p>
            <% } %>
        </div> 

        <script src="js/controladorLotes.js"></script>

        <!-- Enlaces a jQuery, Popper.js y Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


    </body>
</html>
