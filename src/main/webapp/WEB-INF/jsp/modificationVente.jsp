<%@ page import="org.enchere.bo.Articles" %>
<%@ page import="org.enchere.bo.Categorie" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="../../WEB-INF/jsp/fragment/head.jsp">
    <jsp:param name="title" value="ENI Encheres - Modification Vente"/>
</jsp:include>
<body>
<%@include file="../../WEB-INF/jsp/fragment/navbar.jsp"%>

<% Articles article = (Articles) request.getAttribute("article"); %>

<main class="container">
    <div class="main_vente">
        <div class="main_vente__pix">
            <img class="groot" src="${pageContext.request.contextPath}/images/groot.png" alt="groot" >
        </div>
        <div class="main_vente__form">
            <h1 class="text-center mt-5">Modifier ma vente</h1>

            <form action="<%=request.getContextPath()%>/ModificationVente" method="post" class="row g-3">
                <input type="hidden" name="id_article" value="<%= article.getId() %>">
                <div class="col-12">
                    <label for="nom">Nom de l'articles</label>
                    <input type="hidden" name="id_article" value="<%= article.getId() %>">
                    <input type="text" name="nom" id="nom" class="form-control" required value=<%= article.getNomArticles() %>>
                </div>

                <div class="col-12">
                    <label for="description">Description</label>
                    <input type="text" name="description" id="description" class="form-control" required value=<%= article.getDescription() %>>
                </div>

                <div class="col-6">
                    <label for="prix">Prix</label>
                    <input  type="number" name="prix" id="prix" class="form-control" required value=<%= article.getMiseAprix() %>>
                </div>

                <div class="col-6">
                    <label for="categorie" >Catégorie</label>

                    <select  name="categorie" id="categorie" class="form-control" required>
                        <% List<Categorie> categorieList = (List<Categorie>) request.getAttribute("categorieList");
                            for (Categorie ct: categorieList) {
                                if(ct.getNoCategorie() == article.getCaterogie().getNoCategorie()){ %>
                        <option value="<%= ct.getNoCategorie() %>" selected><%= ct.getLibelle() %></option>
                        <%}else{%>
                        <option value="<%= ct.getNoCategorie() %>"><%= ct.getLibelle() %></option>
                        <%}%>

                        <% } %>
                    </select>
                </div>

                <div class="col-6">
                    <label for="date_debut">Date debut</label>
                    <input type="date" name="date_debut" id="date_debut" class="form-control" min="<%= request.getAttribute("aujourdhui") %>" value="<%= article.getDateDebutEncheres() %>" required>
                </div>

                <div class="col-6">
                    <label for="heure_debut">Heure debut</label>
                    <input type="time" name="heure_debut" id="heure_debut" class="form-control" min="<%= request.getAttribute("maintenant") %>" value="<%= article.getHeureDebut() %>" required>
                </div>

                <div class="col-6">
                    <label for="date_fin">Date Fin</label>
                    <input type="date" name="date_fin" id="date_fin" class="form-control" min="<%= request.getAttribute("aujourdhui") %>" value="<%= article.getDateFinEncheres() %>" required>
                </div>

                <div class="col-6">
                    <label for="heure_fin">Heure Fin</label>
                    <input type="time" name="heure_fin" id="heure_fin" class="form-control" min="<%= request.getAttribute("maintenant") %>" value="<%= article.getHeureFin() %>" required>
                </div>

                <button type="submit" class="btn btn-primary">Enregistrer</button>
                <input type="button" onclick="window.location.href = '<%= request.getContextPath() %>/DetailVente?id=<%=article.getId()%>';" class="btn btn-danger" value="Annuler">
            </form>
        </div>
    </div>

</main>
</body>

<%@include file="../../WEB-INF/jsp/fragment/footer.jsp"%>
</html>