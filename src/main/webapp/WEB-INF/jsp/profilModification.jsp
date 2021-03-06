<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.enchere.bo.Utilisateur" %>
<%@ page import="org.enchere.bll.UtilisateurManager" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<jsp:include page="fragment/head.jsp">
    <jsp:param name="title" value="Profil - ${isConnected.pseudo}"/>
</jsp:include>
<body>
<%@include file="fragment/navbar.jsp"%>
<img src="${pageContext.request.contextPath}/images/social.png" class="groot">
<h1 class="text-center my-5"> Modification profil ${isConnected.pseudo}</h1>


    <% Utilisateur isConnected = (Utilisateur) session.getAttribute("isConnected");%>
<core:if test="${erreurMail != null}" var="test">
    <div class="alert alert-danger message-alert" role="alert">${erreurMail}</div>
</core:if>
<section class="section-inscription-form mx-auto">
    <form class="inscription-form" action="<%=request.getContextPath()%>/ProfilModification?<%isConnected.getPseudo();%>" method="post" >
         <div class="row-form">
             <label class="form-label" for="nom"> Nom : </label>
             <input class="form-control" name="nom" id="nom" value="${isConnected.nom}" required placeholder="Votre nom">
             <label class="form-label" for="prenom"> Prénom : </label>
             <input class="form-control" name="prenom" id="prenom" value="${isConnected.prenom}" required placeholder="Votre prénom">
             <label class="form-label" for="email">Email :</label>
             <input class="form-control" type="email" name="email" id="email" value="${isConnected.email}" required placeholder="Votre email" >
        </div>
        <div class="row-form">
            <label class="form-label" for="telephone"> Téléphone : </label>
            <input class="form-control" name="telephone" id="telephone" value="${isConnected.telephone}" required placeholder="Votre téléphone">
            <label class="form-label" for="rue">Rue : </label>
            <input class="form-control" name="rue" id="rue" value="${isConnected.rue}" required placeholder="Votre rue" >
            <label class="form-label" for="ville"> Ville :</label>
            <input class="form-control" name="ville" id="ville" value="${isConnected.ville}" required placeholder="Votre ville">
        </div>
        <div class="row-form">

            <label class="form-label" for="codePostal">Code Postal</label>
            <input class="form-control" name="codePostal" id="codePostal" value="${isConnected.codePostal}" required placeholder="Votre code postal" >
            <label class="form-label" for="password"> Mot de Passe : </label>
            <input class="form-control" type="password" name="password" id="password" required placeholder="Votre mot de passe">
        </div>
        <div class="row-form">

            <label class="form-label" for="newPassword">Nouveau Mot de Passe : </label>
            <input class="form-control" type="password" name="newPassword" id="newPassword"  onchange="check_pass_profil()"  placeholder="Votre nouveau mot de passe">
            <label class="form-label" for="newPassConf">Confirmation Nouveau Mot de Passe: </label>
            <span class="span-conf" id='message' ></span>
            <input class="form-control" type="password" name="newPassConf" id="newPassConf"  onchange="check_pass_profil()"  placeholder="Votre nouveau mot de passe" >
        </div>

        <div class="row-form-btn">
            <button type="submit" id="submit"  class="btn btn-primary">Valider</button>
            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal">
                Supprimer compte
            </button>
        </div>
        <input type="hidden" name="utilisateurs" value="${isConnected.noUtilisateur}">
    </form>
</section>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Confirmation de suppression</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                Êtes-vous sûr de vouloir supprimer votre compte?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Annuler</button>
                <form class="btn-supprimer" action="SupprimerCompte" method="post">
                    <a href="<%= request.getContextPath()%>/SupprimerCompte">
                        <button type="submit" class="btn btn-danger">Confirmer</button>
                    </a>
                </form>
            </div>
        </div>
    </div>
</div>

<%@include file="fragment/footer.jsp"%>
</body>
</html>
