<%@page import ="java.util.List" %>
<%@page import ="logica.Sucursales" %>
<%@page import ="servlets.SucursalesServlet" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registre su Usuario</title>

        <!-- Añade el enlace a Bootstrap 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <style>
            /* Añade estilos personalizados si es necesario */
            .custom-button:hover {
                background-color: #4caf50; /* Verde oscuro en el hover */
            }
        </style>
    </head>
    <body class="text-center">

        <header>
            <div class="mb-3">
                <img src="https://placetopay-static-prod-bucket.s3.us-east-2.amazonaws.com/bancatlan-hn/microsites/images/lKvG0Fmj74XHXYrfYr5RMfi6qGLP5zg3hF1uzgm6.png" width="200px" height="auto" alt="Logo">
            </div>
        </header>

        <div class="container"> <!-- Contenedor para centrar elementos -->
            <h1 class="mb-4">Registre su Usuario</h1>

            <form class="row g-3 mt-4">
                <div class="col-md-6 mb-3">
                    <!-- Username -->
                    <label for="username" class="form-label">Nombre de Usuario</label>
                    <input type="text" id="username" class="form-control" placeholder="Ingrese su nombre de usuario">
                </div>

                <div class="col-md-6 mb-3">
                    <!-- Password -->
                    <label for="password" class="form-label">Contraseña</label>
                    <input type="password" id="password" class="form-control" placeholder="Ingrese su contraseña">
                </div>

                <div class="col-md-12 mb-3">

                    <label for="farmacia" class="form-label">Seleccione su Farmacia</label>
                    <div class="col-md-12 mb-3">
                        <select id="farmacia" name="farmacia">
                            <%
                        List<Sucursales> sucursales = (List<Sucursales>) request.getAttribute("sucursales");

                        if(sucursales != null){
                        for(Sucursales sucursal: sucursales){  
                                            %>
                            <option value="<%=sucursal.getSTOREID()%>">
                                <%= sucursal.getNAME() %>
                            </option>

                            <%}%>

                        </select>
<%
                      }
                    %>
                    </div>

                 



                </div>





                <div class="col-md-12 mb-3">
                    <!-- Button -->
                    <button class="btn btn-success custom-button btn-lg">Crear Usuario</button>
                </div>
            </form>
        </div>

        <!-- Añade el script de Bootstrap 5 (JavaScript) al final del body -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
