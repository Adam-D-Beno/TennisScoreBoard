package service;

import config.HibernateConfig;
import model.Match;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import repository.MatchRepository;
import repository.SpecMatchRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

public class FinishedMatchesPersistenceService {
    private final SpecMatchRepository<Match, Integer> matchRepository;
    private final OngoingMatchesService ongoingMatchesService;
    private static final int MATCHES_ON_PAGE = 5;

    public FinishedMatchesPersistenceService(OngoingMatchesService ongoingMatchesService) {
        this.matchRepository = new MatchRepository();
        this.ongoingMatchesService = ongoingMatchesService;
    }

    public void save(UUID matchId) {
        executionTransaction(toMatch(matchId));
    }

    public List<Match> getMatchesByPlayerName(int pageNumber, String winPlayerName) {
        Session session = HibernateConfig
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();

        session.beginTransaction();
        List<Match> matches = matchRepository.getByWinPlayerName(MATCHES_ON_PAGE, pageNumber, winPlayerName, session);
        session.getTransaction().commit();
        return matches;
    }

    private void executionTransaction(Match match) {
        Session session = HibernateConfig
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        try {
            session.beginTransaction();
            matchRepository.save(match, session);
            session.getTransaction().commit();

        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new HibernateException(e);
        }
    }

    private Match toMatch(UUID matchId) {
        return ongoingMatchesService.getMatchScores(matchId).map(
                (matchScore -> Match.builder()
                        .firstPlayer(matchScore.getFirstPlayer())
                        .secondPlayer(matchScore.getSecondPlayer())
                        .winner(matchScore.getWinner())
                        .build())
        ).orElseThrow(() -> new EntityNotFoundException("Object match is not found in scoreModel"));
    }

    public long getTotalPage() {
        Session session = HibernateConfig
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();

        session.beginTransaction();
        long totalMatches = matchRepository.getTotalMatches(session);
        session.getTransaction().commit();
        return totalMatches;
    }
}
