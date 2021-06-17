<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<jsp:include page="fragment/head.jsp">
    <jsp:param name="title" value="Encheres.org"/>
</jsp:include>
<head>
    <title>Liste des enchères</title>
</head>
<body>

<%@include file="fragment/navbar.jsp"%>
<img src="${pageContext.request.contextPath}/images/shop.png" class="groot">
<h1 class="text-center my-5">Liste des enchères</h1>

<form action="<%=request.getContextPath()%>/Encheres" method="post">
    <div class="saisie search-bar">
        <input class="form-control me-2 input-search" type="search" id="recherche-article" name="recherche"
               placeholder="Rechercher sur ENI Encheres"
               aria-label="Rechercher l'article">
        <div class="filtre">
            <select  class="form-select form-select-lg mb-3" name="categories" id="categories">
                <option name="lCategorie" selected value="-1">Toutes les catégories</option>
                <c:forEach var="categorie" items="${categories}">
                    <option name="sCategorie" value="${categorie.noCategorie}">${categorie.libelle}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="checkbox-block">
        <div class="mb-3">
            <div>
                <input type="radio" name="choix" id="achats" checked value="achat">
                <label class="mr-2" for="achats">Achats</label>
            </div>
            <div>
                <input type="checkbox" id="ouverte" name="enchereOuverte" checked>
                <label class="mr-2" for="ouverte">enchères ouvertes</label>
            </div>
            <div>
                <input type="checkbox" id="encours" name="mesEncheres">
                <label class="mr-2" for="encours">mes enchères</label>
            </div>
            <div>
                <input type="checkbox" id="remportees" name="enchereRemportee">
                <label class="mr-2" for="remportees">mes enchères remportées</label>
            </div>
        </div>
        <div>
            <div>
                <input type="radio" name="choix" id="ventes" value="vente">
                <label class="mr-2" for="ventes">Mes ventes</label>
            </div>
            <div>
                <input type="checkbox" id="venteEncours" name="venteEncours">
                <label class="mr-2" for="venteEncours">mes ventes en cours</label>
            </div>
            <div>
                <input type="checkbox" id="nonDebute" name="nonDebute">
                <label class="mr-2" for="nonDebute">ventes non débutées</label>
            </div>
            <div>
                <input type="checkbox" id="terminees" name="terminees">
                <label class="mr-2" for="terminees">ventes terminées</label>
            </div>
        </div>
    </div>
    <div class="checkbox-block"  >
        <button  class="btn btn-outline-success valider-checkbox" type="submit">Valider</button>
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
            <img class="card-img-top image-article" src="${pageContext.request.contextPath}/images/groot.png" alt="Card image cap">
            <div class="card-body">
                <p class="card-text">Description : ${articles.getDescription()}</p>
                <p class="card-text">Prix Initial: ${articles.getMiseAprix()}</p>
                <p class="card-text">Fin de l'enchere: le ${articles.convertToFRDAte(articles.getDateFinEncheres())} à ${articles.getHeureFin()}</p>
                <p class="card-text">Enchere actuelle: ${articles.getLastEncheres().getMontant_enchere()}</p>
                <p class="card-text vendeur">Vendeur : <a  href="<%=request.getContextPath()%>/Profil?id=${articles.getUtilisateur().getNoUtilisateur()}" > ${articles.getUtilisateur().getPseudo()}</a></p>
            </div>
            <div class="card-footer">
                <a href="<%=request.getContextPath()%>/DetailVente?id=${articles.getId()}" class="btn btn-primary">Détail article</a>
            </div>
        </div>
    </c:forEach>
</section>

<%@include file="fragment/footer.jsp"%>
</body>
</html>