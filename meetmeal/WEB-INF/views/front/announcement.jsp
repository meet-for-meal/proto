<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<section id="container">

    <div id="large_bg_img" class="clearfix">

        <div id="large_bg_img_overlay"></div>
        <img src="/meetformeal/res/styles/default/img/bg2.jpg" id="image" class="big" alt="" />

        <h1>Annonce de <c:if test="${!empty announce}">${announce.creator.firstname} ${announce.creator.lastname}</c:if></h1>

    </div>

    <div class="wrapper">

        <div class="col-2">

            <h2>D�tail de l'annonce :</h2>
			<c:choose>
				<c:when test="${!empty announce}">
					<div id="announcement-info">
            
            
		                <p>
		                    &laquo; ${announce.message} &raquo;
		                </p>
		                <p>
		                    <i class="icon-time"></i>Disponibilit� : <strong>Le <fmt:formatDate type="date" value="${announce.disponibilityDate}" /> � <fmt:formatDate type="time" timeStyle="short" value="${announce.disponibilityDate}" /></strong><br/> <!-- <i class="icon-map-marker"></i>Localisation : <strong>Paris V�me</strong>-->
		                	<i class="icon-heart"></i>Centres d'int�r�ts : 
                            <c:forEach items="${sessionScope.interests}" var="interest" varStatus="loop">  

                           		<a href="#" title=""/>#${fn:toLowerCase(interest.tag)}</a>

                            </c:forEach>
                            <br>
                            <i class="icon-glass"></i>Pr�f�rences culinaires : 
                            <c:forEach items="${sessionScope.categories}" var="category" varStatus="loop">  

                           		<a href="#" title=""/>#${fn:toLowerCase(category.name)}</a>

                            </c:forEach>
		                </p>
		            </div>

		            <div id="announcement-user">

		                <img src="/meetformeal/res/styles/default/img/users/default.png" width="64" height="64" class="avatar" alt="">
		                <p>
		                    Propos� par <strong>${announce.creator.firstname} ${announce.creator.lastname}</strong><br>

		                </p>
		                <p>
		                    <span><a href="/user/display?userId=${announce.creator.id}" class="btn"><i class="icon-eye-open icon-red"></i> Voir son profil</a></span>
		                </p>

		            </div>

			    </c:when>
				<c:otherwise>
					Vous n'avez pas pris le bon chemin pour acc�der � cette page. Peut-�tre �tes vous perdu ? N'h�sitez pas � cliquer <a href="index" title="Vers l'accueil"/>ici</a> pour retourner � l'accueil.
				</c:otherwise>
			</c:choose>

        </div>

        <div class="col-1">

            <h2>R�pondre � l'annonce :</h2>

            <p>
                <a href="messagecreate?id_user=${announce.creator.id}&ask=${announce.id}" class="btn"><i class="icon-pencil"></i>Je suis int�res�!</a>
            </p>

        </div>

        <div class="col-2">

        </div>

        <div class="col-1">

        </div>

    </div>

</section>