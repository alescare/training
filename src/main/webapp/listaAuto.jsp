<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<body>
    <jsp:include page="header.jsp"/>
    <table>

        <tr>
            <th>Costruttore</th>
            <th>Modello</th>
            <th>Tipologia</th>
            <th>Targa</th>
            <th>Anno immatricolazione</th>

        </tr>

        <c:forEach var="auto" items="${listaAuto}">

            <tr>
                <td>${auto.costruttore}</td>
                <td>${auto.modello}</td>
                <td>${auto.tipologia}</td>
                <td>${auto.targa}</td>
                <td>${auto.anniImmatricolazione}</td>
            </tr>

        </c:forEach>

    </table>
</body>
</html>
