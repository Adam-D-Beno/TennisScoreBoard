<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style><%@ include file="/css/style.css"%></style>
    <style><%@ include file="/css/final-score.css"%></style>
    <title>Final score</title>
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
                <h1>Match finished! ${matchScore.winner.name} wins!</h1>
                <div class="content-body">
                    <table>
                        <thead>
                            <tr>
                                <td class="player">PLAYER</td>
                                <td class="set">SETS</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>${matchScore.firstPlayer.name}</td>
                                <td>${matchScore.firstPlayerSets}</td>
                            </tr>
                            <tr>
                                <td>${matchScore.secondPlayer.name}</td>
                                <td>${matchScore.secondPlayerSets}</td>
                            </tr>
                        </tbody>
                    </table>
                    <a class="homeBtn" href="index"><div>HOME</div></a>
                </div>
            </div>
        </article>
    </section>
</body>
</html>