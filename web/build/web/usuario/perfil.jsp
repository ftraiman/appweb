<%@page import="edu.innova.webapp.dtos.FuncionDTO"%>
<%@page import="edu.innova.webapp.dtos.PlataformaDTO"%>
<%@page import="edu.innova.webapp.dtos.CategoriaDTO"%>
<%@page import="edu.innova.webapp.servlets.ServletAltaEspectaculo"%>
<%@page import="edu.innova.webapp.dtos.InformacionEspectaculoDTO"%>
<%@page import="edu.innova.webapp.helpers.HelperFechas"%>
<%@page import="edu.innova.webapp.dtos.EspectaculoDTO"%>
<%@page import="edu.innova.webapp.servlets.ServletInformacionUsuario"%>
<%@page import="edu.innova.webapp.dtos.EspectaculosDeUsuario"%>
<%@page import="java.util.List"%>
<%@page import="edu.innova.webapp.helpers.Constantes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String carpeta = "Usuario";
    String pagina = "Perfil";

    UsuarioDTO usuarioInfo = (UsuarioDTO) request.getAttribute(Constantes.USUARIO);
    String tipo = usuarioInfo.getTipo().equalsIgnoreCase(Constantes.ARTISTA) ? "Artista" : "Espectador";
    List<UsuarioDTO> usuariosQueSigue = (List<UsuarioDTO>) request.getAttribute("usuariosQueSigue");
    List<UsuarioDTO> usuariosQueLoSiguen = (List<UsuarioDTO>) request.getAttribute("usuariosQueLoSiguen");
    Boolean puedeSeguirUsuario = (Boolean) request.getAttribute("puedeSeguirUsuario");
    String lblSeguirUsuario = "";
    if (puedeSeguirUsuario != null) {
        lblSeguirUsuario = puedeSeguirUsuario ? "Seguir usuario" : "Dejar de seguir";
    }

    EspectaculosDeUsuario espectaculosDeUsuario = new EspectaculosDeUsuario();
    if (Constantes.ARTISTA.equalsIgnoreCase(tipo)) {
        espectaculosDeUsuario = ServletInformacionUsuario.getEspectaculosDeUsuarioPorIdArtista(usuarioInfo.getId());
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
                <div class="container-fluid">
                    <div class="page-header min-height-300 border-radius-xl mt-4" style="background-image: url('/web/assets/img/curved-images/curved0.jpg'); background-position-y: 50%;">
                        <span class="mask bg-gradient-primary opacity-6"></span>
                    </div>
                    <div class="card card-body blur shadow-blur mx-4 mt-n6 overflow-hidden">
                        <div class="row gx-4">
                            <div class="col-auto">
                                <div class="avatar avatar-xl position-relative">
                                    <img src="/web/imagenes?carpeta=usuarios&archivo=<%=usuarioInfo.getImagen()%>" alt="profile_image" class="w-100 border-radius-lg shadow-sm" >
                                </div>
                            </div>
                            <div class="col-auto my-auto">
                                <div class="h-100">
                                    <h5 class="mb-1">
                                        <%=usuarioInfo.getNombre()%> <%=usuarioInfo.getApellido()%>
                                    </h5>
                                    <p class="mb-0 font-weight-bold text-sm">
                                        <%=usuarioInfo.getNickname()%> / <%=tipo%>
                                    </p>
                                </div>
                            </div>
                            <% if (puedeSeguirUsuario != null) { %>
                            <div class="col-lg-4 col-md-6 my-sm-auto ms-sm-auto me-sm-0 mx-auto mt-3">
                                <div class="nav-wrapper position-relative end-0">
                                    <ul class="nav nav-pills nav-fill p-1 bg-transparent" role="tablist">
                                        <li class="nav-item">
                                            <% if (puedeSeguirUsuario) {%>
                                            <a class="nav-link mb-0 px-0 py-1 active " href="/web/modificarusuario?seguirusuario=<%=usuarioInfo.getId()%>" />
                                            <% } else {%>
                                            <a class="nav-link mb-0 px-0 py-1 active " href="/web/modificarusuario?dejardeseguir=<%=usuarioInfo.getId()%>" />
                                            <% }%>
                                            <span class="ms-1"><%=lblSeguirUsuario%></span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <% }%>
                        </div>
                    </div>
                </div>
                <div class="container-fluid py-4">
                    <div class="row">
                        <div class="col-12 col-xl-4">
                            <div class="card h-100">
                                <div class="card-header pb-0 p-3">
                                    <h6 class="mb-0">Usuarios que sigue <b><u><%= usuarioInfo.getNickname()%></u></b></h6>
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
                                            <a class="btn btn-link pe-3 ps-0 mb-0 ms-auto" href="/web/informacionusuario?idUsuario=<%=usuarioqueSigue.getId()%>">Ver perfil</a>
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
                                    <h6 class="mb-0">Usuarios que lo siguen a <b><u><%= usuarioInfo.getNickname()%></u></b></h6>
                                </div>
                                <div class="card-body p-3">
                                    <ul class="list-group">
                                        <% if (usuariosQueLoSiguen.size() > 0) {
                                                for (UsuarioDTO usuarioqueLoSigue : usuariosQueLoSiguen) {
                                        %>
                                        <li class="list-group-item border-0 d-flex align-items-center px-0 mb-2">
                                            <div class="avatar me-3">
                                                <img src="/web/imagenes?carpeta=usuarios&archivo=<%=usuarioqueLoSigue.getImagen()%>" alt="<%=usuarioqueLoSigue.getNickname()%>"  class="border-radius-lg shadow" style="width: 50px !important; height: 50px !important">
                                            </div>
                                            <div class="d-flex align-items-start flex-column justify-content-center">
                                                <h6 class="mb-0 text-sm"><%=usuarioqueLoSigue.getNickname()%></h6>
                                                <p class="mb-0 text-xs"><%=usuarioqueLoSigue.getNombre()%> <%=usuarioqueLoSigue.getApellido()%></p>
                                            </div>
                                            <a class="btn btn-link pe-3 ps-0 mb-0 ms-auto" href="/web/informacionusuario?idUsuario=<%=usuarioqueLoSigue.getId()%>">Ver perfil</a>
                                        </li>
                                        <% }
                                            }%>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <% if (Constantes.ARTISTA.equalsIgnoreCase(tipo)) { %>
                    <div class="container-fluid py-4">
                <div class="row">
                    <div class="col-12">
                        <div class="card mb-4">
                            <div class="card-header pb-0">
                                <h6>Espectáculos del Artista</h6>
                            </div>
                            <div class="card-body px-0 pt-0 pb-2">
                                <div class="table-responsive p-0">
                                    <table class="table align-items-center mb-0">
                                        <thead>
                                            <tr>
                                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Detalles</th>
                                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">Plataforma</th>
                                                <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Categoría</th>
                                                <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Costo ($)</th>
                                                <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Duración (minutos)</th>
                                                <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Url</th>
                                                <th class="text-secondary opacity-7"></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <% if (espectaculosDeUsuario.getEspectaculosAceptados().size() > 0) {
                                            for (EspectaculoDTO e : espectaculosDeUsuario.getEspectaculosAceptados()) {
                                                
                                                InformacionEspectaculoDTO infoEspectaculo = ServletAltaEspectaculo.getEspectaculoPorId(e.getId());
                                                
                                                EspectaculoDTO espectaculo = infoEspectaculo.getEspectaculo();
                                                CategoriaDTO categoria = infoEspectaculo.getCategoria();
                                                PlataformaDTO plataforma = infoEspectaculo.getPlataforma();
                                                List<FuncionDTO> funciones = espectaculo.getFunciones();
                                                UsuarioDTO artista = infoEspectaculo.getUsuario();
                                                
                                                String nombre = espectaculo.getNombre();
//                                                String colorTipo = lblTipo.equals("Artista") ? "success" : "info";
                                                String fechaNacimiento = HelperFechas.dateToString(espectaculo.getFechaRegistro(), "dd/MM/yyyy");
                                            %>
                                            <tr>
                                                <td>
                                                    <div class="d-flex px-2 py-1">
                                                        <div>
                                                            <img src="/web/imagenes?carpeta=espectaculos&archivo=<%=espectaculo.getImagen()%>" class="avatar avatar-sm me-3" alt="<%=espectaculo.getNombre()%>">
                                                        </div>
                                                        <div class="d-flex flex-column justify-content-center">
                                                            <h6 class="mb-0 text-sm"><%=espectaculo.getNombre()%></h6>
                                                            <p class="text-xs text-secondary mb-0"><%=espectaculo.getDescripcion()%></p>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <p class="text-xs font-weight-bold mb-0"><%=plataforma.getNombre()%></p>
                                                </td>
                                                <td class="align-middle text-center text-sm">
                                                    <%=categoria.getNombre()%>
                                                </td>
                                                <td class="align-middle text-center">
                                                    <span class="text-secondary text-xs font-weight-bold"><%=espectaculo.getCosto()%></span>
                                                </td>
                                                <td class="align-middle text-center">
                                                    <span class="text-secondary text-xs font-weight-bold"><%=espectaculo.getDuracion()%></span>
                                                </td>
                                                <td class="align-middle text-center">
                                                    <span class="text-secondary text-xs font-weight-bold"><%=espectaculo.getUrl()%></span>
                                                </td>
                                                <td class="align-middle">
                                                    <a class="btn btn-link pe-3 ps-0 mb-0 ms-auto" href="/web/espectaculo/detalle.jsp?idEspectaculo=<%=espectaculo.getId()%>">Ver más detalles</a>                                                    
                                                </td>
                                            </tr>
                                          <%  } } %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

                <% }%>

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