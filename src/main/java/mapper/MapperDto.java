package mapper;

import dto.PageDto;
import model.Match;

public class MapperDto {
    public PageDto toDto(Match match) {
        return new PageDto(
                match.getId(),
                match.getFirstPlayer().getName(),
                match.getSecondPlayer().getName(),
                match.getWinner().getName()
        );
    }
}
