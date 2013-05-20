<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div id="large_bg_img" class="clearfix">

    <div id="large_bg_img_overlay"></div>
    <img src="/meetformeal/res/styles/default/img/bg2.jpg" id="image" class="big" alt="" />

</div>

<header>

    <div class="wrapper">

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

            <div id="welcome">
                <h2>Administration</h2>
                <p>
                	Ce contenu est restreint au groupe des administrateurs.
                </p>
            </div>
			<c:if test="${!empty not_exist}">
				<p>Mauvais identifiant / mot de passe</p>
			</c:if>
            <form id="login-form" action="connect-admin" method="post">
                <p>
                    <label for="email">Entrez votre email</label>
                    <input type="text" id="email" name="email" value="<c:out value="${user.email}"/>">
                    <span class="erreur">${form.errors['email']}</span>
                </p>
                <p>
                    <label for="password">Entrez votre mot de passe</label>
                    <input type="password" id="password" name="password">
                    <span class="erreur">${form.errors['password']}</span>
                </p>
                <p>
                    <input type="submit" value="Se connecter">
                </p>
            </form>
			<p class="${empty form.errors ? 'succes' : 'erreur'}">${form.result}</p>
            <p id="extra-form">
                <a href="register">Cliquez ici pour ouvrir un compte Meet for Meal</a>
            </p>
            <c:if test="${!empty sessionScope.sessionUser}">
				<p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUser.email}</p>
			</c:if>
        </div>

    </div>

</section>



<footer>

</footer>