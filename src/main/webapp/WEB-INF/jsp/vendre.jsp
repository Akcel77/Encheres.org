<%@ page import="org.enchere.bo.Categorie" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Modji
  Date: 06/06/2021
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="../../WEB-INF/jsp/fragment/head.jsp">
    <jsp:param name="title" value="Encheres.org"/>
</jsp:include>
<body>
<%@include file="../../WEB-INF/jsp/fragment/navbar.jsp"%>
<main class="container">
    <h1>Nouvelle vente</h1>
    <form action="NouvelleVente" method="post">
        <div class="form-group row">
            <label for="nom" class="col-sm-2 col-form-label">Nom de l'articles</label>
            <input class="col-sm-10" type="text" name="nom" id="nom" class="form-control" required>
        </div>

        <div class="form-group row">
            <label for="description" class="col-sm-2 col-form-label">Description</label>
            <input class="col-sm-10" type="text" name="description" id="description" class="form-control" required>
        </div>

        <div class="form-group row">
            <label for="categorie" class="col-sm-2 col-form-label">Catégorie</label>
            <select class="col-sm-10" name="categorie" id="categorie" class="form-control" required>
                <% List<Categorie> categorieList = (List<Categorie>) request.getAttribute("categorieList");
                    for (Categorie ct: categorieList) { %>
                <option value="<%= ct.getNoCategorie() %>"><%= ct.getLibelle() %></option>
                <% } %>
            </select>
        </div>

        <!-- TODO INPUT PHOTO : itération 2
        <div class="form-group row">
            <label for="photo" class="col-sm-2 col-form-label">Photos de l'article</label>
            <input class="col-sm-10" type="file" name="photo" id="photo" class="form-control">
        </div>
        -->

        <div class="form-group row">
            <label for="prix" class="col-sm-2 col-form-label">Prix</label>
            <input class="col-sm-10" type="number" name="prix" id="prix" class="form-control" required>
        </div>

        <div class="form-group row">
            <label for="date_debut" class="col-sm-2 col-form-label">Date debut</label>
            <input class="col-sm-10" type="date" name="date_debut" id="date_debut" class="form-control" required>
        </div>

        <div class="form-group row">
            <label for="date_fin" class="col-sm-2 col-form-label">Date Fin</label>
            <input class="col-sm-10" type="date" name="date_fin" id="date_fin" class="form-control" required>
        </div>

        <fieldset>
            <legend>Retrait</legend>
            <% Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur"); %>
            <div class="form-group row">
                <label for="rue" class="col-sm-2 col-form-label">Rue</label>
                <input class="col-sm-10" type="text" name="rue" id="rue" class="form-control" value="<%= utilisateur.getRue() %>" required>
            </div>
            <div class="form-group row">
                <label for="codePostal" class="col-sm-2 col-form-label">Code Postal</label>
                <input class="col-sm-10" type="text" name="codePostal" id="codePostal" class="form-control" value="<%= utilisateur.getCodePostal() %>" required>
            </div>
            <div class="form-group row">
                <label for="ville" class="col-sm-2 col-form-label">Ville</label>
                <input class="col-sm-10" type="text" name="ville" id="ville" class="form-control" value="<%= utilisateur.getVille() %>" required>
            </div>
        </fieldset>

        <button type="submit" class="btn btn-primary">Enregistrer</button>
        <input type="button" onclick="window.location.href = '<%= request.getContextPath() %>/ServletHome';" class="btn btn-danger" value="Annuler">
    </form>
</main>
</body>

<%@include file="../../WEB-INF/jsp/fragment/footer.jsp"%>
</html>
