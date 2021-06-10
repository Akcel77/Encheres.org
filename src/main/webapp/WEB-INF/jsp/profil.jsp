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
        <jsp:param name="title" value="Profil - "/>
    </jsp:include>
    <body>
        <%@include file="fragment/navbar.jsp"%>

        <h1>Profil</h1>

        <%if (session.getAttribute("isConnected") != null){
            Utilisateur isConnected = (Utilisateur) session.getAttribute("isConnected");
            Utilisateur utilisateurEnCours = (Utilisateur) session.getAttribute("utilisateurEnCours");
        }%>

        <section class="" ></section>


        <%@include file="fragment/footer.jsp"%>
        </body>
    </html>
