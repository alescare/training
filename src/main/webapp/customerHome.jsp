<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<body>
    <jsp:include page="header.jsp"/>

    <form action="/UtenteServlet" method="get">
        <input type="submit" name="azione" value="Visualizza profilo">
        <br/><br/>
    </form>
    <form action="/AutoServlet" method="get">
        <input type="submit" name="azione" value="Prenota auto">
    </form>

</body>
</html>
