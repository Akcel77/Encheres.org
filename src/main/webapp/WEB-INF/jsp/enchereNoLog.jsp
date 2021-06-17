<%@ page import="org.enchere.bo.Categorie" %>
<%@ page import="java.util.List" %>
<%@ page import="org.enchere.bo.Utilisateur" %>
<%@ page import="org.enchere.bo.Articles" %>
<%@ page import="org.enchere.bll.UtilisateurManager" %>
<%@ page import="org.enchere.bll.ArticleManager" %>
<%@ page import="org.enchere.bo.Enchere" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                <option selected value="-1">Toutes les catégories</option>
                <c:forEach var="categories" items="${categories}">
                    <option name="sCategorie" value="${categories.noCategorie}">${categories.libelle}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="checkbox-block"  >
        <button  class="btn btn-outline-success valider-checkbox" type="submit">Valider</button>
    </div>
</form>
<h1 class="text-center">Liste des enchères </h1>
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
                <p class="card-text vendeur"><strong>Vendeur :</strong> ${articles.getUtilisateur().getPseudo()}</p>
            </div>
            <div class="card-footer">
                <a href="<%=request.getContextPath()%>/Connexion" class="btn btn-primary">Voir le détail</a>
            </div>
        </div>
    </c:forEach>
</section>

<%@include file="fragment/footer.jsp"%>

</body>
</html>