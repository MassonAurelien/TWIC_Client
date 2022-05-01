<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Renan
  Date: 01/05/2022
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Affiche ville</title>
    <style>
        table
        {
            border-collapse: collapse;
        }
        td, th /* Mettre une bordure sur les td ET les th */
        {
            border: 1px solid black;
        }
    </style>
</head>
<body>
    <div>
        <table>
            <caption>List des villes</caption>

            <tr>
                <th>Code INSEE</th>
                <th>Commune</th>
                <th>Code Postal</th>
                <th>Libelle Acheminement</th>
                <th>Ligne 5</th>
                <th>Longitude</th>
                <th>Latitude</th>

            </tr>
            <c:forEach items="${villesAffichees}" var="ville">
                <tr>
                    <td>${ville.getCodeINSEE()}</td>
                    <td>${ville.getCommune()}</td>
                    <td>${ville.getCodePostal()}</td>
                    <td>${ville.getLibelleAcheminement()}</td>
                    <td>${ville.getLigne5()}</td>
                    <td>${ville.getLongitude()}</td>
                    <td>${ville.getLatitude()}</td>
                </tr>
            </c:forEach>
        </table>

        <form method = "post" action="ListeVille">
            <c:forEach var="i" begin="1" end="68">
                <input type="submit" type="hidden" name="numPage" id="numPage" value="${i}">
            </c:forEach>
        </form>

    </div>
</body>
</html>
