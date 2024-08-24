package service;

import config.HibernateConfig;
import model.MatchScore;
import model.Player;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import repository.PlayerRepository;
import repository.SpecPlayerRepository;

import java.util.Optional;
import java.util.UUID;


public class GenerationMatchScoreService {

    private final SpecPlayerRepository<Player, Integer> playerRepository;
    private final OngoingMatchesService ongoingMatchesService;

    public GenerationMatchScoreService(OngoingMatchesService ongoingMatchesService) {
        this.playerRepository = new PlayerRepository();
        this.ongoingMatchesService = ongoingMatchesService;
    }

    public Optional<UUID> createNewMatchScores(String firstPlayerName, String secondPlayerName) {
        MatchScore matchScore = MatchScore.builder()
                .firstPlayer(getOrCreatePlayer(firstPlayerName))
                .secondPlayer(getOrCreatePlayer(secondPlayerName))
                .uuid(getRandomUUID())
                .build();

        return ongoingMatchesService.setMatchScores(matchScore);
    }

    private Player getOrCreatePlayer(String playerName) {
        return executeTransaction(playerName);
    }

    private Player executeTransaction(String playerName) {
        Session session = HibernateConfig.getInstance()
                .getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            Player player = playerRepository.getByName(playerName, session)
                    .orElseGet(
                            () -> playerRepository.save(Player.builder().name(playerName).build(), session).get()
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

    private UUID getRandomUUID() {
        return UUID.randomUUID();
    }
}




