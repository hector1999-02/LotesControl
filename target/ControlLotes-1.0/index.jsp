<%@ page import="jakarta.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <script src="js/script.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" 
              rel="stylesheet" 
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
              crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="login.css">
        <link rel="canonical shortcut icon" href="https://farmafacil.attachments.freshservice.com/data/helpdesk/attachments/production/22001435201/logo/logos_farmafacil-07.png?Expires=1709052529&Signature=b0Vt2OOAZeG7u8Vca0k3ryaUd2PyWyQ2y9iN3TCLEWN0fnUGc~VYJ7ZRIC5XL-WUEw2G-CFAijs25b8OX78LzBUX~EvRvEvwWVQ-wmyZsRVYfIVwbAIYUsF5XZ-Yx63j9t9XijS59CfvDKFCOBlavYDQ70TZp54d8UdZYnOBMpLHDCPfVgJHN3NyQfI324QkjGTrll~tmJru8Uqu9pRguJ67SiIJ-N2g7VoQz9Tsi~Z0zYE2EX~Tm6QRsKIelPhOe3CwhuHuklX8Zt5t6Ms8siaeNcG7PjzKu2D1C-Yt64Qco8wi4V8trefNyZkhqlHvJPTTijILiJUDLtOjpxY6HQ__&Key-Pair-Id=APKAIPHBXWY2KT5RCMPQ" />

    </head>
    <body>
        <div class="wrapper fadeInDown">
            <div id="formContent">
                <div class="fadeIn first">
                    <img  src="https://placetopay-static-prod-bucket.s3.us-east-2.amazonaws.com/bancatlan-hn/microsites/images/lKvG0Fmj74XHXYrfYr5RMfi6qGLP5zg3hF1uzgm6.png" id="icon" alt="User Icon" />
                </div>
                <form action="LoginServlet" method="post" onsubmit="return validarCampos()">
                    <input type="text" id="login" class="fadeIn second"  name="username" placeholder="Usuario">
                    <input type="password" id="password" class="fadeIn third" name="password" placeholder="Contraseña">
                    <input type="submit" class="fadeIn fourth" value="Iniciar Sesión">
                </form>
            </div>
        </div>

        <%-- Muestra el mensaje de error si existe --%>
        <%-- Mostrar alerta si existe un error --%>
        <%-- Mostrar alerta si existe un error --%>
        <% String error = (String) session.getAttribute("error"); %>
        <% if (error != null) { %>
        <script>
            // Generar código JavaScript para mostrar el alerta
            alert("<%= error %>");
            // Recargar la página después de 2 segundos
            <%session.removeAttribute("error"); %>
        </script>
        <% } %>
    </body>    

</html>