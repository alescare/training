<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

   <h3>Utente: ${session.utente.nome} ${session.utente.cognome}   </h3>

   <form action="/UtenteServlet" method="get">
       <input type="submit" name="azione" value="Home">
   </form>

    <form action="/UtenteServlet" method="get">
        <input type="submit" name="azione" value="Esci">
    </form>
    <hr>


