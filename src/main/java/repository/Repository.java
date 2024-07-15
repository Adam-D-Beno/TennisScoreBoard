package repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Repository<E, K extends Serializable> {

    Long save (E entity);
    Optional<E> getById(Long id);
    List<E> getAllGames();
}
