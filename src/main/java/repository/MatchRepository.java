package repository;

import entity.Match;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class MatchRepository implements Repository<Match, Long> {

    @Override
    public Match save(Match match, Session session) {
        session.save(match);
        return match;
    }

    @Override
    public Optional<Match> getById(Long id, Session session) {
        return Optional.ofNullable(session.get(Match.class, id));
    }

    @Override
    public List<Match> getAllGames(Session session) {
        return null;
    }

    @Override
    public Optional<Match> getByName(String name, Session session) {
        return Optional.empty();
    }
}
