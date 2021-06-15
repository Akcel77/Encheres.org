<%@ page import="org.enchere.bo.Articles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<jsp:include page="fragment/head.jsp">
    <jsp:param name="title" value="Encheres.org"/>
</jsp:include>
<head>
    <title>Vente réussie</title>
</head>
<body>
<%@include file="fragment/navbar.jsp"%>
    <h1 class="vente_title">Bravo, vous remportez la vente !</h1>

<div>
    <p>${article.getNomArticles()}</p>
    <p>Description : ${article.getDescription()}</p>
    <p>Catégorie : ${article.getCaterogie().getLibelle()}</p>
    <p>Meilleure offre : ${article.getLastEncheres().getMontant_enchere()} pts</p>
    <p>Mise à prix : ${article.getMiseAprix()} pts </p>
    <p>Retrait : ${article.getRetrait()}</p>
    <p>Vendeur : ${article.getUtilisateur().getPseudo()}</p>
    <p>Téléphone : ${article.getUtilisateur().getTelephone()}</p>
</div>
<div class="card-footer">
        <a href="<%=request.getContextPath()%>/Encheres" class="btn btn-primary">Précédent</a>
</div>

<%@include file="fragment/footer.jsp"%>


</body>
</html>