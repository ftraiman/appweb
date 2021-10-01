<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String carpeta = "Principal";
    String pagina = "Alta Usuario";
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
            <div class="container-fluid">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-2">
                        </div>
                        <div class="col-sm-8">
                            <div class="form-group" style="text-align: center">
                                <h1>Alta de Usuario</h1>
                            </div>
                            <form role="form" method="POST" action="/web/altausuario">
                                <div class="form-group">
                                    <label for="nickname">Nickname</label>
                                    <input name="nickname" type="text" class="form-control" id="nickname">

                                    <% if (request.getAttribute("errorNickname") != null) {
                                    %>
                                    <label>
                                      <%=request.getAttribute("errorNickname") %>  
                                    </label>
                                    <%
                                        }
                                    %>
                                </div>
                                <div class="form-group">
                                    <label for="nombre">Nombre</label>
                                    <input name="nombre" type="text" class="form-control" id="nombre">
                                </div>
                                <div class="form-group">
                                    <label for="nombre">Apellido</label>
                                    <input name="apellido" type="text" class="form-control" id="apellido">
                                </div>
                                <div class="form-group">
                                    <label for="fechanacimiento">Fecha de nacimiento</label>
                                    <input name="fechanacimiento" type="date" class="form-control" id="fechanacimiento">
                                </div>
                                <div class="form-group">
                                    <label for="exampleFormControlSelect1">Seleccionar tipo de usuario</label>
                                    <select name="tipo" id="tipo" class="form-control" id="exampleFormControlSelect1">
                                        <option value="espectador" >Espectador</option>
                                        <option value="artista">Artista</option>
                                    </select>
                                </div>
                                <div id="datosAdicionales" class="collapse"> 
                                    <div class="form-group">
                                        <label for="descripcion">Descripción</label>
                                        <input name="descripcion" type="text" class="form-control" id="descripcion">
                                    </div>
                                    <div class="form-group">
                                        <label for="biografia">Biografía</label>
                                        <input name="biografia" type="text" class="form-control" id="biografia">
                                    </div>
                                    <div class="form-group">
                                        <label for="link">Link</label>
                                        <input name="link" type="text" class="form-control" id="link">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input name="email" type="email" class="form-control" id="email">
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input name="password" type="password" class="form-control" id="password">
                                </div>
                                <div class="form-group" style="text-align: center">
                                    <button type="submit" class="btn btn-primary">Alta de usuario</button>
                                </div>

                            </form>
                        </div>
                        <div class="col-sm-2">

                        </div>
                    </div>
                </div>
                <script type="text/javascript">
                    $(document).ready(function () {
                        $('#tipo').on('change', function () {
                            if (this.value === 'artista') {
                                $('#datosAdicionales').addClass("show");
                            } else {
                                $('#datosAdicionales').removeClass("show");
                            }
                        });

                    });
                </script>

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