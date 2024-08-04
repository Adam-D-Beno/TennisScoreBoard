package service;

import model.Match;
import model.MatchScore;
import model.Player;
import java.util.Objects;
import java.util.UUID;

import static util.PointNumberWon.*;

public class MatchScoreCalculationService {

    public void scoringGames(Integer playerId, UUID matchId) {

        //todo validation exist in bd player
        if (match.getFirstPlayer().getId().equals(playerId)) {
            increasePoints(match.getFirstPlayer());
            checkPoints(match.getFirstPlayer());
        }

        if (match.getSecondPlayer().getId().equals(playerId)) {
            checkPoints(match.getSecondPlayer());
        }
           finishGame(match);
    }

    private void checkPoints(Player player) {

        if (player.getPoints() > 40) {
            checkGames(player);
        }
    }

    private void checkGames(Player player) {
        increaseGames(player);

        if (player.getGames() >= 6) {
            checkSets(player);
        }
    }

    private void checkSets(Player player) {
        increaseSets(player);
    }

    private void finishGame(MatchScore match) {

        if (match.getFirstPlayer().getSets() - match.getSecondPlayer().getSets() >= 2) {
            match.setWinner(match.getFirstPlayer());
            match.setGameEnd(true);
        }
        if (match.getFirstPlayer().getSets() - match.getSecondPlayer().getSets() <= 2) {
             match.setWinner(match.getSecondPlayer());
             match.setGameEnd(true);
        }
    }

    private void increasePoints(Player player) {
        if (Objects.isNull(player.getPointNumberWon())) {
            player.setPointNumberWon(FIRST_POINT);
            player.setPoints(FIRST_POINT.getNumberWon());
        }

        player.setPointNumberWon(player.getPointNumberWon());

        if (player.getPointNumberWon().equals(SECOND_POINT)) {
            player.setPointNumberWon(SECOND_POINT);
            player.setPoints(SECOND_POINT.getNumberWon());
        }
        if (player.getPointNumberWon().equals(THIRD_POINT)) {
            player.setPointNumberWon(THIRD_POINT);
            player.setPoints(THIRD_POINT.getNumberWon());
        }
    }

    private void increaseGames(Player player) {
        player.setGames(1);
    }

    private void increaseSets(Player player) {
        player.setSets(1);
    }

    private void tieBreak() {

    }
}
