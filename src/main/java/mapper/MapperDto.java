package mapper;

import dto.MatchDto;
import model.Match;

public class MapperDto {
    public MatchDto toDto(Match match) {
        return new MatchDto(
                match.getId(),
                match.getFirstPlayer().getName(),
                match.getSecondPlayer().getName(),
                match.getWinner().getName()
        );
    }
}
