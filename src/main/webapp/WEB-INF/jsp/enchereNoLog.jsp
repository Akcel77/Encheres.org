<%--
  Created by IntelliJ IDEA.
  User: Modji
  Date: 06/06/2021
  Time: 18:54
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
    <h1>Enchere.org</h1>
    <h2>Menu</h2>
    <ul>
        <li><a href="<%= request.getContextPath()%>/Connexion">Connexion</a></li>
        <li><a href="<%= request.getContextPath()%>/Inscription">Inscription</a></li>
        <li><a href="<%= request.getContextPath()%>/TestJb">TestJb : articles</a></li>
        <li><a href="<%= request.getContextPath()%>/ServletTestFlo">TestFlo : encheres</a></li>
    </ul>
    </body>
    <%@include file="fragment/footer.jsp"%>
</html>
