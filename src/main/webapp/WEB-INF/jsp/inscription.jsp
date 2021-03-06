<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
    <html>
        <jsp:include page="fragment/head.jsp">
            <jsp:param name="title" value="ENI Encheres - Inscription "/>
        </jsp:include>
    <body>
        <%@include file="fragment/navbar.jsp"%>

        <img src="${pageContext.request.contextPath}/images/signup.png" class="groot">

        <h1 class="text-center mt-5">Inscription</h1>
        <core:if test="${erreurPseudo != null}" var="test">
            <div class="alert alert-danger message-alert" role="alert">${erreurPseudo}</div>
        </core:if>
        <core:if test="${erreurMail != null}" var="test">
            <div class="alert alert-danger message-alert" role="alert">${erreurMail}</div>
        </core:if>
        <core:if test="${erreurUtilisateur != null}" var="test">
            <div class="alert alert-danger message-alert" role="alert">${erreurUtilisateur}</div>
        </core:if>

        <div>
            <h1 class="signup-subtitle">* Tous les champs sont obligatoires.</h1>
        </div>

        <section class="section-inscription-form mx-auto">
            <form class="inscription-form" action="<%=request.getContextPath()%>/Inscription" method="post" >

                <div class="row-form">
                    <label class="form-label"  for="pseudo"> Pseudo* :</label>
                    <span class="span-conf span-pseudo" id='pseudoValid' ></span>
                    <input class="form-control" onchange="validatePseudo()" name="pseudo" id="pseudo" minlength="5" maxlength="25" required placeholder="Votre pseudo">
                    <label class="form-label" for="nom">Nom* :</label>
                    <input class="form-control" name="nom" id="nom" pattern="[^0-9]{3,30}" required placeholder="Votre nom" >
                </div>

                <div class="row-form">
                    <label class="form-label" for="prenom"> Prénom* : </label>
                    <input class="form-control" name="prenom" id="prenom" pattern="[^0-9]{3,30}" required placeholder="Votre prénom">
                    <label class="form-label" for="email">Email* :</label>
                    <input class="form-control" type="email" name="email" id="email" required placeholder="Votre email" >
                </div>

                <div class="row-form">
                    <label class="form-label" for="telephone"> Téléphone* : </label>
                    <input class="form-control" name="telephone" id="telephone" required placeholder="Votre téléphone">
                    <label class="form-label" for="rue">Rue* : </label>
                    <input class="form-control" name="rue" id="rue" required placeholder="Votre rue" >
                </div>

                <div class="row-form">
                    <label class="form-label" for="ville"> Ville* :</label>
                    <input class="form-control" name="ville" id="ville" required placeholder="Votre ville">
                    <label class="form-label" for="codePostal">Code Postal* : </label>
                    <input class="form-control" name="codePostal" id="codePostal" required placeholder="Votre code postal" >
                </div>

                <div class="row-form">
                    <label class="form-label" for="psw"> Mot de passe* : </label>
                    <input class="form-control" type="password" name="psw" id="psw"  maxlength="30" onchange='check_pass();' required placeholder="Votre mot de passe">
                </div>

                <div class="row-form">
                    <label class="form-label" for="pswConf">Confirmation mot de passe* : </label>
                    <span class="span-conf" id='message' ></span>
                    <input class="form-control" type="password" name="pswConf" id="pswConf"  maxlength="30" onchange='check_pass();' required placeholder="Votre mot de passe" >
                </div>

                <div class="row-form-btn">
                    <button type="submit" id="submit" disabled class="btn btn-primary">Valider</button>

                </div>
                <div class="row-form-btn">
                    <a href="<%= request.getContextPath()%>/Encheres">
                        <button type="button" class="btn btn-danger">Annuler</button>
                    </a>
                </div>
            </form>
        </section>

    <%@include file="fragment/footer.jsp"%>
    </body>
    </html>
