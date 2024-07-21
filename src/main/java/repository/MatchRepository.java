package repository;

import entity.Match;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class MatchRepository implements Repository<Match, Long> {
    private final Session session;

    @Override
    public Match save(Match match) {
        session.save(match);
        return match;
    }

    @Override
    public Optional<Match> getById(Long id) {
        return Optional.ofNullable(session.get(Match.class, id));
    }

    @Override
    public List<Match> getAllGames() {
        return null;
    }

    @Override
    public Optional<Match> getByName(String name) {
        return Optional.empty();
    }
}
