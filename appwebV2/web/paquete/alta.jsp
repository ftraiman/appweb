<%@page import="edu.innova.webapp.dtos.PlataformaDTO"%>
<%@page import="java.util.List"%>
<%@page import="edu.innova.webapp.dtos.CategoriaDTO"%>
<%@page import="edu.innova.webapp.logica.servicios.impl.ServicioMenu"%>
<%@page import="edu.innova.webapp.helpers.Constantes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String carpeta = "Paquete";
    String pagina = "Alta";

    UsuarioDTO u = (UsuarioDTO) request.getSession().getAttribute("usuario");
    if (u == null || !Constantes.ARTISTA.equalsIgnoreCase(u.getTipo())) {
        response.sendRedirect("/web/principal/index.jsp");
        return;
    }

%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="apple-touch-icon" sizes="76x76" href="/web/assets/img/apple-icon.png">
        <link rel="icon" type="image/png" href="/web/assets/img/favicon.png">
        <title>
            Coronatickets.uy
        </title>
        <!--     Fonts and icons     -->
        <link href="/web/assets/googleapisfonts.css" rel="stylesheet" />
        <!-- Nucleo Icons -->
        <link href="/web/assets/css/nucleo-icons.css" rel="stylesheet" />
        <link href="/web/assets/css/nucleo-svg.css" rel="stylesheet" />
        <!-- Font Awesome Icons -->
        <script src="/web/assets/42d5adcbca.js "crossorigin="anonymous"></script>
        <link href="/web/assets/css/nucleo-svg.css" rel="stylesheet" />
        <!-- CSS Files -->
        <link id="pagestyle" href="/web/assets/css/soft-ui-dashboard.css" rel="stylesheet" />

        <script src="/web/assets/js/jquery.js"></script>
    </head>

    <body class="g-sidenav-show  bg-gray-100">
        <!-- Menu lateral izquierdo -->
        <%@include file="../common/lateral.jsp" %>
        <main class="main-content position-relative max-height-vh-100 h-100 mt-1 border-radius-lg ">
            <!-- Navbar -->
            <%@include file="../common/header.jsp" %>
            <!-- End Navbar -->
            <div class="container-fluid py-4">
                <div class="col-sm-2">
                </div>
                <div class="col-sm-8">
                    <div class="form-group" style="text-align: center">
                        <h1>Alta de Paquete</h1>
                    </div>
                    <%  if (request.getAttribute(Constantes.ERROR) != null) {%>
                    <div class="alert alert-warning" role="alert">
                        <%= request.getAttribute("error")%>
                    </div>
                    <%  }%>
                    <form role="form" method="POST" action="/web/paquete" enctype="multipart/form-data">
                        <input type="hidden" name="operacion" value="altapaquete" />
                        <div class="form-group">
                            <label for="nombre">Nombre</label>
                            <input name="nombre" type="text" class="form-control" id="nombre" required>
                        </div>
                        <div class="form-group">
                            <label for="descripcion">Descripción</label>
                            <input name="descripcion" type="text" class="form-control" id="descripcion" required>
                        </div>
                        <div class="form-group">
                            <label for="descuento">Descuento</label>
                            <input name="descuento" type="number" min="0" max="100" class="form-control" id="descuento" required>
                        </div>
                        <div class="form-group">
                            <label for="fechaInicio">Fecha de Inicio</label>
                            <input name="fechaInicio" type="date" class="form-control" id="fechaInicio" required>
                        </div>
                        <div class="form-group">
                            <label for="fechaFin">Fecha de Fin</label>
                            <input name="fechaFin" type="date" class="form-control" id="fechaFin" required>
                        </div>
                        <div class="form-group">
                            <label for="imagen">Imagen</label>
                            <input name="imagen" type="file" class="form-control-file" id="imagen">
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button type="submit" class="btn btn-linkedin">Alta de paquete</button>
                        </div>
                    </form>
                </div>
                <div class="col-sm-2">
                </div>
            </div>
            <%@include file="../common/footer.jsp" %>
        </main>
        <!--   Core JS Files   -->
        <script src="/web/assets/js/core/popper.min.js"></script>
        <script src="/web/assets/js/core/bootstrap.min.js"></script>
        <script src="/web/assets/js/plugins/perfect-scrollbar.min.js"></script>
        <script src="/web/assets/js/plugins/smooth-scrollbar.min.js"></script>
        <script src="/web/assets/js/plugins/chartjs.min.js"></script>
        <script>
            var win = navigator.platform.indexOf('Win') > -1;
            if (win && document.querySelector('#sidenav-scrollbar')) {
                var options = {
                    damping: '0.5'
                }
                Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
            }
        </script>
        <!--         Github buttons 
                <script async defer src="https://buttons.github.io/buttons.js"></script>-->
        <!-- Control Center for Soft Dashboard: parallax effects, scripts for the example pages etc -->
        <script src="/web/assets/js/soft-ui-dashboard.min.js"></script>
    </body>

</html>