
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

    <c: var = "nome" value = ${utente}>
    <h1>Utente: ${utente.nome} ${utente.cognome}</h1>
    </c:>
    <br/><br/>
    <a href="aggiungiAuto">Aggiungi auto</a>
    <br/>
    <a href="aggiungiCustomer">Aggiungi customer</a>
    <br/>
    <a href="approvaPrenotazioni">Approva prenotazioni</a>
    <br/>
    <a href="cancellaPrenotazioni">Cancella prenotazioni</a>
<body>

</body>
</html>
