<%--
  Created by IntelliJ IDEA.
  User: LoanB
  Date: 10/06/2021
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test 1 2 3</title>
</head>
<body>
<h1>Liste des enchères</h1>
<form method="post" action="<%=request.getContextPath()%>/ServletTestLoane">
    <div>
        <input type="search" id="recherche-article" name="recherche"
               placeholder="Rechercher sur ENI Encheres"
               aria-label="Rechercher l'article">
    </div>
    <div>
        <select name="categorie" id="select-categorie">
            <option value="all">Toutes les catégories</option>
            <option value="informatique">Informatique</option>
            <option value="ameublement">Ameublement</option>
            <option value="vetement">Vêtement</option>
            <option value="sportloisirs">Sport & Loisirs</option>
        </select>
    </div>
    <input type="submit" value="OK">
</form>
</body>
</html>
