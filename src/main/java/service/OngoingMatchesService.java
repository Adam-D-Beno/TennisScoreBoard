package service;

import model.MatchScore;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private final Map<UUID, MatchScore> matchScores;

    public OngoingMatchesService() {
        this.matchScores = new ConcurrentHashMap<>();
    }

    public void ongoingGame() {
    }

    public Optional<MatchScore> getMatchScores(UUID uuid) {
        return Optional.ofNullable(matchScores.get(uuid));
    }

    public Optional<UUID> setMatchScores(MatchScore match) {
        return Optional.ofNullable(matchScores.put(match.getUuid(), match).getUuid());
    }

    public void removeMatchScores(UUID matchId) {
       getMatchScores(matchId).ifPresent(matchScore ->  matchScores.remove(matchScore.getUuid()));
    }
}
