package service;

import entity.Match;
import entity.Player;

public class MatchScoreCalculationService {

    public void scoringGames(Integer playerId, Match match) {

        //todo validation exist in bd player
        if (match.getFirstPlayer().getId().equals(playerId)) {
            checkIncreaseAddPoints(match.getFirstPlayer());
        }

        if (match.getSecondPlayer().getId().equals(playerId)) {
            checkIncreaseAddPoints(match.getSecondPlayer());
        }
           finishGame(match);
    }

    private void checkIncreaseAddPoints(Player player) {
        player.addPoint();

        if (player.getPoints() > 40) {
            checkIncreaseAddGames(player);
        }
    }

    private void checkIncreaseAddGames(Player player) {
        player.addGame();

        if (player.getGames() >= 6) {
            checkIncreaseAddSets(player);
        }
    }

    private void checkIncreaseAddSets(Player player) {
        player.addSet();
    }

    private void finishGame(Match match) {

        if (match.getFirstPlayer().getSets() - match.getSecondPlayer().getSets() >= 2) {
            match.setWinner(match.getFirstPlayer());
            match.setGameEnd(true);
        }
        if (match.getFirstPlayer().getSets() - match.getSecondPlayer().getSets() <= 2) {
             match.setWinner(match.getSecondPlayer());
             match.setGameEnd(true);
        }

    }
}
