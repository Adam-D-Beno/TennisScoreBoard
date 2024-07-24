package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.NewMatchService;

import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "new-match", urlPatterns = "/new-match")
public class NewMatchController extends HttpServlet {
    private final NewMatchService newMatchService;

    public NewMatchController() {
        this.newMatchService = new NewMatchService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstPlayerName = req.getParameter("player1");
        String secondPlayerName = req.getParameter("player2");
        UUID match_id = newMatchService.CreateNewMatch(firstPlayerName, secondPlayerName);
        resp.sendRedirect("/match-score.jsp?uuid=$" + match_id);
    }
}
