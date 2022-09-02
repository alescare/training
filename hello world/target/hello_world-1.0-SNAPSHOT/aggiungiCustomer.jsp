<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
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
