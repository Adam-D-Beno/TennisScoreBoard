package model;

import entity.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MatchScoreModel {
    private Player firstPlayer;
    private Player secondPlayer;
    private int points;
    private int games;
    private int sets;

}
