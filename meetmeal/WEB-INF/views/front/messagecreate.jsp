<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<section id="container">
<div id="alerts">
<c:choose>

	<c:when test="${sessionScope.success == true}">
		<div class="alert succes" style="display: block;"><a class="close" href="#">x</a>Message envoy� avec succ�s.</div>
	</c:when>
	<c:when test="${sessionScope.success == false}">
		<div class="alert erreur" style="display: block;"><a class="close" href="#">x</a>Erreur lors de l'envoi du message</div>
	</c:when>
	<c:otherwise>
		
	</c:otherwise>

</c:choose>
</div>
    <div id="large_bg_img" class="clearfix">

        <div id="large_bg_img_overlay"></div>
        <img src="/meetformeal/res/styles/default/img/bg2.jpg" id="image" class="big" alt="" />

        <h1>Envoyer un message</h1>

    </div>

    <div class="wrapper">

        <div class="col-2 centered">

            <form id="announcement-form" action="messagecreate" method="post">
            	<input type="hidden" name="receiver" value="<%= request.getParameter("id_user") %>"/>
				<input type="hidden" name="sender" value="${sessionScope.sessionUser.id}"/>
				<input type="hidden" name="ask" value="<%= request.getParameter("ask") %>"/>
                <p>
                    <label for="message"><strong><i class="icon-pencil"></i> Votre message :</strong></label>
                    <textarea id="description" name="message" cols="30" rows="10"></textarea>
                </p>
                <p>
                    <input type="submit" class="btn" value="Envoyer un message">
                </p>
            </form>

        </div>

    </div>

</section>