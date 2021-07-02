<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <jsp:param name="current" value="home"/>
    </jsp:include>
<main>
    <section>
        <h2>Welkom!</h2>
        <p>Op deze website kunt u elke TV-show die u ooit bekeken hebt toevoegen aan een grote lijst. Elke show krijgt een score op 10 en een indicatie of u deze hebt uitgekeken of niet. </p>
        <p>Op de  <a href="Servlet?command=voegToeForm">voeg toe</a> pagina kunt u een show toevoegen en deze een score geven. Op de <a href="Servlet?command=overzicht">overzicht</a> pagina krijgt u een mooi overzicht te zien van alle shows met hun score.</p>
        <c:if test="${hoogsteScore.naam != null}"> <p>De show met de hoogste score is <strong> ${hoogsteScore.naam} </strong> </p> </c:if>
    </section>
    <img src="images/TV-pica.png" alt="tv" id="tv-picture">
</main>
    <footer>
        <p>Jaan Lavaerts</p>
        <p>Webontwikkeling 2</p>
    </footer>
</body>
</html>