package repository;

import model.Player;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.Optional;

public interface SpecPlayerRepository<E, K extends Serializable> extends Repository<E, K>{
    Optional<Player> getByName(String name, Session session);
}
