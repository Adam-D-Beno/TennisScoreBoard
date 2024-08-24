package Controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.MainGameService;

import java.io.IOException;

@WebServlet(name = "matches", urlPatterns = "/matches")
public class MatchesController extends HttpServlet {
    private MainGameService mainGameService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        mainGameService = (MainGameService) config.getServletContext().getAttribute("mainGameService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
