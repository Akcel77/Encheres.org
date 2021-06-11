<%--
  Created by IntelliJ IDEA.
  User: Modji
  Date: 06/06/2021
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.enchere.bo.Utilisateur" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
    <html>
    <jsp:include page="fragment/head.jsp">
        <jsp:param name="title" value="Profil - ${utilisateurEnCours.pseudo}"/>
    </jsp:include>
    <body>
        <%@include file="fragment/navbar.jsp"%>

        <h1 class="text-center my-5">Profil ${utilisateurEnCours.pseudo}</h1>


        <%if (session.getAttribute("isConnected") != null){
            Utilisateur isConnected = (Utilisateur) session.getAttribute("isConnected");
            Utilisateur utilisateurEnCours = (Utilisateur) session.getAttribute("utilisateurEnCours");
        %>

        <section class="profil-section" >
            <div class="mr-5 profil-info">
                <p>Pseudo :</p>
                <p>Nom :</p>
                <p>Prenom :</p>
                <p>Email :</p>
                <p>Telephone :</p>
                <p>Rue :</p>
                <p>Code Postal :</p>
                <p>Ville :</p>
            </div>
            <div class="ml-5 profil-info-user" >
                <p>${utilisateurEnCours.pseudo}</p>
                <p>${utilisateurEnCours.nom}</p>
                <p>${utilisateurEnCours.prenom}</p>
                <p>${utilisateurEnCours.email}</p>
                <p>${utilisateurEnCours.telephone}</p>
                <p>${utilisateurEnCours.rue}</p>
                <p>${utilisateurEnCours.codePostal}</p>
                <p>${utilisateurEnCours.ville}</p>

            </div>



        </section>
        <core:if test="${utilisateurEnCours.pseudo == isConnected.pseudo}" >
            <div class="credit-info">
                <div class="mr-5 ">
                    <p>Credit :</p>
                </div>
                <div class="ml-5 credit-user">
                    <p>${utilisateurEnCours.credit}</p>
                </div>
            </div>
            <a class="btn-modif" href="<%= request.getContextPath()%>/ProfilModification">
                <button type="button" class="btn btn-primary">Modifier Compte</button>
            </a>



        </core:if>

        <%}%>

        <%@include file="fragment/footer.jsp"%>
        </body>
    </html>
