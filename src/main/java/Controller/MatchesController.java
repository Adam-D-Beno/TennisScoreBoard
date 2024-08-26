package Controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MainGameService;
import validation.MatchValidate;

import java.io.IOException;

@WebServlet(name = "matches", urlPatterns = "/matches")
public class MatchesController extends HttpServlet {
    private MainGameService mainGameService;
    private MatchValidate matchValidate;

    @Override
    public void init(ServletConfig config) throws ServletException {
        mainGameService = (MainGameService) config.getServletContext().getAttribute("mainGameService");
        matchValidate = MatchValidate.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = getPage(req);
        String playerName = getPlayerName(req);
        req.setAttribute("content", mainGameService.getMatchesPlayed(page, playerName));
        req.getRequestDispatcher("/finished-matches.jsp").forward(req,resp);

    }

    private String getPlayerName(HttpServletRequest req) {
        matchValidate.isEmptyOrNull(req.getParameter("filter_by_player_name"));
        return req.getParameter("filter_by_player_name");
    }

    private int getPage(HttpServletRequest req) {
        matchValidate.isEmptyOrNull(req.getParameter("page"));
        matchValidate.isNumber(req.getParameter("page"));
        return Integer.parseInt(req.getParameter("page"));
    }

}
