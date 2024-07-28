package model;

import entity.Match;
import lombok.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchScoreModel {

    private static Map<UUID, Match> matches;

    private static MatchScoreModel INSTANCE;

    public static MatchScoreModel getInstance() {
        if (INSTANCE == null) {
            synchronized (MatchScoreModel.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MatchScoreModel();
                    matches =new HashMap<>();
                }
            }
        }
        return INSTANCE;
    }




    public Optional<Match> getMatch(UUID uuid) {
        return Optional.ofNullable(matches.get(uuid));
    }

    public void setMatch(UUID uuid, Match match) {
        matches.put(uuid, match);
    }

}
