package repository;


import model.Match;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MatchRepository implements SpecMatchRepository<Match, Integer> {

    @Override
    public Optional<Match> save(Match entity, Session session) {
        session.save(entity);
        return Optional.ofNullable(entity);
    }

    @Override
    public Optional<Match> getById(Integer id, Session session) {
        return Optional.ofNullable(session.get(Match.class, id));
    }

    @Override
    public List<Match> getByPlayerName(int pageSize, int pageNumber, String PlayerName, Session session) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Match> criteriaQuery = criteriaBuilder.createQuery(Match.class);
        Root<Match> matchRoot = criteriaQuery.from(Match.class);
        criteriaQuery.select(matchRoot);

        Query query = session.createQuery(criteriaQuery);

        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public long getTotalMatches(Session session) {
        return (long) session.createQuery("SELECT COUNT (m.id) FROM Match m").uniqueResult();
    }
}
