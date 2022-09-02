<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<body>
<form action="/LoginServlet" method="GET">

    Nome: <input type="text" name="nome" />

    <br/><br/>

    Cognome: <input type="text" name="cognome" />

    <br/><br/>

    <input type="submit" value="Entra" />

    <table>

        <tr>
            <th>Auto</th>
            <th>Data inizio</th>
            <th>Data fine</th>
            <th>Azioni</th>
        </tr>


            <tr>
                <td>owkdoiwd</td>
                <td>kajcna</td>
                <td>kiasncknj</td>
                <td>
                    <form action = "/ApprovaPrenotazioniServlet" method="get">
                        <input type= "submit" name="auto" value= "Approva">
                    </form>
                </td>
            </tr>



    </table>

</form>
</body>
</html>