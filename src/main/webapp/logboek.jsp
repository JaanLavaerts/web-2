<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.model.Show" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial scale=1.0">
    <title>TV-Shows | Home</title>
    <link rel="icon" href="images/logo-tv.ico">
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=PT+Sans&display=swap" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="current" value="logboek"/>
</jsp:include>
<main>
    <h2>Logboek</h2>
    <section>
        <c:choose>
            <c:when test="${not empty shows}">


                    <table class="logboek">
                        <tr>
                            <th>Show</th>
                        </tr>
                        <c:forEach var="naam" items="${shows}">
                            <tr>
                                <td>${naam}</td>
                            </tr>
                        </c:forEach>
                    </table>


            </c:when>

            <c:otherwise>
                <p>U heeft nog geen shows gezocht.</p>
                <p><a href="zoek.jsp"> Zoek</a> nu een show!</p>
            </c:otherwise>
        </c:choose>
    </section>
</main>
<footer>
    <p>Jaan Lavaerts</p>
    <p>Webontwikkeling 2</p>
</footer>
</body>
</html>