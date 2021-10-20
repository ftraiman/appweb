<%@page import="edu.innova.webapp.helpers.Constantes"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="edu.innova.webapp.helpers.HelperFechas"%>
<%@page import="edu.innova.webapp.servlets.ServletPaquete"%>
<%@page import="edu.innova.webapp.dtos.PaqueteDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String carpeta = "Paquete";
    String pagina = "Inicio";

    List<PaqueteDTO> paquetes = ServletPaquete.getTodosLosPaquetes();
    
    UsuarioDTO u = (UsuarioDTO) request.getSession().getAttribute("usuario");
    boolean ofrecerPaquete = false;
    if (u != null) {
        ofrecerPaquete = true;
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
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet" />
        <!-- Nucleo Icons -->
        <link href="/web/assets/css/nucleo-icons.css" rel="stylesheet" />
        <link href="/web/assets/css/nucleo-svg.css" rel="stylesheet" />
        <!-- Font Awesome Icons -->
        <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
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
                <div class="col-sm-12">
                    <%  if (request.getAttribute(Constantes.ERROR) != null) {%>
                    <div class="alert alert-warning" role="alert">
                        <%= request.getAttribute(Constantes.ERROR)%>
                    </div>
                    <%  } %>
                    <%  if (request.getAttribute(Constantes.MENSAJE) != null) {%>
                    <div class="alert alert-success" role="alert">
                        <%= request.getAttribute(Constantes.MENSAJE)%>
                    </div>
                    <%  } %>
                    <div class="container-fluid py-4">
                        <div class="row">
                            <div class="col-12">
                                <div class="card mb-4">
                                    <div class="card-header pb-0">
                                        <h6>Todos los Paquetes</h6>
                                    </div>
                                    <div class="card-body px-0 pt-0 pb-2">
                                        <div class="table-responsive p-0">
                                            <table class="table align-items-center mb-0">
                                                <thead>
                                                    <tr>
                                                        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Nombre</th>
                                                        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Fecha de inicio</th>
                                                        <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Fecha de Fin</th>
                                                        <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Descuento</th>
                                                        <th class="text-secondary opacity-7"></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <% if (paquetes.size() > 0) {
                                                            for (PaqueteDTO paquete : paquetes) {
                                                                String nombre = paquete.getNombre();
                                                                String descripcion = paquete.getDescripcion();
                                                                String fechaInicio = HelperFechas.dateToString(paquete.getFechaInicio(), "dd/MM/yyyy");
                                                                String fechaFin = HelperFechas.dateToString(paquete.getFechaInicio(), "dd/MM/yyyy");
                                                                String imagen = paquete.getImagen();
                                                                BigDecimal descuento = paquete.getDescuento();
                                                                Long idPaquete = paquete.getId();
                                                    %>
                                                    <tr>
                                                        <td>
                                                            <div class="d-flex px-2 py-1">
                                                                <div>
                                                                    <img src="/web/imagenes?carpeta=paquetes&archivo=<%=imagen%>" class="avatar avatar-sm me-3" alt="<%=nombre%>">
                                                                </div>
                                                                <div class="d-flex flex-column justify-content-center">
                                                                    <h6 class="mb-0 text-sm"><%=nombre%></h6>
                                                                    <p class="text-xs font-weight-bold mb-0"><%=descripcion%></p>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <p class="text-xs font-weight-bold mb-0"><%=fechaInicio%></p>
                                                        </td>
                                                        <td class="align-middle text-center text-sm">
                                                            <p class="text-xs font-weight-bold mb-0"><%=fechaFin%></p>
                                                        </td>
                                                        <td class="align-middle text-center">
                                                            <span class="text-secondary text-xs font-weight-bold"><%=descuento%> %</span>
                                                        </td>
                                                        <td class="align-middle">
                                                            <a class="btn btn-link pe-3 ps-0 mb-0 ms-auto" href="/web/paquete/detalle.jsp?idPaquete=<%=idPaquete%>">Ver detalle</a> 
                                                            <%if (ofrecerPaquete) { %>
                                                                    / <a class="btn btn-link pe-3 ps-0 mb-0 ms-auto" href="/web/paquete?operacion=adquirirpaquete&idPaquete=<%=idPaquete%>">Adquirir</a> 
                                                            
                                                            <% } %>
                                                        </td>
                                                    </tr>
                                                    <%  }
                                                        }%>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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