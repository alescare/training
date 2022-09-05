<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<body>
    <jsp:include page="header.jsp"/>
    <c:if test = "${utente.nome == null}">
        <h5 style="color: crimson">Utente non inserito: credenziali non valide.</h5>
    </c:if>


    <form action="/AggiungiUtenteServlet" method="get">
        Nome:<br/>
        <input type="text" name="nome"><br/>
        Cognome:<br/>
        <input type="text" name="cognome"> <br/>
        Data di nascita:<br/>
        <input type="date" name="dataNascita"><br/>
        <input type="submit" value="Aggiungi"/>
    </form>
</body>
</html>
