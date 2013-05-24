<%@ page pageEncoding="UTF-8" %><!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <title>Meet For Meal - <%= request.getAttribute("title") %></title>
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Droid+Sans:400,700">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Droid+Serif">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Economica:700,400italic">
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/res/styles/default/img/favicon.png">
    <%
        boolean isHomepage = (request.getAttribute("url").equals("homepage") || request.getAttribute("url").equals("register") || request.getAttribute("url").equals("connect-admin"));

	 	if(isHomepage){
	 		out.println("<link href=\"" + request.getContextPath() +"/res/styles/default/homepage.css\" rel=\"stylesheet\">");
	 	}
	 	else{
	 		out.println("<link href=\""+ request.getContextPath() +"/res/styles/default/blitzer/jquery-ui-custom.css\" rel=\"stylesheet\">");
	 		out.println("<link href=\""+ request.getContextPath() +"/res/styles/default/styles.css\" rel=\"stylesheet\">");
	 	}
	%>
</head>
<body>

<%
    if(!isHomepage){
%>
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
                <li><a href="logout" class="no-text"><i class="icon-off"></i></a></li>
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
                    <span class="red">Rémy Hannequin</span> souhaite devenir votre ami.<br>
                    <p class="notification-action">
                        <a href="#" class="btn mfm-action" data-action="accept-request">Accepter</a> <a href="#" class="btn mfm-action btn-neutral" data-action="ignore-request">Ignorer</a>
                    </p>
                    <span class="message-date">28/02/2013 à 14h42</span>
                </li>
                <li>
                    <span class="red">Rémy Hannequin</span> souhaite devenir votre ami.<br>
                    <p class="notification-action">
                    <a href="#" class="btn mfm-action" data-action="accept-request">Accepter</a> <a href="#" class="btn mfm-action btn-neutral" data-action="ignore-request">Ignorer</a>
                    </p>
                    <span class="message-date">28/02/2013 à 14h42</span>
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

<% } %>

<% 
 out.flush();
 request.getRequestDispatcher("/WEB-INF/views/front/"+request.getAttribute("url")+".jsp")
         .include(request, response);
%>

<%
	 	if(!isHomepage){
%>

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

<% } %>

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
<!-- build:js scripts/main.js -->
<script id="require-js" data-params='{"applicationPath": "<%= request.getContextPath() %>", "foursquareCategories":[{"name":"Boulangerie","id":"4bf58dd8d48988d16a941735"},{"name":"Brasserie","id":"50327c8591d4c4b30a586d5d"},{"name":"Lieu servant des hamburgers","id":"4bf58dd8d48988d16c941735"},{"name":"Restaurant chinois","id":"4bf58dd8d48988d145941735"},{"name":"Café-restaurant","id":"4bf58dd8d48988d147941735"},{"name":"Fast-food","id":"4bf58dd8d48988d16e941735"},{"name":"Restaurant français","id":"4bf58dd8d48988d10c941735"},{"name":"Restaurant grec","id":"4bf58dd8d48988d10e941735"},{"name":"Restaurant indien","id":"4bf58dd8d48988d10f941735"},{"name":"Restaurant japonais","id":"4bf58dd8d48988d111941735"},{"name":"Restaurant coréen","id":"4bf58dd8d48988d113941735"},{"name":"Pizzeria","id":"4bf58dd8d48988d1ca941735"},{"name":"Sandwicherie","id":"4bf58dd8d48988d1c5941735"},{"name":"Restaurant de fruits de mer","id":"4bf58dd8d48988d1ce941735"},{"name":"Snack","id":"4bf58dd8d48988d1c7941735"},{"name":"Restaurant-grill","id":"4bf58dd8d48988d1cc941735"},{"name":"Bar à sushis","id":"4bf58dd8d48988d1d2941735"},{"name":"Bar à tapas","id":"4bf58dd8d48988d1db931735"},{"name":"Restaurant thaïlandais","id":"4bf58dd8d48988d149941735"},{"name":"Restaurant turc","id":"4f04af1f2fb6e1c99f3db0bb"},{"name":"Restaurant végétarien/végétalien","id":"4bf58dd8d48988d1d3941735"}]}' data-main="<%= request.getContextPath() %>/res/js/main" src="<%= request.getContextPath() %>/res/js/require.js"></script>
<!-- endbuild -->

</body>
</html>
