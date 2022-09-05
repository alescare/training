<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
   <h3>Utente: ${session.utente.nome} ${session.utente.cognome}</h3>
   <c:choose>

        <c:when test = "${session.utente.admin == true}">
            <a href="superUserHome.jsp">Home</a>
        </c:when>

        <c:otherwise>
            <a href="customerHome.jsp">Home</a>
        </c:otherwise>

    </c:choose>

    <a href="index.jsp">Esci</a>
    <hr>
</body>
</html>
