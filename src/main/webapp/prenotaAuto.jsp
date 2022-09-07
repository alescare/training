<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<body>
    <jsp:include page="header.jsp"/>

    <form action = "/AutoServlet" method="get">

        Data inizio: <input type="date" name="dataInizio"><br/>
        Data fine: <input type="date" name="dataFine"><br/>
        <input type="submit" name="azione" value="Cerca">

    </form>

    <hr>

    <c:if test="${request.getParameter(dataInizio) != null && request.getParameter(dataFine) != null}">
        <table>

            <tr>
                <th>Costruttore</th>
                <th>Modello</th>
                <th>Tipologia</th>
                <th>Anno immatricolazione</th>
                <th>Azioni</th>
            </tr>

            <c:forEach var="auto" items="${listaAutoDisponibili}">

                <tr>
                    <td>${auto.costruttore}</td>
                    <td>${auto.modello}</td>
                    <td>${auto.tipologia}</td>
                    <td>${auto.annoImmatricolazione}</td>
                    <td>
                        <form action="/PrenotazioneServlet" method="get">
                            <input type="hidden" name="idAuto" value${auto.id}>
                            <input type="submit" name="azione" value="Prenota">
                        </form>
                    </td>
                </tr>

            </c:forEach>
        </c:if>

    </table>

</body>
</html>
