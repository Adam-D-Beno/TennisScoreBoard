package service;

import config.HibernateConfig;
import entity.Match;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import repository.MatchRepository;
import repository.PlayerRepository;

public class FinishedMatchesPersistenceService {
    private final MatchRepository matchRepository;

    public FinishedMatchesPersistenceService() {
        matchRepository = new MatchRepository();
    }

    public void save(Match match) {
        executionTransaction(match);
        deleteCurrentMatch(match);
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

    private void deleteCurrentMatch(Match match) {

    }
}
