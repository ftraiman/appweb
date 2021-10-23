<%@page import="edu.innova.webapp.helpers.Constantes"%>
<%@page import="java.util.List"%>
<%@page import="edu.innova.webapp.helpers.HelperFechas"%>
<%@page import="edu.innova.webapp.dtos.EspectaculoDTO"%>
<%@page import="edu.innova.webapp.dtos.FuncionDTO"%>
<%@page import="edu.innova.webapp.servlets.ServletFuncion"%>
<%@page import="edu.innova.webapp.dtos.InformacionFuncionDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String carpeta = "Función";
    String pagina = "Detalle";

    if (request.getParameter("idFuncion") == null) {
        response.sendRedirect("/web/principal/index.jsp");
        return;
    }
    Long idFuncion = Long.valueOf(request.getParameter("idFuncion"));

    InformacionFuncionDTO infoFuncion = ServletFuncion.getInformacionFuncion(idFuncion, request);
    FuncionDTO funcion = infoFuncion.getFuncion();
    EspectaculoDTO espectaculo = infoFuncion.getEspectaculo();
    UsuarioDTO artista = infoFuncion.getArtista();
    List<UsuarioDTO> artistasInvitados = funcion.getArtistasInvitados();
    Boolean isUsuarioLogueado = infoFuncion.getIsUsuarioLogueado();
    Boolean isUsuarioRegistradoEnFuncion = infoFuncion.getIsUsuarioRegistradoEnFuncion();
    Boolean isFuncionCompleta = infoFuncion.getIsFuncionCompleta();
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
                <div class="col-12 mt-4">
                    <div class="card mb-4">
                        <div class="card-header pb-0 p-3">
                            <h6 class="mb-1">Función: <%=funcion.getNombre()%></h6>
                            <br />
                            <p class="text-sm">Fecha: <b><%=HelperFechas.dateToString(funcion.getFechaInicio(), "yyyy-MM-dd HH:mm")%></b></p>
                            <div>Artista: 
                                <img src="/web/imagenes?carpeta=usuarios&archivo=<%=artista.getImagen()%>" class="avatar avatar-sm me-3" alt="<%=artista.getNickname()%>" >
                                <b><%=artista.getNombre()%> <%=artista.getApellido()%> <u>(<%=artista.getNickname()%>)</u></b>
                            </div>
                        </div>
                        <% if (isUsuarioLogueado) {%>
                        <div class="col-lg-4 col-md-6 my-sm-auto ms-sm-auto me-sm-0 mx-auto mt-1">
                            <div class="nav-wrapper position-relative end-0">
                                <ul class="nav nav-pills nav-fill p-1 bg-transparent" role="tablist">
                                    <li class="nav-item">
                                        <%if (isFuncionCompleta) { %>
                                        <div class="alert alert-light" role="alert">
                                            La Función se encuentra completa!
                                        </div>        
                                        <% } %>
                                        <%if (isUsuarioRegistradoEnFuncion) { %>
                                        <div class="alert alert-light" role="alert">
                                            Ya te encuentras registrado en la Función!
                                        </div>        
                                        <% } else if (!isFuncionCompleta) {%>
                                        <a class="nav-link mb-0 px-0 py-1 active " href="/web/funcion/registro.jsp?idFuncion=<%=funcion.getId()%>" >Regístrate en la Función</a>
                                        <% } %>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        <% } %>
                        <div class="card-body p-3">
                            <h4>Artistas invitados</h4>
                            <table class="table align-items-center mb-0">
                                <thead>
                                    <tr>
                                        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Usuario</th>
                                        <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Nombre</th>
                                        <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Rol</th>
                                        <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Fecha de nacimiento</th>
                                        <th class="text-secondary opacity-7"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% if (artistasInvitados.size() > 0) {
                                            for (UsuarioDTO artistaInvitado : artistasInvitados) {
                                                String lblTipo = Constantes.ARTISTA.equalsIgnoreCase(artistaInvitado.getTipo()) ? "Artista" : "Espectador";
                                                String colorTipo = lblTipo.equals("Artista") ? "success" : "info";
                                                String fechaNacimiento = HelperFechas.dateToString(artistaInvitado.getFechaNacimiento(), "dd/MM/yyyy");
                                    %>
                                    <tr>
                                        <td>
                                            <div class="d-flex px-2 py-1">
                                                <div>
                                                    <img src="/web/imagenes?carpeta=usuarios&archivo=<%=artistaInvitado.getImagen()%>" class="avatar avatar-sm me-3" alt="<%=artistaInvitado.getNickname()%>">
                                                </div>
                                                <div class="d-flex flex-column justify-content-center">
                                                    <h6 class="mb-0 text-sm"><%=artistaInvitado.getNickname()%></h6>
                                                    <p class="text-xs text-secondary mb-0"><%=artistaInvitado.getEmail()%></p>
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            <p class="text-xs font-weight-bold mb-0"><%=artistaInvitado.getNombre()%> <%=artistaInvitado.getApellido()%></p>
                                        </td>
                                        <td class="align-middle text-center text-sm">
                                            <span class="badge badge-sm bg-gradient-<%=colorTipo%>"><%=lblTipo%></span>
                                        </td>
                                        <td class="align-middle text-center">
                                            <span class="text-secondary text-xs font-weight-bold"><%=fechaNacimiento%></span>
                                        </td>
                                        <td class="align-middle">
                                            <a class="btn btn-link pe-3 ps-0 mb-0 ms-auto" href="/web/informacionusuario?idUsuario=<%=artistaInvitado.getId()%>">Ver perfil</a>
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

            <div class="container-fluid">
                <div class="col-12 mt-4">
                    <div class="card mb-4">
                        <div class="card-header pb-0 p-3">
                            <h6 class="mb-1">Espectáculo : <%=espectaculo.getNombre()%></h6>
                        </div>
                        <div class="card-body p-3">
                            <div class="row">
                                <div class="col-xl-6 col-md-12 mb-xl-0 mb-12">
                                    <div class="card card-blog card-plain">
                                        <div class="position-relative">
                                            <a class="d-block shadow-xl border-radius-xl">
                                                <img src="/web/imagenes?carpeta=espectaculos&archivo=<%=espectaculo.getImagen()%>"  class="img-fluid shadow border-radius-xl" style="height: 200px ">
                                            </a>
                                        </div>
                                        <div class="card-body px-1 pb-0">
                                            <p class="text-gradient text-dark mb-2 text-sm"><b>Descripción</b></p>
                                            <p class="text-gradient text-dark mb-2 text-sm"><%=espectaculo.getDescripcion()%></p>
                                            <p class="text-gradient text-dark mb-2 text-sm"><b>Duración</b> <%=espectaculo.getDuracion()%> minutos</p>
                                            <p class="text-gradient text-dark mb-2 text-sm"><b>Espectadores Mínimos</b> <%=espectaculo.getEspectadoresMinimos()%> minutos</p>
                                            <p class="text-gradient text-dark mb-2 text-sm"><b>Espectadores Máximos</b> <%=espectaculo.getEspectadoresMaximos()%> minutos</p>
                                            <p class="mb-4 text-sm"><b>Link a Espectáculo:</b><%=espectaculo.getUrl()%></p>
                                        </div>
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