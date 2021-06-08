<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Enchere.org</title>
</head>
<body>
    <h1>Enchere.org</h1>
    <h2>Menu</h2>
    <ul>
        <li><a href="<%= request.getContextPath()%>/Connexion">Connexion</a></li>
        <li><a href="<%= request.getContextPath()%>/Inscription">Inscription</a></li>
        <li><a href="<%= request.getContextPath()%>/TestJb">TestJb : articles</a></li>
    </ul>
</body>
</html>