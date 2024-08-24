package Controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MainGameService;
import validation.MatchValidate;
import validation.PlayerValidate;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "match-score", urlPatterns = "/match-score")
public class MatchScoreController extends HttpServlet {

    private MainGameService mainGameService;
    private final PlayerValidate playerValidate = PlayerValidate.getInstance();
    private final MatchValidate matchValidate = MatchValidate.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {
        mainGameService = (MainGameService) config.getServletContext().getAttribute("mainGameService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("matchScore", mainGameService.getMatchScore(getMatchId(req)).get());
        req.getRequestDispatcher("/match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        mainGameService.beginGame(getPlayerId(req), getMatchId(req))
                .ifPresent(matchScore -> {
                    req.setAttribute("matchScore", matchScore);
                    req.setAttribute("isTieBreak", matchScore.isTieBreak());
                    req.setAttribute("isDeuce", matchScore.isDeuce());
                });

        if (mainGameService.endGame(getMatchId(req))) {
            req.getRequestDispatcher("/final-score.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/match-score.jsp").forward(req, resp);
        }
    }

    private int getPlayerId(HttpServletRequest req) {
        if (playerValidate.isEmptyOrNull(req.getParameter("playerId"))) {
            throw new IllegalArgumentException("Player id is empty or null");
        }
        if (!playerValidate.isNumber(req.getParameter("playerId"))) {
            throw new IllegalArgumentException("Player id is not numeric");
        }
        return Integer.parseInt(req.getParameter("playerId"));
    }

    private UUID getMatchId(HttpServletRequest req) {
        //todo chance on match validate
        if (matchValidate.isEmptyOrNull(req.getParameter("uuid"))) {
            throw new IllegalArgumentException("UUID is empty or null");
        }
        return UUID.fromString(req.getParameter("uuid"));
    }
}
