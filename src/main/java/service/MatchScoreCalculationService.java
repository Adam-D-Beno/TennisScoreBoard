package service;

import model.MatchScore;
import model.Player;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

public class MatchScoreCalculationService {
    private final OngoingMatchesService ongoingMatchesService;

    public MatchScoreCalculationService() {
        ongoingMatchesService = new OngoingMatchesService();
    }


    public void ScoreCalculation(int playerId, UUID matchId) {

        MatchScore matchScore = ongoingMatchesService.getMatchScores(matchId)
                .orElseThrow(() -> new EntityNotFoundException("Object match is not found in scoreModel"));
        //todo validation exist in bd player
        //todo validation exist in MatchScore player

        scorePoints(matchScore, playerId);
        scoreGames(matchScore, playerId);
        scoreSets(matchScore, playerId);

    }

    private void scorePoints(MatchScore matchScore, int winPlayerId) {
        winPoint(matchScore, winPlayerId);
        scoreDeuce(matchScore);
    }

    private void scoreGames(MatchScore matchScore, int winPlayerId) {
        if (matchScore.isDeuce()) {
            increaseAd(matchScore, winPlayerId);
            getPlayerIdWinDeuce(matchScore);
        } else {
            winGame(matchScore);
        }

    }

    private void scoreSets(MatchScore matchScore, int winPlayerId) {
        if (matchScore.isTieBreak()) {
            increaseTieBreak(matchScore, winPlayerId);
            getPlayerIdWinTieBreak(matchScore);
        } else {
//            winSets(matchScore);
            scoreTieBreak(matchScore);
        }
    }

    private void winPoint(MatchScore matchScore, int winPlayerId) {
        increasePoints(matchScore, winPlayerId);
    }

    private void winGame(MatchScore matchScore) {
        increaseGames(matchScore, getPlayerIdWinGameFourPointsScoredRowInGame(matchScore));
        increaseGames(matchScore, getPlayerIdWinGameDecisivePoint(matchScore));
        increaseGames(matchScore, getPlayerIdWinDeuce(matchScore));
    }

    private void winSets(MatchScore matchScore, int winPlayerId) {
        increaseSets(matchScore, winPlayerId);
        if (matchScore.getFirstPlayerSets() == 2) {
            finishGame(matchScore);
            return;
        }
        if (matchScore.getSecondPlayerSets() == 2) {
            matchScore.setMathEnd(true);
            finishGame(matchScore);
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

    private void finishGame(MatchScore matchScore) {
        matchScore.setMathEnd(true);
    }

    private void scoreDeuce(MatchScore matchScore) {
        if (matchScore.getFirstPlayerPoints() == 40 && matchScore.getSecondPlayerPoints() == 40) {
            matchScore.setDeuce(true);
        }
    }

    private void scoreTieBreak(MatchScore matchScore) {
        if (matchScore.getFirstPlayerGames() == 6 && matchScore.getSecondPlayerGames() == 6) {
            matchScore.setTieBreak(true);
        }
    }

    private int getPlayerIdWinGameFourPointsScoredRowInGame(MatchScore matchScore) {
        if (matchScore.getFirstPlayerPoints() == 40 && matchScore.getSecondPlayerPoints() == 0) {
            matchScore.setFirstPlayerPoints(0);
            return matchScore.getFirstPlayer().getId();
        }
        if (matchScore.getSecondPlayerPoints() == 40 && matchScore.getFirstPlayerPoints() == 0) {
            matchScore.setSecondPlayerPoints(0);
            return matchScore.getSecondPlayer().getId();
        }
        return -1;
    }

    private int getPlayerIdWinGameDecisivePoint(MatchScore matchScore) {
        if (matchScore.getFirstPlayerPoints() == 41) {
            matchScore.setFirstPlayerPoints(0);
            return matchScore.getFirstPlayer().getId();
        }
        if (matchScore.getSecondPlayerPoints() == 41) {
            matchScore.setSecondPlayerPoints(0);
            return matchScore.getSecondPlayer().getId();
        }
        return -1;
    }

    private int getPlayerIdWinDeuce(MatchScore matchScore) {
        if (matchScore.getAdFirstPlayer() - matchScore.getAdSecondPlayer() >= 2) {
            matchScore.setAdFirstPlayer(0);
            matchScore.setDeuce(false);
            return matchScore.getFirstPlayer().getId();
        }
        if (matchScore.getAdSecondPlayer() - matchScore.getAdFirstPlayer() <= -2) {
            matchScore.setAdSecondPlayer(0);
            matchScore.setDeuce(false);
            return matchScore.getSecondPlayer().getId();
        }
        return -1;
    }

    private int getPlayerIdWinTieBreak(MatchScore matchScore) {
        if (matchScore.getTieBreakFirstPlayer() >= 7 || matchScore.getTieBreakSecondPlayer() >= 7) {
            if (matchScore.getTieBreakFirstPlayer() - matchScore.getTieBreakSecondPlayer() >= 2) {
                matchScore.setTieBreakFirstPlayer(0);
                matchScore.setTieBreak(false);
                return matchScore.getFirstPlayer().getId();
            }
            if (matchScore.getTieBreakSecondPlayer() - matchScore.getTieBreakFirstPlayer() <= -2) {
                matchScore.setTieBreakSecondPlayer(0);
                matchScore.setTieBreak(false);
                return matchScore.getSecondPlayer().getId();
            }
        }
        return -1;
    }
}
