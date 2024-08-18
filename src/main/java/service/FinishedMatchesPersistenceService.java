package service;

import config.HibernateConfig;
import model.Match;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import repository.MatchRepository;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class FinishedMatchesPersistenceService {
    private final MatchRepository matchRepository;
    private final OngoingMatchesService ongoingMatchesService;

    public FinishedMatchesPersistenceService(OngoingMatchesService ongoingMatchesService) {
        this.matchRepository = new MatchRepository();
        this.ongoingMatchesService = ongoingMatchesService;
    }

    public void save(UUID matchId) {
        executionTransaction(toMatch(matchId));
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
}
