<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="domain.model.Show" %>
<%@ page import="domain.db.ShowDB" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Collection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial scale=1.0">
    <title>TV-Shows | Overzicht</title>
    <link rel="icon" href="images/logo-tv.ico">
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=PT+Sans&display=swap" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="current" value="overzicht"/>
</jsp:include>
<main>
    <h2>Overzicht</h2>
    <div class="overzicht">
        <c:choose>
        <c:when test="${shows != null}">
    <table>
        <thead>
        <tr>
                <th>Naam</th>
                <th>Score</th>
                <th>Uitgekeken</th>
                <th>Pas aan</th>
                <th>Verwijder</th>
        </thead>
        <tbody>
        <c:forEach var="show" items="${shows}">
            <tr>
                <td>
                    ${show.naam}
                </td>
                <td>
                    ${show.score}
                </td>
                <td>
                    ${show.uitgekeken}
                </td>
                <td>
                    <a href="Servlet?command=update&naam=${show.naam}">Pas aan</a>
                </td>
                <td>
                    <a href="Servlet?command=verwijderConfirm&naam=${show.naam}">Verwijder</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
        </c:when>
            <c:otherwise>
                <p>U heeft nog geen shows toegevoegd...</p>
                <p><a href="voeg-toe.jsp"> Voeg nu een show toe!</a></p>
            </c:otherwise>
        </c:choose>
    </div>
    <section>
        <c:if test="${hoogsteScore.naam != null}"> <h2>Algemeen</h2> </c:if>
        <c:if test="${hoogsteScore.naam != null}"> <p> <img class="sad_happy" src="images/HAPPY.png" alt="happy face"> <span id="goed">Beste show:</span>  <strong>${hoogsteScore.naam} </strong></p> </c:if>
        <c:if test="${laagsteScore.naam != null}"> <p> <img class="sad_happy" src="images/SAD.png" alt="sad face"> <span id="slecht">Slechtste show:</span>  <strong>${laagsteScore.naam} </strong></p> </c:if>

    </section>
</main>
    <footer>
        <p>Jaan Lavaerts</p>
        <p>Webontwikkeling 2</p>
    </footer>
</body>
</html>