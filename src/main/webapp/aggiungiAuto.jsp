<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
    <jsp:include page="header.jsp"/>
    <c:choose>
        <c:when test="${auto == null}">
            Auto non inserita: targa già esistente.
            <br/>
        </c:when>
    </c:choose>
    <form action="/AggiungiAutoServlet" method="get">
        Costruttore:<br/>
        <input type="text" name="costruttore"><br/>
        Modello:<br/>
        <input type="text" name="modello"> <br/>
        Targa:<br/>
        <input type="text" name="targa"><br/>
        Tipologia:<br/>
        <input type="text" name="tipologia"><br/>
        Anno di immatricolazione:<br/>
        <input type="text" name="annoImmatricolazione"><br/>
        <input type="submit" value="Aggiungi"/>
    </form>
</body>
</html>
