<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<body>
<jsp:include page="header.jsp"/>
<form action="AutoServlet" method="get">
    <input type="submit" name="azione" value="Gestisci auto">
</form>
<br/><br/>
<form action="UtenteServlet" method="get">
    <input type="submit" name="azione" value="Gestisci utenti">
</form>
<br/><br/>
<form action="PrenotazioneServlet" method="get">
    <input type="hidden" name="azione" value="prenotazioni da approvare">
    <input type="submit" value="Approva prenotazioni">
    <br/><br/>
    <input type="hidden" name="azione" value="prenotazioni da cancellare">
    <input type="submit" value="Cancella prenotazioni">
</form>

</body>
</html>
