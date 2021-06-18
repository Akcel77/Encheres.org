<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<jsp:include page="fragment/head.jsp">
    <jsp:param name="title" value="ENI Encheres"/>
</jsp:include>
<head>
    <title>Liste des enchères</title>
</head>
<body>

<%@include file="fragment/navbar.jsp"%>
<img src="${pageContext.request.contextPath}/images/shop.png" class="groot">

<form action="<%=request.getContextPath()%>/Encheres" method="post">
    <div class="saisie search-bar">
        <input class="form-control me-2 input-search" type="search" id="recherche-article" name="recherche"
               placeholder="Rechercher sur ENI Encheres"
               aria-label="Rechercher l'article">
        <div class="filtre">
            <select  class="form-select form-select-lg mb-3 ml-1" name="categories" id="categories">
                <option name="lCategorie" selected value="-1">Toutes les catégories</option>
                <c:forEach var="categorie" items="${categories}">
                    <option name="sCategorie" value="${categorie.noCategorie}">${categorie.libelle}</option>
                </c:forEach>
            </select>
        </div>
        <button  class="btn btn-outline-success valider-checkbox" type="submit">Valider</button>
    </div>

    <div class="checkbox-block">
        <div id="achat-block" class="mb-3">
            <div>
                <input type="radio" onchange='witchCheckbox();' name="choix" id="achats" checked value="achat">
                <label class="mr-2" for="achats">Achats</label>
            </div>
            <div>
                <input type="checkbox" id="ouverte" name="enchereOuverte" >
                <label class="mr-2" for="ouverte">Enchères ouvertes</label>
            </div>
            <div>
                <input type="checkbox" id="encours" name="mesEncheres">
                <label class="mr-2" for="encours">Mes enchères</label>
            </div>
            <div>
                <input type="checkbox" id="remportees" name="enchereRemportee">
                <label class="mr-2" for="remportees">Mes enchères remportées</label>
            </div>
        </div>
        <div>
            <div id="vente-block" >
                <input type="radio" onchange='witchCheckbox();' name="choix" id="ventes" value="vente">
                <label class="mr-2" for="ventes">Ventes</label>
            </div>
            <div>
                <input type="checkbox" id="venteEncours" name="venteEncours">
                <label class="mr-2" for="venteEncours">Mes ventes en cours</label>
            </div>
            <div>
                <input type="checkbox" id="nonDebute" name="nonDebute">
                <label class="mr-2" for="nonDebute">Ventes non débutées</label>
            </div>
            <div>
                <input type="checkbox" id="terminees" name="terminees">
                <label class="mr-2" for="terminees">Ventes terminées</label>
            </div>
        </div>

    </div>

</form>

<div class="enchere_subtitle">
    <% if(request.getAttribute("articlesNull")!=null) { %>
    <h1>Aucun article ne correspond à votre recherche.</h1>
    <% } %>
</div>

<core:if test="${aucuneEnchere != null}" var="test">
    <h1 class="text-center my-5">${aucuneEnchere}</h1>
</core:if>

<section class="enchere-section">
    <c:forEach var="articles" items="${articles}">
        <div class="card article-box" >
            <h4 class="card-title text-center title-card" > ${articles.getNomArticles()}</h4>
            <img class="card-img-top image-article" src="${pageContext.request.contextPath}/images/${articles.getCaterogie().getLibelle()}.jpg" alt="Card image cap">
            <div class="card-body">
                <p class="card-text"><strong>Description :</strong> ${articles.getDescription()}</p>

                <c:choose>
                    <c:when test="${articles.getLastEncheres() == null}">
                        <p class="card-text"><strong>Enchère actuelle :</strong> Aucune enchère en cours </p>
                    </c:when>
                    <c:otherwise>
                        <p class="card-text"><strong>Enchère actuelle :</strong> ${articles.getLastEncheres().getMontant_enchere()} pts</p>
                    </c:otherwise>
                </c:choose>

                <p class="card-text"><strong>Prix initial :</strong> ${articles.getMiseAprix()} pts</p>
                <p class="card-text"><strong>Fin de l'enchère :</strong> le ${articles.convertToFRDAte(articles.getDateFinEncheres())} à ${articles.getHeureFin()}</p>
                <p class="card-text vendeur"><strong>Vendeur :</strong> <a class="vendeur-a" href="<%=request.getContextPath()%>/Profil?id=${articles.getUtilisateur().getNoUtilisateur()}" > ${articles.getUtilisateur().getPseudo()}</a></p>
            </div>
            <div class="card-footer">
                <a href="<%=request.getContextPath()%>/DetailVente?id=${articles.getId()}" class="btn btn-outline-success">Détail article</a>
            </div>
        </div>
    </c:forEach>
</section>

<%@include file="fragment/footer.jsp"%>
</body>
</html>