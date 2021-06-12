<%@ page import="org.enchere.bo.Categorie" %>
<%@ page import="java.util.List" %>
<%@ page import="org.enchere.servlet.test.ServletTestLoane" %>
<%@ page import="org.enchere.bo.Utilisateur" %>
<%@ page import="org.enchere.bo.Articles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <!--<link href="< %=request.getContextPath() %>/css/style.css" rel="stylesheet">-->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>ENI Enchères</title>
</head>

<body>
    <h1>Liste des enchères</h1>
    <form action="<%=request.getContextPath()%>/ServletHome" method="post">
        <div class="saisie">
            <input type="search" id="recherche-article" name="recherche"
                   placeholder="Rechercher sur ENI Encheres"
                   aria-label="Rechercher l'article">
        </div>
        <div class="filtre">
            <select name="categories" id="categories">
                <option selected value="all">Toutes les catégories</option>
                <c:forEach var="c" items="${categories}">
                    <option value="">${c.getLibelle()}</option>
                </c:forEach>
            </select>
        </div>
        <div>
            <div>
                <input type="radio" name="choix" id="achats" checked value="achat">
                <label for="achats">Achats</label>
                <input type="checkbox" id="ouverte" name="enchereOuverte" checked>
                <label for="ouverte">enchères ouvertes</label>
                <input type="checkbox" id="encours" name="mesEncheres">
                <label for="encours">mes enchères</label>
                <input type="checkbox" id="remportees" name="enchereRemportee">
                <label for="remportees">mes enchères remportées</label>
            </div>
            <div>
                <input type="radio" name="choix" id="ventes" value="vente">
                <label for="ventes">Mes ventes</label>
                <input type="checkbox" id="venteEncours" name="venteEncours">
                <label for="venteEncours">mes ventes en cours</label>
                <input type="checkbox" id="nonDebute" name="nonDebute">
                <label for="nonDebute">ventes non débutées</label>
                <input type="checkbox" id="terminees" name="terminees">
                <label for="terminees">ventes terminées</label>
            </div>
        </div>

        <input type="submit" value="OK">
    </form>
    <section class="enchere-section">

        <c:forEach var="encheres" items="${articles}">
            <div class="card" style="width: 18rem;">
                <img class="card-img-top" src="${pageContext.request.contextPath}/images/groot.png" alt="Card image cap">
                <div class="card-body">
                    <p class="card-title" >${articles.getNomArticles}</p>
                    <p class="card-text">${articles.getDescription}</p>
                    <p class="card-text">${articles.getMiseAprix}</p>
                    <p class="card-text">${utilisateur.getPseudo}</p>
                    <a href="#" class="btn btn-primary">Go somewhere</a>
                </div>
            </div>

        </c:forEach>

    </section>






</body>
</html>