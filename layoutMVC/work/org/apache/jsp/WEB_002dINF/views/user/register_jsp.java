/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2013-03-01 14:46:41 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.user;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class register_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("/res/styles/default/homepage.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("<div id=\"large_bg_img\" class=\"clearfix\">\n");
      out.write("\n");
      out.write("    <div id=\"large_bg_img_overlay\"></div>\n");
      out.write("    <img src=\"/layoutMVC/res/styles/default/img/bg2.jpg\" id=\"image\" class=\"big\" alt=\"\" />\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("<header>\n");
      out.write("\n");
      out.write("    <div class=\"wrapper\">\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("</header>\n");
      out.write("\n");
      out.write("<section id=\"container\">\n");
      out.write("\n");
      out.write("    <div class=\"wrapper\">\n");
      out.write("\n");
      out.write("        <div id=\"intro\">\n");
      out.write("\n");
      out.write("            <h1>Meet for Meal</h1>\n");
      out.write("\n");
      out.write("            <a href=\"index\"><img src=\"/layoutMVC/res/styles/default/img/logo.png\" id=\"logo\" alt=\"\" /></a>\n");
      out.write("\n");
      out.write("            <h2>\n");
      out.write("                Eat better. Eat together.\n");
      out.write("            </h2>\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div id=\"login\">\n");
      out.write("\n");
      out.write("            <form id=\"login-form\" action=\"index.html\">\n");
      out.write("                <p>\n");
      out.write("                    <label for=\"lastname\">Entrez votre nom :</label>\n");
      out.write("                    <input type=\"text\" id=\"lastname\" name=\"lastname\" value=\"\">\n");
      out.write("                </p>\n");
      out.write("                <p>\n");
      out.write("                    <label for=\"firstname\">Entrez votre prenom :</label>\n");
      out.write("                    <input type=\"text\" id=\"firstname\" name=\"firstname\" value=\"\">\n");
      out.write("                </p>\n");
      out.write("                <p>\n");
      out.write("                    <label for=\"email\">Entrez votre adresse email</label>\n");
      out.write("                    <input type=\"text\" id=\"email\" name=\"email\" value=\"\">\n");
      out.write("                </p>\n");
      out.write("                <p>\n");
      out.write("                    <label for=\"password\">Entrez un mot de passe</label>\n");
      out.write("                    <input type=\"password\" id=\"password\" name=\"password\">\n");
      out.write("                </p>\n");
      out.write("                <p>\n");
      out.write("                    <label for=\"password2\">Retapez le mot de passe</label>\n");
      out.write("                    <input type=\"password\" id=\"password2\" name=\"password2\">\n");
      out.write("                </p>\n");
      out.write("                <p>\n");
      out.write("                    <input type=\"submit\" value=\"CrÃ©er le compte\">\n");
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