<%--
  Created by IntelliJ IDEA.
  User: Modji
  Date: 06/06/2021
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="org.enchere.bo.Utilisateur"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
    <html>
    <jsp:include page="fragment/head.jsp">
        <jsp:param name="title" value="Profil - ${utilisateur.pseudo}"/>
    </jsp:include>
    <body>
        <%@include file="fragment/navbar.jsp"%>

        <h1>${utilisateur.pseudo} profil</h1>

        <div class="container d-flex justify-content-center pt-5 mt-5">
            <div class="pr-5">
                <p>Pseudo : </p>
                <p>Nom : </p>
                <p>Prénom : </p>
                <p>Email : </p>
                <p>Téléphone : </p>
                <p>Rue : </p>
                <p>Code Postal : </p>
                <p>Ville : </p>
            </div>
            <div class="text-center pl-5">
                <p>${utilisateur.pseudo }</p>
                <p>${utilisateur.nom }</p>
                <p>${utilisateur.prenom }</p>
                <p>${utilisateur.email }</p>
                <p>${utilisateur.telephone }</p>
                <p>${utilisateur.rue }</p>
                <p>${utilisateur.codePostal }</p>
                <p>${utilisateur.ville }</p>
            </div>
        </div>
        <div class="text-center mt-5">

        <c:if test="${modifier==oui }">
            <a class="btn btn-outline-secondary" href="#">Modifier</a>
        </c:if>
        <%@include file="fragment/footer.jsp"%>
        </body>
    </html>
