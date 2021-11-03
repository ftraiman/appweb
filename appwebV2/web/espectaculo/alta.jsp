<%@page import="edu.innova.webapp.dtos.PlataformaDTO"%>
<%@page import="java.util.List"%>
<%@page import="edu.innova.webapp.dtos.CategoriaDTO"%>
<%@page import="edu.innova.webapp.logica.servicios.impl.ServicioMenu"%>
<%@page import="edu.innova.webapp.helpers.Constantes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String carpeta = "Espectáculo";
    String pagina = "Alta";

    UsuarioDTO u = (UsuarioDTO) request.getSession().getAttribute("usuario");
    if (u == null || !Constantes.ARTISTA.equalsIgnoreCase(u.getTipo())) {
        response.sendRedirect("/webV2/principal/index.jsp");
        return;
    }
    List<CategoriaDTO> categorias = ServicioMenu.getCategoriasDTO();
    List<PlataformaDTO> plataformas = ServicioMenu.getPlataformas();

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
                <div class="col-sm-2">
                </div>
                <div class="col-sm-8">
                    <div class="form-group" style="text-align: center">
                        <h1>Alta de Espectáculo</h1>
                    </div>
                    <%  if (request.getAttribute(Constantes.ERROR) != null) {%>
                    <div class="alert alert-warning" role="alert">
                        <%= request.getAttribute("error")%>
                    </div>
                    <%  }%>
                    <form role="form" method="POST" action="/webV2/altaespectaculo" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="categoria">Seleccionar categoría</label>
                            <select name="categoria" id="categoria" class="form-control">
                                <% for (CategoriaDTO categoria : categorias) {%>
                                <option value="<%=categoria.getId()%>" ><%=categoria.getNombre()%></option>
                                <% }%>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="plataforma">Seleccionar plataforma</label>
                            <select name="plataforma" id="plataforma" class="form-control">
                                <% for (PlataformaDTO plataforma : plataformas) {%>
                                <option value="<%=plataforma.getId()%>" ><%=plataforma.getNombre()%></option>
                                <% }%>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="nombre">Nombre</label>
                            <input name="nombre" type="text" class="form-control" id="nombre" required>
                        </div>
                        <div class="form-group">
                            <label for="descripcion">Descripción</label>
                            <input name="descripcion" type="text" class="form-control" id="descripcion">
                        </div>
                        <div class="form-group">
                            <label for="url">Link</label>
                            <input name="url" type="text" class="form-control" id="url">
                        </div>
                        <div class="form-group">
                            <label for="duracion">Duración (minutos)</label>
                            <input name="duracion" type="number"  id="duracion" required>
                        </div>
                        <div class="form-group">
                            <label for="espectadoresMinimos">Espectadores mínimos</label>
                            <input name="espectadoresMinimos" type="number"  id="espectadoresMinimos" required>
                        </div>
                        <div class="form-group">
                            <label for="espectadoresMaximos">Espectadores máximos</label>
                            <input name="espectadoresMaximos" type="number"  id="espectadoresMaximos" required>
                        </div>
                        <div class="form-group">
                            <label for="costo">Costo</label>
                            <input name="costo" type="number"  id="costo" required>
                        </div>
                        <div class="form-group">
                            <label for="imagen">Imagen</label>
                            <input name="imagen" type="file" class="form-control-file" id="imagen">
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button type="submit" class="btn btn-primary">Alta de Espectáculo</button>
                        </div>

                    </form>
                </div>
                <div class="col-sm-2">
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