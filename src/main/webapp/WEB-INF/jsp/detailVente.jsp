<%--
  Created by IntelliJ IDEA.
  User: Modji
  Date: 06/06/2021
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.enchere.bo.Articles" %>
<%@ page import="org.enchere.bo.Enchere" %>
<%@ page import="org.enchere.bll.UtilisateurManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="fragment/head.jsp">
    <jsp:param name="title" value="Detail vente"/>
</jsp:include>
<body>
<%@include file="fragment/navbar.jsp"%>
    <main class="container">
        <h1 class="text-center mt-5">Detail d'un article</h1>
        <h2>Detail de l'article</h2>
        <% Articles article = (Articles) request.getAttribute("article"); %>
        <table class="table table-info">
            <thead>
            <tr>
                <th scope="col">Nom</th>
                <th scope="col">Categorie</th>
                <th scope="col">Mise à prix</th>
                <th scope="col">Date début</th>
                <th scope="col">Date fin</th>
                <th scope="col">Description</th>
                <th scope="col">Vendeur</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><%= article.getNomArticles() %></td>
                <td><%= article.getCaterogie().getLibelle() %></td>
                <td><%= article.getMiseAprix() %></td>
                <td><%= article.getDateDebutEncheres() %></td>
                <td><%= article.getDateFinEncheres() %></td>
                <td><%= article.getDescription() %></td>
                <td><%= article.getUtilisateur().getPseudo() %></td>
            </tr>
            </tbody>
        </table>

        <h2>Meilleur enchere </h2>
        <% Enchere lastEnchere = (Enchere) article.getLastEncheres();
            if(lastEnchere == null){ %>
            <p>Aucune enchere en cours !</p>
        <% }else{ %>
        <table class="table table-info">
            <thead>
            <tr>
                <th scope="col">Utilisateur</th>
                <th scope="col">Prix</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><%= UtilisateurManager.selectUserByID(lastEnchere.getNo_utilisateur()).getPseudo()  %></td>
                <td><%= lastEnchere.getMontant_enchere() %> pts</td>
            </tr>
            </tbody>
        </table>
        <% } %>

        <h2>Retrait</h2>
        <table class="table table-info">
            <thead>
            <tr>
                <th scope="col">Rue</th>
                <th scope="col">Code Postal</th>
                <th scope="col">Ville</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><%= article.getRetrait().getRue() %></td>
                <td><%= article.getRetrait().getCode_postal() %></td>
                <td><%= article.getRetrait().getVille() %></td>
            </tr>
            </tbody>
        </table>

        <h2>Faire une proposition</h2>
        <form action="DetailVente" method="post"  class="row g-3">
            <div class="col-12">
                <label for="nombreEnchere">Ma proposition </label>
                <input type="hidden" name="id_article" value="<%= article.getId() %>">
                <input class="form-control" type="number" min="<%= lastEnchere!=null?lastEnchere.getMontant_enchere()+1:article.getMiseAprix()+1 %>" value="<%= lastEnchere!=null?lastEnchere.getMontant_enchere():article.getMiseAprix() %>" name="nombreEnchere" id="nombreEnchere">
            </div>
            <button type="submit" class="btn btn-primary">Encherir !</button>
        </form>
    </main>
<%@include file="fragment/footer.jsp"%>
</body>
</html>
