package service;

import model.MatchScore;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class MatchScoreCalculationService {
    private final OngoingMatchesService ongoingMatchesService;

    public MatchScoreCalculationService(OngoingMatchesService ongoingMatchesService) {
        this.ongoingMatchesService = ongoingMatchesService;
    }


    public void ScoreCalculation(int winPlayerId, UUID matchId) {
        MatchScore matchScore = ongoingMatchesService.getMatchScores(matchId)
                .orElseThrow(() -> new EntityNotFoundException("Object match is not found in scoreModel"));
        //todo validation exist in bd player
        //todo validation exist in MatchScore player

        scorePoint(matchScore, winPlayerId);
        scoreGames(matchScore);
        scoreSets(matchScore, winPlayerId);
        finishGame(matchScore);
    }

    private void scorePoint(MatchScore matchScore, int winPlayerId) {
        if (scoreDeuce(matchScore)) {
            increaseAd(matchScore, winPlayerId);
        } else {
            increasePoints(matchScore, winPlayerId);
        }
    }

    private void scoreGames(MatchScore matchScore) {
        increaseGames(matchScore, getPlayerIdWinGameByScoringFourPointsInRowInGame(matchScore));
        increaseGames(matchScore, getPlayerIdWinGameDecisivePoint(matchScore));
        increaseGames(matchScore, getPlayerIdWinDeuce(matchScore));
    }

    private void scoreSets(MatchScore matchScore, int winPlayerId) {
        if (scoreTieBreak(matchScore)) {
            increaseTieBreak(matchScore, winPlayerId);
            increaseSets(matchScore, getPlayerIdWinTieBreak(matchScore));
        } else {
            increaseSets(matchScore, getPlayerIdWinGameByFourGemaInSet(matchScore));
            increaseSets(matchScore, winningSetWithDifferenceOfTwoGames(matchScore));
        }
    }

    private void finishGame(MatchScore matchScore) {
        if (matchScore.getFirstPlayerSets() == 2) {
            matchScore.setWinner(matchScore.getFirstPlayer());
            matchScore.setMatchEnd(true);
            return;
        }
        if (matchScore.getSecondPlayerSets() == 2) {
            matchScore.setWinner(matchScore.getSecondPlayer());
            matchScore.setMatchEnd(true);
            return;
        }
    }

    private void increasePoints(MatchScore matchScore, int winPlayerId) {

        if (matchScore.getFirstPlayer().getId() == winPlayerId) {
            int point = matchScore.getFirstPlayerPoints();
            if (point < 30) {
                matchScore.setFirstPlayerPoints(point + 15);
                return;
            }
            if (point == 30) {
                matchScore.setFirstPlayerPoints(point + 10);
                return;
            }
            if (point == 40) {
                matchScore.setFirstPlayerPoints(point + 1);
                return;
            }
        }
        if (matchScore.getSecondPlayer().getId() == winPlayerId) {
            int point = matchScore.getSecondPlayerPoints();
            if (point < 30) {
                matchScore.setSecondPlayerPoints(point + 15);
                return;
            }
            if (point == 30) {
                matchScore.setSecondPlayerPoints(point + 10);
                return;
            }
            if (point == 40) {
                matchScore.setSecondPlayerPoints(point + 1);
                return;
            }
        }
    }

    private void increaseGames(MatchScore matchScore, int winPlayerId) {
        int games;
        if (matchScore.getFirstPlayer().getId() == winPlayerId) {
            games = matchScore.getFirstPlayerGames();
            matchScore.setFirstPlayerGames(games + 1);
            return;
        }
        if (matchScore.getSecondPlayer().getId() == winPlayerId) {
            games = matchScore.getSecondPlayerGames();
            matchScore.setSecondPlayerGames(games + 1);
            return;
        }
    }

    private void increaseSets(MatchScore matchScore, int winPlayerId) {

        int sets;
        if (matchScore.getFirstPlayer().getId() == winPlayerId) {
            sets = matchScore.getFirstPlayerSets();
            matchScore.setFirstPlayerSets(sets + 1);
            return;
        }
        if (matchScore.getSecondPlayer().getId() == winPlayerId) {
            sets = matchScore.getSecondPlayerSets();
            matchScore.setSecondPlayerSets(sets + 1);
            return;
        }
    }

    private void increaseAd(MatchScore matchScore, int winPlayerId) {
        if (matchScore.getFirstPlayer().getId() == winPlayerId) {
            matchScore.setAdSecondPlayer(matchScore.getAdFirstPlayer() + 1);
        }
        if (matchScore.getSecondPlayer().getId() == winPlayerId) {
            matchScore.setAdSecondPlayer(matchScore.getAdFirstPlayer() + 1);
        }
    }

    private void increaseTieBreak(MatchScore matchScore, int winPlayerId) {
        if (matchScore.getFirstPlayer().getId() == winPlayerId) {
            matchScore.setTieBreakFirstPlayer(matchScore.getTieBreakFirstPlayer() + 1);
        }
        if (matchScore.getSecondPlayer().getId() == winPlayerId) {
            matchScore.setTieBreakSecondPlayer(matchScore.getTieBreakSecondPlayer() + 1);
        }
    }

    private boolean scoreDeuce(MatchScore matchScore) {
        if (matchScore.getFirstPlayerPoints() == 40 && matchScore.getSecondPlayerPoints() == 40) {
            matchScore.setDeuce(true);
            return true;
        }
        return false;
    }

    private boolean  scoreTieBreak(MatchScore matchScore) {
        if (matchScore.getFirstPlayerGames() == 6 && matchScore.getSecondPlayerGames() == 6) {
            matchScore.setTieBreak(true);
            return true;
        }
        return false;
    }

    private int getPlayerIdWinGameByScoringFourPointsInRowInGame(MatchScore matchScore) {
        if (matchScore.getFirstPlayerPoints() == 40 && matchScore.getSecondPlayerPoints() == 0) {
            matchScore.clearPoints();
            return matchScore.getFirstPlayer().getId();
        }
        if (matchScore.getSecondPlayerPoints() == 40 && matchScore.getFirstPlayerPoints() == 0) {
            matchScore.clearPoints();
            return matchScore.getSecondPlayer().getId();
        }
        return -1;
    }

    private int getPlayerIdWinGameDecisivePoint(MatchScore matchScore) {
        if (matchScore.getFirstPlayerPoints() == 41) {
            matchScore.clearPoints();
            return matchScore.getFirstPlayer().getId();
        }
        if (matchScore.getSecondPlayerPoints() == 41) {
            matchScore.clearPoints();
            return matchScore.getSecondPlayer().getId();
        }
        return -1;
    }

    private int getPlayerIdWinDeuce(MatchScore matchScore) {
        if (matchScore.getAdFirstPlayer() - matchScore.getAdSecondPlayer() >= 2) {
            matchScore.clearAd();
            matchScore.setDeuce(false);
            return matchScore.getFirstPlayer().getId();
        }
        if (matchScore.getAdSecondPlayer() - matchScore.getAdFirstPlayer() <= -2) {
            matchScore.clearAd();
            matchScore.setDeuce(false);
            return matchScore.getSecondPlayer().getId();
        }
        return -1;
    }

    private int getPlayerIdWinTieBreak(MatchScore matchScore) {
        if (matchScore.getTieBreakFirstPlayer() >= 7 || matchScore.getTieBreakSecondPlayer() >= 7) {
            if (matchScore.getTieBreakFirstPlayer() - matchScore.getTieBreakSecondPlayer() >= 2) {
                matchScore.clearTieBreak();
                matchScore.setTieBreak(false);
                return matchScore.getFirstPlayer().getId();
            }
            if (matchScore.getTieBreakSecondPlayer() - matchScore.getTieBreakFirstPlayer() <= -2) {
                matchScore.clearTieBreak();
                matchScore.setTieBreak(false);
                return matchScore.getSecondPlayer().getId();
            }
        }
        return -1;
    }

    private int getPlayerIdWinGameByFourGemaInSet(MatchScore matchScore) {
        if (matchScore.getFirstPlayerGames() == 6 && matchScore.getSecondPlayerGames() == 0) {
            matchScore.clearGames();
            return matchScore.getFirstPlayer().getId();
        }
        if (matchScore.getSecondPlayerGames() == 6 && matchScore.getFirstPlayerGames() == 0) {
            matchScore.clearGames();
            return matchScore.getSecondPlayer().getId();
        }
        return -1;
    }

    public int winningSetWithDifferenceOfTwoGames(MatchScore matchScore) {
        if (matchScore.getFirstPlayerGames() >= 6 || matchScore.getSecondPlayerGames() >= 6) {
            if (matchScore.getFirstPlayerGames() - matchScore.getSecondPlayerGames() >= 2) {
                matchScore.clearGames();
                return matchScore.getFirstPlayer().getId();
            }
            if (matchScore.getSecondPlayerGames() - matchScore.getFirstPlayerGames() <= -2) {
                matchScore.clearGames();
                return matchScore.getSecondPlayer().getId();
            }
        }
        return -1;
    }
}
