<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
<%@page import="org.essilab.module.user.model.User"%>
<%@page import="org.essilab.module.interest.model.Interest"%>
<%@page import="java.util.ArrayList"%>

<%
User user = (User)session.getAttribute("user");
%>

<section id="container">

    <div id="large_bg_img" class="clearfix">

        <div id="large_bg_img_overlay"></div>
        <img src="/meetformeal/res/styles/default/img/bg2.jpg" id="image" class="big" alt="" />

        <h1>Profil de <%=user.getFirstname() + user.getLastname() %></h1>

    </div>

    <div class="wrapper userpage">

        <div class="col-2">
            <h2>Informations sur l'utilisateur</h2>
            <img src="/meetformeal/res/styles/default/img/users/romain.jpg" width="64" height="64" class="avatar" alt="">
            <p>
                <i class="icon-user"></i><strong>${sessionScope.user.Firstname } ${sessionScope.user.Lastname }</strong>
            </p>
            <p>
                Sexe : 
				<strong> 
                <% 
                if(user.getGender() == 1)
                {
                	out.print("Homme");
                }
                else if(user.getGender() == 0)
                {
                	out.print("Femme");
                }
                else
                	out.print("Non défini");
                %>
                </strong>
            </p>
            <p>
                Age : <strong><% out.print(user.getAge()); %></strong>
            </p>
            <p>
                Gouts culinaires : 
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
            
            <p>
                <a href="#" class="btn"><i class="icon-envelope icon-plus"></i>Ajouter en ami</a>
            </p>
        </div>

        <div class="col-1">

            <h2>Amis</h2>
			<c:if test="${!empty sessionScope.friends}">
				<c:forEach items="${sessionScope.friends}" var="friend">  
					<a href="userpage?id=${friend.id }" title="${friend.lastname }"><img src="/meetformeal/res/styles/default/img/users/${friend.lastname }.jpg" width="64" height="64" class="avatar" alt=""></a>			
				</c:forEach>
			</c:if>
            
		</div>

        <div class="col-2">

        </div>

        <div class="col-1">

        </div>

    </div>

</section>
