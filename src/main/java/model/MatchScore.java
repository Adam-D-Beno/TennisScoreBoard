package model;

import lombok.*;
import util.PointNumberWon;
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
    private boolean isMathEnd;
    private boolean isTieBreak;
    private boolean isDeuce;

}
