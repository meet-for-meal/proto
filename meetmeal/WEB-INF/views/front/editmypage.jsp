<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
<%@page import="org.essilab.module.user.model.User"%>
<%@page import="org.essilab.module.interest.model.Interest"%>
<%@page import="org.essilab.module.category.model.Category"%>
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
            <img src="/meetformeal/res/styles/default/img/users/default.png" width="64" height="64" class="avatar" alt="">
            <br/><br/><br/><br/>
            <form id="user-search-form" action="mypage" method="post">
                    <p>
                        <label for="lastname"><strong>Pr�nom : </strong></label>
                        <input type="text" name="lastname" id="lastname" value="<%=user.getLastname() %>">
                    </p>
                    <p>
                        <label for="firstname"><strong>Nom : </strong></label>
                        <input type="text" name="firstname" id="firstname" value="<%=user.getFirstname() %>">
                    </p>
                    <p>
                        <label for="gender"><strong>Sexe : </strong></label>
                        <select name="gender"> 
						<% 
						if(user.getGender() == 1)
						{ 
							%> 
						    <option value="1" selected="selected">Homme</option> 
						    <option value="0">Femme</option> 
							<% 
						} 
						else
						{
							%> 
						    <option value="1" >Homme</option> 
						    <option value="0" selected="selected">Femme</option> 
							<%
						}
						%> 
						</select> 
                    </p>
                    <p>
                        <label for="age"><strong>Age : </strong></label>
                        <input type="text" name="age" id="age" value="<%=user.getAge() %>">
                    </p>
                    <p>
                        <label for="taste"><strong>Go�ts culinaires : </strong></label><br/><br/>
                        <% Boolean b = false; %>
                        <c:forEach items="${sessionScope.allcategories}" var="allcat">  
							<c:forEach items="${sessionScope.usercategories}" var="usercat">  
								<c:if test="${usercat.id == allcat.id}"><% b = true; %></c:if>									
							</c:forEach>
							<%
							if(b == true)
							{
								%>
								<input type="checkbox" name="category" id="${allcat.id}" value="${allcat.id}" checked="checked">
								${allcat.name}
								<%
							}
							else
							{
								%>
								<input type="checkbox" name="category" id="${allcat.id}" value="${allcat.id}">
								${allcat.name}
								<%
							}
							b = false;
							%>			
						</c:forEach>
                        
                    </p>
                    <p>
                        <label for="interest"><strong>Centres d'int�r�ts : </strong></label><br/><br/>
                        <% Boolean b2 = false; %>
                        <c:forEach items="${sessionScope.allinterests}" var="allint">  
							<c:forEach items="${sessionScope.userinterests}" var="userint">  
								<c:if test="${userint.id == allint.id}"><% b2 = true; %></c:if>									
							</c:forEach>
							<%
							if(b2 == true)
							{
								%>
								<input type="checkbox" name="interest" id="${allint.id}" value="${allint.id}" checked="checked">
								${allint.tag}
								<%
							}
							else
							{
								%>
								<input type="checkbox" name="interest" id="${allint.id}" value="${allint.id}">
								${allint.tag}
								<%
							}
							b2 = false;
							%>			
						</c:forEach>
                    </p>
                    <br/>
                    <p>
                    <input style="float:left;" type="submit" class="btn" value="Mettre � jour votre profil">
                	</p>
                </form>
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
				<a href="userpage" title="${friend.lastname }"><img src="/meetformeal/res/styles/default/img/users/default.png" width="64" height="64" class="avatar" alt=""></a>			
			</c:forEach>

        </div>

        <div class="col-2">

        </div>

        <div class="col-1">

        </div>

    </div>

</section>