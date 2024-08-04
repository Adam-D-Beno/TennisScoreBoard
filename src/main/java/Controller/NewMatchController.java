package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.NewMatchService;
import validation.PlayerValidate;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "new-match", urlPatterns = "/new-match")
public class NewMatchController extends HttpServlet {
    private final NewMatchService newMatchService;
    private final PlayerValidate playerValidate = PlayerValidate.getInstance();
    public NewMatchController() {
        this.newMatchService = new NewMatchService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstPlayerName = getPlayerName(req, "player1");
        String secondPlayerName = getPlayerName(req, "player2");
        UUID match_id = newMatchService.createNewMatchScores(firstPlayerName, secondPlayerName).get();
        resp.sendRedirect("/match-score?uuid=$" + match_id);
    }

    private String getPlayerName(HttpServletRequest req, String playerName) {
        if (playerValidate.isEmptyOrNull(req.getParameter(playerName))) {
            throw new IllegalArgumentException("Player name is empty or null");
        }
        return req.getParameter(playerName);
    }
}
