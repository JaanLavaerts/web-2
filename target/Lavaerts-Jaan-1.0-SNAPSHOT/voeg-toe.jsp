<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial scale=1.0">
    <title>TV-Shows | Voeg toe</title>
    <link rel="icon" href="images/logo-tv.ico">
    <link rel="stylesheet" href="css/normalize.css">
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=PT+Sans&display=swap" rel="stylesheet">
</head>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="current" value="voegtoe"/>
</jsp:include>
<main>
    <section>
        <h2>Voeg een TV-show toe</h2>
        <p>Voeg hier een show toe aan de lijst door het formulier correct in te vullen. (* = verplicht)</p>
    </section>
    <section>
        <c:if test="${not empty errors}">
            <div class="alert">
            <ul>
                <c:forEach items="${errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </c:if>
    <form method="POST" action="Servlet?command=voegtoe" novalidate>
        <p class="form-group ${statusNaam}">
        <label for="naam">
            Naam*
            <input id="naam" name="naam" type="text" placeholder="Naam van de show..." value="${naamVorigeWaarde}">
        </label>
        </p>
        <p class="form-group ${statusScore}">
        <label for="score">
            Score*
            <input type="number" id="score" name="score" min="0" max="10" placeholder="Score tussen 0 en 10..." required value="${scoreVorigeWaarde}">
        </label>
        </p>
        <p class="form-group ${statusUitgekeken}">
        <label for="uitgekeken">
            Uitgekeken?
            <select id="uitgekeken" name="uitgekeken">
                <option value="" selected>--</option>
                <option value="Ja">Ja</option>
                <option value="Nee">Nee</option>
            </select>
        </label>
        </p>
        <input id="submit-button" type="submit" value="Bevestig">
    </form>
    </section>
</main>
    <footer>
        <p>Jaan Lavaerts</p>
        <p>Webontwikkeling 2</p>
    </footer>
</body>
</html>