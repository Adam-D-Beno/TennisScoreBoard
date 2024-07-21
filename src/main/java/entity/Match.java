package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "matches")
@AllArgsConstructor
@Builder
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "firstPlayerId")
    private Player firstPlayer;

    @ManyToOne
    @JoinColumn(name = "secondPlayerId")
    private Player secondPlayer;

    @ManyToOne
    @JoinColumn(name = "playerWinId")
    private Player winner;

    @Transient
    private int points;

    @Transient
    private int games;

    @Transient
    private int sets;

}
