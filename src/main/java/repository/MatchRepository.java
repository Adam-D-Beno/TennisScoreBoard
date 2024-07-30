package repository;

import entity.Match;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class MatchRepository implements Repository<Match, Long> {

    @Override
    public Optional<Match> save(Match match, Session session) {
        session.save(match);
        return Optional.ofNullable(match);
    }

    @Override
    public Optional<Match> getById(Long id, Session session) {
        return Optional.ofNullable(session.get(Match.class, id));
    }

    @Override
    public Optional<List<Match>> getAllGames(Session session) {
        return Optional.empty();
    }

    @Override
    public Optional<Match> getByName(String name, Session session) {
        return Optional.empty();
    }
}
