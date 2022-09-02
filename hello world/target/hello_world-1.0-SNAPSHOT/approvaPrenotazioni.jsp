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

        <c:forEach var="listaPrenotazioni" items="${listaPrenotazioni}">

            <tr>
                <td>${listaPrenotazioni.auto.targa}</td>
                <td>${listaPrenotazioni.dataInizio}</td>
                <td>${listaPrenotazioni.dataFine}</td>
                <td>
                    <form action = "/ApprovaPrenotazioniServlet" method="get">
                        <input type="Approva" name="auto" value= ${listaPrenotazioni.id}>
                    </form>
                </td>
            </tr>

        </c:forEach>

    </table>
</body>
</html>
