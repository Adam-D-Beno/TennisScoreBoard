package repository;

import java.io.Serializable;
import java.util.List;

public interface Repository<E, K extends Serializable> {

    Long save (E entity);
    E getById(Long id);
    List<E> getAllGames();
}
