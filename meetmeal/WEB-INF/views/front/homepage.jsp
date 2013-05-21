<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div id="large_bg_img" class="clearfix">

    <div id="large_bg_img_overlay"></div>
    <img src="/meetmeal/res/styles/default/img/bg2.jpg" id="image" class="big" alt="" />

</div>

<header>

    <div class="wrapper">

    </div>

</header>

<section id="container">

    <div class="wrapper">

        <div id="intro">

            <h1>Meet for Meal</h1>

            <a href="index"><img src="/meetmeal/res/styles/default/img/logo.png" id="logo" alt="" /></a>

            <h2>
                Eat better. Eat together.
            </h2>

        </div>

        <div id="login">

            <div id="welcome">
                <h2>Un réseau de partage</h2>
                <p>
                    Meet for Meal est un réseau qui permet de trouver des partenaires de repas à  travers une recherche en temps réel qui vous connecte aux personnes proches de vous.
                </p>
                <p>
                    Trouvez simplement des partenaires culinaires que vous pensez étre les plus intéressants pour partager un repas avec vous.
                </p>
            </div>
			<c:if test="${!empty not_exist}">
				<p>Mauvais identifiant / mot de passe</p>
			</c:if>
            <form id="login-form" action="homepage" method="post">
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