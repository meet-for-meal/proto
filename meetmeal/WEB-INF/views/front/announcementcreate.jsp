<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<section id="container">
<div id="alerts">
<c:choose>

	<c:when test="${sessionScope.success == true}">
		<div class="alert succes" style="display: block;"><a class="close" href="#">x</a>Annonce déposée avec succès.</div>
	</c:when>
	<c:when test="${sessionScope.success == false}">
		<div class="alert erreur" style="display: block;"><a class="close" href="#">x</a>Erreur lors de l'envoi de l'annonce</div>
	</c:when>
	<c:otherwise>
		
	</c:otherwise>

</c:choose>
</div>
    <div id="large_bg_img" class="clearfix">

        <div id="large_bg_img_overlay"></div>
        <img src="/meetformeal/res/styles/default/img/bg2.jpg" id="image" class="big" alt="" />

        <h1>Créer une annonce</h1>

    </div>

    <div class="wrapper">

        <div class="col-2 centered">

            <form id="announcement-form" action="announcementcreate" method="post">
                <p>

                    <label for="time-start"><strong><i class="icon-time"></i> Vos disponibilités ? </strong></label>
                    Le <input type="text" id="date-start" name="dispo-date"> à <input type="text" id="time-start" name="dispo-hour">

                </p>
                <p>
                    <label for="description"><strong><i class="icon-pencil"></i> Description de l'annonce :</strong></label>
                    <textarea id="description" name="description" cols="30" rows="10"></textarea>
                </p>
                <p>
                    <input type="submit" class="btn" value="Créer l'annonce">
                </p>
            </form>

        </div>

    </div>

</section>