<%--
  Created by IntelliJ IDEA.
  User: Modji
  Date: 06/06/2021
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.enchere.bo.Utilisateur" %>
<%@ page import="org.enchere.bll.UtilisateurManager" %>
<html>
<jsp:include page="fragment/head.jsp">
    <jsp:param name="title" value="Profil - ${utilisateurEnCours.pseudo}"/>
</jsp:include>
<body>
<%@include file="fragment/navbar.jsp"%>

<h1> Modification Profil ${utilisateurEnCours.pseudo}</h1>


    <% Utilisateur isConnected = (Utilisateur) session.getAttribute("isConnected");%>

<section class="section-inscription-form">
    <form class="inscription-form" action="<%=request.getContextPath()%>/ProfilModification?<%isConnected.getPseudo();%>" method="post" >
         <div class="row-form">
             <label class="form-label" for="nom"> Prenom : </label>
             <input class="form-control" name="nom" id="nom" value="${isConnected.nom}" required placeholder="Votre prenom">
             <label class="form-label" for="prenom"> Prenom : </label>
             <input class="form-control" name="prenom" id="prenom" value="${isConnected.prenom}" required placeholder="Votre prenom">
             <label class="form-label" for="email">Email :</label>
             <input class="form-control" type="email" name="email" id="email" value="${isConnected.email}" required placeholder="Votre email" >
        </div>
        <div class="row-form">
            <label class="form-label" for="telephone"> Telephone : </label>
            <input class="form-control" name="telephone" id="telephone" value="${isConnected.telephone}" required placeholder="Votre telephone">
            <label class="form-label" for="rue">Rue : </label>
            <input class="form-control" name="rue" id="rue" value="${isConnected.rue}" required placeholder="Votre rue" >
        </div>
        <div class="row-form">
            <label class="form-label" for="ville"> Ville :</label>
            <input class="form-control" name="ville" id="ville" value="${isConnected.ville}" required placeholder="Votre ville">
            <label class="form-label" for="codePostal">Code Postal</label>
            <input class="form-control" name="codePostal" id="codePostal" value="${isConnected.codePostal}" required placeholder="Votre code Postal" >
        </div>
        <div class="row-form">
            <label class="form-label" for="password"> Mot de passe : </label>
            <input class="form-control" type="password" name="password" id="password" required placeholder="Votre Mot de passe">
            <label class="form-label" for="newPassword">Nouveau Mot de passe : </label>
            <input class="form-control" type="password" name="newPassword" id="newPassword"  required placeholder="Votre Nouveau Mot de passe">
            <label class="form-label" for="newPassConf">Confirmation Nouveau Mot de passe: </label>
            <input class="form-control" type="password" name="newPassConf" id="newPassConf"  required placeholder="Votre Confirmation de nouveau mot de passe" >
        </div>

        <div class="row-form-btn">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </form>
</section>


</body>
</html>
