<%@page import="edu.innova.logica.Constantes"%>
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
                    <div class="col-lg-5">
                        <div class="card p-3">
                            <div class="overflow-hidden position-relative border-radius-lg bg-cover h-100" id="collapseExample" >
                                <span class=""></span>
                                <h4 style="text-align: center">Modificar datos</h4>
                                <form role="form" method="POST" action="/web/altausuario" enctype="multipart/form-data">
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
                                        <input name="fechanacimiento" type="date" class="form-control" id="fechanacimiento" value="<%= HelperFechas.dateToString(u.getFechaNacimiento(), "yyyy-MM-dd") %>" required>
                                    </div>
                                    <div id="datosAdicionales" class="collapse <%= Constantes.ARTISTA.equalsIgnoreCase(u.getTipo()) ? "show" : "" %>"> 
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
                                        


                <%@include file="../common/footer.jsp" %>
            </div>
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