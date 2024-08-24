package repository;

import lombok.NoArgsConstructor;
import model.Match;
import lombok.RequiredArgsConstructor;
import model.Player;
import org.hibernate.Session;

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
    public List<Match> getByWinPlayerName(String winPlayerName, Session session) {
        return Collections.emptyList();
    }
}
