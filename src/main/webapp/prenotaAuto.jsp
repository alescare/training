<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<body>
<jsp:include page="header.jsp"/>

<form action="AutoServlet" method="post">

    Data inizio: <input type="date" name="dataInizio" value="${sessionScope.dataInizio}"><br/>
    Data fine: <input type="date" name="dataFine" value="${sessionScope.dataFine}"><br/>
    <input type="hidden" name="azione" value="cerca auto disponibili">
    <input type="submit" value="Cerca">

</form>

<hr>

<c:if test="${sessionScope.dataInizio != null && sessionScope.dataFine != null}">
<table style="text-align: center" border="3">

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
                <form action="PrenotazioneServlet" method="post">
                    <input type="hidden" name="idAuto" value="${auto.id}">
                    <input type="hidden" name="azione" value="prenota auto">
                    <input type="submit" name="azione" value="Prenota">
                </form>
            </td>
        </tr>

    </c:forEach>
    </c:if>

</table>

</body>
</html>
