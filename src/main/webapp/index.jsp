<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<body>

<form action="UtenteServlet" method="post">
    Username:<br/>
    <input type="text" name="username"/>
    <br/><br/>
    Password:<br/>
    <input type="password" name="password"/>
    <br/><br/>
    <input type="submit" name="azione" value="Entra"/>
</form>

<h4>${loginFallito}</h4>

</body>
</html>