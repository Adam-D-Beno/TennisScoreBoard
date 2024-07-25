package service;

import entity.Match;
import entity.Player;
import model.MatchScoreModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import repository.PlayerRepository;
import repository.Repository;

import java.util.UUID;

import static config.HibernateConfig.buildSessionFactory;

public class NewMatchService {
    private final Repository<Player, Long> playerRepository;

    public NewMatchService() {
        this.playerRepository = new PlayerRepository();
    }

    public UUID CreateNewMatch(String firstPlayerName, String secondPlayerName) {

        Match match = Match.builder()
                .firstPlayer(getOrCreatePlayer(firstPlayerName))
                .secondPlayer(getOrCreatePlayer(secondPlayerName))
                .build();

        UUID uuid = getUUID();
        MatchScoreModel.getINSTANCE().setNewMatch(uuid, match);
        return uuid;
    }

    private Player getOrCreatePlayer(String playerName) {

        return executeTransaction(playerName);
    }

    private Player executeTransaction(String playerName) {
        Session session = buildSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Player player = playerRepository.getByName(playerName, session)
                    .orElseGet(
                            () -> playerRepository.save(Player.builder().name(playerName).build(), session)
                    );
            session.getTransaction().commit();
            return player;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new HibernateException(e);
        }
    }

    private UUID getUUID() {
        return UUID.randomUUID();
    }
}




