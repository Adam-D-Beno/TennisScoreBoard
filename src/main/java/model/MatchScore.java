package model;

import lombok.*;
import util.PointNumberWon;
import java.util.UUID;

@NoArgsConstructor()
@AllArgsConstructor
@Setter
@Getter
@Builder
public class MatchScore {

    private UUID uuid;
    private Player firstPlayer;
    private Player secondPlayer;
    private Player winner;
    private boolean gameEnd;
    private int points;
    private int games;
    private int sets;
    private PointNumberWon pointNumberWon;

}
