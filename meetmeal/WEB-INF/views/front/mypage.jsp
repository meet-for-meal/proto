<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
<%@page import="org.essilab.module.user.model.User"%>
<%@page import="org.essilab.module.interest.model.Interest"%>
<%@page import="java.util.ArrayList"%>

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
            <img src="/meetformeal/res/styles/default/img/users/<%=user.getLastname() %>.jpg" width="64" height="64" class="avatar" alt="">           
            <p>
                <i class="icon-user"></i><strong><% out.print(user.getLastname() + " " + user.getFirstname()); %></strong>
            </p>
            <p>
                Sexe :
                <strong> 
                <% 
                if(user.getGender() == 1)
                {
                	out.print("Homme");
                }
                else 
                {
                	out.print("Femme");
                }
                %>
                </strong>	
            </p>
            <p>
                Age : <strong><% out.print(user.getAge()); %></strong>
            </p>
            <p>
                Go�ts culinaires : 
                <c:forEach items="${sessionScope.categories}" var="category">  
					<a href="#">#${category.name} </a>				
				</c:forEach>
                
            </p>
            <p>
                Centres d'int�r�ts : 

				<c:forEach items="${sessionScope.interests}" var="interest">  
					<a href="#">#${interest.tag} </a>				
				</c:forEach>
                
            </p>
            <!--  <p>
                Pr�entation : Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
                Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.
            </p>
            
            <p>
                <i class="icon-eye-open"></i>Profil complété à <strong>90%</strong>
            </p>-->
            <p>
                <a href="editmypage" class="btn"><i class="icon-cog icon-plus"></i>Modifier votre profil</a>
            </p>
        </div>

        <div class="col-1">

            <h2>Nouvelles demandes d'ajout</h2>

            <div class="user">
                <img src="/meetformeal/res/styles/default/img/users/alexandra.jpg" width="64" height="64" class="avatar" alt="">
                <p>
                    <a href="userpage" class="black"><strong>Alexandra Martin</strong></a><br>
                    <a href="#">#communication</a> <a href="#">#musique</a> <a href="#">#badminton</a><br>
                    <a href="#" class="btn"><i class="icon-ok"></i>Accepter</a> <a href="#" class="btn btn-neutral"><i class="icon-remove"></i>Ignorer</a>
                </p>
            </div>

            <hr>

            <h2>Mes amis</h2>
			
			<c:forEach items="${sessionScope.friends}" var="friend">  
				<a href="userpage" title="${friend.lastname }"><img src="/meetformeal/res/styles/default/img/users/${friend.lastname }.jpg" width="64" height="64" class="avatar" alt=""></a>			
			</c:forEach>

        </div>

        <div class="col-2">

        </div>

        <div class="col-1">

        </div>

    </div>

</section>