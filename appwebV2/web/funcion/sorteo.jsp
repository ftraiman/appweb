<%@page import="edu.innova.webapp.helpers.HelperFechas"%>
<%@page import="java.util.List"%>
<%@page import="edu.innova.webapp.helpers.Constantes"%>
<%@page import="edu.innova.webapp.servlets.ServletFuncion"%>
<%@page import="edu.innova.webapp.dtos.InformacionFuncionDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String carpeta = "Función";
    String pagina = "Sorteo";

    UsuarioDTO usuarioLogueado = (UsuarioDTO) session.getAttribute(Constantes.USUARIO);

    if (request.getParameter("idFuncion") == null || usuarioLogueado == null) {
        response.sendRedirect("/webV2/principal/index.jsp");
        return;
    }

    Long idFuncion = Long.valueOf(request.getParameter("idFuncion"));
    Long idUsuario = usuarioLogueado.getId();
    InformacionFuncionDTO infoFuncion = ServletFuncion.getInformacionFuncion(idFuncion, request);

    List<UsuarioDTO> usuariosEnFuncion = ServletFuncion.getUsuariosEnFuncion(idFuncion);
    List<UsuarioDTO> usuariosGanadores = ServletFuncion.getGanadoresDelSorteo(idFuncion, 2);
//    ServletFuncion.registrarGanadoresDelSorteo(idFuncion, idUsuarios, infoFuncion.getFuncion().getDescripcionPremios());

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
                <div class="row">
                    <div class="col-12">
                        <div class="card mb-4">
                            <div class="card-header pb-0">
                                <h6>Usuarios registrados a la Función</h6>
                            </div>
                            <div class="card-body px-0 pt-0 pb-2">
                                <div class="table-responsive p-0">
                                    <table class="table align-items-center mb-0">
                                        <thead>
                                            <tr>
                                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Usuario</th>
                                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Nombre</th>
                                                <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Rol</th>
                                                <th class="text-secondary opacity-7"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% if (usuariosEnFuncion.size() > 0) {
                                                    for (UsuarioDTO usuarioSistema : usuariosEnFuncion) {
                                                        String lblTipo = Constantes.ARTISTA.equalsIgnoreCase(usuarioSistema.getTipo()) ? "Artista" : "Espectador";
                                                        String colorTipo = lblTipo.equals("Artista") ? "success" : "info";
                                            %>
                                            <tr>
                                                <td>
                                                    <div class="d-flex px-2 py-1">
                                                        <div>
                                                            <img src="/webV2/imagenes?carpeta=usuarios&archivo=<%=usuarioSistema.getImagen()%>" class="avatar avatar-sm me-3" alt="<%=usuarioSistema.getNickname()%>">
                                                        </div>
                                                        <div class="d-flex flex-column justify-content-center">
                                                            <h6 class="mb-0 text-sm"><%=usuarioSistema.getNickname()%></h6>
                                                            <p class="text-xs text-secondary mb-0"><%=usuarioSistema.getEmail()%></p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <p class="text-xs font-weight-bold mb-0"><%=usuarioSistema.getNombre()%> <%=usuarioSistema.getApellido()%></p>
                                                </td>
                                                <td class="align-middle text-center text-sm">
                                                    <span class="badge badge-sm bg-gradient-<%=colorTipo%>"><%=lblTipo%></span>
                                                </td>
                                                <td class="align-middle">
                                                    <a class="btn btn-link pe-3 ps-0 mb-0 ms-auto" href="/webV2/informacionusuario?idUsuario=<%=usuarioSistema.getId()%>">Ver perfil</a>
                                                    <!--                                                    <a href="javascript:;" class="text-secondary font-weight-bold text-xs" data-toggle="tooltip" data-original-title="Edit user">
                                                                                                            Ver perfil
                                                                                                        </a>-->
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
            <div class="container-fluid py-4">
                <div class="row">
                    <div class="col-12">
                        <div class="card mb-4">
                            <div class="card-header pb-0">
                                <h6>Usuarios Ganadores</h6>
                            </div>
                            <div class="card-body px-0 pt-0 pb-2">
                                <div class="table-responsive p-0">
                                    <table class="table align-items-center mb-0">
                                        <thead>
                                            <tr>
                                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Usuario</th>
                                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Nombre</th>
                                                <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Rol</th>
                                                <th class="text-secondary opacity-7"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% if (usuariosGanadores.size() > 0) {
                                                    for (UsuarioDTO usuarioSistema : usuariosGanadores) {
                                                        String lblTipo = Constantes.ARTISTA.equalsIgnoreCase(usuarioSistema.getTipo()) ? "Artista" : "Espectador";
                                                        String colorTipo = lblTipo.equals("Artista") ? "success" : "info";
                                            %>
                                            <tr>
                                                <td>
                                                    <div class="d-flex px-2 py-1">
                                                        <div>
                                                            <img src="/webV2/imagenes?carpeta=usuarios&archivo=<%=usuarioSistema.getImagen()%>" class="avatar avatar-sm me-3" alt="<%=usuarioSistema.getNickname()%>">
                                                        </div>
                                                        <div class="d-flex flex-column justify-content-center">
                                                            <h6 class="mb-0 text-sm"><%=usuarioSistema.getNickname()%></h6>
                                                            <p class="text-xs text-secondary mb-0"><%=usuarioSistema.getEmail()%></p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <p class="text-xs font-weight-bold mb-0"><%=usuarioSistema.getNombre()%> <%=usuarioSistema.getApellido()%></p>
                                                </td>
                                                <td class="align-middle text-center text-sm">
                                                    <span class="badge badge-sm bg-gradient-<%=colorTipo%>"><%=lblTipo%></span>
                                                </td>
                                                <td class="align-middle">
                                                    <a class="btn btn-link pe-3 ps-0 mb-0 ms-auto" href="/webV2/informacionusuario?idUsuario=<%=usuarioSistema.getId()%>">Ver perfil</a>
                                                    <!--                                                    <a href="javascript:;" class="text-secondary font-weight-bold text-xs" data-toggle="tooltip" data-original-title="Edit user">
                                                                                                            Ver perfil
                                                                                                        </a>-->
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