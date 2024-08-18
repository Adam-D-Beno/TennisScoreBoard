package service;

import model.MatchScore;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class MainGameService {
    private final MatchScoreCalculationService matchScoreCalculationService;
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    private final OngoingMatchesService ongoingMatchesService;
    private final GenerationMatchService generationMatchService;

    public MainGameService() {
        this.ongoingMatchesService = new OngoingMatchesService();
        this.matchScoreCalculationService = new MatchScoreCalculationService(ongoingMatchesService);
        this.finishedMatchesPersistenceService = new FinishedMatchesPersistenceService(ongoingMatchesService);
        this.generationMatchService = new GenerationMatchService(ongoingMatchesService);
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

    public MatchScore getMatchScore(UUID matchId) {
        return ongoingMatchesService.getMatchScores(matchId)
                .orElseThrow(() -> new EntityNotFoundException("Object match is not found in scoreModel"));
    }
}
