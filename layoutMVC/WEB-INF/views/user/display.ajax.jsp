<%@page import="java.util.List"%>
<%@page import="org.essilab.module.user.model.User"%>
<%
List<User> users = (List<User>)request.getAttribute("users");
%>
[
<%
boolean first = true;
for (User user : users) {
	if (first)
		first = false;
	else { 
		%>,<%
		}
	%>
{ "user" : "<%= user.login %>", "password" : "<% out.print(user.password); %>"}
<% }  %>
]
