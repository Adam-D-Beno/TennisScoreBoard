package repository;

import config.HibernateConfig;
import entity.Player;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class PlayerRepository implements Repository<Player, Long> {

    @Override
    public Player save(Player player, Session session) {
        session.save(player);
        return player;
    }

    @Override
    public Optional<Player> getById(Long id, Session session ) {
        return Optional.ofNullable(session.get(Player.class, id));
    }

    @Override
    public List<Player> getAllGames(Session session) {
        return session.createQuery("select p from Player p", Player.class).getResultList();
    }

    @Override
    public Optional<Player> getByName(String playerName, Session session) {
        return session.createQuery("select p from Player p where name = :name", Player.class)
                .setParameter("name", playerName).uniqueResultOptional();
    }
}
