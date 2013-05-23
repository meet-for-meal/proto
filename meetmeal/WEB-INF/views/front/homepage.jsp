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

        <c:if test="${!empty sessionScope.sessionUser}">
        <span class="alert succes">
            <a class="close" href="#">x</a>
            Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUser.email}
        </span>
        </c:if>

        <c:if test="${!empty not_exist}">
        <span class="alert erreur">
            <a class="close" href="#">x</a>
            Mauvais identifiant / mot de passe
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

            <div id="welcome">
                <h2>Un réseau de partage</h2>
                <p>
                    Meet for Meal est un réseau qui permet de trouver des partenaires de repas à travers une recherche en temps réel qui vous connecte aux personnes proches de vous.
                </p>
                <p>
                    Trouvez simplement des partenaires culinaires que vous pensez être les plus intéressants pour partager un repas avec vous.
                </p>
            </div>
            <form id="login-form" action="homepage" method="post">
                <p>
                    <label for="email">Entrez votre email</label>
                    <input type="text" id="email" name="email" value="<c:out value="${user.email}"/>">
                    <c:if test="${!empty form.errors['email']}">
                    <span class="message erreur">
                    ${form.errors['email']}
                    </span>
                    </c:if>
                </p>
                <p>
                    <label for="password">Entrez votre mot de passe</label>
                    <input type="password" id="password" name="password">
                    <c:if test="${!empty form.errors['password']}">
                    <span class="message erreur">
                    ${form.errors['password']}
                    </span>
                    </c:if>
                </p>
                <p>
                    <input type="submit" value="Se connecter">
                </p>
            </form>
            <p id="extra-form">
                <a href="register">Cliquez ici pour ouvrir un compte Meet for Meal</a>
            </p>

        </div>

    </div>

</section>



<footer>

</footer>
