<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String resto = request.getParameter("resto");
String email = request.getParameter("email");
String object = request.getParameter("object");
String message = request.getParameter("message");
if(resto == null) resto = "";
if(email == null) email = "";
if(object == null) object = "";
if(message == null) message = "";


%>

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
                <h2>Contactez nous</h2>
                <p>
                    Pour devenir un de nos partenaires ou nous poser des questions , il vous suffit de remplir ces champs !
                </p>
            </div>
            <form id="login-form" action="contact" method="post">
                <p>
                    <label for="resto_name">Entrez le nom de votre restaurant :</label>
                    <input type="text" id="resto_name" name="resto_name" value="<%=resto %>">
                    <c:if test="${!empty form.errors['resto_name']}">
                    <span class="message erreur">
                    ${form.errors['rest_name']}
                    </span>
                    </c:if>
                </p>
                <p>
                    <label for="email">Entrez votre adresse email :</label>
                    <input type="text" id="email" name="email" value="<%=email %>">
                    <c:if test="${!empty form.errors['email']}">
                    <span class="message erreur">
                    ${form.errors['email']}
                    </span>
                    </c:if>
                </p>
                <p>
                    <label for="object">Objet :</label>
                    <input type="text" id="object" name="object" value="<%=object %>">
                    <c:if test="${!empty form.errors['object']}">
                    <span class="message erreur">
                    ${form.errors['object']}
                    </span>
                    </c:if>
                </p>
                <p>
                	<label for="message">Votre Message :</label>
                    <textarea cols="10" type="text" id="message" name="message" style="width:350px; height:150px; resize:none;"><%=message %></textarea>
                    <c:if test="${!empty form.errors['message']}">
                    <span class="message erreur">
                    ${form.errors['message']}
                    </span>
                    </c:if>
                </p>
                <p>
                    <input type="submit" value="Envoyer">
                </p>
            </form>

        </div>

    </div>

</section>

<footer>

</footer>
