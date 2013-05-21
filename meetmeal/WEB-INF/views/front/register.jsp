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

            <form id="login-form" action="register" method="post">
                <p>
                    <label for="lastname">Entrez votre nom :</label>
                    <input type="text" id="last_name" name="last_name" value="<c:out value="${user.lastname}"/>">
                    <span class="erreur">${form.errors['last_name']}</span>
                </p>
                <p>
                    <label for="firstname">Entrez votre prenom :</label>
                    <input type="text" id="first_name" name="first_name" value="<c:out value="${user.firstname}"/>">
                    <span class="erreur">${form.errors['first_name']}</span>
                </p>
                <p>
                    <label for="email">Entrez votre adresse email</label>
                    <input type="text" id="email" name="email" value="<c:out value="${user.email}"/>">
                    <span class="erreur">${form.errors['email']}</span>
                </p>
                <p>
                    <label for="password">Entrez un mot de passe</label>
                    <input type="password" id="password" name="password">
                    <span class="erreur">${form.errors['password']}</span>
                </p>
                <p>
                    <label for="confirmation">Retapez le mot de passe</label>
                    <input type="password" id="confirmation" name="confirmation">
                    <span class="erreur">${form.errors['confirmation']}</span>
                </p>
                <p>
                    <input type="submit" value="Créer le compte">
                </p>
                <p class="${empty form.errors ? 'succes' : 'erreur'}">${form.result}</p>
            </form>

        </div>

    </div>

</section>

<footer>

</footer>