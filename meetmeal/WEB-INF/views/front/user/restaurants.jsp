
<header>

    <div class="wrapper">

        <a href="index" id="logo"><img src="/meetmeal/res/styles/default/img/logo-mini.png" alt=""></a>

        <form id="search-form">
            <input type="text" name="search" placeholder="Rechercher une personne ou un restaurant...">
        </form>

        <nav>
            <ul>
                <li><a href="#" class="no-text toggle-notif"><i class="icon-info-sign"></i><span class="notif">3</span></a></li>
                <li><a href="index"><i class="icon-home"></i> Accueil</a></li>
                <li class="current"><a href="restaurants"><i class="icon-map-marker"></i>Les restaurants</a></li>
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
    <div id="restaurant-map" style="position:relative;">

        <!-- starts: Google Maps -->
        <div id="googlemaps-container-top"></div>
        <div id="googlemaps" class="google-map google-map-full"></div>
        <div id="googlemaps-container-bottom"></div>
        <!-- end: Google Maps -->

    </div>
    <!-- end: Map -->

    <div class="wrapper">

        <div id="map-action-bar">
            <div class="actions">
                <button class="btn find-venues"><i class="icon-search"></i>Rechercher</button>
                <button class="btn check-all"><i class="icon-check"></i>Tout cocher</button>
                <button class="btn uncheck-all"><i class="icon-remove"></i>Tout décocher</button>
            </div>
            <div class="categories"></div>
        </div>

        <div class="col-3">

            <hr>

            <h2>Les restaurants autour de vous</h2>
            <ul id="search-result">
                <li class="featured">
                    <img src="https://foursquare.com/img/categories_v2/food/sandwiches_bg_32.png" class="restaurant-category">
                    <p class="restaurant-description">
                        <span><strong class="title red">BALT</strong></span><br>
                        <span>Catégorie : <strong>Sandwicherie</strong></span><br>
                        <span>Adresse : <strong>15, rue Monsigny Paris (75002) </strong></span><br>
                        <span>&laquo; Les sandwichs Balt doivent beaucoup à la baguette Julien. Ils sont à composer soi-même : rosbif, œuf dur écrasé, poivron mariné ou mayonnaise au basilic etc. &raquo;</span><br>
                        <a href="#" class="btn"><i class="icon-eye-open icon-red"></i>Consulter</a>
                    </p>
                </li>
                <li>
                    <img src="https://foursquare.com/img/categories_v2/nightlife/bar_bg_32.png" class="restaurant-category">
                    <p class="restaurant-description">
                        <span><strong class="title red">2 bis</strong></span><br>
                        <span>Catégorie : <strong>Bar / Sandwicherie</strong></span><br>
                        <span>Adresse : <strong>2 bis rue des Ecoles (75005) </strong></span><br>
                        <span>&laquo; La pinte la moins chère de Paris &raquo;</span><br>
                    </p>
                </li>
                <li>
                    <img src="https://foursquare.com/img/categories_v2/food/sandwiches_bg_32.png" class="restaurant-category">
                    <p class="restaurant-description">
                        <span><strong class="title red">BALT</strong></span><br>
                        <span>Catégorie : <strong>Sandwicherie</strong></span><br>
                        <span>Adresse : <strong>15, rue Monsigny Paris (75002) </strong></span><br>
                        <span>&laquo; Les sandwichs Balt doivent beaucoup à la baguette Julien. Ils sont à composer soi-même : rosbif, œuf dur écrasé, poivron mariné ou mayonnaise au basilic etc. &raquo;</span><br>
                    </p>
                </li>
                <li>
                    <img src="https://foursquare.com/img/categories_v2/nightlife/bar_bg_32.png" class="restaurant-category">
                    <p class="restaurant-description">
                        <span><strong class="title red">2 bis</strong></span><br>
                        <span>Catégorie : <strong>Bar</strong></span><br>
                        <span>Adresse : <strong>2 bis rue des Ecoles (75005) </strong></span><br>
                        <span>&laquo; La pinte la moins chère de Paris &raquo;</span><br>
                    </p>
                </li>
            </ul>

        </div>

        <div class="col-1">

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
<script id="require-js" data-params='{"foursquareCategories":[{"name":"Boulangerie","id":"4bf58dd8d48988d16a941735"},{"name":"Brasserie","id":"50327c8591d4c4b30a586d5d"},{"name":"Lieu servant des hamburgers","id":"4bf58dd8d48988d16c941735"},{"name":"Restaurant chinois","id":"4bf58dd8d48988d145941735"},{"name":"Café-restaurant","id":"4bf58dd8d48988d147941735"},{"name":"Fast-food","id":"4bf58dd8d48988d16e941735"},{"name":"Restaurant français","id":"4bf58dd8d48988d10c941735"},{"name":"Restaurant grec","id":"4bf58dd8d48988d10e941735"},{"name":"Restaurant indien","id":"4bf58dd8d48988d10f941735"},{"name":"Restaurant japonais","id":"4bf58dd8d48988d111941735"},{"name":"Restaurant coréen","id":"4bf58dd8d48988d113941735"},{"name":"Pizzeria","id":"4bf58dd8d48988d1ca941735"},{"name":"Sandwicherie","id":"4bf58dd8d48988d1c5941735"},{"name":"Restaurant de fruits de mer","id":"4bf58dd8d48988d1ce941735"},{"name":"Snack","id":"4bf58dd8d48988d1c7941735"},{"name":"Restaurant-grill","id":"4bf58dd8d48988d1cc941735"},{"name":"Bar à sushis","id":"4bf58dd8d48988d1d2941735"},{"name":"Bar à tapas","id":"4bf58dd8d48988d1db931735"},{"name":"Restaurant thaïlandais","id":"4bf58dd8d48988d149941735"},{"name":"Restaurant turc","id":"4f04af1f2fb6e1c99f3db0bb"},{"name":"Restaurant végétarien/végétalien","id":"4bf58dd8d48988d1d3941735"}]}' data-main="/meetmeal/res/js/main" src="/meetmeal/res/js/require.js"></script>
<!-- endbuild -->