package model;

import entity.Match;
import lombok.*;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
public class MatchScoreModel {
   static private Map<UUID, Match> matches = new HashMap<>();

    public static Optional<Match> getNewMatch(UUID uuid) {
        return Optional.ofNullable(matches.get(uuid));
    }

    public static void setNewMatch(UUID uuid, Match match) {
        matches.put(uuid, match);
    }


}
