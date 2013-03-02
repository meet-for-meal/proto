<%@page import="java.util.List"%>
<%@page import="org.essilab.module.user.model.User"%>
<div>
<h1><%= request.getAttribute("title") %></h1>
<%
List<User> users = (List<User>)request.getAttribute("users");
%>
<ul>
<% for (User user : users) { %>
<li><%= user.lastName %> : <% out.println(user.firstname); %></li>
<% }  %>

</ul>
</div>