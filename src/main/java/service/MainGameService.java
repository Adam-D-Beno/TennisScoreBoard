package service;

import entity.Match;
import entity.Player;
import model.MatchScoreModel;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

public class MainGameService {

    public void beginGame(Integer playerId, UUID matchId) {

        scoringGames(playerId, getMatch(matchId));
    }



    private void scoringGames(Integer playerId, Match match) {
        Player firstPlayer = match.getFirstPlayer();
        Player secondPlayer = match.getSecondPlayer();

        //todo validation exist in bd
        if (firstPlayer.getId().equals(playerId)) {
            checkIncreaseAddPoints(firstPlayer);

        }

        if (secondPlayer.getId().equals(playerId)) {
            checkIncreaseAddPoints(secondPlayer);
        }

    }

    private void checkIncreaseAddPoints(Player player) {
        player.addPoints();

        if (player.getPoints() >= 40) {
            checkIncreaseAddGames(player);
        }
    }

    private void checkIncreaseAddGames(Player player) {
        player.addGames();

        if (player.getGames() >= 6) {
            checkIncreaseAddSets(player);
        }
    }

    private void checkIncreaseAddSets(Player player) {
        player.addSets();


    }

    private Match getMatch(UUID matchId) {
        return MatchScoreModel.getInstance().getMatch(matchId)
                .orElseThrow(() -> new EntityNotFoundException("Object match is not found in scoreModel"));
    }
}
