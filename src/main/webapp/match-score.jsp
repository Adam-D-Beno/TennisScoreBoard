<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style><%@ include file="/css/style.css"%></style>
    <style><%@ include file="/css/match-score.css"%></style>
    <title>Match score</title>
</head>
<body>
    <section>
        <header>
            <nav>
                <div class="nav-list">
                    <a class="nav-link" href="index.jsp"><div>HOME</div></a>
                    <a class="nav-link" href="new-match.jsp"><div>NEW</div></a>
                    <a class="nav-link" href="finished-matches.jsp"><div>MATCHES</div></a>
                </div>
            </nav>
        </header>
    </section>
    <section>
        <article>
            <div class="content">
                <h1>Match score</h1>
                <div class="content-body">
                    <table class="main-tbl">
                        <thead>
                            <tr>
                                <td class="player">PLAYER</td>
                                <td class="set">SETS</td>
                                <td class="game">GAMES</td>
                                <td class="point">POINTS</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>${matchScore.firstPlayer.name}</td>
                                <td class="points">${matchScore.firstPlayerSets}</td>
                                <td class="points">${matchScore.firstPlayerGames}</td>
                                <c:choose>
                                    <c:when test="${isDeuce == false}">
                                        <td class="points">${matchScore.firstPlayerPoints}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${isDeuce == true}">
                                                <td class="points">AD</td>
                                            </c:when>
                                            <c:otherwise>
                                                <td></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <tr>
                                <td>${matchScore.secondPlayer.name}</td>
                                <td class="points">${matchScore.secondPlayerSets}</td>
                                <td class="points">${matchScore.secondPlayerGames}</td>
                                <c:choose>
                                    <c:when test="${isDeuce == false}">
                                        <td class="points">${matchScore.secondPlayerPoints}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${isDeuce == true}">
                                                <td class="points">AD</td>
                                            </c:when>
                                            <c:otherwise>
                                                <td></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </tbody>
                    </table>
                    <c:if test="${isTieBreak}">
                        <div class="tieBreak">
                            <h3>Tie break!</h3>
                            <table>
                                <thead>
                                    <tr>
                                        <td class="col">${matchScore.secondPlayer.name}</td>
                                        <td class="col">${matchScore.firstPlayer.name}</td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td class="col, points">${matchScore.tieBreakFirstPlayer}</td>
                                        <td class="col, points">${matchScore.tieBreakSecondPlayer}</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </c:if>
                    <div class="buttons">
                        <form action="match-score?uuid=${param.uuid}" method="POST">
                            <button class="button" name="playerId" value="1">Player 1 wins point!</button>
                            <button class="button" name="playerId" value="2">Player 2 wins point!</button>
                        </form>
                    </div>
                </div>
            </div>
        </article>
    </section>
</body>
</html>
