<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
<%@page import="org.essilab.module.user.model.User"%>
<%@page import="org.essilab.module.interest.model.Interest"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
User user = (User)session.getAttribute("sessionUser");
%>

<section id="container">

    <div id="large_bg_img" class="clearfix">

        <div id="large_bg_img_overlay"></div>
        <img src="/meetformeal/res/styles/default/img/bg2.jpg" id="image" class="big" alt="" />

        <h1>Mon profil</h1>

    </div>

    <div class="wrapper userpage">

        <div class="col-2">
            <h2>Informations sur votre profil</h2>
            <img src="/meetformeal/res/styles/default/img/users/<%=user.getFirstname() %>.jpg" width="64" height="64" class="avatar" alt="">           
            <p>
                <i class="icon-user"></i><strong><% out.print(user.getFirstname() + " " + user.getLastname()); %></strong>
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
                Goûts culinaires : 
                <c:forEach items="${sessionScope.categories}" var="category">  
					<a href="#">#${fn:toLowerCase(category.name)} </a>				
				</c:forEach>
                
            </p>
            <p>
                Centres d'intérêts : 
				<c:forEach items="${sessionScope.interests}" var="interest">  
					<a href="#">#${fn:toLowerCase(interest.tag)} </a>				
				</c:forEach>
                
            </p>
            <!--  <p>
                Préentation : Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
                Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.
            </p>
            
            <p>
                <i class="icon-eye-open"></i>Profil complÃ©tÃ© Ã  <strong>90%</strong>
            </p>-->
            <p>
                <a href="editmypage" class="btn"><i class="icon-cog icon-plus"></i>Modifier votre profil</a>
            </p>
        </div>

        <div class="col-1">

            <h2>Nouvelles demandes</h2>
            <c:choose>
				<c:when test="${!empty sessionScope.invitation}">
					<div class="user">
		                <img src="/meetformeal/res/styles/default/img/users/default.png" width="64" height="64" class="avatar" alt="">
		                <p>
		                    <a href="userpage?uid=${sessionScope.invitation.sender.id}" class="black"><strong>${sessionScope.invitation.sender.firstname} ${sessionScope.invitation.sender.lastname}</strong></a><br>
		                    <a href="#">#communication</a> <a href="#">#musique</a> <a href="#">#badminton</a><br>
		                    <a href="mypage?accepted=true" class="btn"><i class="icon-ok"></i>Accepter</a> <a href="mypage?accepted=false" class="btn btn-neutral"><i class="icon-remove"></i>Ignorer</a>
		                </p>
		            </div>
			        
			    </c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${!empty sessionScope.meal && sessionScope.accepted.id != null}">
									<p>Votre invitation a été acceptée pour votre prochain repas.</p>				        			        
					    </c:when>
						<c:when test="${!empty sessionScope.meal && sessionScope.accepted.id == null}">
						        <p>Vous avez accepté une invitation pour votre prochain repas</p>
					    </c:when>					    
						<c:otherwise>
							<p>Pas de demandes en cours</p>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>

	            


            <hr>

            <h2>Mes amis</h2>
			<c:if test="${!empty sessionScope.friends}">
				<c:forEach items="${sessionScope.friends}" var="friend">  
					<a href="userprofile?id=${friend.id }" title="${friend.firstname }"><img src="/meetformeal/res/styles/default/img/users/${friend.firstname }.jpg" width="64" height="64" class="avatar" alt=""></a>			
				</c:forEach>
			</c:if>
        </div>

        <div class="col-2">

        </div>

        <div class="col-1">

        </div>

    </div>

</section>