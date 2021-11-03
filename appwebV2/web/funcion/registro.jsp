<%@page import="java.math.BigDecimal"%>
<%@page import="edu.innova.webapp.dtos.PaqueteDTO"%>
<%@page import="edu.innova.webapp.dtos.InformacionCanjePaqueteDTO"%>
<%@page import="edu.innova.webapp.dtos.FuncionDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="edu.innova.webapp.dtos.InformacionCanjeTresPorUno"%>
<%@page import="edu.innova.webapp.dtos.EspectaculoDTO"%>
<%@page import="edu.innova.webapp.servlets.ServletRegistroEnFuncion"%>
<%@page import="edu.innova.webapp.servlets.ServletFuncion"%>
<%@page import="edu.innova.webapp.dtos.InformacionFuncionDTO"%>
<%@page import="edu.innova.webapp.helpers.Constantes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String carpeta = "Función";
    String pagina = "Registro";

    UsuarioDTO usuarioLogueado = (UsuarioDTO) session.getAttribute(Constantes.USUARIO);

    if (request.getParameter("idFuncion") == null || usuarioLogueado == null) {
        response.sendRedirect("/webV2/principal/index.jsp");
        return;
    }
    Long idFuncion = Long.valueOf(request.getParameter("idFuncion"));
    Long idUsuario = usuarioLogueado.getId();
    InformacionFuncionDTO infoFuncion = ServletFuncion.getInformacionFuncion(idFuncion, request);
    if (ServletRegistroEnFuncion.isDatosInvalidos(infoFuncion)) {
        response.sendRedirect("/webV2/principal/index.jsp");
        return;
    }
    EspectaculoDTO espectaculo = infoFuncion.getEspectaculo();
    Long idEspectaculo = espectaculo.getId();

    InformacionCanjeTresPorUno ictpu = ServletFuncion.getInfoCanjeTresPorUno(idUsuario);
    List<FuncionDTO> funcionesParaCanjear = ictpu.getFuncionesCanjeables();

    InformacionCanjePaqueteDTO icpdto = ServletFuncion.getInfoCanjePaquete(idUsuario, idFuncion);
    List<PaqueteDTO> paquetes = icpdto.getPaquetesCanjeables();
    BigDecimal costoEspectaculo = espectaculo.getCosto();


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
            <%  if (request.getAttribute(Constantes.ERROR) != null) {%>
            <br />
            <div class="alert alert-warning" role="alert">
                <%= request.getAttribute(Constantes.ERROR)%>
            </div>
            <%  }%>
            <div class="container-fluid py-4">
                <!-- Compra directa-->
                <div class="col-12 mt-4">
                    <div class="card mb-4">
                        <div class="card-header pb-0 p-3">
                            <h3 class="mb-1">Compra directa</h3>
                            <br />
                            <p class="text-sm">Costo: <b><%=espectaculo.getCosto()%> $</b></p>
                            <form role="form" method="POST" action="/webV2/registrofuncion" enctype="multipart/form-data">
                                <input type="hidden" name="operacion" value="compradirecta"/>
                                <input type="hidden" name="idFuncion" value="<%=idFuncion%>"/>
                                <div class="form-group" style="text-align: center">
                                    <button type="submit" class="btn btn-linkedin">Adquirir función</button>
                                </div>
                            </form>
                        </div> 
                    </div>
                </div>
            </div>
            <!-- Canje 3x1 -->
            <div class="container-fluid py-4">
                <div class="col-12 mt-4">
                    <div class="card mb-4">
                        <div class="card-header pb-0 p-3">
                            <h3 class="mb-1">Canje 3 x 1</h3>
                            <br />
                            <p class="text-sm">Costo: <b>0 $</b></p>
                            <form role="form" method="POST" action="/webV2/registrofuncion" enctype="multipart/form-data">
                                <input type="hidden" name="operacion" value="canjetresporuno"/>
                                <input type="hidden" name="idFuncion" value="<%=idFuncion%>"/>
                                <input type="hidden" name="idEspectaculo" value="<%=idEspectaculo%>"/>
                                <p><b>Por favor selecciona 3 funciones</b></p>
                                <div class="form-group" style="text-align: center">
                                    <select name="tresporuno" class="form-select" size="3" aria-label="Selecciona 3 funciones para cambiar" multiple="true">
                                        <%
                                            for (FuncionDTO funcion : funcionesParaCanjear) {
                                        %>
                                        <option value="<%=funcion.getId()%>"><%=funcion.getNombre()%></option>
                                        <% }%>
                                    </select>
                                </div>
                                <div class="form-group" style="text-align: center">
                                    <button type="submit" class="btn btn-linkedin">Canjear 3 funciones por una</button>
                                </div>
                            </form>
                        </div> 
                    </div>
                </div>
            </div>
            <!-- Canje Paquete -->
            <div class="container-fluid py-4">
                <div class="col-12 mt-4">
                    <div class="card mb-4">
                        <div class="card-header pb-0 p-3">
                            <h3 class="mb-1">Canje Paquete</h3>
                            <br />
                            <p class="text-sm">Costo: <b>Depende del Paquete</b></p>
                            <form role="form" method="POST" action="/webV2/registrofuncion" enctype="multipart/form-data">
                                <input type="hidden" name="operacion" value="canjepaquete"/>
                                <input type="hidden" name="idFuncion" value="<%=idFuncion%>"/>
                                <input type="hidden" name="idEspectaculo" value="<%=idEspectaculo%>"/>
                                <p><b>Por favor selecciona 1 Paquete</b></p>
                                <div class="form-group" style="text-align: center">
                                    <select name="idPaquete" class="form-select" size="3" aria-label="Selecciona 1 Paquete">
                                        <%
                                            for (PaqueteDTO paquete : paquetes) {
                                        %>
                                        <option value="<%=paquete.getId()%>"><%=paquete.getNombre()%> (Descuento <%=paquete.getDescuento()%>)</option>
                                        <% }%>
                                    </select>
                                </div>
                                <div class="form-group" style="text-align: center">
                                    <button type="submit" class="btn btn-linkedin">Adquirir Función usando Paquete</button>
                                </div>
                            </form>
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