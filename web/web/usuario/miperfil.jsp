<%@page import="edu.innova.webapp.dtos.PaqueteDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.innova.webapp.servlets.ServletPaquete"%>
<%@page import="edu.innova.webapp.dtos.EspectaculosDeUsuario"%>
<%@page import="edu.innova.webapp.servlets.ServletInformacionUsuario"%>
<%@page import="edu.innova.webapp.helpers.Constantes"%>
<%@page import="edu.innova.webapp.dtos.EspectaculoDTO"%>
<%@page import="java.util.List"%>
<%@page import="edu.innova.webapp.servlets.ServletModificarUsuario"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="edu.innova.webapp.dtos.UsuarioDTO"%>
<%@page import="edu.innova.webapp.helpers.HelperFechas"%>

<%
    String carpeta = "Usuario";
    String pagina = "Mi perfil";
    UsuarioDTO u = (UsuarioDTO) request.getSession().getAttribute("usuario");
    if (u == null) {
        response.sendRedirect("/web/principal/index.jsp");
        return;
    }
    String tipoUsuario = u.getTipo();
    List<UsuarioDTO> usuariosQueSigue = ServletModificarUsuario.getUsuariosSeguidos(request);
    EspectaculosDeUsuario espectaculosDeUsuario = ServletInformacionUsuario.getEspectaculosDeUsuarioPorIdArtista(u.getId());
    List<PaqueteDTO> paquetesCreadosPorArtista = Constantes.ARTISTA.equalsIgnoreCase(tipoUsuario) ? ServletPaquete.getPaquetesPorIdUsuario(u.getId()) : new ArrayList<PaqueteDTO>();
    List<PaqueteDTO> paquetesCompradosPorUsuario = ServletPaquete.getPaquetesCompradosPorIdUsuario(u.getId());

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
                    <div class="col-lg-7 mb-lg-0 mb-4">
                        <div class="card">
                            <div class="card-body p-3">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="d-flex flex-column h-100">
                                            <p class="mb-1 pt-2 text-bold"><%=u.getNickname()%></p>
                                            <h5 class="font-weight-bolder"><%=u.getNombre()%>  <%=u.getApellido()%></h5>
                                            <h6 class="font-weight-bolder"><%=u.getEmail()%>  </h6>
                                            <p class="mb-5">Fecha de nacimiento : <b><%=HelperFechas.dateToString(u.getFechaNacimiento(), "dd MMMM, yyyy")%></b></p>
                                            <% if (Constantes.ARTISTA.equalsIgnoreCase(u.getTipo())) {%>
                                            <h6 class="font-weight-bolder">Descripción</h6>
                                            <p class="mb-5"><b><%=u.getDescripcion()%></b></p>
                                            <h6 class="font-weight-bolder">Biografía</h6>
                                            <p class="mb-5"><b><%=u.getBiografia()%></b></p>
                                            <h6 class="font-weight-bolder">Link</h6>
                                            <p class="mb-5"><b><%=u.getLinkUsuario()%></b></p>
                                                    <% }%>

                                        </div>
                                    </div>
                                    <div class="col-lg-5 ms-auto text-center mt-5 mt-lg-0">
                                        <div>
                                            <img src="/web/imagenes?carpeta=usuarios&archivo=<%=u.getImagen()%>" class="position-absolute h-100 w-50 top-0 d-lg-block d-none" alt="waves" style="width: 200px !important; height: 200px !important">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--modificar datos-->
                    <div class="col-lg-5">
                        <div class="card p-3">
                            <div class="overflow-hidden position-relative border-radius-lg bg-cover h-100" id="collapseExample" >
                                <span class=""></span>
                                <h4 style="text-align: center">Modificar datos</h4>
                                <form role="form" method="POST" action="/web/modificarusuario" enctype="multipart/form-data">
                                    <div class="form-group">
                                        <label for="nombre">Nombre</label>
                                        <input name="nombre" type="text" class="form-control" id="nombre" value="<%=u.getNombre()%>" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="nombre">Apellido</label>
                                        <input name="apellido" type="text" class="form-control" id="apellido" value="<%=u.getApellido()%>" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="fechanacimiento">Fecha de nacimiento</label>
                                        <input name="fechanacimiento" type="date" class="form-control" id="fechanacimiento" value="<%= HelperFechas.dateToString(u.getFechaNacimiento(), "yyyy-MM-dd")%>" required>
                                    </div>
                                    <div id="datosAdicionales" class="collapse <%= Constantes.ARTISTA.equalsIgnoreCase(u.getTipo()) ? "show" : ""%>"> 
                                        <div class="form-group">
                                            <label for="descripcion">Descripción</label>
                                            <input name="descripcion" type="text" class="form-control" value="<%=u.getDescripcion()%>" id="descripcion">
                                        </div>
                                        <div class="form-group">
                                            <label for="biografia">Biografía</label>
                                            <input name="biografia" type="text" class="form-control" value="<%=u.getBiografia()%>" id="biografia">
                                        </div>
                                        <div class="form-group">
                                            <label for="link">Link</label>
                                            <input name="link" type="text" class="form-control" value="<%=u.getLinkUsuario()%>" id="link">
                                        </div>
                                    </div>
                                    <div class="form-group" style="text-align: center">
                                        <button type="submit" class="btn btn-primary">Modificar usuario</button>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--Usuarios que sigue-->
            <div class="container-fluid py-4">
                <div class="row">
                    <div class="col-12 col-xl-4">
                        <div class="card h-100">
                            <div class="card-header pb-0 p-3">
                                <h6 class="mb-0">Usuarios que sigo</h6>
                            </div>
                            <div class="card-body p-3">
                                <ul class="list-group">
                                    <% if (usuariosQueSigue.size() > 0) {
                                            for (UsuarioDTO usuarioqueSigue : usuariosQueSigue) {
                                    %>
                                    <li class="list-group-item border-0 d-flex align-items-center px-0 mb-2">
                                        <div class="avatar me-3">
                                            <img src="/web/imagenes?carpeta=usuarios&archivo=<%=usuarioqueSigue.getImagen()%>" alt="<%=usuarioqueSigue.getNickname()%>"  class="border-radius-lg shadow" style="width: 50px !important; height: 50px !important">
                                        </div>
                                        <div class="d-flex align-items-start flex-column justify-content-center">
                                            <h6 class="mb-0 text-sm"><%=usuarioqueSigue.getNickname()%></h6>
                                            <p class="mb-0 text-xs"><%=usuarioqueSigue.getNombre()%> <%=usuarioqueSigue.getApellido()%></p>
                                        </div>
                                        <a class="btn btn-link pe-3 ps-0 mb-0 ms-auto" href="/web/modificarusuario?dejardeseguir=<%=usuarioqueSigue.getId()%>">Dejar de seguir</a>
                                        <!--<a class="btn btn-link pe-3 ps-0 mb-0 ms-auto" href="/web/modificarusuario">Dejar de seguir</a>-->
                                    </li>
                                    <% }
                                        }%>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--Bloque espectaculos-->
            <% if (Constantes.ARTISTA.equalsIgnoreCase(tipoUsuario)) { %>
            <div class="container-fluid py-4">
                <div class="row">
                    <div class="col-12 col-xl-4">
                        <div class="card h-100">
                            <div class="card-header pb-0 p-3">
                                <h6 class="mb-0" style="background-color: wheat">Espectaculos Ingresados</h6>
                            </div>
                            <div class="card-body p-3">
                                <ul class="list-group">
                                    <% if (espectaculosDeUsuario.getEspectaculosIngresados().size() > 0) {
                                            for (EspectaculoDTO e : espectaculosDeUsuario.getEspectaculosIngresados()) {
                                    %>
                                    <li class="list-group-item border-0 d-flex align-items-center px-0 mb-2">
                                        <div class="avatar me-3">
                                            <img src="/web/imagenes?carpeta=espectaculos&archivo=<%=e.getImagen()%>" alt="<%=e.getNombre()%>"  class="border-radius-lg shadow" style="width: 50px !important; height: 50px !important">
                                        </div>
                                        <div class="d-flex align-items-start flex-column justify-content-center">
                                            <h6 class="mb-0 text-sm"><%=e.getNombre()%></h6>
                                            <p class="mb-0 text-xs"><%=e.getDescripcion()%> / <%=e.getUrl()%></p>
                                        </div>
                                        <!--<a class="btn btn-link pe-3 ps-0 mb-0 ms-auto" href="/web/modificarusuario?dejardeseguir=<%=e.getId()%>">Dejar de seguir</a>-->
                                        <a class="btn btn-link pe-3 ps-0 mb-0 ms-auto" href="/web/espectaculo/detalle.jsp?idEspectaculo=<%=e.getId()%>">Ver detalles</a>
                                    </li>
                                    <% }
                                        }%>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-xl-4">
                        <div class="card h-100">
                            <div class="card-header pb-0 p-3">
                                <h6 class="mb-0" style="background-color: greenyellow">Espectaculos Aceptados</h6>
                            </div>
                            <div class="card-body p-3">
                                <ul class="list-group">
                                    <% if (espectaculosDeUsuario.getEspectaculosAceptados().size() > 0) {
                                            for (EspectaculoDTO e : espectaculosDeUsuario.getEspectaculosAceptados()) {
                                    %>
                                    <li class="list-group-item border-0 d-flex align-items-center px-0 mb-2">
                                        <div class="avatar me-3">
                                            <img src="/web/imagenes?carpeta=espectaculos&archivo=<%=e.getImagen()%>" alt="<%=e.getNombre()%>"  class="border-radius-lg shadow" style="width: 50px !important; height: 50px !important">
                                        </div>
                                        <div class="d-flex align-items-start flex-column justify-content-center">
                                            <h6 class="mb-0 text-sm"><%=e.getNombre()%></h6>
                                            <p class="mb-0 text-xs"><%=e.getDescripcion()%> / <%=e.getUrl()%></p>
                                        </div>
                                        <a class="btn btn-link pe-3 ps-0 mb-0 ms-auto" href="/web/espectaculo/detalle.jsp?idEspectaculo=<%=e.getId()%>">Ver detalles</a>
                                        <a class="btn btn-link pe-3 ps-0 mb-0 ms-auto" href="/web/funcion/alta.jsp?idEspectaculo=<%=e.getId()%>">Agregar Función</a>
                                    </li>
                                    <% }
                                        }%>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-12 col-xl-4">
                        <div class="card h-100">
                            <div class="card-header pb-0 p-3">
                                <h6 class="mb-0" style="background-color: floralwhite">Espectaculos Rechazados</h6>
                            </div>
                            <div class="card-body p-3">
                                <ul class="list-group">
                                    <% if (espectaculosDeUsuario.getEspectaculosRechazados().size() > 0) {
                                            for (EspectaculoDTO e : espectaculosDeUsuario.getEspectaculosRechazados()) {
                                    %>
                                    <li class="list-group-item border-0 d-flex align-items-center px-0 mb-2">
                                        <div class="avatar me-3">
                                            <img src="/web/imagenes?carpeta=espectaculos&archivo=<%=e.getImagen()%>" alt="<%=e.getNombre()%>"  class="border-radius-lg shadow" style="width: 50px !important; height: 50px !important">
                                        </div>
                                        <div class="d-flex align-items-start flex-column justify-content-center">
                                            <h6 class="mb-0 text-sm"><%=e.getNombre()%></h6>
                                            <p class="mb-0 text-xs"><%=e.getDescripcion()%> / <%=e.getUrl()%></p>
                                        </div>
                                        <!--<a class="btn btn-link pe-3 ps-0 mb-0 ms-auto" href="/web/modificarusuario?dejardeseguir=<%=e.getId()%>">Dejar de seguir</a>-->
                                        <a class="btn btn-link pe-3 ps-0 mb-0 ms-auto" href="/web/espectaculo/detalle.jsp?idEspectaculo=<%=e.getId()%>">Ver detalles</a>
                                    </li>
                                    <% }
                                        }%>
                                </ul>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <% }%>
            <!--Bloque paquetes-->
            <div class="container-fluid py-4">
                <div class="row">
                    <% if (Constantes.ARTISTA.equalsIgnoreCase(tipoUsuario)) { %>
                    <div class="col-12 col-xl-4">
                        <div class="card h-100">
                            <div class="card-header pb-0 p-3">
                                <h6 class="mb-0" style="background-color: wheat">Mis Paquetes creados</h6>
                            </div>
                            <div class="card-body p-3">
                                <ul class="list-group">
                                    <% if (paquetesCreadosPorArtista.size() > 0) {
                                            for (PaqueteDTO i : paquetesCreadosPorArtista) {
                                    %>
                                    <li class="list-group-item border-0 d-flex align-items-center px-0 mb-2">
                                        <div class="avatar me-3">
                                            <img src="/web/imagenes?carpeta=paquetes&archivo=<%=i.getImagen()%>" alt="<%=i.getNombre()%>"  class="border-radius-lg shadow" style="width: 50px !important; height: 50px !important">
                                        </div>
                                        <div class="d-flex align-items-start flex-column justify-content-center">
                                            <h6 class="mb-0 text-sm"><%=i.getNombre()%></h6>
                                            <p class="mb-0 text-xs"><%=i.getDescripcion()%></p>
                                            <p class="mb-0 text-xs">Descuento: <%=i.getDescuento()%> %</p>
                                        </div>
                                        <a class="btn btn-link pe-3 ps-0 mb-0 ms-auto" href="/web/paquete/detalle.jsp?idPaquete=<%=i.getId()%>">Ver detalles</a>
                                    </li>
                                    <% }
                                        }%>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <% } %>
                    <div class="col-12 col-xl-4">
                        <div class="card h-100">
                            <div class="card-header pb-0 p-3">
                                <h6 class="mb-0" style="background-color: wheat">Mis Paquetes comprados</h6>
                            </div>
                            <div class="card-body p-3">
                                <ul class="list-group">
                                    <% if (paquetesCompradosPorUsuario.size() > 0) {
                                            for (PaqueteDTO i : paquetesCompradosPorUsuario) {
                                    %>
                                    <li class="list-group-item border-0 d-flex align-items-center px-0 mb-2">
                                        <div class="avatar me-3">
                                            <img src="/web/imagenes?carpeta=paquetes&archivo=<%=i.getImagen()%>" alt="<%=i.getNombre()%>"  class="border-radius-lg shadow" style="width: 50px !important; height: 50px !important">
                                        </div>
                                        <div class="d-flex align-items-start flex-column justify-content-center">
                                            <h6 class="mb-0 text-sm"><%=i.getNombre()%></h6>
                                            <p class="mb-0 text-xs"><%=i.getDescripcion()%></p>
                                            <p class="mb-0 text-xs">Descuento: <%=i.getDescuento()%> %</p>
                                        </div>
                                        <a class="btn btn-link pe-3 ps-0 mb-0 ms-auto" href="/web/paquete/detalle.jsp?idPaquete=<%=i.getId()%>">Ver detalles</a>
                                    </li>
                                    <% }
                                        }%>
                                </ul>
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