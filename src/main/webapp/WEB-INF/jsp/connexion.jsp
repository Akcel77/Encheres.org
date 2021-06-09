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
    <jsp:param name="title" value="Encheres.org"/>
</jsp:include>
<body>
<%@include file="fragment/navbar.jsp"%>
    <h1>Page de connexion</h1>
    <section>
        <form action="<%=request.getContextPath()%>/Connexion" method="post" >
            <div class="mb-3">
                <label for="id" class="form-label">Identifiant</label>
                <input type="text" class="form-control" id="id" name="id" placeholder="Identifiant" required="required">
            </div>
            <div class="mb-3">
                <label for="psw" class="form-label">Password</label>
                <input type="password" class="form-control" name="psw" placeholder="Mot de passe" required="required" id="psw">
            </div>
            <div class="mb-3 form-check">
                <input type="checkbox" class="form-check-input" id="exampleCheck1">
                <label class="form-check-label" for="exampleCheck1">Check me out</label>
            </div>
            <div class="mb-3">
                <a href="#">Mot de passe oublie</a>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>

    </section>
</body>
</html>
