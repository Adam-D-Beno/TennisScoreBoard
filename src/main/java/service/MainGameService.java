package service;

import model.MatchScore;

import java.util.Optional;
import java.util.UUID;

public class MainGameService {
    private final MatchScoreCalculationService matchScoreCalculationService;
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService;
    private final OngoingMatchesService ongoingMatchesService;
    private final GenerationMatchScoreService generationMatchScoreService;

    public MainGameService() {
        this.ongoingMatchesService = new OngoingMatchesService();
        this.matchScoreCalculationService = new MatchScoreCalculationService(ongoingMatchesService);
        this.finishedMatchesPersistenceService = new FinishedMatchesPersistenceService(ongoingMatchesService);
        this.generationMatchScoreService = new GenerationMatchScoreService(ongoingMatchesService);
    }

    public Optional<MatchScore> beginGame(Integer playerId, UUID matchId) {
        matchScoreCalculationService.ScoreCalculation(playerId, matchId);
        Optional<MatchScore> matchScore = getMatchScore(matchId);
        matchScore.filter(MatchScore::isMatchEnd)
                .ifPresent(ms -> {
                    finishedMatchesPersistenceService.save(matchId);
                    ongoingMatchesService.removeMatchScores(matchId);
                });
        return matchScore;
    }

    public UUID generationMatchService(String firstPlayerName, String secondPlayerName) {
       return generationMatchScoreService.createNewMatchScores(firstPlayerName, secondPlayerName)
                .orElseThrow(() -> new IllegalArgumentException("UUID is not found"));
    }

    public Optional<MatchScore> getMatchScore(UUID matchId) {
        return ongoingMatchesService.getMatchScores(matchId);
    }

    public boolean endGame(UUID matchId){
       return getMatchScore(matchId).isEmpty();
    }


}
