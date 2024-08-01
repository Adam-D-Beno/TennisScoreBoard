package entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "players", indexes = @Index(name = "name_index", columnList = "name", unique = true))
@Builder
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PRIVATE)
    private Integer id;
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Transient
    @Setter(AccessLevel.PRIVATE)
    private int points;

    @Transient
    @Setter(AccessLevel.PRIVATE)
    private int games;

    @Transient
    @Setter(AccessLevel.PRIVATE)
    private int sets;

    public void addPoint() {
        this.points += 15;
    }

    public void addGame() {
        this.games += 1;
    }

    public void addSet() {
        this.sets += 1;
    }
}
