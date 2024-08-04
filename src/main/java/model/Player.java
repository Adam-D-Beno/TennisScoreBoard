package model;

import lombok.*;
import util.PointNumberWon;

import javax.persistence.*;

@Getter
@Setter
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

}
