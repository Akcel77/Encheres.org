<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="fragment/head.jsp">
    <jsp:param name="title" value="Connexion - Encheres.org"/>
</jsp:include>
<body>
<%@include file="fragment/navbar.jsp"%>
    <img src="${pageContext.request.contextPath}/images/login.png" class="groot">

    <h1 class="text-center mt-5">Connexion</h1>
    <core:if test="${erreurE != null}" var="test">
        <div class="alert alert-danger message-alert" role="alert">${erreurE}</div>
    </core:if>

    <section class="login-form">
        <form class="form-connection" action="<%=request.getContextPath()%>/Connexion" method="post" >
            <div class="mb-3">
                <label for="id" class="form-label">Identifiant :</label>
                <input type="text" class="form-control" id="id" name="id" placeholder="Identifiant" required="required">
            </div>
            <div class="mb-3">
                <label for="psw" class="form-label">Mot de passe :</label>
                <input type="password" class="form-control" name="psw" placeholder="Mot de passe" required="required" id="psw">
            </div>
            <div class="mb-3 block-rememberMe">
                <button type="submit" class="btn btn-primary">Valider</button>
                <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Se souvenir de moi</label>
                </div>
            </div>

            <div class="mb-3 forgot_pass">
                <a href="#">Mot de passe oublié ?</a>
            </div>

            <a href="<%= request.getContextPath()%>/Encheres">
                <button type="button" class="btn btn-danger">Annuler</button>
            </a>
        </form>

    </section>

<%@include file="fragment/footer.jsp"%>
</body>
</html>
