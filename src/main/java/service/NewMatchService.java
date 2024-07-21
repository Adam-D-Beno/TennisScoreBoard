package service;

import entity.Match;
import entity.Player;
import model.MatchScoreModel;
import repository.PlayerRepository;

import java.util.Map;
import java.util.UUID;

public class NewMatchService {
    private PlayerRepository playerRepository;

    public UUID CreateNewMatch(String firstPlayerName, String secondPlayerName) {

        Match match = Match.builder()
                .firstPlayer(getOrCreatePlayer(firstPlayerName))
                .secondPlayer(getOrCreatePlayer(secondPlayerName))
                .build();

        UUID uuid = getUUID();
        MatchScoreModel.setNewMatch(uuid, match);
        return uuid;
    }

    private Player getOrCreatePlayer(String playerName) {
        return playerRepository.getByName(playerName)
                .orElseGet(() -> playerRepository
                        .save(Player.builder().name(playerName)
                                .build()));
    }

    private UUID getUUID() {
        return UUID.randomUUID();
    }
}
