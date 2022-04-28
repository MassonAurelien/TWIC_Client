<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Villes Distance</h1>
<p>
<form action="distance" method="post">
    <select name="ville1">
        <c:forEach items="${listVilles}" var="ville1">
            <option value="${ville1.codePostal}">${ville1.commune}</option>
        </c:forEach>
    </select>
    <select name="ville2">
        <c:forEach items="${sessionScope.listVilles}" var="ville2">
            <option value="${ville2.codePostal}">${ville2.commune}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Submit" />
</form>
</p>
<p>
    <c:if test = "${sessionScope.afficher == true}">
    <a>La distance est de <c:out value="${distance} km"/></a>
    </c:if>
</p>
</body>
</html>
