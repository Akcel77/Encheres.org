<%--
  Created by IntelliJ IDEA.
  User: flori
  Date: 09/06/2021
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test Enchères</title>
</head>
<body>
<h1>Test classe enchère</h1>

<form method="post" action="<%=request.getContextPath()%>/ServletTestFlo">
    <label for="date">Date : </label><input type="date" name="date" id="date">
    <label for="montant">Montant : </label><input type="number" name="montant" id="montant">
    <label for="article">Numéro d'article : </label><input type="number" name="no_article" id="article">
    <label for="utilisateur">Numéro d'utilisateur : </label><input type="number" name="no_utilisateur" id="utilisateur">
    <input type="submit" name="valider" value="valider l'enchère">
</form>

</body>
</html>
