package util;

import lombok.Getter;
@Getter
public enum PointNumberWon {
    FIRST_POINT(15),
    SECOND_POINT(30),
    THIRD_POINT(40),
    FOURTH_POINT(0);

    private final int numberWon;

    PointNumberWon(int numberWon) {
        this.numberWon = numberWon;
    }

    public PointNumberWon getNextDay() {
        int currentIndex = this.ordinal();
        int nextIndex = (currentIndex + 1) % PointNumberWon.values().length;
        return PointNumberWon.values()[nextIndex];
    }
}
