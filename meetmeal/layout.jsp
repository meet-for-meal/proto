<%@ page pageEncoding="UTF-8" %><!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <title><%= request.getAttribute("title") %></title>
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Droid+Sans:400,700">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Droid+Serif">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Economica:700,400italic">
    <% 
	 	if(request.getAttribute("url").equals("homepage") || request.getAttribute("url").equals("register") || request.getAttribute("url").equals("connect-admin")){
	 		out.println("<link href=\"" + request.getContextPath() +"/res/styles/default/homepage.css\" rel=\"stylesheet\">");
	 	}
	 	else{
	 		out.println("<link href=\""+ request.getContextPath() +"/res/styles/default/blitzer/jquery-ui-custom.css\" rel=\"stylesheet\">");
	 		out.println("<link href=\""+ request.getContextPath() +"/res/styles/default/styles.css\" rel=\"stylesheet\">");
	 	}
	%>
</head>
<body>

<% 
 out.flush();
 request.getRequestDispatcher("/WEB-INF/views/front/"+request.getAttribute("url")+".jsp")
         .include(request, response);
%>

</body>
</html>
