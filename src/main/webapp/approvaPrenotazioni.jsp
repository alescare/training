<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<body>
<jsp:include page="header.jsp"/>
<table border="3" style="text-align: center">

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
                <form action="PrenotazioneServlet" method="post">
                    <input type="hidden" name="idPrenotazione" value=${prenotazione.id}>
                    <input type="hidden" name="azione" value="approva prenotazione">
                    <input type="submit"  value="Approva">
                </form>
            </td>
        </tr>

    </c:forEach>

</table>
</body>
</html>
