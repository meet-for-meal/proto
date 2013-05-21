<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

        <h1>Annonce de <c:if test="${!empty announce}">${announce.creator.firstname} ${announce.creator.lastname}</c:if></h1>

    </div>

    <div class="wrapper">

        <div class="col-2">

            <h2>Détail de l'annonce :</h2>
			<c:choose>
				<c:when test="${!empty announce}">
					<div id="announcement-info">
            
            
		                <p>
		                    &laquo; ${announce.message} &raquo;
		                </p>
		                <p>
		                    <i class="icon-time"></i>Disponibilité : <strong>${announce.disponibilityDate}</strong> <!-- <i class="icon-map-marker"></i>Localisation : <strong>Paris VÃ¨me</strong>-->
		                </p>
		            </div>
		
		            <div id="announcement-user">
		
		                <img src="/meetformeal/res/styles/default/img/users/default.png" width="64" height="64" class="avatar" alt="">
		                <p>
		                    Proposé par <strong>${announce.creator.firstname} ${announce.creator.lastname}</strong><br>
		                    <!-- <i class="icon-heart"></i>Centres d'intérÃªts  : <a href="#">#communication</a> <a href="#">#badminton</a><br>
		                    <i class="icon-glass"></i>Préférences culinaires : <a href="#">#italien</a> <a href="#">#japonais</a><br>-->
		                </p>
		                <p>
		                    <span><a href="/user/display?userId=${announce.creator.id}" class="btn"><i class="icon-eye-open icon-red"></i> Voir son profil</a></span>
		                </p>
		
		            </div>
			        
			    </c:when>
				<c:otherwise>
					Vous n'avez pas pris le bon chemin pour accéder à cette page. Peut-être êtes vous perdu ? N'hésitez pas à cliquer <a href="index" title="Vers l'accueil"/>ici</a> pour retourner à l'accueil.
				</c:otherwise>
			</c:choose>

        </div>

        <div class="col-1">

            <h2>Répondre à  l'annonce :</h2>

            <p>
                <a href="#" class="btn"><i class="icon-pencil"></i>Je suis intéressé !</a>
            </p>

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
            <h3>Ã€ propos de Meet For Meal</h3>
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