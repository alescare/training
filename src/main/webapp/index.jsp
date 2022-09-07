<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<body>

<form action="/UtenteServlet" method="GET">
    Nome:<br/>
    <input type="text" name="nome" />
    <br/><br/>
    Cognome:<br/>
    <input type="text" name="cognome" />
    <br/><br/>
    <input type="submit" name="azione" value="Entra" />
</form>

</body>
</html>