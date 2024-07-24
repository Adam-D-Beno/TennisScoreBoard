package service;

import entity.Match;
import entity.Player;
import model.MatchScoreModel;
import org.hibernate.Session;
import repository.PlayerRepository;
import repository.Repository;

import java.util.UUID;
import java.util.function.Supplier;

import static config.HibernateConfig.buildSessionFactory;

public class NewMatchService {
    private final Repository<Player, Long> playerRepository;
    private final MatchScoreModel matchScoreModel = MatchScoreModel.getINSTANCE();
    private final Session session = buildSessionFactory().getCurrentSession();

    public NewMatchService() {
        this.playerRepository = new PlayerRepository();
    }

    public UUID CreateNewMatch(String firstPlayerName, String secondPlayerName) {

        Match match = Match.builder()
                .firstPlayer(getOrCreatePlayer(firstPlayerName))
                .secondPlayer(getOrCreatePlayer(secondPlayerName))
                .build();

        UUID uuid = getUUID();
        matchScoreModel.setNewMatch(uuid, match);
        return uuid;
    }

    private Player getOrCreatePlayer(String playerName) {
        return playerRepository.getByName(playerName)
                .orElseGet(savePlayer(playerName)
                );
    }

    private Supplier<Player> savePlayer(String name) {
        return () -> {
            session.beginTransaction();
            Player player = playerRepository.save(
                    Player.builder()
                            .name(name)
                            .build()
            );
            session.getTransaction().commit();
            return player;
        };
    }

    private UUID getUUID() {
        return UUID.randomUUID();
    }
}


