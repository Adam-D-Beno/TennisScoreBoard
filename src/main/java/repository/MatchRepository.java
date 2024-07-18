//package repository;
//
//import entity.Match;
//import lombok.RequiredArgsConstructor;
//import org.hibernate.Session;
//
//import java.util.List;
//import java.util.Optional;
//
//@RequiredArgsConstructor
//public class MatchRepository implements Repository<Object, Long> {
//    private final Session session;
//
//    @Override
//    public Object save(Object match) {
//        session.save(match);
//        return match;
//    }
//
//    @Override
//    public Optional<Object> getById(Long id) {
//        return Optional.ofNullable(session.get(Match.class, id));
//    }
//
//    @Override
//    public List<Object> getAllGames() {
//        return null;
//    }
//}
