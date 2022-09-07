<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
    <jsp:include page="header.jsp"/>

    <form action="/AutoServlet" method="get">
        Costruttore:<br/>
        <input type="text" name="costruttore">
        <br/><br/>
        Modello:<br/>
        <input type="text" name="modello">
        <br/><br/>
        Targa:<br/>
        <input type="text" name="targa">
        <br/><br/>
        Tipologia:<br/>
        <input type="text" name="tipologia">
        <br/><br/>
        Anno di immatricolazione:<br/>
        <input type="text" name="annoImmatricolazione">
        <br/><br/>
        <input type="submit" name="azione" value="Aggiungi"/>
    </form>

    <hr>

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
