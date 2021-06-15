<%--
  Created by IntelliJ IDEA.
  User: Modji
  Date: 06/06/2021
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
    <html>
    <jsp:include page="fragment/head.jsp">
        <jsp:param name="title" value="Insciption - Encheres.org"/>
    </jsp:include>
    <body>
    <%@include file="fragment/navbar.jsp"%>
    <h1 class="text-center mt-5">Inscription</h1>
        <section class="section-inscription-form mx-auto">
            <form class="inscription-form" action="<%=request.getContextPath()%>/Inscription" method="post" >
                <div class="row-form">
                    <label class="form-label"  for="pseudo"> Pseudo* :</label>
                    <span class="span-conf span-pseudo" id='pseudoValid' ></span>
                    <input class="form-control" onchange="validatePseudo()" name="pseudo" id="pseudo" required placeholder="Votre pseudo">
                    <label class="form-label" for="nom">Nom* :</label>
                    <input class="form-control" name="nom" id="nom" required placeholder="Votre nom" >
                </div>
                <div class="row-form">
                    <label class="form-label" for="prenom"> Prenom* : </label>
                    <input class="form-control" name="prenom" id="prenom" required placeholder="Votre prenom">
                    <label class="form-label" for="email">Email* :</label>
                    <input class="form-control" type="email" name="email" id="email" required placeholder="Votre email" >
                </div>
                <div class="row-form">
                    <label class="form-label" for="telephone"> Telephone* : </label>
                    <input class="form-control" name="telephone" id="telephone" required placeholder="Votre telephone">
                    <label class="form-label" for="rue">Rue* : </label>
                    <input class="form-control" name="rue" id="rue" required placeholder="Votre rue" >
                </div>
                <div class="row-form">
                    <label class="form-label" for="ville"> Ville* :</label>
                    <input class="form-control" name="ville" id="ville" required placeholder="Votre ville">
                    <label class="form-label" for="codePostal">Code Postal* : </label>
                    <input class="form-control" name="codePostal" id="codePostal" required placeholder="Votre code Postal" >
                </div>
                <div class="row-form">
                    <label class="form-label" for="psw"> Mot de passe* : </label>
                    <input class="form-control" type="password" name="psw" id="psw" onchange='check_pass();' required placeholder="Votre Mot de passe">

                </div>
                <div class="row-form">

                    <label class="form-label" for="pswConf">Confirmation* : </label>
                    <span class="span-conf" id='message' ></span>
                    <input class="form-control" type="password" name="pswConf" id="pswConf" onchange='check_pass();' required placeholder="Votre Confirmation mot de passe" >

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
