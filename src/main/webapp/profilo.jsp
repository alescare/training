<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<body>
<jsp:include page="header.jsp"/>

<h4>Dati anagrafici utente:</h4>
<h5>Nome: ${sessionScope.utenteLoggato.nome}</h5>
<h5>Cognome: ${sessionScope.utenteLoggato.cognome}</h5>
<h5>Data di nascita: ${sessionScope.utenteLoggato.dataNascita}</h5>

<hr>

<h4>Credenziali utente:</h4>

<form action="UtenteServlet" method="post">

    <h5>Username: <input type="text" value="${sessionScope.utenteLoggato.username}" name="username"></h5>
    <h5>Password: <input type="text" value="${sessionScope.utenteLoggato.password}" name="password"></h5>
    <input type="hidden" name="azione" value="modifica credenziali">
    <input type="submit" value="Applica modifiche">

</form>


<hr>

<h4>Storico noleggi:</h4>

<table border="3" style="text-align: center">

    <tr>
        <th>Costruttore</th>
        <th>Modello</th>
        <th>Targa</th>
        <th>Data inizio</th>
        <th>Data fine</th>
        <th>Approvata</th>
    </tr>

    <c:forEach var="prenotazione" items="${listaPrenotazioni}">

        <tr>
            <td>${prenotazione.auto.costruttore}</td>
            <td>${prenotazione.auto.modello}</td>
            <td>${prenotazione.auto.targa}</td>
            <td>${prenotazione.dataInizio}</td>
            <td>${prenotazione.dataFine}</td>
            <td>
                <c:choose>
                    <c:when test="${prenotazione.approvata}">SI</c:when>
                    <c:otherwise>NO</c:otherwise>
                </c:choose>
            </td>
        </tr>

    </c:forEach>

</table>
</body>
</html>
