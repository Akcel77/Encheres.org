<%@ page import="org.enchere.bo.Categorie" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: LoanB
  Date: 10/06/2021
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Test 1 2 3</title>
</head>
<body>
<h1>Liste des enchères</h1>
    <form method="post" action="<%=request.getContextPath()%>/ServletTestLoane">
        <div>
            <input type="search" id="recherche-article" name="recherche"
                   placeholder="Rechercher sur ENI Encheres"
                   aria-label="Rechercher l'article">
        </div>
        <div>
            <select name="categories" id="categories">
                <option selected value="all">Toutes les catégories</option>
                <c:forEach var="c" items="${categories}">
                <option value="">${c.getLibelle()}</option>
                </c:forEach>
            </select>
        </div>
        <input type="submit" value="OK">
    </form>

<article>


</article>

</body>
</html>