<%@page import="edu.innova.webapp.dtos.InformacionPaqueteDTO"%>
<%@page import="edu.innova.webapp.dtos.EspectaculoDTO"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="edu.innova.webapp.helpers.HelperFechas"%>
<%@page import="java.util.Date"%>
<%@page import="edu.innova.webapp.servlets.ServletPaquete"%>
<%@page import="edu.innova.webapp.dtos.PaqueteDTO"%>
<%@page import="edu.innova.webapp.dtos.PlataformaDTO"%>
<%@page import="java.util.List"%>
<%@page import="edu.innova.webapp.dtos.CategoriaDTO"%>
<%@page import="edu.innova.webapp.logica.servicios.impl.ServicioMenu"%>
<%@page import="edu.innova.webapp.helpers.Constantes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String carpeta = "Paquete";
    String pagina = "Alta de Espectáculo";

    UsuarioDTO usuarioSession = (UsuarioDTO) request.getSession().getAttribute("usuario");
    if (usuarioSession == null || !Constantes.ARTISTA.equalsIgnoreCase(usuarioSession.getTipo()) || request.getParameter("idPaquete") == null) {
        response.sendRedirect("/webV2/principal/index.jsp");
        return;
    }

    Long id = Long.valueOf(request.getParameter("idPaquete"));

    InformacionPaqueteDTO informacionPaquete = ServletPaquete.getPaquetePorId(id);
    PaqueteDTO p = informacionPaquete.getPaquete();
    List<EspectaculoDTO> espectaculos = p.getEspectaculos();
    List<EspectaculoDTO> espectaculosNoIncluidosEnPaquete = ServletPaquete.getEspectaculosNoIncluidosEnPaquetePorId(usuarioSession.getId(), id);


