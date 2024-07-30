package service;

import entity.Match;
import entity.Player;
import model.MatchScoreModel;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

public class MainGameService {
    private final MatchScoreCalculationService matchScoreCalculationService;
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService;

    public MainGameService() {
        matchScoreCalculationService = new MatchScoreCalculationService();
        finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
    }

    public void beginGame(Integer playerId, UUID matchId) {

        matchScoreCalculationService.scoringGames(playerId, getMatch(matchId));
    }



    private Match getMatch(UUID matchId) {
        return MatchScoreModel.getInstance().getMatch(matchId)
                .orElseThrow(() -> new EntityNotFoundException("Object match is not found in scoreModel"));
    }
}
