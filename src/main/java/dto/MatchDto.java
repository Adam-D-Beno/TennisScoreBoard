package dto;

public record MatchDto(
        int id,
        String firstPlayer,
        String secondPlayer,
        String winner) {}
