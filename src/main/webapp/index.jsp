<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<jsp:include page="WEB-INF/jsp/fragment/head.jsp">
    <jsp:param name="title" value="Encheres.org"/>
</jsp:include>
    <body>
        <%@include file="WEB-INF/jsp/fragment/navbar.jsp"%>
        <h1>Enchere.org</h1>
        <h2>Menu</h2>
        <ul>
            <li><a href="<%= request.getContextPath()%>/TestJb">TestJb : articles</a></li>
            <li><a href="<%= request.getContextPath()%>/ServletTestFlo">TestFlo : encheres</a></li>
        </ul>
    </body>

    <%@include file="WEB-INF/jsp/fragment/footer.jsp"%>
</html>