<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<body>
    <jsp:include page="header.jsp"/>

    <form action="/UtenteServlet" method="get">
        Nome:<br/>
        <input type="text" name="nome"><br/><br/>
        Cognome:<br/>
        <input type="text" name="cognome"> <br/><br/>
        Data di nascita:<br/>
        <input type="date" name="dataNascita"><br/><br/>
        <input type="submit" value="Aggiungi"/>
    </form>
</body>
</html>
