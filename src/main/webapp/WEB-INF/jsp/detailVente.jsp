<%@ page import="org.enchere.bo.Articles" %>
<%@ page import="org.enchere.bo.Enchere" %>
<%@ page import="org.enchere.bll.UtilisateurManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="fragment/head.jsp">
    <jsp:param name="title" value="ENI Encheres - Detail de l'article"/>
</jsp:include>
<body>
<%@include file="fragment/navbar.jsp"%>
<main class="container">


    <core:if test="${erreurEncheres != null}" var="test">
        <div class="alert alert-danger message-alert" role="alert">${erreurEncheres}</div>
    </core:if>
    <core:if test="${successEnchere != null}" var="test">
        <div class="alert alert-success message-alert" role="alert">${successEnchere}</div>
    </core:if>

    <div class="main-detail-vente row">

        <% if(!(boolean)request.getAttribute("enCours")&&(boolean)request.getAttribute("venteRemportee")){ %>
        <div class="detail-vente-cadre">
            <h1 class="vente_title">Bravo ! Vous avez remporté l'article.</h1>
        </div>

         <% } else if (!(boolean)request.getAttribute("enCours")&&(boolean)request.getAttribute("maVente")){ %>
        <div class="detail-vente-cadre">
            <h1 class="vente_title">L'enchère est terminée.</h1>
            <h2 class="vente_subtitle">Vendez de nouveaux articles !</h2>
        </div>

        <% } else if (!(boolean)request.getAttribute("enCours")&&!(boolean)request.getAttribute("venteRemportee")){ %>
        <div class="detail-vente-cadre">
            <h1 class="vente_title">L'enchère est terminée.</h1>
        </div>

        <% } else if (request.getAttribute("enchereRemportee")!=null){ %>
            <div class="detail-vente-cadre">
                <h1 class="vente_title">L'enchère est terminée.</h1>
                <h2 class="vente_subtitle">L'enchère a été remportée par un autre enchérisseur. Recherchez des articles similaires pour trouver votre bonheur !</h2>
            </div>
        <% } %>

        <h1 class="m-5 text-center" >Détail de l'article</h1>

        <% Articles article = (Articles) request.getAttribute("article"); %>
        <div class="main-image-article col-md-4 col-12 align-self-center mb-5">
            <img class="groot" src="${pageContext.request.contextPath}/images/groot.png" alt="groot" >
        </div>

        <div class="main-detail-article col-md-7 col-12 row g-3">
            <div class=" col-4"> <strong>Article</strong> : </div>
            <div class=" col-8"><%= article.getNomArticles() %></div>

            <div class=" col-4"><strong>Description</strong> :</div>
            <div class=" col-8"><%= article.getDescription() %></div>

            <div class="col-4"><strong>Catégorie</strong> :</div>
            <div class=" col-8"><%= article.getCaterogie().getLibelle() %></div>

            <div class=" col-4"><strong>Prix actuel</strong> :</div>
            <div class=" col-8"><% Enchere lastEnchere = (Enchere) article.getLastEncheres();
                if(lastEnchere == null){ %>
                Aucune enchère en cours !
                <% }else{ %>
                <%= lastEnchere.getMontant_enchere() %> pts par
                <%= UtilisateurManager.selectUserByID(lastEnchere.getNo_utilisateur()).getPseudo() %><% } %></div>

            <div class="col-4"><strong>Mise à prix</strong> :</div>
            <div class=" col-8"><%= article.getMiseAprix() %></div>

            <div class=" col-4"><strong>Date fin </strong>:</div>
            <div class="col-8"><%= article.getDateFinEncheres() %></div>

            <div class=" col-4"><strong>Retrait </strong>:</div>
            <div class=" col-8"><%= article.getRetrait().getRue() %>,
                <%= article.getRetrait().getCode_postal() %>
                <%= article.getRetrait().getVille() %></div>

            <div class=" col-4"><strong>Vendeur</strong> :</div>
            <div class=" col-8 mb-4"><%= article.getUtilisateur().getPseudo() %></div>

            <% if(!(boolean)request.getAttribute("maVente") && (boolean)request.getAttribute("enCours")){ %>
            <form action="DetailVente" method="post"  class="row g-3 proposition-form ml-2">
                <div id="label-prop" class=" col-12 my-auto ">Faire une proposition</div>
                <div class="col-12 my-auto ">
                    <input type="hidden" name="id_article" value="<%= article.getId() %>">
                    <input class="form-control" type="number" min="<%= lastEnchere!=null?lastEnchere.getMontant_enchere()+1:article.getMiseAprix()+1 %>" value="<%= lastEnchere!=null?lastEnchere.getMontant_enchere():article.getMiseAprix() %>" name="nombreEnchere" id="nombreEnchere">
                </div>
                <button type="submit" class="btn btn-primary  btn-detail mt-5">Encherir !</button>

            <!-- ajout bouton "modifier" avec le elseif-->
                    <% } if ((boolean)request.getAttribute("maVente") && !(boolean)request.getAttribute("encherePasCommencee")){ %>
                <input type="button" onclick="window.location.href = '<%= request.getContextPath() %>/Encheres';" class="btn btn-danger btn-detail  mt-5" value="Retour">
                <input type="button" onclick="window.location.href = '<%= request.getContextPath() %>/ModificationVente?id=<%= article.getId() %>';" class="btn btn-primary btn-detail  mt-5" value="Modifier">
                    <% }else{ %>
                <input type="button" onclick="window.location.href = '<%= request.getContextPath() %>/Encheres';" class="btn btn-danger btn-detail mt-5" value="Retour">
                    <% } %>
        </div>
    </div>
</main>
<%@include file="fragment/footer.jsp"%>
</body>
</html>
