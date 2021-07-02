<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <h1>TV-Show</h1>
    <nav>
        <ul>
            <li ${param.current eq 'home'?"id = current":""}>
                <a href="Servlet?command=home">Home</a></li>
            <li ${param.current eq 'voegtoe'?"id = current":""}>
                <a href="Servlet?command=voegToeForm">Voeg toe</a></li>
            <li ${param.current eq 'overzicht'?"id = current":""}>
                <a href="Servlet?command=overzicht">Overzicht</a></li>
            <li ${param.current eq 'zoek'?"id = current":""}>
                <a href="Servlet?command=zoek">Zoek</a></li>
            <li ${param.current eq 'logboek'?"id = current":""}>
                <a href="Servlet?command=logboek">Logboek</a></li>
        </ul>
    </nav>
</header>