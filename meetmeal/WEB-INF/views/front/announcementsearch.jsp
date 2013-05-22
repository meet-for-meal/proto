<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <li><a href="mypage"><i class="icon-user"></i>Mon profil</a></li>
                <li><a href="message" class="no-text"><i class="icon-envelope"></i><span class="notif">12</span></a></li>
                <li><a href="homepage" class="no-text"><i class="icon-off"></i></a></li>
            </ul>
        </nav>

        <div id="notifications">
            <ul>
                <li>
                    <a href="#">
                        <span class="red">Alexandra Martin</span> a proposé une annonce.<br>
                        <span class="message-date">28/02/2013 Ã  14h42</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span class="red">Rémy Hannequin</span> souhaite devenir votre ami.<br>
                        <span class="message-date">28/02/2013 Ã  14h42</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span class="red">Pierre Grimaud</span> a proposé une annonce.<br>
                        <span class="message-date">28/02/2013 Ã  14h42</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span class="red">Romain Deligny</span> a proposé une annonce.<br>
                        <span class="message-date">28/02/2013 Ã  14h42</span>
                    </a>
                </li>
            </ul>
        </div>

    </div>

</header>

<section id="container">

    <div id="large_bg_img" class="clearfix">

        <div id="large_bg_img_overlay"></div>
        <img src="/meetformeal/res/styles/default/img/bg2.jpg" id="image" class="big" alt="" />

        <h1>Résultats de la recherche</h1>

    </div>

    <div class="wrapper">

        <div class="col-2">
            <h2>Les annonces proches de vous</h2>

            <c:choose>
				<c:when test="${!empty sessionScope.announces}">
					<ul>
					<c:forEach items="${sessionScope.announces}" var="announce">  
					  <li class="result-search">
					  	<div class="user-interact">
					  		<a href="announcement?announceid=${announce.id}" title="" class="btn see-profile"><i class="icon-eye-open"></i>En savoir plus</a>
		                    <a href="#" title="" class="btn add-friend"><i class="icon-plus"></i>Inviter à  manger</a>
					  	</div>
					  	<img src="/meetformeal/res/styles/default/img/users/default.png" width="40" height="40" class="avatar" alt="">
					  	<a href="userpage">
		                    <span class="user-title">${announce.creator.firstname} ${announce.creator.lastname}</span>
		                </a>
						<div class="user-content">
	                        <p class="announce-msg">&laquo; ${announce.message} &raquo;<p>
	                        <p>

	                            <i class="icon-time"></i>Disponibilité : <strong>Le <fmt:formatDate type="date" value="${announce.disponibilityDate}" /> à <fmt:formatDate type="time" timeStyle="short" value="${announce.disponibilityDate}" /></strong><br>
	                            <!-- <i class="icon-map-marker"></i>Localisation : <strong>Paris VÃ¨me</strong><br> -->
	                            <!-- <i class="icon-heart"></i>Centres d'intérêts : <a href="#">#informatique</a> <a href="#">#chat</a><br> -->
	                            <!-- <i class="icon-glass"></i>Préférences culinaires : <a href="#">#chinois</a> <a href="#">#grec</a> <a href="#">#pizzeria</a>-->
	                        </p>
	                    </div>
					  <li>
					</c:forEach>  
					</ul>
			        
			    </c:when>
				<c:otherwise>
					Aucun résultat trouvé pour votre recherche.
				</c:otherwise>
			</c:choose>

        </div>

        <div id="conversations" class="col-1">
            <h2>Pas de résultats?</h2>
            <p><span><a href="announcementcreate" class="btn"><i class="icon-pencil"></i> Poster une annonce</a></span></p>
            
            <h2>Meet For Meal</h2>
            <p>
                Le principe du site est simple: Trouver un partenaire pour partager un repas.
            </p>
            <p>
                Vous Ãªtes seul ce midi (/soir) ? Recherchez un partenaire aux alentours afin de partager un moment ensemble autour dâ€™un repas. Vous pouvez effectuer une recherche par type de restaurant ou par tranche horaires et choisir, parmi la liste des personnes libres, celle qui vous intéresse ! La visibilité de ses centres dâ€™intérÃªts vous aidera Ã  choisir un sujet de conversation.
            </p>
            <p>
                Personne ne vous intéresse ? Postez votre propre annonce !
                La recherche de partenaire est effectuée dans un rayon de 1km aux alentours de votre géolocalisation.
            </p>
        </div>

    </div>

</section>

<footer>

    <div class="wrapper">

        <div class="col-left">
            <h3>A€ propos de Meet For Meal</h3>
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
                Nos pages Facebook et Twitter seront bientÃ´t disponibles !
            </p>

        </div>

        <div id="copyright">
            <p>
                &copy; 2013, Meet For Meal.
            </p>
        </div>

    </div>

</footer>