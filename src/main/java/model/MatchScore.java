package model;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@Setter
@Getter
@Builder
@RequiredArgsConstructor
public class MatchScore {

    private UUID uuid;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private Player winner;
    private int firstPlayerPoints;
    private int secondPlayerPoints;
    private int firstPlayerGames;
    private int secondPlayerGames;
    private int firstPlayerSets;
    private int secondPlayerSets;
    private int adFirstPlayer;
    private int adSecondPlayer;
    private int tieBreakFirstPlayer;
    private int tieBreakSecondPlayer;
    private boolean isMatchEnd;
    private boolean isTieBreak;
    private boolean isDeuce;


    public void clearPoints() {
        firstPlayerPoints = 0;
        secondPlayerPoints = 0;
    }

    public void clearGames() {
        firstPlayerGames = 0;
        secondPlayerGames = 0;
    }

    public void clearAd() {
        adFirstPlayer = 0;
        adSecondPlayer = 0;
    }

    public void clearTieBreak() {
        tieBreakFirstPlayer = 0;
        tieBreakSecondPlayer = 0;
    }

}
