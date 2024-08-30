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

    public FinishedMatchesPersistenceService(OngoingMatchesService ongoingMatchesService) {
        this.matchRepository = new MatchRepository();
        this.ongoingMatchesService = ongoingMatchesService;
    }

    public void save(UUID matchId) {

        Session session = HibernateConfig
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();
        try {
            session.beginTransaction();
            matchRepository.save(toMatch(matchId), session);
            session.getTransaction().commit();

        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new HibernateException(e);
        }
    }

    public List<Match> getMatchesByPlayerName(int MATCHES_ON_PAGE, int pageNumber, String PlayerName) {
        Session session = HibernateConfig
                .getInstance()
                .getSessionFactory()
                .getCurrentSession();

        session.beginTransaction();
        List<Match> matches = matchRepository.getByPlayerName(MATCHES_ON_PAGE, pageNumber, PlayerName, session);
        session.getTransaction().commit();
        return matches;
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
