<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

     <form action="UtenteServlet" method="get">
         <h2>Utente: ${sessionScope.utenteLoggato.username}
         <input type="submit" name="azione" value="Home">
         <input type="submit" name="azione" value="Esci">
         </h2>
    </form>
    <hr>


