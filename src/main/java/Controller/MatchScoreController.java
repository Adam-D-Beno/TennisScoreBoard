package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.NewMatchService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "match-score", urlPatterns = "/match-score")
public class MatchScoreController extends HttpServlet {
    private NewMatchService newMatchService;

    public MatchScoreController() {
        this.newMatchService = new NewMatchService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer playerId = getPlayerId(req);
        UUID matchId = getMatchId(req);
    }

    private Integer getPlayerId(HttpServletRequest req) {
        //todo validation is null, is Alfabet
        return Integer.parseInt(req.getParameter("playerId"));
    }

    private UUID getMatchId(HttpServletRequest req) {
        return UUID.nameUUIDFromBytes(req.getParameter("uuid").getBytes());
    }
}
