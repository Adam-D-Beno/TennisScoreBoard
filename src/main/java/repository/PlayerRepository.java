package repository;

import model.Player;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import java.util.List;
import java.util.Optional;

public class PlayerRepository implements SpecPlayerRepository<Player, Integer> {

    @Override
    public Optional<Player> save(Player entity, Session session) {
        session.save(entity);
        return Optional.ofNullable(entity);
    }

    @Override
    public Optional<Player> getById(Integer id, Session session) {
        return Optional.ofNullable(session.get(Player.class, id));
    }

    @Override
    public Optional<Player> getByName(String playerName, Session session) {
        return session.createQuery("select p from Player p where name = :name", Player.class)
                .setParameter("name", playerName).uniqueResultOptional();
    }
}
