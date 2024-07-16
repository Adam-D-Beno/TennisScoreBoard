<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Tennis board</title>
</head>

<body>
    <form action="http://localhost:8080/new-match" method="post">
        <label>
            Name player1:
            <input type="text" name="player1"/>
        </label>
        <br>
        <label>
            Name player2:
            <input type="text" name="player2"/>
        </label>
        <br>
            <input type="submit" value="Start"/>
    </form>
</body>
</html>
