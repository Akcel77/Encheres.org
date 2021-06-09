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
<body class="container">
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

        <tbody>

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

    <h2>Insert</h2>
    <form action="TestJb" method="post">
        <div class="form-group">
        <label for="insert_name">Nom</label>
        <input type="text" name="insert_name" id="insert_name" class="form-control">
        </div>

            <div class="form-group">
        <label for="insert_description">Description</label>
        <input type="text" name="insert_description" id="insert_description" class="form-control">
            </div>
                <div class="form-group">
        <label for="insert_date_debut">Date debut</label>
        <input type="date" name="insert_date_debut" id="insert_date_debut" class="form-control">
                </div>
                    <div class="form-group">
        <label for="insert_date_fin">Date Fin</label>
        <input type="date" name="insert_date_fin" id="insert_date_fin" class="form-control">
                    </div>
                        <div class="form-group">
        <label for="insert_prix">Prix</label>
        <input type="text" name="insert_prix" id="insert_prix" class="form-control">
                        </div>

        <input type="submit" value="Inserer" id="insert_submit" name="insert_submit">
    </form>

    <h2>Select par id</h2>
    <form action="TestJb" method="post">
        <label for="select_id">ID</label>
        <input type="number" name="select_id" id="select_id">
        <input type="submit" value="Chercher" id="select_id_submit" name="select_id_submit">
    </form>

    <% Articles article = (Articles) request.getAttribute("select_article");
        if(article != null){%>
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
        </tbody>
    </table>
    <% } %>

    <h2>Delete par id</h2>
    <form action="TestJb" method="post">
        <label for="delete_id">ID</label>
        <input type="number" name="delete_id" id="delete_id">
        <input type="submit" value="Suprimmer" id="delete_id_submit" name="delete_id_submit">
    </form>
</body>
</html>
