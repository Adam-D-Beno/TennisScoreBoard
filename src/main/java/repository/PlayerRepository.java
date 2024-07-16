package repository;

import entity.Match;
import entity.Player;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PlayerRepository implements Repository<Player, Long> {

    private final Session session;
    private final Class<?> aClass;

    @Override
    public Player save(Player player) {
        session.save(player);
        return player;
    }

    @Override
    public Optional<Player> getById(Long id) {
        return Optional.ofNullable(session.get(Player.class, id));
    }

    @Override
    public List<Player> getAllGames() {
        return session.createQuery("select p from Player p").getResultList();
    }
}
