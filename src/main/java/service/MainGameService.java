package service;

import model.MatchScore;

import java.util.UUID;

public class MainGameService {
    private final MatchScoreCalculationService matchScoreCalculationService;
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    private final OngoingMatchesService ongoingMatchesService;
    private final GenerationMatchService generationMatchService;

    public MainGameService() {
        this.matchScoreCalculationService = new MatchScoreCalculationService();
        this.finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
        this.ongoingMatchesService = new OngoingMatchesService();
        this.generationMatchService = new GenerationMatchService();
    }

    public void beginGame(Integer playerId, UUID matchId) {
        matchScoreCalculationService.ScoreCalculation(playerId, matchId);
        ongoingMatchesService.getMatchScores(matchId)
                .filter(MatchScore::isMatchEnd)
                .ifPresent(matchScore -> {
                    finishedMatchesPersistenceService.save(matchId);
                    ongoingMatchesService.removeMatchScores(matchId);
                });
    }

    public UUID generationMatchService(String firstPlayerName, String secondPlayerName) {
       return generationMatchService.createNewMatchScores(firstPlayerName, secondPlayerName)
                .orElseThrow(() -> new IllegalArgumentException("UUID is not found"));
    }

}
