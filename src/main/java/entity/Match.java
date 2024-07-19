package entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "playerOneId")
    private Player player1;
    @OneToOne
    @JoinColumn(name = "playerTwoId")
    private Player player2;
    @OneToOne
    @JoinColumn(name = "playerWinId")
    private Player winner;

}
