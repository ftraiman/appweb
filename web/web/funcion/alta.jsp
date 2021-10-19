<%@page import="edu.innova.webapp.dtos.EspectaculoDTO"%>
<%@page import="edu.innova.webapp.servlets.ServletAltaEspectaculo"%>
<%@page import="edu.innova.webapp.dtos.InformacionEspectaculoDTO"%>
<%@page import="edu.innova.webapp.servlets.ServletFuncion"%>
<%@page import="edu.innova.webapp.dtos.PlataformaDTO"%>
<%@page import="java.util.List"%>
<%@page import="edu.innova.webapp.dtos.CategoriaDTO"%>
<%@page import="edu.innova.webapp.logica.servicios.impl.ServicioMenu"%>
<%@page import="edu.innova.webapp.helpers.Constantes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String carpeta = "Función";
    String pagina = "Alta";

    UsuarioDTO u = (UsuarioDTO) request.getSession().getAttribute("usuario");

    if (u == null || !Constantes.ARTISTA.equalsIgnoreCase(u.getTipo()) || request.getParameter(Constantes.ID_ESPECTACULO) == null) {
        response.sendRedirect("/web/principal/index.jsp");
        return;
    }
    Long idEspectaculo = Long.valueOf(request.getParameter(Constantes.ID_ESPECTACULO));
    List<UsuarioDTO> artistasParaInvitar = ServletFuncion.getTodosLosArtisas(u.getId());
    InformacionEspectaculoDTO infoEspectaculo = ServletAltaEspectaculo.getEspectaculoPorId(idEspectaculo);

    EspectaculoDTO espectaculo = infoEspectaculo.getEspectaculo();
    CategoriaDTO categoria = infoEspectaculo.getCategoria();
    PlataformaDTO plataforma = infoEspectaculo.getPlataforma();
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
                <div class="col-sm-8">
                    <div class="form-group" style="text-align: center">
                        <h1>Alta de Función</h1>
                    </div>
                    <div class="row">
                        <div class="col-xl-6 col-sm-6 mb-xl-0 mb-4">
                            <div class="card">
                                <div class="card-body p-3">
                                    <div class="row">
                                        <div class="col-8">
                                            <div class="numbers">
                                                <p class="text-sm mb-0 text-capitalize font-weight-bold"><%=espectaculo.getNombre()%></p>
                                                <h5 class="font-weight-bolder mb-0">
                                                    $<%=espectaculo.getCosto()%>    
                                                </h5>
                                                <p class="text-sm mb-0 text-capitalize font-weight-bold">Categoría: <%=categoria.getNombre()%></p>
                                                <p class="text-sm mb-0 text-capitalize font-weight-bold">Plataforma: <%=plataforma.getNombre()%></p>
                                            </div>
                                        </div>
                                        <div class="col-4 text-end">
                                            <div class="icon icon-shape bg-gradient-primary shadow text-center border-radius-md">
                                                <i class="ni ni-basket text-lg opacity-10" aria-hidden="true"></i>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%  if (request.getAttribute(Constantes.ERROR) != null) {%>
                    <br />
                    <div class="alert alert-warning" role="alert">
                        <%= request.getAttribute("error")%>
                    </div>
                    <%  }%>
                    <form role="form" method="POST" action="/web/funcion" enctype="multipart/form-data">
                        <div class="form-group">
                            <input type="hidden" name="idEspectaculo" id="idEspectaculo" value="<%=idEspectaculo%>" />
                            <label for="nombre">Nombre</label>
                            <input name="nombre" type="text" class="form-control" id="nombre" required>
                        </div>
                        <div class="form-group">
                            <label for="fecha">Fecha de inicio</label>
                            <input name="fecha" type="datetime-local" class="form-control" id="fecha" required>
                        </div>
                        <select class="form-select" name="invitados" multiple aria-label="multiple select">
                            <option value="0" selected>Se puede seleccionar varios artistas</option>
                            <% for (UsuarioDTO artista : artistasParaInvitar) {%>
                            <option value="<%=artista.getId()%>"><%=artista.getNombre()%> <%=artista.getApellido()%></option>
                            <% }%>
                        </select>
                        <!--                        <div class="form-group">
                                                    <label for="imagen">Imagen</label>
                                                    <input name="imagen" type="file" class="form-control-file" id="imagen">
                                                </div>-->
                        <br />
                        <div class="form-group" style="text-align: center">
                            <button type="submit" class="btn btn-linkedin">Alta de Función</button>
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