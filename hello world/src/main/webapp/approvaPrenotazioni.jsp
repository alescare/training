<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>Title</title>
</head>
<body>
    <table>

        <tr>
            <th>Auto</th>
            <th>Data inizio</th>
            <th>Data fine</th>
            <th>Azioni</th>
        </tr>

        <c:forEach var="listaPrenotazioni" items="${prenotazione}">

            <tr>
                <td>${prenotazione.auto.targa}</td>
                <td>${prenotazione.dataInizio}</td>
                <td>${prenotazione.dataFine}</td>
                <td>
                    <form action = "/ApprovaPrenotazioniServlet" method="get">
                        <input name="prenotazione" value= ${prenotazione.id}>
                        <input type= "submit" name="auto" value= "Approva">
                    </form>
                </td>
            </tr>

        </c:forEach>

    </table>
</body>
</html>
