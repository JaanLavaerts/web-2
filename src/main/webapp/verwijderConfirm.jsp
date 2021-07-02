<%@ page import="domain.model.Show" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial scale=1.0">
    <title>TV-Shows | Confirm</title>
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
    <section>
        <h2>Confirmation</h2>
        <p> <strong> ${param.naam} </strong> wordt verwijderd. Bent u zeker dat u wilt doorgaan? </p>
        <form action="Servlet?command=verwijder&naam=${param.naam}" method="POST">
            <input id="submit-button" type="submit" value="Ja"/>
        </form>
        <p><a href="Servlet?command=overzicht">Annuleer</a></p>
    </section>
</main>
<footer>
    <p>Jaan Lavaerts</p>
    <p>Webontwikkeling 2</p>
</footer>
</body>
</html>