<html>
<head><title><%= request.getAttribute("title") %></title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/res/styles/default/style.css"/>
</head>
<body>
<div id="container">
  <div id="header">Header</div>
  <div id="context">
<% 
 out.flush();
 request.getRequestDispatcher("/WEB-INF/views/"+request.getAttribute("url")+".jsp")
         .include(request, response);
%>


</div>
  <div id="footer"> @ creative commons</div>
</div>
</body>
</html>
