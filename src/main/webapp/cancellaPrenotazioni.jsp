<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<body>
    <jsp:include page="header.jsp"/>
    <table>

        <tr>
            <th>Auto</th>
            <th>Data inizio</th>
            <th>Data fine</th>
            <th>Azioni</th>
        </tr>

        <c:forEach var="prenotazione" items="${listaPrenotazioni}">

            <tr>
                <td>${prenotazione.auto.targa}</td>
                <td>${prenotazione.dataInizio}</td>
                <td>${prenotazione.dataFine}</td>
                <td>
                    <form action="/PrenotazioneServlet" method="get">
                        <input type="hidden" name="prenotazione" value= ${prenotazione.id}>
                        <input type="submit" name="azione" value="Cancella">
                    </form>
                </td>
            </tr>

        </c:forEach>

    </table>
</body>
</html>
