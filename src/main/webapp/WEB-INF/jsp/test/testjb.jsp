<%@ page import="org.enchere.bo.Articles" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Modji
  Date: 08/06/2021
  Time: 03:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
    <h1>Liste de tout les articles</h1>

    <table class="table table-primary">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Nom</th>
            <th scope="col">Description</th>
            <th scope="col">Date debut</th>
            <th scope="col">Date fin</th>
            <th scope="col">Prix initial</th>
            <th scope="col">Prix actuel</th>
            <th scope="col">Vendeur</th>
            <th scope="col">Categorie</th>
        </tr>
        </thead>

        <tbody>
<!-- -->
    <% List<Articles> articles = (List<Articles>) request.getAttribute("articles");
        for (Articles article : articles) { %>
        <tr>
            <td><%= article.getId() %></td>
            <td><%= article.getNomArticles() %></td>
            <td><%= article.getDescription() %></td>
            <td><%= article.getDateDebutEncheres() %></td>
            <td><%= article.getDateFinEncheres() %></td>
            <td><%= article.getMiseAprix() %></td>
            <td>TODO</td>
            <td>TODO</td>
            <td>TODO</td>
        </tr>
        <% } %>
        </tbody>
    </table>
<!-- -->
</body>
</html>
