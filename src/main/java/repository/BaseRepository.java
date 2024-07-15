package repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
public class BaseRepository<E, K extends Serializable> implements Repository<E, K> {
    private Session session;
    private Class<?> aClass;

    @Override
    public Long save(E entity) {
        return 0L;
    }

    @Override
    public Optional<E> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<E> getAllGames() {
        return List.of();
    }
}
