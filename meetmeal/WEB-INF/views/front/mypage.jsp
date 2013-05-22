<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.List"%>
<%@page import="org.essilab.module.user.model.User"%>
<%@page import="org.essilab.module.interest.model.Interest"%>
<%@page import="java.util.ArrayList"%>

<%
User user = (User)session.getAttribute("sessionUser");


%>
<header>

    <div class="wrapper">

        <a href="index" id="logo"><img src="/meetformeal/res/styles/default/img/logo-mini.png" alt=""></a>

        <form id="search-form">
            <input type="text" name="search" placeholder="Rechercher une personne ou un restaurant...">
        </form>

        <nav>
            <ul>
                <li><a href="#" class="no-text toggle-notif"><i class="icon-info-sign"></i><span class="notif">3</span></a></li>
                <li><a href="index"><i class="icon-home"></i> Accueil</a></li>
                <li><a href="restaurants"><i class="icon-map-marker"></i>Les restaurants</a></li>
                <li class="current"><a href="mypage"><i class="icon-user"></i>Mon profil</a></li>
                <li><a href="message" class="no-text"><i class="icon-envelope"></i><span class="notif">12</span></a></li>
                <li><a href="homepage" class="no-text"><i class="icon-off"></i></a></li>
            </ul>
        </nav>

        <div id="notifications">
            
        </div>

    </div>

</header>

<section id="container">

    <div id="large_bg_img" class="clearfix">

        <div id="large_bg_img_overlay"></div>
        <img src="/meetformeal/res/styles/default/img/bg2.jpg" id="image" class="big" alt="" />

        <h1>Mon profil</h1>

    </div>

    <div class="wrapper userpage">

        <div class="col-2">
            <h2>Informations sur votre profil</h2>
            <img src="/meetformeal/res/styles/default/img/users/herve.jpg" width="64" height="64" class="avatar" alt="">
            <p>
                <i class="icon-user"></i><strong><% out.print(user.getLastname()); %></strong>
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
					${category.name}				
				</c:forEach>
                
            </p>
            <p>
                Centres d'int�r�ts : 

				<c:forEach items="${sessionScope.interests}" var="interest">  
					${interest.tag}				
				</c:forEach>
                
            </p>
            <p>
                Présentation : Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.
                Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.
            </p>
            <p>
                <i class="icon-eye-open"></i>Profil complété à <strong>90%</strong>
            </p>
            <p>
                <a href="#" class="btn"><i class="icon-cog icon-plus"></i>Modifier votre profil</a>
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

            <a href="userpage" title="Pierre"><img src="/meetformeal/res/styles/default/img/users/pierre.jpg" width="64" height="64" class="avatar" alt=""></a>
            <a href="userpage" title="Romain"><img src="/meetformeal/res/styles/default/img/users/romain.jpg" width="64" height="64" class="avatar" alt=""></a>
        </div>

        <div class="col-2">

        </div>

        <div class="col-1">

        </div>

    </div>

</section>

<footer>

    <div class="wrapper">

        <div class="col-left">
            <h3>À propos de Meet For Meal</h3>
            <p>
                Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat.<br>
                Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.
            </p>
        </div>

        <div class="col-middle">
            <h3>Contactez-nous</h3>
            <p>
                <strong>Meet For Meal</strong><br>
                61 rue lafayette<br>
                93100 Rue Rapatal<br>
                FRANCE<br>
                <a href="mailto:contact@meetformeal.com">contact@meetformeal.com</a>
            </p>
        </div>

        <div class="col-right">

            <h3>Suivez-nous</h3>

            <div id="footer-menu-back-to-top">
                <a href="#"></a>
            </div>

            <p>
                Nos pages Facebook et Twitter seront bientôt disponibles !
            </p>

        </div>

        <div id="copyright">
            <p>
                &copy; 2013, Meet For Meal.
            </p>
        </div>

    </div>

</footer>