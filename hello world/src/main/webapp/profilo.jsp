<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table>

        <tr>
            <th>Costruttore</th>
            <th>Modello</th>
            <th>Targa</th>
            <th>Data inizio</th>
            <th>Data fine</th>
        </tr>

        <c:forEach var="listaPrenotazioni" items="${prenotazione}">

            <tr>
                <td>${prenotazione.auto.costruttore}</td>
                <td>${prenotazione.auto.modello}</td>
                <td>${prenotazione.auto.targa}</td>
                <td>${prenotazione.dataInizio}</td>
                <td>${prenotazione.dataFine}</td>
            </tr>

        </c:forEach>

    </table>
</body>
</html>
