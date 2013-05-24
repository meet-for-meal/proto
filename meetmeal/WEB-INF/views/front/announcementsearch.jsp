<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                            <a href="messagecreate?id_user=${announce.creator.id}&ask=${announce.id}" class="btn mfm-action" data-action="friend-request"><i class="icon-plus"></i>Inviter à manger</a>
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
                Vous Ãªtes seul ce midi (/soir) ? Recherchez un partenaire aux alentours afin de partager un moment ensemble autour dâ€™un repas. Vous pouvez effectuer une recherche par type de restaurant ou par tranche horaires et choisir, parmi la liste des personnes libres, celle qui vous intï¿½resse ! La visibilitï¿½ de ses centres dâ€™intï¿½rÃªts vous aidera Ã  choisir un sujet de conversation.
            </p>
            <p>
                Personne ne vous intï¿½resse ? Postez votre propre annonce !
                La recherche de partenaire est effectuï¿½e dans un rayon de 1km aux alentours de votre gï¿½olocalisation.
            </p>
        </div>

    </div>

</section>