<%@page import="java.util.List"%>
<%@page import="org.essilab.module.user.model.User"%>



<header>

    <div class="wrapper">

        <a href="index" id="logo"><img src="<%= request.getContextPath() %>/res/styles/default/img/logo-mini.png" alt=""></a>

        <form id="search-form" action="userpage">
            <input type="text" name="search" placeholder="Rechercher une personne ou un restaurant...">
        </form>

        <nav>
            <ul>
                <li><a href="#" class="no-text toggle-notif"><i class="icon-info-sign"></i><span class="notif">3</span></a></li>
                <li class="current"><a href="index"><i class="icon-home"></i> Accueil</a></li>
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
                        <span class="message-date">28/02/2013 à 14h42</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span class="red">Rémy Hannequin</span> souhaite devenir votre ami.<br>
                        <span class="message-date">28/02/2013 à 14h42</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span class="red">Pierre Grimaud</span> a proposé une annonce.<br>
                        <span class="message-date">28/02/2013 à 14h42</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span class="red">Romain Deligny</span> a proposé une annonce.<br>
                        <span class="message-date">28/02/2013 à 14h42</span>
                    </a>
                </li>
            </ul>
        </div>

    </div>

</header>

<section id="container">

    <!-- start: Map -->
    <div style="position:relative;">

        <!-- starts: Google Maps -->
        <div id="googlemaps-container-top"></div>
        <div id="googlemaps" class="google-map google-map-full"></div>
        <div id="googlemaps-container-bottom"></div>
        <!-- end: Google Maps -->

        <div id="closest-users">
            <h3>Utilisateurs proches de vous</h3>
            <ul>
                <li class="user">
                    <a href="announcement">
                        <img src="<%= request.getContextPath() %>/res/styles/default/img/users/alexandra.jpg" width="40" height="40" class="avatar" alt="">
                        <span class="user-title">Alexandra Martin</span>
                        <span class="user-time">12H - 13H</span>
                        <span class="user-tags">#communication #musique #badminton</span>
                    </a>
                </li>
                <li class="user">
                    <a href="announcement">
                        <img src="<%= request.getContextPath() %>/res/styles/default/img/users/herve.jpg" width="40" height="40" class="avatar" alt="">
                        <span class="user-title">Hervé Tran</span>
                        <span class="user-time">13H - 14H30</span>
                        <span class="user-tags">#multimedia #guitare #blues</span>
                    </a>
                </li>
                <li class="user">
                    <a href="announcement">
                        <img src="<%= request.getContextPath() %>/res/styles/default/img/users/pierre.jpg" width="40" height="40" class="avatar" alt="">
                        <span class="user-title">Pierre Grimaud</span>
                        <span class="user-time">13H - 14H</span>
                        <span class="user-tags">#informatique #chat</span>
                    </a>
                </li>
                <li class="user">
                    <a href="announcement">
                        <img src="<%= request.getContextPath() %>/res/styles/default/img/users/romain.jpg" width="40" height="40" class="avatar" alt="">
                        <span class="user-title">Romain Deligny</span>
                        <span class="user-time">12H - 13H30</span>
                        <span class="user-tags">#infirmier #football</span>
                    </a>
                </li>
            </ul>
        </div>

    </div>
    <!-- end: Map -->

    <div class="wrapper home">

        <div class="col-container">
            <div class="col-2">
                <h2>Recherche avancée</h2>
                <form id="user-search-form" action="announcementsearch">
                    <p>
                        <label for="time-start"><strong><i class="icon-time"></i> Quand ? </strong></label>
                        De <input type="text" id="time-start"> à <input type="text" id="time-end" >
                        <input type="submit" class="btn" value="Trouver">
                    </p>
                    <p>
                        <label for="interests"><strong><i class="icon-heart"></i> Goûts / Intérêts</strong></label>
                        <input type="text" id="interests">
                    </p>
                </form>

            </div>
            <div id="current-user" class="col-1">
                <img src="<%= request.getContextPath() %>/res/styles/default/img/users/herve.jpg" width="64" height="64" class="avatar" alt="">
                <h3><a href="#" class="black">Hervé Tran</a></h3>
                <span class="user-tags"><a href="#">#multimedia</a> <a href="#">#guitare</a> <a href="#">#blues</a></span>
                <span><a href="announcementcreate" class="btn"><i class="icon-pencil"></i> Poster une annonce</a></span>
            </div>
        </div>
        <div class="col-2">

            <hr>

            <h2>À découvrir</h2>

            <div id="featured-restaurants">
                <div class="restaurant">
                    <img src="<%= request.getContextPath() %>/res/styles/default/img/restaurants/ocinq.jpg" title="Restaurant Ocinq" alt="">
                    <div class="restaurant-infos">
                        <a href="#" title="Restaurant Ocinq">
                            <h3>Restaurant Ocinq</h3>
                            <p>Situé à quelques pas de Montparnasse, rue de la Gaîté, le restaurant Ocinq vous accueille dans un cadre exceptionnel : décoration moderne aux teintes chaleureuses, ambiance lounge, large baie vitrée, il a tout pour plaire !</p>
                        </a>
                    </div>
                </div>
                <div class="restaurant">
                    <img src="<%= request.getContextPath() %>/res/styles/default/img/restaurants/creperie-framboise.jpg" title="Crêperie Framboise" alt="">
                    <div class="restaurant-infos">
                        <a href="#" title="Crêperie Framboise">
                            <h3>Crêperie Framboise</h3>
                            <p>La Crêperie Framboise, située dans le 8ème arrondissement de Paris, à deux pas du rond-point des Champs-Élysées, vous invite pour une pause gourmande dans un cadre élégant, moderne et très lumineux. </p>
                        </a>
                    </div>
                </div>
                <div class="restaurant">
                    <img src="<%= request.getContextPath() %>/res/styles/default/img/restaurants/vingt-vins-dart.jpg" title="Vingt vins d'art" alt="">
                    <div class="restaurant-infos">
                        <a href="#" title="Vingt vins d'art">
                            <h3>Vingt vins d'art</h3>
                            <p>Dans le Marais, entre les métros Saint-Paul et Pont Marie, le Vingt vins d'art a ouvert ses portes tout récemment. À la fois bar à (très bons) vins, restaurant et galerie d'art, le lieu propose aussi quelques concerts pour des soirées chaudement recommandées !</p>
                        </a>
                    </div>
                </div>
            </div>

        </div>

        <div class="col-1">

            <hr>

            <h2>Meet For Meal</h2>
            <p>
                Le principe du site est simple: Trouver un partenaire pour partager un repas.
            </p>
            <p>
                Vous êtes seul ce midi (/soir) ? Recherchez un partenaire aux alentours afin de partager un moment ensemble autour d’un repas. Vous pouvez effectuer une recherche par type de restaurant ou par tranche horaires et choisir, parmi la liste des personnes libres, celle qui vous intéresse ! La visibilité de ses centres d’intérêts vous aidera à choisir un sujet de conversation.
            </p>
            <p>
                Personne ne vous intéresse ? Postez votre propre annonce !
                La recherche de partenaire est effectuée dans un rayon de 1km aux alentours de votre géolocalisation.
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

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
<!-- build:js scripts/main.js -->
<script id="require-js" data-params='{"foursquareCategories":[{"name":"Boulangerie","id":"4bf58dd8d48988d16a941735"},{"name":"Brasserie","id":"50327c8591d4c4b30a586d5d"},{"name":"Lieu servant des hamburgers","id":"4bf58dd8d48988d16c941735"},{"name":"Restaurant chinois","id":"4bf58dd8d48988d145941735"},{"name":"Café-restaurant","id":"4bf58dd8d48988d147941735"},{"name":"Fast-food","id":"4bf58dd8d48988d16e941735"},{"name":"Restaurant français","id":"4bf58dd8d48988d10c941735"},{"name":"Restaurant grec","id":"4bf58dd8d48988d10e941735"},{"name":"Restaurant indien","id":"4bf58dd8d48988d10f941735"},{"name":"Restaurant japonais","id":"4bf58dd8d48988d111941735"},{"name":"Restaurant coréen","id":"4bf58dd8d48988d113941735"},{"name":"Pizzeria","id":"4bf58dd8d48988d1ca941735"},{"name":"Sandwicherie","id":"4bf58dd8d48988d1c5941735"},{"name":"Restaurant de fruits de mer","id":"4bf58dd8d48988d1ce941735"},{"name":"Snack","id":"4bf58dd8d48988d1c7941735"},{"name":"Restaurant-grill","id":"4bf58dd8d48988d1cc941735"},{"name":"Bar à sushis","id":"4bf58dd8d48988d1d2941735"},{"name":"Bar à tapas","id":"4bf58dd8d48988d1db931735"},{"name":"Restaurant thaïlandais","id":"4bf58dd8d48988d149941735"},{"name":"Restaurant turc","id":"4f04af1f2fb6e1c99f3db0bb"},{"name":"Restaurant végétarien/végétalien","id":"4bf58dd8d48988d1d3941735"}]}' data-main="<%= request.getContextPath() %>/res/js/main" src="<%= request.getContextPath() %>/res/js/require.js"></script>
<!-- endbuild -->