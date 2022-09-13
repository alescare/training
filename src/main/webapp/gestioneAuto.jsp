<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
<jsp:include page="header.jsp"/>

<form action="AutoServlet" method="post">

    <input type="hidden" name="idAutoDaModificare" value="${autoDaModificare.id}">
    Costruttore:<br/>
    <input type="text" name="costruttore" value="${autoDaModificare.costruttore}">
    <br/><br/>
    Modello:<br/>
    <input type="text" name="modello" value="${autoDaModificare.modello}">
    <br/><br/>
    Targa:<br/>
    <input type="text" name="targa" value="${autoDaModificare.targa}">
    <br/><br/>
    Tipologia:<br/>
    <input type="text" name="tipologia" value="${autoDaModificare.tipologia}">
    <br/><br/>
    Anno di immatricolazione:<br/>
    <input type="text" name="annoImmatricolazione" value="${autoDaModificare.annoImmatricolazione}">
    <br/><br/>
    <input type="submit" name="azione" value="Salva"/>

</form>

<hr>

<table border="3" style="text-align: center">

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
        <td>${auto.annoImmatricolazione}</td>
        <td>
            <form action="AutoServlet" method="post">
                <input type="hidden" name="idAuto" value="${auto.id}">
                <input type="hidden" name="azione" value="modifica auto">
                <input type="submit" value="Modifica">
            </form>
        </td>
        <td>
            <form action="AutoServlet" method="post">
                <input type="hidden" name="idAuto" value="${auto.id}">
                <input type="hidden" name="azione" value="elimina auto">
                <input type="submit" value="Elimina">
            </form>
        </td>
    </tr>


    </c:forEach>

</body>
</html>
