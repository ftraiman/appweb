package org.apache.jsp.principal;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<html lang=\"es\">\n");
      out.write("<head>\n");
      out.write("  <meta charset=\"utf-8\" />\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n");
      out.write("  <link rel=\"apple-touch-icon\" sizes=\"76x76\" href=\"../assets/img/apple-icon.png\">\n");
      out.write("  <link rel=\"icon\" type=\"image/png\" href=\"../assets/img/favicon.png\">\n");
      out.write("  <title>\n");
      out.write("    CoronaTickets.uy\n");
      out.write("  </title>\n");
      out.write("  <!--     Fonts and icons     -->\n");
      out.write("  <link href=\"https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700\" rel=\"stylesheet\" />\n");
      out.write("  <!-- Nucleo Icons -->\n");
      out.write("  <link href=\"../assets/css/nucleo-icons.css\" rel=\"stylesheet\" />\n");
      out.write("  <link href=\"../assets/css/nucleo-svg.css\" rel=\"stylesheet\" />\n");
      out.write("  <!-- Font Awesome Icons -->\n");
      out.write("  <script src=\"https://kit.fontawesome.com/42d5adcbca.js\" crossorigin=\"anonymous\"></script>\n");
      out.write("  <link href=\"../assets/css/nucleo-svg.css\" rel=\"stylesheet\" />\n");
      out.write("  <!-- CSS Files -->\n");
      out.write("  <link id=\"pagestyle\" href=\"../assets/css/soft-ui-dashboard.css?v=1.0.3\" rel=\"stylesheet\" />\n");
      out.write("  \n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body class=\"\">\n");
      out.write("  <div class=\"container position-sticky z-index-sticky top-0\">\n");
      out.write("    <div class=\"row\">\n");
      out.write("      <div class=\"col-12\">\n");
      out.write("        <!-- Navbar -->\n");
      out.write("        <nav class=\"navbar navbar-expand-lg blur blur-rounded top-0 z-index-3 shadow position-absolute my-3 py-2 start-0 end-0 mx-4\">\n");
      out.write("          <div class=\"container-fluid\">\n");
      out.write("            <a class=\"navbar-brand font-weight-bolder ms-lg-0 ms-3 \" href=\"../pages/dashboard.html\">\n");
      out.write("              CoronaTickets.uy\n");
      out.write("            </a>\n");
      out.write("            <button class=\"navbar-toggler shadow-none ms-2\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navigation\" aria-controls=\"navigation\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n");
      out.write("              <span class=\"navbar-toggler-icon mt-2\">\n");
      out.write("                <span class=\"navbar-toggler-bar bar1\"></span>\n");
      out.write("                <span class=\"navbar-toggler-bar bar2\"></span>\n");
      out.write("                <span class=\"navbar-toggler-bar bar3\"></span>\n");
      out.write("              </span>\n");
      out.write("            </button>\n");
      out.write("            <div class=\"collapse navbar-collapse\" id=\"navigation\">\n");
      out.write("              <ul class=\"navbar-nav mx-auto\">\n");
      out.write("                <li class=\"nav-item\">\n");
      out.write("                  <a class=\"nav-link d-flex align-items-center me-2 active\" aria-current=\"page\" href=\"../pages/dashboard.html\">\n");
      out.write("                    <i class=\"fa fa-chart-pie opacity-6 text-dark me-1\"></i>\n");
      out.write("                    Menú principal\n");
      out.write("                  </a>\n");
      out.write("                </li>\n");
      out.write("                <li class=\"nav-item\">\n");
      out.write("                  <a class=\"nav-link me-2\" href=\"../pages/profile.html\">\n");
      out.write("                    <i class=\"fa fa-user opacity-6 text-dark me-1\"></i>\n");
      out.write("                    Mi perfil\n");
      out.write("                  </a>\n");
      out.write("                </li>\n");
      out.write("                <li class=\"nav-item\">\n");
      out.write("                  <a class=\"nav-link me-2\" href=\"../pages/sign-up.html\">\n");
      out.write("                    <i class=\"fas fa-user-circle opacity-6 text-dark me-1\"></i>\n");
      out.write("                    Registrarse\n");
      out.write("                  </a>\n");
      out.write("                </li>\n");
      out.write("                <li class=\"nav-item\">\n");
      out.write("                    <a class=\"nav-link me-2\" href=\"/web/principal/login.jsp\">\n");
      out.write("                    <i class=\"fas fa-key opacity-6 text-dark me-1\"></i>\n");
      out.write("                    Log In\n");
      out.write("                  </a>\n");
      out.write("                </li>\n");
      out.write("              </ul>\n");
      out.write("            </div>\n");
      out.write("          </div>\n");
      out.write("        </nav>\n");
      out.write("        <!-- End Navbar -->\n");
      out.write("      </div>\n");
      out.write("    </div>\n");
      out.write("  </div>\n");
      out.write("  <main class=\"main-content  mt-0\">\n");
      out.write("    <section>\n");
      out.write("      <div class=\"page-header min-vh-75\">\n");
      out.write("        <div class=\"container\">\n");
      out.write("          <div class=\"row\">\n");
      out.write("            <div class=\"col-xl-4 col-lg-5 col-md-6 d-flex flex-column mx-auto\">\n");
      out.write("              <div class=\"card card-plain mt-8\">\n");
      out.write("                <div class=\"card-header pb-0 text-left bg-transparent\">\n");
      out.write("                  <h3 class=\"font-weight-bolder text-info text-gradient\">Bienvenido!</h3>\n");
      out.write("                  <p class=\"mb-0\">Ingresa tu email o usuario para ingresar al sistema</p>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"card-body\">\n");
      out.write("                    <form role=\"form\" action=\"/web/login\" method=\"POST\">\n");
      out.write("                    <label>Email / Nickname</label>\n");
      out.write("                    <div class=\"mb-3\">\n");
      out.write("                        <input id=\"email\" name=\"email\" type=\"text\" class=\"form-control\" placeholder=\"Email\" aria-label=\"Email\" aria-describedby=\"email-addon\">\n");
      out.write("                    </div>\n");
      out.write("                    <label>Clave</label>\n");
      out.write("                    <div class=\"mb-3\">\n");
      out.write("                        <input id=\"clave\" name=\"clave\" type=\"password\" class=\"form-control\" placeholder=\"Password\" aria-label=\"Password\" aria-describedby=\"password-addon\">\n");
      out.write("                    </div>\n");
      out.write("<!--                    <div class=\"form-check form-switch\">\n");
      out.write("                      <input class=\"form-check-input\" type=\"checkbox\" id=\"rememberMe\" checked=\"\">\n");
      out.write("                      <label class=\"form-check-label\" for=\"rememberMe\">Recordar mi usuario</label>\n");
      out.write("                    </div>-->\n");
      out.write("                    ");
 
                        if (request.getParameter("error") != null) {    
                    
      out.write("\n");
      out.write("                        <div class=\"alert alert-warning\" role=\"alert\">\n");
      out.write("                            ");
      out.print( request.getParameter("error") );
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                    ");
  }  
      out.write("\n");
      out.write("                    \n");
      out.write("                    <div class=\"text-center\">\n");
      out.write("                        <button  type=\"submit\" class=\"btn bg-gradient-info w-100 mt-4 mb-0\">Sign in</button>\n");
      out.write("                    </div>\n");
      out.write("                  </form>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"card-footer text-center pt-0 px-lg-2 px-1\">\n");
      out.write("                  <p class=\"mb-4 text-sm mx-auto\">\n");
      out.write("                    ¿No tienes una cuenta?\n");
      out.write("                    <a href=\"javascript:;\" class=\"text-info text-gradient font-weight-bold\">Regístrate aquí</a>\n");
      out.write("                  </p>\n");
      out.write("                </div>\n");
      out.write("              </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-md-6\">\n");
      out.write("              <div class=\"oblique position-absolute top-0 h-100 d-md-block d-none me-n8\">\n");
      out.write("                <div class=\"oblique-image bg-cover position-absolute fixed-top ms-auto h-100 z-index-0 ms-n6\" style=\"background-image:url('../assets/img/curved-images/curved6.jpg')\"></div>\n");
      out.write("              </div>\n");
      out.write("            </div>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("      </div>\n");
      out.write("    </section>\n");
      out.write("  </main>\n");
      out.write("  <!-- -------- START FOOTER 3 w/ COMPANY DESCRIPTION WITH LINKS & SOCIAL ICONS & COPYRIGHT ------- -->\n");
      out.write("  <footer class=\"footer py-5 invisible\">\n");
      out.write("    <div class=\"container\">\n");
      out.write("      <div class=\"row\">\n");
      out.write("        <div class=\"col-lg-8 mb-4 mx-auto text-center\">\n");
      out.write("          <a href=\"javascript:;\" target=\"_blank\" class=\"text-secondary me-xl-5 me-3 mb-sm-0 mb-2\">\n");
      out.write("            Company\n");
      out.write("          </a>\n");
      out.write("          <a href=\"javascript:;\" target=\"_blank\" class=\"text-secondary me-xl-5 me-3 mb-sm-0 mb-2\">\n");
      out.write("            About Us\n");
      out.write("          </a>\n");
      out.write("          <a href=\"javascript:;\" target=\"_blank\" class=\"text-secondary me-xl-5 me-3 mb-sm-0 mb-2\">\n");
      out.write("            Team\n");
      out.write("          </a>\n");
      out.write("          <a href=\"javascript:;\" target=\"_blank\" class=\"text-secondary me-xl-5 me-3 mb-sm-0 mb-2\">\n");
      out.write("            Products\n");
      out.write("          </a>\n");
      out.write("          <a href=\"javascript:;\" target=\"_blank\" class=\"text-secondary me-xl-5 me-3 mb-sm-0 mb-2\">\n");
      out.write("            Blog\n");
      out.write("          </a>\n");
      out.write("          <a href=\"javascript:;\" target=\"_blank\" class=\"text-secondary me-xl-5 me-3 mb-sm-0 mb-2\">\n");
      out.write("            Pricing\n");
      out.write("          </a>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"col-lg-8 mx-auto text-center mb-4 mt-2\">\n");
      out.write("          <a href=\"javascript:;\" target=\"_blank\" class=\"text-secondary me-xl-4 me-4\">\n");
      out.write("            <span class=\"text-lg fab fa-dribbble\"></span>\n");
      out.write("          </a>\n");
      out.write("          <a href=\"javascript:;\" target=\"_blank\" class=\"text-secondary me-xl-4 me-4\">\n");
      out.write("            <span class=\"text-lg fab fa-twitter\"></span>\n");
      out.write("          </a>\n");
      out.write("          <a href=\"javascript:;\" target=\"_blank\" class=\"text-secondary me-xl-4 me-4\">\n");
      out.write("            <span class=\"text-lg fab fa-instagram\"></span>\n");
      out.write("          </a>\n");
      out.write("          <a href=\"javascript:;\" target=\"_blank\" class=\"text-secondary me-xl-4 me-4\">\n");
      out.write("            <span class=\"text-lg fab fa-pinterest\"></span>\n");
      out.write("          </a>\n");
      out.write("          <a href=\"javascript:;\" target=\"_blank\" class=\"text-secondary me-xl-4 me-4\">\n");
      out.write("            <span class=\"text-lg fab fa-github\"></span>\n");
      out.write("          </a>\n");
      out.write("        </div>\n");
      out.write("      </div>\n");
      out.write("      <div class=\"row\">\n");
      out.write("        <div class=\"col-8 mx-auto text-center mt-1\">\n");
      out.write("          <p class=\"mb-0 text-secondary\">\n");
      out.write("            Copyright © <script>\n");
      out.write("              document.write(new Date().getFullYear())\n");
      out.write("            </script> Soft by Creative Tim.\n");
      out.write("          </p>\n");
      out.write("        </div>\n");
      out.write("      </div>\n");
      out.write("    </div>\n");
      out.write("  </footer>\n");
      out.write("  <!-- -------- END FOOTER 3 w/ COMPANY DESCRIPTION WITH LINKS & SOCIAL ICONS & COPYRIGHT ------- -->\n");
      out.write("  <!--   Core JS Files   -->\n");
      out.write("  <script src=\"../assets/js/core/popper.min.js\"></script>\n");
      out.write("  <script src=\"../assets/js/core/bootstrap.min.js\"></script>\n");
      out.write("  <script src=\"../assets/js/plugins/perfect-scrollbar.min.js\"></script>\n");
      out.write("  <script src=\"../assets/js/plugins/smooth-scrollbar.min.js\"></script>\n");
      out.write("  <script>\n");
      out.write("    var win = navigator.platform.indexOf('Win') > -1;\n");
      out.write("    if (win && document.querySelector('#sidenav-scrollbar')) {\n");
      out.write("      var options = {\n");
      out.write("        damping: '0.5'\n");
      out.write("      }\n");
      out.write("      Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);\n");
      out.write("    }\n");
      out.write("  </script>\n");
      out.write("<!--   Github buttons \n");
      out.write("  <script async defer src=\"https://buttons.github.io/buttons.js\"></script>\n");
      out.write("   Control Center for Soft Dashboard: parallax effects, scripts for the example pages etc \n");
      out.write("  <script src=\"../assets/js/soft-ui-dashboard.min.js?v=1.0.3\"></script>-->\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
