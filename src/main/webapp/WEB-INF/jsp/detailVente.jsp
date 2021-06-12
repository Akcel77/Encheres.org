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
        <h1>Detail Vente</h1>
        <% Articles article = (Articles) request.getAttribute("article"); %>
        <p>Nom : <%= article.getNomArticles() %></p>
        <p>Description : <%= article.getDescription() %></p>
        <p>Categorie : <%= article.getCaterogie().getLibelle() %></p>
        <p>Mise à prix : <%= article.getMiseAprix() %></p>
        <p>Fin de l'enchere : <%= article.getDateFinEncheres() %></p>
        <h2>Meilleur Enchere</h2>
        <% Enchere lastEnchere = (Enchere) article.getLastEncheres();
            if(lastEnchere == null){ %>
            <p>Aucune enchere en cours !</p>
        <% }else{ %>
           <p><%= lastEnchere.getMontant_enchere() %> pts par <%= UtilisateurManager.selectUserByID(lastEnchere.getNo_utilisateur()).getPrenom()  %></p>
        <% } %>

        <h2>Retrait</h2>
        <p><%= article.getRetrait().getRue() %></p>
        <p><%= article.getRetrait().getCode_postal() %> <%= article.getRetrait().getVille() %></p>
        
        <h2>Vendeur</h2>
        <p><%= article.getUtilisateur().getPseudo() %> (<%= article.getUtilisateur().getNom() %> <%= article.getUtilisateur().getPrenom() %> ) </p>
        
        <h2>Faire une proposition</h2>
        <form action="DetailVente" method="post">
            <label for="nombreEnchere">Ma proposition </label>
            <input type="hidden" name="id_article" value="<%= article.getId() %>">
            <input type="number" min="<%= lastEnchere!=null?lastEnchere.getMontant_enchere()+1:article.getMiseAprix()+1 %>" value="<%= lastEnchere!=null?lastEnchere.getMontant_enchere():article.getMiseAprix() %>" name="nombreEnchere" id="nombreEnchere">
            <input type="submit" value="Encherir">
        </form>
    </main>
<%@include file="fragment/footer.jsp"%>
</body>
</html>
