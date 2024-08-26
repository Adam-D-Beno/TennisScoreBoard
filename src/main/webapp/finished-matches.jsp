<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style><%@ include file="/css/style.css"%></style>
    <style><%@ include file="/css/finished-matches.css"%></style>
    <title>Finished matches</title>
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
                <h1>Finished matches</h1>
                <div class="content-body">
                    <div class="filter">
                        <form action="matches">
                            <%--@declare id="filter_by_player_name"--%><label class="nameLabel" for="filter_by_player_name">Name:</label>
                            <input id="playerNameInput" class="playerName" type="text" value="${playerName}" name="filter_by_player_name">
                            <input type="submit" value="Search" class="filterButton" />
                            <a href="matches" class="clearLink"><input type="button" value="Clear" class="clearButton" /></a>
                        </form>
                        <c:if test="${error != null}">
                            <div class="error">${error}</div>
                        </c:if>
                    </div>
                    <table>
                        <thead>
                            <tr>
                                <td class="matchId">ID</td>
                                <td class="playerOneName">Player 1</td>
                                <td class="playerTwoName">Player 2</td>
                                <td class="winnerName">Winner</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${page.content}" var="match">
                                <tr>
                                    <td>${match.id}</td>
                                    <td>${match.firstPlayer.name}</td>
                                    <td>${match.secondPlayer.name}</td>
                                    <td>${match.winner.name}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="pagination">
                        <c:choose>
                            <c:when test="${empty playerName}">
                                <c:set var="filter" value="" />
                            </c:when>
                            <c:otherwise>
                                <c:set var="filter" value="&filter_by_player_name=${playerName}" />
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${page.totalPages > 1}">
                            <c:choose>
                                <c:when test="${page.pageNumber == 1}">
                                    <div class="page"><a disabled>Prev</a></div>
                                </c:when>
                                <c:when test="${page.pageNumber > 1}">
                                    <div class="page"><a href="matches?page=${page.pageNumber - 1}${filter}">Prev</a></div>
                                </c:when>
                            </c:choose>
                            <div class="page"><a disabled>${page.pageNumber}</a></div>
                            <c:choose>
                                <c:when test="${page.pageNumber == page.totalPages}">
                                    <div class="page"><a disabled>Next</a></div>
                                </c:when>
                                <c:when test="${page.pageNumber < page.totalPages}">
                                    <div class="page"><a href="matches?page=${page.pageNumber + 1}${filter}">Next</a></div>
                                </c:when>
                            </c:choose>
                        </c:if>
                    </div>
                </div>
            </div>
        </article>
    </section>
</body>
</html>