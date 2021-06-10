<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="fragment/head.jsp">
    <jsp:param name="title" value="Encheres.org"/>
</jsp:include>
<head>
    <title>Liste des enchères</title>
</head>
<body>
<%@include file="fragment/navbar.jsp"%>
<h1>Enchere.org</h1>
<h2>Menu</h2>
<ul>
    <li><a href="<%= request.getContextPath()%>/TestJb">TestJb : articles</a></li>
    <li><a href="<%= request.getContextPath()%>/ServletTestFlo">TestFlo : encheres</a></li>
</ul>

<!--<header>
    <nav role="navigation">
        <ul>
            <li><a href="<%= request.getContextPath()%>/Inscription">S'inscrire</a></li>
            <li><a href="<%= request.getContextPath()%>/Connexion">Se connecter</a></li>
        </ul>
    </nav>
</header>-->

<h1>Liste des enchères</h1>
<form>
    <div>
        <input type="search" id="recherche-article" name="recherche"
               placeholder="Le nom de l'article contient"
               aria-label="Filtrer avec le nom de l'article">
    </div>
    <div>
        <label for="select-categorie">Catégorie:</label>
        <select name="categorie" id="select-categorie">
            <option value="all">Toutes</option>
            <option value="informatique">Informatique</option>
            <option value="ameublement">Ameublement</option>
            <option value="vetement">Vêtement</option>
            <option value="sportloisirs">Sport & Loisirs</option>
        </select>
    </div>
    <input type="submit" value="Go">
</form>



</body>
<%@include file="fragment/footer.jsp"%>
</html>

