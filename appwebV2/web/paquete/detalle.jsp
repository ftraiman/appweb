<%@page import="edu.innova.webapp.helpers.HelperFechas"%>
<%@page import="java.util.List"%>
<%@page import="edu.innova.webapp.dtos.EspectaculoDTO"%>
<%@page import="edu.innova.webapp.dtos.InformacionPaqueteDTO"%>
<%@page import="edu.innova.webapp.dtos.PaqueteDTO"%>
<%@page import="edu.innova.webapp.servlets.ServletPaquete"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String carpeta = "Paquete";
    String pagina = "Detalle";

    if (request.getParameter("idPaquete") == null) {
        response.sendRedirect("/web/principal/index.jsp");
        return;
    }

    Long idPaquete = Long.valueOf(request.getParameter("idPaquete"));

    InformacionPaqueteDTO infoPaquete = ServletPaquete.getPaquetePorId(idPaquete);
    PaqueteDTO paquete = infoPaquete.getPaquete();
    String categorias = infoPaquete.getCategorias();
    String plataformas = infoPaquete.getPlataformas();
    List<EspectaculoDTO> espectaculos = paquete.getEspectaculos();

    System.err.println(categorias);
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
                <div class="row mt-4">
                    <div class="col-lg-12 mb-lg-0 mb-4">
                        <div class="card">
                            <div class="card-body p-3">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="d-flex flex-column h-50">
                                            <h5 class="font-weight-bolder"><%=paquete.getNombre()%></h5>
                                            <p class="mb-1 pt-2 text-bold"><%=paquete.getDescripcion()%></p>
                                            <p class="mb-0">Plataformas: <b><%=plataformas%></b></p>
                                            <p class="mb-5">Categorías: <b><%=categorias%></b></p>
                                        </div>
                                    </div>
                                    <div class="col-lg-5 ms-auto text-center mt-5 mt-lg-0">
                                        <div class="border-radius-lg">
                                            <img src="/web/imagenes?carpeta=paquetes&archivo=<%=paquete.getImagen()%>" alt="img-blur-shadow" class="img-fluid shadow border-radius-xl" style="height: 200px ">
                                        </div>
                                    </div>
                                </div>
                                <% if (espectaculos.size() > 0) { %>    
                                <div class="card mb-4">
                                    <div class="card-header pb-0">
                                        <h6>Espectaculos del paquete</h6>
                                    </div>
                                    <div class="card-body px-0 pt-0 pb-2">
                                        <div class="table-responsive p-0">
                                            <table class="table align-items-center mb-0">
                                                <thead>
                                                    <tr>
                                                        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Nombre</th>
                                                        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Fecha de registro</th>
                                                        <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Costo</th>
                                                        <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Duración (minutos)</th>
                                                        <th class="text-secondary opacity-7"></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <% for (EspectaculoDTO espectaculo : espectaculos) {%>
                                                    <tr>
                                                        <td>
                                                            <div class="d-flex px-2 py-1">
                                                                <div class="d-flex flex-column justify-content-center">
                                                                    <h6 class="mb-0 text-sm"><%=espectaculo.getNombre()%></h6>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <p class="text-xs font-weight-bold mb-0"><%=HelperFechas.dateToString(espectaculo.getFechaRegistro(), "dd/MM/yyyy")%></p>
                                                        </td>
                                                        <td class="align-middle text-center text-sm">
                                                            <p class="text-xs font-weight-bold mb-0"><%=espectaculo.getCosto()%> $</p>
                                                        </td>
                                                        <td class="align-middle text-center">
                                                            <span class="text-secondary text-xs font-weight-bold">
                                                                <%=espectaculo.getDuracion()%>
                                                            </span>
                                                        </td>
                                                        <td class="align-middle">
                                                            <a href="/web/espectaculo/detalle.jsp?idEspectaculo=<%=espectaculo.getId()%>" class="text-dark font-weight-bold text-xs" data-toggle="tooltip" data-original-title="Edit user">
                                                                Más información
                                                            </a>
                                                        </td>
                                                    </tr>
                                                    <% }%>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>

                                <% }%>
                            </div>
                        </div>
                    </div>
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