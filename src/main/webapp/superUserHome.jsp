<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<body>
    <jsp:include page="header.jsp"/>
    <form action="/AutoServlet" method="get">
        <input type="submit" name="azione" value="Aggiungi auto">
    </form>
    <br/><br/>
    <form action="/UtenteServlet" method="get">
        <input type="submit" name="azione" value="Aggiungi customer">
    </form>
    <br/><br/>
    <form action="/PrenotazioneServlet" method="get">
        <input type="submit" name="azione" value="Approva prenotazioni">
        <br/><br/>
        <input type="submit" name="azione" value="Cancella Prenotazioni">
    </form>

</body>
</html>
