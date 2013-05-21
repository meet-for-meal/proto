<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="large_bg_img" class="clearfix">

    <div id="large_bg_img_overlay"></div>
    <img src="/meetformeal/res/styles/default/img/bg2.jpg" id="image" class="big" alt="" />

</div>

<header>

    <div id="alerts">

        <c:if test="${!empty form.errors}">
        <span class="alert ${empty form.errors ? 'succes' : 'erreur'}">
            <a class="close" href="#">x</a>
            ${form.result}
        </span>
        </c:if>

        <c:if test="${!empty form.errors['last_name']}">
        <span class="alert erreur">
            <a class="close" href="#">x</a>
            ${form.errors['last_name']}
        </span>
        </c:if>

        <c:if test="${!empty form.errors['first_name']}">
        <span class="alert erreur">
            <a class="close" href="#">x</a>
            ${form.errors['first_name']}
        </span>
        </c:if>

        <c:if test="${!empty form.errors['email']}">
        <span class="alert erreur">
            <a class="close" href="#">x</a>
            ${form.errors['email']}
        </span>
        </c:if>

        <c:if test="${!empty form.errors['password']}">
        <span class="alert erreur">
            <a class="close" href="#">x</a>
            ${form.errors['password']}
        </span>
        </c:if>

        <c:if test="${!empty form.errors['confirmation']}">
        <span class="alert erreur">
            <a class="close" href="#">x</a>
            ${form.errors['confirmation']}
        </span>
        </c:if>

    </div>

</header>

<section id="container">

    <div class="wrapper">

        <div id="intro">

            <h1>Meet for Meal</h1>

            <a href="index"><img src="/meetformeal/res/styles/default/img/logo.png" id="logo" alt="" /></a>

            <h2>
                Eat better. Eat together.
            </h2>

        </div>

        <div id="login">

            <form id="login-form" action="register" method="post">
                <p>
                    <label for="last_name">Entrez votre nom :</label>
                    <input type="text" id="last_name" name="last_name" value="<c:out value="${user.lastname}"/>">
                </p>
                <p>
                    <label for="first_name">Entrez votre prenom :</label>
                    <input type="text" id="first_name" name="first_name" value="<c:out value="${user.firstname}"/>">
                </p>
                <p>
                    <label for="email">Entrez votre adresse email</label>
                    <input type="text" id="email" name="email" value="<c:out value="${user.email}"/>">
                </p>
                <p>
                    <label for="password">Entrez un mot de passe</label>
                    <input type="password" id="password" name="password">
                </p>
                <p>
                    <label for="confirmation">Retapez le mot de passe</label>
                    <input type="password" id="confirmation" name="confirmation">
                </p>
                <p>
                    <input type="submit" value="Valider">
                </p>
            </form>

        </div>

    </div>

</section>

<footer>

</footer>

<script id="require-js" data-params='{"foursquareCategories":[{"name":"Boulangerie","id":"4bf58dd8d48988d16a941735"},{"name":"Brasserie","id":"50327c8591d4c4b30a586d5d"},{"name":"Lieu servant des hamburgers","id":"4bf58dd8d48988d16c941735"},{"name":"Restaurant chinois","id":"4bf58dd8d48988d145941735"},{"name":"Café-restaurant","id":"4bf58dd8d48988d147941735"},{"name":"Fast-food","id":"4bf58dd8d48988d16e941735"},{"name":"Restaurant français","id":"4bf58dd8d48988d10c941735"},{"name":"Restaurant grec","id":"4bf58dd8d48988d10e941735"},{"name":"Restaurant indien","id":"4bf58dd8d48988d10f941735"},{"name":"Restaurant japonais","id":"4bf58dd8d48988d111941735"},{"name":"Restaurant coréen","id":"4bf58dd8d48988d113941735"},{"name":"Pizzeria","id":"4bf58dd8d48988d1ca941735"},{"name":"Sandwicherie","id":"4bf58dd8d48988d1c5941735"},{"name":"Restaurant de fruits de mer","id":"4bf58dd8d48988d1ce941735"},{"name":"Snack","id":"4bf58dd8d48988d1c7941735"},{"name":"Restaurant-grill","id":"4bf58dd8d48988d1cc941735"},{"name":"Bar à sushis","id":"4bf58dd8d48988d1d2941735"},{"name":"Bar à tapas","id":"4bf58dd8d48988d1db931735"},{"name":"Restaurant thaïlandais","id":"4bf58dd8d48988d149941735"},{"name":"Restaurant turc","id":"4f04af1f2fb6e1c99f3db0bb"},{"name":"Restaurant végétarien/végétalien","id":"4bf58dd8d48988d1d3941735"}]}' data-main="<%= request.getContextPath() %>/res/js/main" src="<%= request.getContextPath() %>/res/js/require.js"></script>