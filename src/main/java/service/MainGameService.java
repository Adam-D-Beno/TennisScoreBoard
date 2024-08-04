package service;

import java.util.UUID;

public class MainGameService {
    private final MatchScoreCalculationService matchScoreCalculationService;
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    private final OngoingMatchesService ongoingMatchesService;

    public MainGameService() {
        this.matchScoreCalculationService = new MatchScoreCalculationService();
        this.finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
        this. ongoingMatchesService = new OngoingMatchesService();
    }

    public void beginGame(Integer playerId, UUID matchId) {
        matchScoreCalculationService.scoringGames(playerId, matchId);
        finishedMatchesPersistenceService.save(matchId);
        ongoingMatchesService.removeMatchScores(matchId);
    }



}
