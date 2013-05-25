<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<section id="container">

    <div id="large_bg_img" class="clearfix">

        <div id="large_bg_img_overlay"></div>
        <img src="/meetformeal/res/styles/default/img/bg2.jpg" id="image" class="big" alt="" />

        <h1>Votre messagerie</h1>

    </div>

    <div class="wrapper">

        <div class="col-2">
			<c:choose>
				<c:when test="${!empty sessionScope.messages}">
					<h2>Conversation</h2>
					<c:forEach items="${sessionScope.messages}" var="message">  
					   <div class="single-msg">
			                <img src="/meetformeal/res/styles/default/img/users/default.png" width="40" height="40" class="avatar" alt="">
			                <p class="author-msg"><a href="userprofile?id=${message.sender.id}" title=""/>${message.sender.lastname} ${message.sender.firstname}</a></p>
			                <p class="message-date">Le <fmt:formatDate type="date" value="${message.createdDate}" /> à <fmt:formatDate type="time" timeStyle="short" value="${message.createdDate}" /></p>
			                <div class="content-msg">
								<p>${message.content}</p>
			                </div>
			            </div>
					</c:forEach>  
					<c:set var="unique" value="0"></c:set>
					<c:forEach items="${sessionScope.messages}" var="message">
						<c:if test="${sessionScope.sessionUser.id != message.sender.id && unique == 0}">
               				<a href="messagecreate?id_user=${message.sender.id}" class="btn" ><i class="icon-pencil"></i>Ecrire un message à ${message.sender.firstname }</a>
               				<c:set var="unique" value="1"></c:set>
                    	</c:if>
						
					</c:forEach>
			    </c:when>
				<c:otherwise>
					Vous pouvez consulter ici toutes vos messages.
				</c:otherwise>
			</c:choose>
            
           

            
        </div>
        <div id="conversations" class="col-1">
            <h2>Mes conversations</h2>
            
            <c:choose>
				<c:when test="${!empty sessionScope.conversations}">
				
					<ul>
					<c:forEach items="${sessionScope.conversations}" var="conversation"> 

					  <c:choose>
						<c:when test="${conversation.sender.id == sessionScope.conv}">
					  	<li class="conversation current">
					  	</c:when>
					  	<c:otherwise>
					  	<li class="conversation">
					  	</c:otherwise>
					  </c:choose>
						<img src="/meetformeal/res/styles/default/img/users/default.png" width="64" height="64" class="avatar" alt="">
                    	<p class="name-conversation"><a href="#" title="">
                    		<c:set var="unique" value="0"></c:set>
                    		<c:forEach items="${sessionScope.users}" var="user"> 
                    			<c:if test="${conversation.sender.id == user.id && unique == 0}">		
                    				<a href="message?id_conv=${conversation.sender.id}" class="no-style-block"/>${user.lastname} ${user.firstname}</a>
                    				<c:set var="unique" value="1"></c:set>

                    			</c:if>
                    		</c:forEach>  
                    	</a></p>
                    	<p class="message-date">Le <fmt:formatDate type="date" value="${conversation.createdDate}" /> à <fmt:formatDate type="time" timeStyle="short" value="${conversation.createdDate}" /></p>
                    	<p class="extract-conversation">${fn:substring(conversation.content, 0, 30)}...</p></a>
					  </li>
					</c:forEach>  
					</ul>

			    </c:when>
				<c:otherwise>
					Aucune conversation en cours
				</c:otherwise>
			</c:choose>
            
            
            
            
        </div>

        <div class="col-2">

        </div>

        <div class="col-1">

        </div>

    </div>

</section>