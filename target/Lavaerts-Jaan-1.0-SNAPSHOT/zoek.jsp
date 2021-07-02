<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial scale=1.0">
    <title>TV-Shows | Zoek</title>
    <link rel="icon" href="images/logo-tv.ico">
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=PT+Sans&display=swap" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="current" value="zoek"/>
</jsp:include>
<main>
    <section>
        <h2>Zoek een TV-show</h2>
        <p>Zoek een specifieke show door dit formulier in te vullen.</p>
    </section>
    <section>
        <c:if test="${cookie.naam.value != null}">
        <p> Laatste zoekopdracht: <strong>${cookie.naam.value.replace('+', ' ')}</strong> </p>
        </c:if>
        <form method="POST" action="Servlet?command=zoekNaam" novalidate>
            <label for="naam">
                Naam*
                <input id="naam" name="naam" placeholder="Naam van de show..." required>
            </label>
            <label>
            <input id="submit-button" type="submit" value="Zoek">
            </label>
        </form>
    </section>
</main>
<footer>
    <p>Jaan Lavaerts</p>
    <p>Webontwikkeling 2</p>
</footer>
</body>
</html>