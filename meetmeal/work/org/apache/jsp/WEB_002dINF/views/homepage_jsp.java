/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2013-05-18 10:29:39 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class homepage_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div id=\"large_bg_img\" class=\"clearfix\">\r\n");
      out.write("\r\n");
      out.write("    <div id=\"large_bg_img_overlay\"></div>\r\n");
      out.write("    <img src=\"/meetmeal/res/styles/default/img/bg2.jpg\" id=\"image\" class=\"big\" alt=\"\" />\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<header>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"wrapper\">\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("</header>\r\n");
      out.write("\r\n");
      out.write("<section id=\"container\">\r\n");
      out.write("\r\n");
      out.write("    <div class=\"wrapper\">\r\n");
      out.write("\r\n");
      out.write("        <div id=\"intro\">\r\n");
      out.write("\r\n");
      out.write("            <h1>Meet for Meal</h1>\r\n");
      out.write("\r\n");
      out.write("            <a href=\"index\"><img src=\"/meetmeal/res/styles/default/img/logo.png\" id=\"logo\" alt=\"\" /></a>\r\n");
      out.write("\r\n");
      out.write("            <h2>\r\n");
      out.write("                Eat better. Eat together.\r\n");
      out.write("            </h2>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div id=\"login\">\r\n");
      out.write("\r\n");
      out.write("            <div id=\"welcome\">\r\n");
      out.write("                <h2>Un rÃ©seau de partage</h2>\r\n");
      out.write("                <p>\r\n");
      out.write("                    Meet for Meal est un rÃ©seau qui permet de trouver des partenaires de repas Ã  travers une recherche en temps rÃ©el qui vous connecte aux personnes proches de vous.\r\n");
      out.write("                </p>\r\n");
      out.write("                <p>\r\n");
      out.write("                    Trouvez simplement des partenaires culinaires que vous pensez Ã©tre les plus intÃ©ressants pour partager un repas avec vous.\r\n");
      out.write("                </p>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <form id=\"login-form\" action=\"index.html\">\r\n");
      out.write("                <p>\r\n");
      out.write("                    <label for=\"email\">Entrez votre email</label>\r\n");
      out.write("                    <input type=\"text\" id=\"email\" name=\"email\" value=\"\">\r\n");
      out.write("                </p>\r\n");
      out.write("                <p>\r\n");
      out.write("                    <label for=\"password\">Entrez votre mot de passe</label>\r\n");
      out.write("                    <input type=\"password\" id=\"password\" name=\"password\">\r\n");
      out.write("                </p>\r\n");
      out.write("                <p>\r\n");
      out.write("                    <input type=\"submit\" value=\"Se connecter\">\r\n");
      out.write("                </p>\r\n");
      out.write("            </form>\r\n");
      out.write("\r\n");
      out.write("            <p id=\"extra-form\">\r\n");
      out.write("                <a href=\"register\">Cliquez ici pour ouvrir un compte Meet for Meal</a>\r\n");
      out.write("            </p>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("</section>\r\n");
      out.write("\r\n");
      out.write("<footer>\r\n");
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
