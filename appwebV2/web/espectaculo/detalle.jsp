<%@page import="edu.innova.webapp.dtos.PaqueteDTO"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.List"%>
<%@page import="edu.innova.webapp.dtos.FuncionDTO"%>
<%@page import="edu.innova.webapp.dtos.PlataformaDTO"%>
<%@page import="edu.innova.webapp.dtos.CategoriaDTO"%>
<%@page import="edu.innova.webapp.dtos.InformacionEspectaculoDTO"%>
<%@page import="edu.innova.webapp.servlets.ServletAltaEspectaculo"%>
<%@page import="edu.innova.webapp.dtos.EspectaculoDTO"%>
<%@page import="edu.innova.webapp.helpers.Constantes"%>
<%@page import="edu.innova.webapp.helpers.HelperFechas"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String carpeta = "Espectaculo";
    String pagina = "Detalle";

    Long idEspectaculo = null;
    if (request.getParameter(Constantes.ID_ESPECTACULO) == null) {
        response.sendRedirect("/webV2/principal/index.jsp");
        return;
    } else {
        idEspectaculo = Long.valueOf(request.getParameter(Constantes.ID_ESPECTACULO));
    }
    UsuarioDTO u = (UsuarioDTO) request.getSession().getAttribute("usuario");
    Boolean isUsuarioLogueado = false;
    Boolean isEspectaculoFavorito = false;
    if (u != null) {
        isUsuarioLogueado = true;
        isEspectaculoFavorito = ServletAltaEspectaculo.isExpectaculoFavorito(idEspectaculo, u.getId());
    }
    
    InformacionEspectaculoDTO infoEspectaculo = ServletAltaEspectaculo.getEspectaculoPorId(idEspectaculo);

    EspectaculoDTO espectaculo = infoEspectaculo.getEspectaculo();
    CategoriaDTO categoria = infoEspectaculo.getCategoria();
    PlataformaDTO plataforma = infoEspectaculo.getPlataforma();
    List<FuncionDTO> funciones = espectaculo.getFunciones();
    UsuarioDTO artista = infoEspectaculo.getUsuario();
    List<PaqueteDTO> paquetes = infoEspectaculo.getPaquetes();


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
                <% if (isUsuarioLogueado) {%>
                        <div class="col-lg-4 col-md-6 my-sm-auto ms-sm-auto me-sm-0 mx-auto mt-1">
                            <div class="nav-wrapper position-relative end-0">
                                <ul class="nav nav-pills nav-fill p-1 bg-transparent" role="tablist">
                                    <li class="nav-item">
                                        <%if (isEspectaculoFavorito) {%>
                                        <form id="form-baja" role="form" method="POST" action="/webV2/espectaculosfavoritos">
                                            <input type="hidden" name="operacion" value="baja" />
                                            <input type="hidden" name="idEspectaculo" value="<%=idEspectaculo%>" />
                                            <input type="submit" class="btn btn-light" value="Quitar de mis favoritos">
                                        </form>
                                        <% } else { %>
                                        <form id="form-alta" role="form" method="POST" action="/webV2/espectaculosfavoritos">
                                            <input type="hidden" name="operacion" value="alta" />
                                            <input type="hidden" name="idEspectaculo" value="<%=idEspectaculo%>" />
                                            <input type="submit" class="btn btn-light" value="Agregar a mis favoritos"/>
                                        </form>
                                        <% } %>
                                    </li>
                                </ul>
                            </div>
                        </div>     
                        <% } %>
                <div class="col-12 mt-4">
                    <div class="card mb-4">
                        <div class="card-header pb-0 p-3">
                            <h6 class="mb-1">Espectáculo : <%=espectaculo.getNombre()%></h6>
                            <p class="text-sm">Plataforma: <b><%=plataforma.getNombre()%></b></p>
                            <p class="text-sm">Categoría:  <b><%=categoria.getNombre()%></b></p>
                            <p class="text-sm">Artista:    <b><%=artista.getNombre()%> <%=artista.getApellido()%> <u>(<%=artista.getNickname()%>)</u></b></p>
                        </div>
                        <div class="card-body p-3">
                            <div class="row">
                                <div class="col-xl-6 col-md-12 mb-xl-0 mb-12">
                                    <div class="card card-blog card-plain">
                                        <div class="position-relative">
                                            <a class="d-block shadow-xl border-radius-xl">
                                                <img src="/webV2/imagenes?carpeta=espectaculos&archivo=<%=espectaculo.getImagen()%>" alt="img-blur-shadow" class="img-fluid shadow border-radius-xl" style="height: 300px ">
                                            </a>
                                        </div>
                                        <div class="card-body px-1 pb-0">
                                            <p class="text-gradient text-dark mb-2 text-sm"><b>Descripción</b></p>
                                            <p class="text-gradient text-dark mb-2 text-sm"><%=espectaculo.getDescripcion()%></p>
                                            <p class="text-gradient text-dark mb-2 text-sm"><b>Duración</b> <%=espectaculo.getDuracion()%> minutos</p>
                                            <p class="text-gradient text-dark mb-2 text-sm"><b>Espectadores Mínimos</b> <%=espectaculo.getEspectadoresMinimos()%> minutos</p>
                                            <p class="text-gradient text-dark mb-2 text-sm"><b>Espectadores Máximos</b> <%=espectaculo.getEspectadoresMaximos()%> minutos</p>
                                            <a href="javascript:;">
                                                <h5>
                                                    Link a espectáculo
                                                </h5>
                                            </a>
                                            <p class="mb-4 text-sm">
                                                <%=espectaculo.getUrl()%>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container-fluid py-4">
                <div class="row">
                    <div class="col-12">
                        <div class="card mb-4">
                            <div class="card-header pb-0">
                                <h6>Funciones del Espectáculo</h6>
                            </div>
                            <div class="card-body px-0 pt-0 pb-2">
                                <div class="table-responsive p-0">
                                    <table class="table align-items-center mb-0">
                                        <thead>
                                            <tr>
                                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Nombre</th>
                                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Fecha de registro</th>
                                                <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Fecha de la Función</th>
                                                <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Artistas invitados</th>
                                                <th class="text-secondary opacity-7"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% for (FuncionDTO funcion : funciones) {%>
                                            <tr>
                                                <td>
                                                    <div class="d-flex px-2 py-1">
                                                        <div class="d-flex flex-column justify-content-center">
                                                            <h6 class="mb-0 text-sm"><%=funcion.getNombre()%></h6>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <p class="text-xs font-weight-bold mb-0"><%=HelperFechas.dateToString(funcion.getFechaRegistro(), "dd/MM/yyyy")%></p>
                                                </td>
                                                <td class="align-middle text-center text-sm">
                                                    <p class="text-xs font-weight-bold mb-0"><%=HelperFechas.dateToString(funcion.getFechaInicio(), "dd/MM/yyyy HH:mm")%></p>
                                                </td>
                                                <td class="align-middle text-center">
                                                    <span class="text-secondary text-xs font-weight-bold">
                                                        <%=funcion.getArtistasContatenados()%>
                                                    </span>
                                                </td>
                                                <td class="align-middle">
                                                    <a href="/webV2/funcion/detalle.jsp?idFuncion=<%=funcion.getId()%>" class="text-dark font-weight-bold text-xs" data-toggle="tooltip" data-original-title="Edit user">
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
                    </div>
                    <div class="col-12">
                        <div class="card mb-4">
                            <div class="card-header pb-0">
                                <h6>Paquetes asociados al Espectáculo</h6>
                            </div>
                            <div class="card-body px-0 pt-0 pb-2">
                                <div class="table-responsive p-0">
                                    <table class="table align-items-center mb-0">
                                        <thead>
                                            <tr>
                                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Nombre</th>
                                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Fecha de Inicio</th>
                                                <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Fecha de Fin</th>
                                                <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Descuento</th>
                                                <th class="text-secondary opacity-7"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% for (PaqueteDTO paquete : paquetes) {%>
                                            <tr>
                                                <td>
                                                    <div class="d-flex px-2 py-1">
                                                        <div class="d-flex flex-column justify-content-center">
                                                            <h6 class="mb-0 text-sm"><%=paquete.getNombre()%></h6>
                                                            <p class="text-xs font-weight-bold mb-0"><%=paquete.getDescripcion()%></p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <p class="text-xs font-weight-bold mb-0"><%=HelperFechas.dateToString(paquete.getFechaInicio(), "dd/MM/yyyy")%></p>
                                                </td>
                                                <td class="align-middle text-center text-sm">
                                                    <p class="text-xs font-weight-bold mb-0"><%=HelperFechas.dateToString(paquete.getFechaFin(), "dd/MM/yyyy")%></p>
                                                </td>
                                                <td class="align-middle text-center">
                                                    <span class="text-secondary text-xs font-weight-bold">
                                                        <%=paquete.getDescuento()%> %
                                                    </span>
                                                </td>
                                                <td class="align-middle">
                                                    <a href="/webV2/paquete/detalle.jsp?idPaquete=<%=paquete.getId()%>" class="text-dark font-weight-bold text-xs" data-toggle="tooltip" data-original-title="Edit user">
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
                    </div>
                </div>
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