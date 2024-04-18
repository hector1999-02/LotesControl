<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            /* Estilos del cuadro modal */
            .modal {
                display: none;
                position: fixed;
                z-index: 1;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                overflow: auto;
                background-color: rgba(0,0,0,0.4);
            }

            .modal-content {
                background-color: #fefefe;
                margin: 15% auto;
                padding: 20px;
                border: 1px solid #888;
                width: 80%;
            }

            .modal-button {
                background-color: #4CAF50;
                color: white;
                padding: 10px 15px;
                margin-top: 15px;
                border: none;
                cursor: pointer;
            }
        </style>
    </head>
    <body>

        <%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
        %>
        <!-- Muestra el cuadro modal al cargar la página -->
        <div id="myModal" class="modal" >
            <form action="ControlLotesServlet" method="GET">
                <div class="modal-content">
                    <p><%= mensaje %></p>
                    <button class="modal-button" type="submit">Ver Control de Inventario</button>
                </div>
            </form>
        </div>

        <script>
            // Muestra el cuadro modal al cargar la página
            window.onload = function () {
                var modal = document.getElementById('myModal');
                modal.style.display = 'block';
            };
        </script>
        <%
        }
        %>
    </body>
</html>
