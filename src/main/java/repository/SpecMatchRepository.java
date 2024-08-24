package repository;

import model.Match;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

public interface SpecMatchRepository<E, K extends Serializable> extends Repository<E, K>{
     List<E> getByWinPlayerName(String winPlayerName, Session session);
}
