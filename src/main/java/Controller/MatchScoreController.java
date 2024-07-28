package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MatchScoreCalculationService;
import service.NewMatchService;
import service.OngoingMatchesService;
import validation.PlayerValidate;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "match-score", urlPatterns = "/match-score")
public class MatchScoreController extends HttpServlet {

    private final OngoingMatchesService ongoingMatchesService;
    private final PlayerValidate playerValidate = PlayerValidate.getInstance();

    public MatchScoreController() {
        this.ongoingMatchesService = new OngoingMatchesService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer playerId = getPlayerId(req);
        UUID matchId = getMatchId(req);

    }

    private Integer getPlayerId(HttpServletRequest req) {
        if (playerValidate.isEmptyOrNull(req.getParameter("playerId"))) {
            throw new IllegalArgumentException("Player id is empty or null");
        }
        if (playerValidate.isNumber(req.getParameter("playerId"))) {
            throw new IllegalArgumentException("Player id is not numeric");
        }
        return Integer.parseInt(req.getParameter("playerId"));
    }

    private UUID getMatchId(HttpServletRequest req) {
       if (playerValidate.isEmptyOrNull(req.getParameter("uuid"))) {
           throw new IllegalArgumentException("UUID is empty or null");
        }
        return UUID.nameUUIDFromBytes(req.getParameter("uuid").getBytes());
    }
}
