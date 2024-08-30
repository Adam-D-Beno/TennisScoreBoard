package repository;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface SpecMatchRepository<E, K extends Serializable> extends Repository<E, K>{
     List<E> getByPlayerName(int pageSize, int pageNumber, String winPlayerName, Session session);
     long getTotalMatches(Session session);

}
