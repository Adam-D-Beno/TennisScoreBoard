package model;

import entity.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class MatchScoreModel {
    private Player player;
    private int points;
    private int games;
    private int sets;
}
