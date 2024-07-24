package model;

import entity.Match;
import lombok.*;
import lombok.experimental.UtilityClass;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchScoreModel {

    private final Map<UUID, Match> matches = new HashMap<>();

    @Getter
    private static final MatchScoreModel INSTANCE  = new MatchScoreModel();

    public Optional<Match> getNewMatch(UUID uuid) {
        return Optional.ofNullable(matches.get(uuid));
    }

    public void setNewMatch(UUID uuid, Match match) {
        matches.put(uuid, match);
    }

}
