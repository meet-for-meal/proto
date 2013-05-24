<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
<%@page import="org.essilab.module.user.model.User"%>
<%@page import="org.essilab.module.interest.model.Interest"%>
<%@page import="java.util.ArrayList"%>

<%
User userprofile = (User)session.getAttribute("userprofile");
%>

<section id="container">

    <div id="large_bg_img" class="clearfix">

        <div id="large_bg_img_overlay"></div>
        <img src="/meetformeal/res/styles/default/img/bg2.jpg" id="image" class="big" alt="" />

        <h1>Profil de <% out.print(userprofile.getFirstname() + " " + userprofile.getLastname()); %></h1>

    </div>

    <div class="wrapper userpage">

        <div class="col-2">
            <h2>Informations du profil</h2>
            <img src="/meetformeal/res/styles/default/img/users/<%=userprofile.getFirstname() %>.jpg" width="64" height="64" class="avatar" alt="">           
            <p>
                <i class="icon-user"></i><strong><% out.print(userprofile.getFirstname() + " " + userprofile.getLastname()); %></strong>
            </p>
            <p>
                Sexe :
                <strong> 
                <% 
                if(userprofile.getGender() == 1)
                {
                	out.print("Homme");
                }
                else if(userprofile.getGender() == 0)
                {
                	out.print("Femme");
                }
                else
                	out.print("Non défini");
                %>
                </strong>	
            </p>
           	<p>
                Age : <strong><% out.print(userprofile.getAge()); %></strong>
            </p>
            <p>
                Goûts culinaires : 
                <c:forEach items="${sessionScope.categories}" var="category">  
					<a href="#">#${category.name} </a>				
				</c:forEach>
                
            </p>
            <p>
                Centres d'intérêts : 

				<c:forEach items="${sessionScope.interests}" var="interest">  
					<a href="#">#${interest.tag} </a>				
				</c:forEach>
                
            </p>
            
            <a href="add?id_user=<%=userprofile.getId() %>" class="btn mfm-action" data-action="friend-request"><i class="icon-plus"></i>Ajouter comme ami</a>
            <a href="messagecreate?id_user=<%=userprofile.getId() %>" class="btn" ><i class="icon-pencil"></i>Ecrire un message</a>
            
        </div>
		
        <div class="col-1">
			

        </div>

        <div class="col-2">

        </div>

        <div class="col-1">

        </div>

    </div>

</section>