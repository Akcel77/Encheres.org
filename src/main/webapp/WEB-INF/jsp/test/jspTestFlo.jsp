<%@ page import="org.enchere.bo.Enchere" %>
<%@ page import="java.util.List" %><%--
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body class="container">
<h1>Test classe enchère</h1>

<h2> Liste des enchères</h2>

<table class="table table-primary">
    <table class="table table-primary">
        <thead>
        <tr>
            <th scope="col">Numéro </th>
            <th scope="col">Date et heure</th>
            <th scope="col">Montant</th>
            <th scope="col">Numéro d'article</th>
            <th scope="col">Numéro d'utilisateur</th>
        </tr>
        </thead>

        <% List<Enchere> liste = (List<Enchere>) request.getAttribute("listeEnchere");
            for (Enchere enchere : liste){%>
        <tbody>
        <tr>
            <td><%= enchere.getNo_enchere() %></td>
            <td><%= enchere.getDate_enchere() %></td>
            <td><%= enchere.getMontant_enchere() %></td>
            <td><%= enchere.getNo_article() %></td>
            <td><%= enchere.getNo_utilisateur() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>

    </tbody>
</table>
<h2> Insertion nouvelle enchère</h2>
<form method="post" action="<%=request.getContextPath()%>/ServletTestFlo">
    <div class="form">
        <label for="date">Date</label>
        <input type="datetime-local" name="date" id="date" class="form-control">
    </div>
    <div class="form">
        <label for="montant">Montant</label>
        <input type="number" name="montant" id="montant" class="form-control">
    </div>
    <div class="form">
        <label for="article">Numéro d'article</label>
        <input type="number" name="no_article" id="article" class="form-control">
    </div>
    <div class="form">
        <label for="utilisateur">Numéro d'utilisateur</label>
        <input type="number" name="no_utilisateur" id="utilisateur" class="form-control">
    </div>
    <div class="form">
        <input type="submit" name="validerAjout" value="valider">
    </div>
</form>


<h2> Sélectionner une enchère</h2>
<form method="post" action="<%=request.getContextPath()%>/ServletTestFlo">
    <div class="form">
        <label>Numéro de l'enchère</label>
        <input type="number" name="selectionParNo" class="form-control">
    </div>
    <div class="form">
        <input type="submit" name="ValiderSelection" value="Valider">
    </div>
</form>
<% Enchere enchere = (Enchere) request.getAttribute("enchereParNo");
    if(enchere != null){%>
<table class="table table-primary">
    <thead>
    <tr>
        <th scope="col">Numéro </th>
        <th scope="col">Date</th>
        <th scope="col">Montant</th>
        <th scope="col">Numéro d'article</th>
        <th scope="col">Numéro d'utilisateur</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><%= enchere.getNo_enchere() %></td>
        <td><%= enchere.getDate_enchere() %></td>
        <td><%= enchere.getMontant_enchere() %></td>
        <td><%= enchere.getNo_article() %></td>
        <td><%= enchere.getNo_utilisateur() %></td>
    </tr>
    </tbody>
</table>
<% } %>


<h2> Supprimer une enchère</h2>
<form method="post" action="<%=request.getContextPath()%>/ServletTestFlo">
    <div class="form">
        <label>Numéro de l'enchère</label>
        <input type="number" name="suppressionParNo" class="form-control">
    </div>
    <div class="form">
        <input type="submit" name="validerSuppression" value="Supprimer">
    </div>
</form>


</body>
</html>
