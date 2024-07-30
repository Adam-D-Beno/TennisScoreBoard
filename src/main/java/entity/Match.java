package entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @Transient
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "firstPlayerId")
    private Player firstPlayer;

    @ManyToOne
    @JoinColumn(name = "secondPlayerId")
    private Player secondPlayer;

    @ManyToOne
    @JoinColumn(name = "playerWinId")
    @Setter
    private Player winner;

    @Setter
    @Getter
    @Transient
    private boolean gameEnd;

}
