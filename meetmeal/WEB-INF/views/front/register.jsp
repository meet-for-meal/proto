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
                <h2>Création de compte</h2>
                <p>
                    Rien de plus simple pour créer un compte, il vous suffit de remplir ces champs !
                </p>
            </div>
            <form id="login-form" action="register" method="post">

                <p>
                    <label for="last_name">Entrez votre nom :</label>
                    <input type="text" id="last_name" name="last_name" value="<c:out value="${user.lastname}"/>">
                    <c:if test="${!empty form.errors['last_name']}">
                    <span class="message erreur">
                    ${form.errors['last_name']}
                    </span>
                    </c:if>
                </p>
                <p>
                    <label for="first_name">Entrez votre prenom :</label>
                    <input type="text" id="first_name" name="first_name" value="<c:out value="${user.firstname}"/>">
                    <c:if test="${!empty form.errors['first_name']}">
                    <span class="message erreur">
                    ${form.errors['first_name']}
                    </span>
                    </c:if>
                </p>
                <p>
                    <label for="email">Entrez votre adresse email</label>
                    <input type="text" id="email" name="email" value="<c:out value="${user.email}"/>">
                    <c:if test="${!empty form.errors['email']}">
                    <span class="message erreur">
                    ${form.errors['email']}
                    </span>
                    </c:if>
                </p>
                <p>
                    <label for="password">Entrez un mot de passe</label>
                    <input type="password" id="password" name="password"><br>

                    <br>

                    <label for="confirmation">Retapez le mot de passe</label>
                    <input type="password" id="confirmation" name="confirmation">

                    <c:if test="${!empty form.errors['password']}">
                    <span class="message erreur">
                    ${form.errors['password']}
                    </span>
                    </c:if>
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
