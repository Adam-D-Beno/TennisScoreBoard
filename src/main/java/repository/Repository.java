package repository;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<E, K extends Serializable>{

    E save (E entity, Session session);
    Optional<E> getById(K id, Session session);
    List<E> getAllGames(Session session);
    Optional<E> getByName(String name, Session session);
}
