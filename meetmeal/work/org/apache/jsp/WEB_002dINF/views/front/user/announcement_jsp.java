/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2013-05-19 13:18:48 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.front.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class announcement_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<header>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"wrapper\">\r\n");
      out.write("\r\n");
      out.write("        <a href=\"index\" id=\"logo\"><img src=\"/meetmeal/res/styles/default/img/logo-mini.png\" alt=\"\"></a>\r\n");
      out.write("\r\n");
      out.write("        <form id=\"search-form\">\r\n");
      out.write("            <input type=\"text\" name=\"search\" placeholder=\"Rechercher une personne ou un restaurant...\">\r\n");
      out.write("        </form>\r\n");
      out.write("\r\n");
      out.write("        <nav>\r\n");
      out.write("            <ul>\r\n");
      out.write("                <li><a href=\"#\" class=\"no-text toggle-notif\"><i class=\"icon-info-sign\"></i><span class=\"notif\">3</span></a></li>\r\n");
      out.write("                <li><a href=\"index\"><i class=\"icon-home\"></i> Accueil</a></li>\r\n");
      out.write("                <li><a href=\"restaurants\"><i class=\"icon-map-marker\"></i>Les restaurants</a></li>\r\n");
      out.write("                <li><a href=\"mypage\"><i class=\"icon-user\"></i>Mon profil</a></li>\r\n");
      out.write("                <li><a href=\"message\" class=\"no-text\"><i class=\"icon-envelope\"></i><span class=\"notif\">12</span></a></li>\r\n");
      out.write("                <li><a href=\"homepage\" class=\"no-text\"><i class=\"icon-off\"></i></a></li>\r\n");
      out.write("            </ul>\r\n");
      out.write("        </nav>\r\n");
      out.write("\r\n");
      out.write("        <div id=\"notifications\">\r\n");
      out.write("            <ul>\r\n");
      out.write("                <li>\r\n");
      out.write("                    <a href=\"#\">\r\n");
      out.write("                        <span class=\"red\">Alexandra Martin</span> a proposÃ© une annonce.<br>\r\n");
      out.write("                        <span class=\"message-date\">28/02/2013 Ã  14h42</span>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li>\r\n");
      out.write("                    <a href=\"#\">\r\n");
      out.write("                        <span class=\"red\">RÃ©my Hannequin</span> souhaite devenir votre ami.<br>\r\n");
      out.write("                        <span class=\"message-date\">28/02/2013 Ã  14h42</span>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li>\r\n");
      out.write("                    <a href=\"#\">\r\n");
      out.write("                        <span class=\"red\">Pierre Grimaud</span> a proposÃ© une annonce.<br>\r\n");
      out.write("                        <span class=\"message-date\">28/02/2013 Ã  14h42</span>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li>\r\n");
      out.write("                    <a href=\"#\">\r\n");
      out.write("                        <span class=\"red\">Romain Deligny</span> a proposÃ© une annonce.<br>\r\n");
      out.write("                        <span class=\"message-date\">28/02/2013 Ã  14h42</span>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </li>\r\n");
      out.write("            </ul>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("</header>\r\n");
      out.write("\r\n");
      out.write("<section id=\"container\">\r\n");
      out.write("\r\n");
      out.write("    <div id=\"large_bg_img\" class=\"clearfix\">\r\n");
      out.write("\r\n");
      out.write("        <div id=\"large_bg_img_overlay\"></div>\r\n");
      out.write("        <img src=\"/meetmeal/res/styles/default/img/bg2.jpg\" id=\"image\" class=\"big\" alt=\"\" />\r\n");
      out.write("\r\n");
      out.write("        <h1>Annonce de Alexandra Martin</h1>\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"wrapper\">\r\n");
      out.write("\r\n");
      out.write("        <div class=\"col-2\">\r\n");
      out.write("\r\n");
      out.write("            <h2>DÃ©tail de l'annonce :</h2>\r\n");
      out.write("\r\n");
      out.write("            <div id=\"announcement-info\">\r\n");
      out.write("                <p>\r\n");
      out.write("                    &laquo; Salut Ã  tous ! Je cherche un homme sympa qui aime manger italien !<br>\r\n");
      out.write("                    Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.<br>\r\n");
      out.write("                    Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. &raquo;\r\n");
      out.write("                </p>\r\n");
      out.write("                <p>\r\n");
      out.write("                    <i class=\"icon-time\"></i>DisponibilitÃ© : <strong>12H</strong> - <strong>13H</strong> - <i class=\"icon-map-marker\"></i>Localisation : <strong>Paris VÃ¨me</strong>\r\n");
      out.write("                </p>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <div id=\"announcement-user\">\r\n");
      out.write("\r\n");
      out.write("                <img src=\"/meetmeal/res/styles/default/img/users/alexandra.jpg\" width=\"64\" height=\"64\" class=\"avatar\" alt=\"\">\r\n");
      out.write("                <p>\r\n");
      out.write("                    ProposÃ© par <strong>Alexandra Martin</strong><br>\r\n");
      out.write("                    <i class=\"icon-heart\"></i>Centres d'intÃ©rÃªts  : <a href=\"#\">#communication</a> <a href=\"#\">#badminton</a><br>\r\n");
      out.write("                    <i class=\"icon-glass\"></i>PrÃ©fÃ©rences culinaires : <a href=\"#\">#italien</a> <a href=\"#\">#japonais</a><br>\r\n");
      out.write("                </p>\r\n");
      out.write("                <p>\r\n");
      out.write("                    <span><a href=\"#\" class=\"btn\"><i class=\"icon-eye-open icon-red\"></i> Voir son profil</a></span>\r\n");
      out.write("                </p>\r\n");
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"col-1\">\r\n");
      out.write("\r\n");
      out.write("            <h2>RÃ©pondre Ã  l'annonce :</h2>\r\n");
      out.write("\r\n");
      out.write("            <p>\r\n");
      out.write("                <a href=\"#\" class=\"btn\"><i class=\"icon-pencil\"></i>Je suis intÃ©ressÃ© !</a>\r\n");
      out.write("            </p>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"col-2\">\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"col-1\">\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("</section>\r\n");
      out.write("\r\n");
      out.write("<footer>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"wrapper\">\r\n");
      out.write("\r\n");
      out.write("        <div class=\"col-left\">\r\n");
      out.write("            <h3>Ã propos de Meet For Meal</h3>\r\n");
      out.write("            <p>\r\n");
      out.write("                Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.<br>\r\n");
      out.write("                Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.\r\n");
      out.write("            </p>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"col-middle\">\r\n");
      out.write("            <h3>Contactez-nous</h3>\r\n");
      out.write("            <p>\r\n");
      out.write("                <strong>Meet For Meal</strong><br>\r\n");
      out.write("                61 rue lafayette<br>\r\n");
      out.write("                93100 Rue Rapatal<br>\r\n");
      out.write("                FRANCE<br>\r\n");
      out.write("                <a href=\"mailto:contact@meetformeal.com\">contact@meetformeal.com</a>\r\n");
      out.write("            </p>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"col-right\">\r\n");
      out.write("\r\n");
      out.write("            <h3>Suivez-nous</h3>\r\n");
      out.write("\r\n");
      out.write("            <div id=\"footer-menu-back-to-top\">\r\n");
      out.write("                <a href=\"#\"></a>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <p>\r\n");
      out.write("                Nos pages Facebook et Twitter seront bientÃ´t disponibles !\r\n");
      out.write("            </p>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div id=\"copyright\">\r\n");
      out.write("            <p>\r\n");
      out.write("                &copy; 2013, Meet For Meal.\r\n");
      out.write("            </p>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("</footer>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
