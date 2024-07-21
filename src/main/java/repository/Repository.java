package repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<E, K extends Serializable>{

    E save (E entity);
    Optional<E> getById(K id);
    List<E> getAllGames();
    Optional<E> getByName(String name);
}
