<%@ page import="org.enchere.bo.Categorie" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="../../WEB-INF/jsp/fragment/head.jsp">
    <jsp:param name="title" value="ENI Encheres - Nouvelle vente"/>
</jsp:include>
<body>
<%@include file="../../WEB-INF/jsp/fragment/navbar.jsp"%>
<core:if test="${erreurVente != null}" var="test">
    <div class="alert alert-danger message-alert" role="alert">${erreurVente}</div>
</core:if>
<core:if test="${successVente != null}" var="test">
    <div class="alert alert-success message-alert" role="alert">${successVente}</div>
</core:if>
<main class="container">
    <div class="main_vente">
        <img class="groot" src="${pageContext.request.contextPath}/images/vente.png" alt="groot">
        <div class="main_vente__form">
            <h1 class="text-center mt-5">Créer une nouvelle vente</h1>
            <p class="main_vente__subTitle">Vendre n'a jamais été aussi simple !</p>

            <form action="NouvelleVente" method="post" class="row g-3">
                <div class="col-12">
                    <label for="nom">Nom de l'article</label>
                    <input type="text" name="nom" id="nom" class="form-control" required placeholder="Entrer un nom d'article">
                </div>

                <div class="col-12">
                    <label for="description">Description</label>
                    <input type="text" name="description" id="description" class="form-control" required placeholder="Entrer une description">
                </div>

                <div class="col-6">
                    <label for="prix">Mise à prix</label>
                    <input  type="number" name="prix" id="prix" class="form-control" required placeholder="1">
                </div>

                <div class="col-6">
                    <label for="categorie" >Catégorie</label>
                    <select  name="categorie" id="categorie" class="form-control" required>
                        <% List<Categorie> categorieList = (List<Categorie>) request.getAttribute("categorieList");
                            for (Categorie ct: categorieList) { %>
                        <option value="<%= ct.getNoCategorie() %>"><%= ct.getLibelle() %></option>
                        <% } %>
                    </select>
                </div>

                <div class="col-6">
                    <label for="date_debut">Date début</label>
                    <input type="date" name="date_debut" id="date_debut" class="form-control" min="<%= request.getAttribute("aujourdhui") %>" value="<%= request.getAttribute("aujourdhui") %>" required>
                </div>

                <div class="col-6">
                    <label for="heure_debut">Heure début</label>
                    <input type="time" name="heure_debut" id="heure_debut" class="form-control" min="<%= request.getAttribute("maintenant") %>" value="<%= request.getAttribute("maintenant") %>" required>
                </div>

                <div class="col-6">
                    <label for="date_fin">Date Fin</label>
                    <input type="date" name="date_fin" id="date_fin" class="form-control" min="<%= request.getAttribute("aujourdhui") %>" value="<%= request.getAttribute("aujourdhui") %>" required>
                </div>

                <div class="col-6">
                    <label for="heure_fin">Heure Fin</label>
                    <input type="time" name="heure_fin" id="heure_fin" class="form-control" required>
                </div>

                <% Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur"); %>

                <div class="col-6">
                    <label for="rue">Rue</label>
                    <input type="text" name="rue" id="rue" class="form-control" value="<%= utilisateur.getRue() %>" required>
                </div>
                <div class="col-6 ">
                    <label for="codePostal">Code Postal</label>
                    <input type="text" name="codePostal" id="codePostal" class="form-control" value="<%= utilisateur.getCodePostal() %>" required>
                </div>
                <div class="col-6 ">
                    <label for="ville">Ville</label>
                    <input type="text" name="ville" id="ville" class="form-control" value="<%= utilisateur.getVille() %>" required>
                </div>


                <div class="nouvelle-vente-btn">
                    <button type="submit" class="btn btn-primary">Enregistrer</button>
                    <input type="button" onclick="window.location.href = '<%= request.getContextPath() %>/Encheres';" class="btn btn-danger" value="Annuler">
                </div>
            </form>
        </div>
    </div>

</main>
</body>

<%@include file="../../WEB-INF/jsp/fragment/footer.jsp"%>
</html>
