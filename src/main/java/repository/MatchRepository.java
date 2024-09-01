package repository;


import model.Match;
import model.Player;
import org.h2.util.StringUtils;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.*;
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
    public List<Match> getByPlayerName(int pageSize, int pageNumber, String playerName, Session session) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Match> criteriaQuery = cb.createQuery(Match.class);
        Root<Match> match = criteriaQuery.from(Match.class);

        if (!StringUtils.isNullOrEmpty(playerName)) {
            Join<Match, Player> p1 = match.join("firstPlayer");
            Join<Match, Player> p2 = match.join("secondPlayer");
            Predicate p1Predicate = cb.equal(p1.get("name"), playerName);
            Predicate p2Predicate = cb.equal(p2.get("name"), playerName);
            Predicate finalPredicate = cb.or(p1Predicate, p2Predicate);
            criteriaQuery.select(match).where(finalPredicate);
        } else {
            criteriaQuery.select(match);
        }

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
