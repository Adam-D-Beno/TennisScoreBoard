package service;

import entity.Match;
import model.MatchScoreModel;

import java.util.Optional;
import java.util.UUID;

public class MainGameService {
    private final MatchScoreModel scoreModel = MatchScoreModel.getInstance();

    public void beginGame(Integer playerId, UUID matchId) {


    }

    private Optional<Match> getMatch(UUID matchId) {
        return scoreModel.getMatch(matchId);
    }

    private void addPoints(Integer playerId) {

    }

    private void addGames(Integer playerId) {

    }

    private void addSets(Integer playerId) {

    }
}