%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="apple-touch-icon" sizes="76x76" href="/webV2/assets/img/apple-icon.png">
        <link rel="icon" type="image/png" href="/webV2/assets/img/favicon.png">
        <title>
            Coronatickets.uy
        </title>
        <!--     Fonts and icons     -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet" />
        <!-- Nucleo Icons -->
        <link href="/webV2/assets/css/nucleo-icons.css" rel="stylesheet" />
        <link href="/webV2/assets/css/nucleo-svg.css" rel="stylesheet" />
        <!-- Font Awesome Icons -->
        <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
        <link href="/webV2/assets/css/nucleo-svg.css" rel="stylesheet" />
        <!-- CSS Files -->
        <link id="pagestyle" href="/webV2/assets/css/soft-ui-dashboard.css" rel="stylesheet" />

        <script src="/webV2/assets/js/jquery.js"></script>
    </head>

    <body class="g-sidenav-show  bg-gray-100">
        <!-- Menu lateral izquierdo -->
        <%@include file="../common/lateral.jsp" %>
        <main class="main-content position-relative max-height-vh-100 h-100 mt-1 border-radius-lg ">
            <!-- Navbar -->
            <%@include file="../common/header.jsp" %>
            <!-- End Navbar -->

            <div class="container-fluid py-4">
                <div class="card mb-1">
                    <div class="card-header pb-0 p-3">
                        <h6 class="mb-1"><%=p.getNombre()%></h6>
                        <p class="text-sm"><%=p.getDescripcion()%></p>
                    </div>
                    <div class="card-body p-3">
                        <div class="row">
                            <div class="col-xl-3 col-md-6 mb-xl-0 mb-4">
                                <div class="card card-blog card-plain">
                                    <div class="position-relative">
                                        <a class="d-block shadow-xl border-radius-xl">
                                            <img src="/webV2/imagenes?carpeta=paquetes&archivo=<%=p.getImagen()%>" class="img-fluid shadow border-radius-xl" style="height: 200px ">
                                        </a>
                                    </div>
                                    <div class="card-body px-1 pb-0">
                                        <p class="text-gradient text-dark mb-2 text-sm">Descuento <%=p.getDescuento()%> %</p>
                                        <a href="javascript:;">
                                            <h5>
                                                Duración
                                            </h5>
                                        </a>
                                        <p class="mb-4 text-sm">
                                            Fecha de Inicio: <%=HelperFechas.dateToString(p.getFechaInicio(), "dd/MM/yyyy")%>
                                        </p>
                                        <p class="mb-4 text-sm">
                                            Fecha de Fin: <%=HelperFechas.dateToString(p.getFechaFin(), "dd/MM/yyyy")%>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container-fluid py-4">
                <div class="row">
                    <div class="col-xl-4 col-md-6 mb-xl-0 mb-4 col-sm-0"></div>
                    <div class="col-xl-4 col-md-6 mb-xl-0 mb-4" style="text-align: center">
                        <h5 style="text-align: center;">Agregar un nuevo Espectáculo al Paquete</h5>
                        <form role="form" method="POST" action="/webV2/paquete" enctype="multipart/form-data">
                            <input type="hidden" name="operacion" value="espectaculoapaquete"/>
                            <input type="hidden" name="idPaquete" value="<%=id%>"/>
                            <% for (EspectaculoDTO espectaculoDTO : espectaculosNoIncluidosEnPaquete) {%>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="espectaculonuevo" id="espectaculonuevo" value="<%=espectaculoDTO.getId()%>">
                                <label class="form-check-label" for="espectaculonuevo">
                                    <%=espectaculoDTO.getNombre()%>
                                </label>
                            </div>

                            <% }%>    
                            <div class="form-group" style="text-align: center">
                                <button type="submit" class="btn btn-linkedin">Agregar Espectáculo al Paquete</button>
                            </div>
                        </form>
                    </div>
                    <div class="col-xl-4 col-md-6 mb-xl-0 mb-4 col-sm-0"></div>
                </div>
            </div>
            <div class="container-fluid py-4">
                <div class="col-sm-2">
                </div>
                <div class="col-sm-12">
                    <%  if (request.getAttribute(Constantes.ERROR) != null) {%>
                    <div class="alert alert-warning" role="alert">
                        <%= request.getAttribute("error")%>
                    </div>
                    <%  }%>
                    <div class="container-fluid py-4">
                        <div class="row">
                            <div class="col-12">
                                <div class="card mb-1">
                                    <div class="card-header pb-0">
                                        <h6>Espectáculos del Paquete</h6>
                                    </div>
                                    <div class="card-body px-0 pt-0 pb-2">
                                        <div class="table-responsive p-0">
                                            <table class="table align-items-center mb-0">
                                                <thead>
                                                    <tr>
                                                        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Nombre</th>
                                                        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Descripción</th>
                                                        <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Costo</th>
                                                        <th class="text-secondary opacity-7"></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <% if (espectaculos.size() > 0) {
                                                            for (EspectaculoDTO espectaculo : espectaculos) {
                                                                String nombre = espectaculo.getNombre();
                                                                String descripcion = espectaculo.getDescripcion();
                                                                BigDecimal costo = espectaculo.getCosto();
                                                                String imagen = espectaculo.getImagen();
                                                    %>
                                                    <tr>
                                                        <td>
                                                            <div class="d-flex px-2 py-1">
                                                                <div>
                                                                    <img src="/webV2/imagenes?carpeta=paquetes&archivo=<%=imagen%>" class="avatar avatar-sm me-3" alt="<%=nombre%>">
                                                                </div>
                                                                <div class="d-flex flex-column justify-content-center">
                                                                    <h6 class="mb-0 text-sm"><%=nombre%></h6>
                                                                </div>
                                                            </div>
                                                        </td>
                                                        <td>
                                                            <p class="text-xs font-weight-bold mb-0"><%=descripcion%></p>
                                                        </td>
                                                        <td class="align-middle text-center text-sm">
                                                            <p class="text-xs font-weight-bold mb-0"><%=costo%> $</p>
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
        <script src="/webV2/assets/js/core/popper.min.js"></script>
        <script src="/webV2/assets/js/core/bootstrap.min.js"></script>
        <script src="/webV2/assets/js/plugins/perfect-scrollbar.min.js"></script>
        <script src="/webV2/assets/js/plugins/smooth-scrollbar.min.js"></script>
        <script src="/webV2/assets/js/plugins/chartjs.min.js"></script>
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
        <script src="/webV2/assets/js/soft-ui-dashboard.min.js"></script>
    </body>

</html>