<%@page import="java.util.List"%>
<%@page import="org.essilab.module.user.model.User"%>

<section id="container">

    <!-- start: Map -->
    <div id="meetformealmap" class="deployed">

        <!-- starts: Google Maps -->
        <div id="googlemaps-container-top"></div>
        <div id="googlemaps" class="google-map google-map-full"></div>
        <div id="googlemaps-container-bottom"></div>
        <!-- end: Google Maps -->

        <div id="closest-users">
            <h3>Utilisateurs proches de vous</h3>
            <ul id="closest-users-list">

            </ul>
        </div>

        <div id="toggle-map">

        </div>

    </div>
    <!-- end: Map -->

    <div class="wrapper home">

        <div class="col-container">
            <div class="col-2">
                <h2>Recherche avancée</h2>
                <form id="user-search-form" action="announcementsearch" method="post">
                    <p>
                        <label for="time-start"><strong><i class="icon-time"></i> Quand ? </strong></label>
                        De <input type="text" id="time-start"> à <input type="text" id="time-end" >
                        <input type="submit" class="btn" value="Trouver">
                    </p>
                    <p>
                        <label for="interests"><strong><i class="icon-heart"></i> Goûts / Intérêts</strong></label>
                        <input type="text" id="interests" name="interests">
                        <span id="suggestions"></span>
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