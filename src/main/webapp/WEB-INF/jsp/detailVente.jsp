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
    <div class="main-detail-vente row">
        <h1 class="m-5 text-center" >Detail d'un article</h1>
        <% Articles article = (Articles) request.getAttribute("article"); %>
        <div class="main-image-article col-4 align-self-center mb-5">
            <img class="groot" src="${pageContext.request.contextPath}/images/groot.png" alt="groot" >
        </div>
        <div class="main-detail-article col-7 row g-3">
            <div class=" col-3"> Article : </div>
            <div class=" col-9"><%= article.getNomArticles() %></div>

            <div class=" col-3">Description :</div>
            <div class=" col-9"><%= article.getDescription() %></div>

            <div class="col-3">Catégorie :</div>
            <div class=" col-9"><%= article.getCaterogie().getLibelle() %></div>

            <div class=" col-3">Meilleure offre :</div>
            <div class=" col-9"><% Enchere lastEnchere = (Enchere) article.getLastEncheres();
                if(lastEnchere == null){ %>
                Aucune enchere en cours !
                <% }else{ %>
                <%= lastEnchere.getMontant_enchere() %> pts par
                <%= UtilisateurManager.selectUserByID(lastEnchere.getNo_utilisateur()).getPseudo() %><% } %></div>

            <div class="col-3">Mise à prix :</div>
            <div class=" col-9"><%= article.getMiseAprix() %></div>

            <div class=" col-3">Fin de l'enchère :</div>
            <div class="col-9"><%= article.getDateFinEncheres() %></div>

            <div class=" col-3">Retrait :</div>
            <div class=" col-9"><%= article.getRetrait().getRue() %>
                <%= article.getRetrait().getCode_postal() %>
                <%= article.getRetrait().getVille() %></div>

            <div class=" col-3">Vendeur :</div>
            <div class=" col-9"><%= article.getUtilisateur().getPseudo() %></div>

            <form action="DetailVente" method="post"  class="row g-3 proposition-form">
                <label for="nombreEnchere" id="label-prop">Faire une proposition</label>
                <div class="col-2">
                    <!-- <label for="nombreEnchere">Ma proposition </label>-->
                    <input type="hidden" name="id_article" value="<%= article.getId() %>">
                    <input class="form-control" type="number" min="<%= lastEnchere!=null?lastEnchere.getMontant_enchere()+1:article.getMiseAprix()+1 %>" value="<%= lastEnchere!=null?lastEnchere.getMontant_enchere():article.getMiseAprix() %>" name="nombreEnchere" id="nombreEnchere">
                </div>
                <button type="submit" class="btn btn-primary col-2">Encherir !</button>
            </form>

        </div>

    </div>
</main>
<%@include file="fragment/footer.jsp"%>
</body>
</html>
