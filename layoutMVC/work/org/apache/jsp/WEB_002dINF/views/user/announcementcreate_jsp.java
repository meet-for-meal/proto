/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2013-03-01 16:23:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class announcementcreate_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<link href=\"");
      out.print( request.getContextPath() );
      out.write("/res/styles/default/styles.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("<header>\n");
      out.write("\n");
      out.write("    <div class=\"wrapper\">\n");
      out.write("\n");
      out.write("        <a href=\"index\" id=\"logo\"><img src=\"/layoutMVC/res/styles/default/img/logo-mini.png\" alt=\"\"></a>\n");
      out.write("\n");
      out.write("        <form id=\"search-form\">\n");
      out.write("            <input type=\"text\" name=\"search\" placeholder=\"Rechercher une personne ou un restaurant...\">\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("        <nav>\n");
      out.write("            <ul>\n");
      out.write("                <li><a href=\"#\" class=\"no-text toggle-notif\"><i class=\"icon-info-sign\"></i><span class=\"notif\">3</span></a></li>\n");
      out.write("                <li><a href=\"index\"><i class=\"icon-home\"></i> Accueil</a></li>\n");
      out.write("                <li><a href=\"restaurants\"><i class=\"icon-map-marker\"></i>Les restaurants</a></li>\n");
      out.write("                <li><a href=\"mypage\"><i class=\"icon-user\"></i>Mon profil</a></li>\n");
      out.write("                <li><a href=\"message\" class=\"no-text\"><i class=\"icon-envelope\"></i><span class=\"notif\">12</span></a></li>\n");
      out.write("                <li><a href=\"homepage\" class=\"no-text\"><i class=\"icon-off\"></i></a></li>\n");
      out.write("            </ul>\n");
      out.write("        </nav>\n");
      out.write("\n");
      out.write("        <div id=\"notifications\">\n");
      out.write("            <ul>\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"#\">\n");
      out.write("                        <span class=\"red\">Alexandra Martin</span> a proposÃ© une annonce.<br>\n");
      out.write("                        <span class=\"message-date\">28/02/2013 Ã  14h42</span>\n");
      out.write("                    </a>\n");
      out.write("                </li>\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"#\">\n");
      out.write("                        <span class=\"red\">RÃ©my Hannequin</span> souhaite devenir votre ami.<br>\n");
      out.write("                        <span class=\"message-date\">28/02/2013 Ã  14h42</span>\n");
      out.write("                    </a>\n");
      out.write("                </li>\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"#\">\n");
      out.write("                        <span class=\"red\">Pierre Grimaud</span> a proposÃ© une annonce.<br>\n");
      out.write("                        <span class=\"message-date\">28/02/2013 Ã  14h42</span>\n");
      out.write("                    </a>\n");
      out.write("                </li>\n");
      out.write("                <li>\n");
      out.write("                    <a href=\"#\">\n");
      out.write("                        <span class=\"red\">Romain Deligny</span> a proposÃ© une annonce.<br>\n");
      out.write("                        <span class=\"message-date\">28/02/2013 Ã  14h42</span>\n");
      out.write("                    </a>\n");
      out.write("                </li>\n");
      out.write("            </ul>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("</header>\n");
      out.write("\n");
      out.write("<section id=\"container\">\n");
      out.write("\n");
      out.write("    <div id=\"large_bg_img\" class=\"clearfix\">\n");
      out.write("\n");
      out.write("        <div id=\"large_bg_img_overlay\"></div>\n");
      out.write("        <img src=\"/layoutMVC/res/styles/default/img/bg2.jpg\" id=\"image\" class=\"big\" alt=\"\" />\n");
      out.write("\n");
      out.write("        <h1>CrÃ©er une annonce</h1>\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("    <div class=\"wrapper\">\n");
      out.write("\n");
      out.write("        <div class=\"col-2 centered\">\n");
      out.write("\n");
      out.write("            <form id=\"announcement-form\" action=\"announcementsearch.html\">\n");
      out.write("                <p>\n");
      out.write("                    <label for=\"time-start\"><strong><i class=\"icon-time\"></i> Vos disponibilitÃ©s ? </strong></label>\n");
      out.write("                    De <input type=\"text\" id=\"time-start\"> Ã  <input type=\"text\" id=\"time-end\" >\n");
      out.write("                </p>\n");
      out.write("                <p>\n");
      out.write("                    <label for=\"description\"><strong><i class=\"icon-pencil\"></i> Description de l'annonce</strong></label>\n");
      out.write("                    <textarea id=\"description\" cols=\"30\" rows=\"10\"></textarea>\n");
      out.write("                </p>\n");
      out.write("                <p>\n");
      out.write("                    <input type=\"submit\" class=\"btn\" value=\"CrÃ©er l'annonce\">\n");
      out.write("                </p>\n");
      out.write("            </form>\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("</section>\n");
      out.write("\n");
      out.write("<footer>\n");
      out.write("\n");
      out.write("    <div class=\"wrapper\">\n");
      out.write("\n");
      out.write("        <div class=\"col-left\">\n");
      out.write("            <h3>Ã propos de Meet For Meal</h3>\n");
      out.write("            <p>\n");
      out.write("                Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.<br>\n");
      out.write("                Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.\n");
      out.write("            </p>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"col-middle\">\n");
      out.write("            <h3>Contactez-nous</h3>\n");
      out.write("            <p>\n");
      out.write("                <strong>Meet For Meal</strong><br>\n");
      out.write("                61 rue lafayette<br>\n");
      out.write("                93100 Rue Rapatal<br>\n");
      out.write("                FRANCE<br>\n");
      out.write("                <a href=\"mailto:contact@meetformeal.com\">contact@meetformeal.com</a>\n");
      out.write("            </p>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div class=\"col-right\">\n");
      out.write("\n");
      out.write("            <h3>Suivez-nous</h3>\n");
      out.write("\n");
      out.write("            <div id=\"footer-menu-back-to-top\">\n");
      out.write("                <a href=\"#\"></a>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <p>\n");
      out.write("                Nos pages Facebook et Twitter seront bientÃ´t disponibles !\n");
      out.write("            </p>\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div id=\"copyright\">\n");
      out.write("            <p>\n");
      out.write("                &copy; 2013, Meet For Meal.\n");
      out.write("            </p>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("\n");
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
