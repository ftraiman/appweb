<%@page import="edu.innova.webapp.helpers.HelperFechas"%>
<%@page import="edu.innova.webapp.dtos.FuncionDTO"%>
<%@page import="edu.innova.webapp.dtos.EspectaculoDTO"%>
<%@page import="edu.innova.webapp.servlets.ServletAltaEspectaculo"%>
<%@page import="edu.innova.webapp.logica.servicios.impl.ServicioMenu"%>
<%@page import="edu.innova.webapp.dtos.PlataformaDTO"%>
<%@page import="edu.innova.webapp.dtos.CategoriaDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String carpeta = "Principal";
    String pagina = "Inicio";

    List<CategoriaDTO> categorias = ServicioMenu.getCategoriasDTO();
    List<PlataformaDTO> plataformas = ServicioMenu.getPlataformas();

    Long idCategoria = request.getParameter("categoria") != null && request.getParameter("categoria") != "" ? Long.valueOf(request.getParameter("categoria")) : null;
    Long idPlataforma = request.getParameter("plataforma") != null && request.getParameter("plataforma") != "" ? Long.valueOf(request.getParameter("plataforma")) : null;

    List<EspectaculoDTO> espectaculos = ServletAltaEspectaculo.getEspectaculosPorPlataformaCategoria(idPlataforma, idCategoria);

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
                <form role="form" method="POST" action="/webV2/espectaculo/consulta.jsp">
                    <div class="row">
                        <div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
                            <div class="card">
                                <div class="card-body p-3">
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="form-group">
                                                <label for="categoria">Seleccionar categoría</label>
                                                <select name="categoria" id="categoria" class="form-control">
                                                    <option value="">Seleccionar categoría</option>
                                                    <% for (CategoriaDTO categoria : categorias) {%>
                                                    <option value="<%=categoria.getId()%>" ><%=categoria.getNombre()%></option>
                                                    <% }%>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
                            <div class="card">
                                <div class="card-body p-3">
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="form-group">
                                                <label for="plataforma">Seleccionar plataforma</label>
                                                <select name="plataforma" id="plataforma" class="form-control">
                                                    <option value="">Seleccionar plataforma</option>
                                                    <% for (PlataformaDTO plataforma : plataformas) {%>
                                                    <option value="<%=plataforma.getId()%>" ><%=plataforma.getNombre()%></option>
                                                    <% }%>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
                            <div class="card">
                                <div class="card-body p-3">
                                    <div class="row">
                                        <div class="col-8">
                                            <div class="form-group">
                                                <label for="filtro">Ejecutar filtro</label>
                                                <br />
                                                <button type="submit" class="btn btn-youtube">Filtrar</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <% if (espectaculos.size() > 0) {
                    for (EspectaculoDTO espectaculo : espectaculos) {
                        Long idEspectaculo = espectaculo.getId();
                        String nombreEspectaculo = espectaculo.getNombre();
                        String plataforma = espectaculo.getPlataforma().getNombre();
                        String categoria = espectaculo.getCategoria().getNombre();
                        String imagen = espectaculo.getImagen();
                        List<FuncionDTO> funciones = espectaculo.getFunciones();
            %>
            <div class="container-fluid py-4">
                <div class="row mt-4">
                    <div class="col-lg-12 mb-lg-0 mb-4">
                        <div class="card">
                            <div class="card-body p-3">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <div class="d-flex flex-column h-50">
                                            <h5 class="font-weight-bolder"><%=nombreEspectaculo%></h5>
                                            <p class="mb-1 pt-2 text-bold">Nombre y apellido (nickname artista)</p>
                                            <p class="mb-0">Plataforma: <%=plataforma%></p>
                                            <p class="mb-5">Categoría: <%=categoria%></p>
                                            <a class="text-body text-sm font-weight-bold mb-0 icon-move-right mt-auto" href="/webV2/espectaculo/detalle.jsp?idEspectaculo=<%=idEspectaculo%>">
                                                Ver detalles
                                                <i class="fas fa-arrow-right text-sm ms-1" aria-hidden="true"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="col-lg-5 ms-auto text-center mt-5 mt-lg-0">
                                        <div class="border-radius-lg">
                                            <img src="/webV2/imagenes?carpeta=espectaculos&archivo=<%=imagen%>" alt="img-blur-shadow" class="img-fluid shadow border-radius-xl" style="height: 200px ">
                                        </div>
                                    </div>
                                </div>
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
                        </div>
                    </div>
                </div>
            </div>
            <% }
                }%>
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